package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.*;
import com.telangana.digitallearning.entity.*;
import com.telangana.digitallearning.exception.ResourceNotFoundException;
import com.telangana.digitallearning.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository optionRepository;
    private final QuizAttemptRepository attemptRepository;
    private final StudentAnswerRepository answerRepository;
    private final TopicRepository topicRepository;
    private final WeakTopicDetectionService weakTopicDetectionService;
    private final RecommendationEngineService recommendationEngineService;
    private final EvaluationService evaluationService;
    private final RecommendationRepository recommendationRepository;

    public List<Quiz> getQuizzesByTopic(Long topicId) {
        return quizRepository.findByTopicId(topicId);
    }

    public List<Quiz> getQuizzesByClass(Integer classLevel) {
        return quizRepository.findByClassLevel(classLevel);
    }

    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found: " + quizId));
    }

    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    @Transactional
    public QuizResultDto submitQuiz(Long studentId, QuizSubmissionDto submission) {
        Quiz quiz = getQuizById(submission.getQuizId());
        List<Question> questions = questionRepository.findByQuizId(quiz.getId());

        double totalMarksPossible = questions.stream().mapToDouble(q -> q.getMarks() != null ? q.getMarks() : 1.0).sum();
        if (totalMarksPossible == 0) totalMarksPossible = 10.0;

        double earnedMarks = 0.0;
        List<QuestionFeedbackDto> feedbacks = new ArrayList<>();

        for (Question question : questions) {
            Long questionId = question.getId();
            AnswerSubmissionDto subAnswer = submission.getAnswers() != null ?
                    submission.getAnswers().stream()
                            .filter(a -> Objects.equals(a.getQuestionId(), questionId))
                            .findFirst()
                            .orElse(null) : null;

            Long selectedOptId = subAnswer != null ? subAnswer.getSelectedOptionId() : null;

            List<QuestionOption> options = optionRepository.findByQuestionId(questionId);
            QuestionOption correctOption = options.stream()
                    .filter(QuestionOption::getIsCorrect)
                    .findFirst()
                    .orElse(null);

            boolean isCorrect = false;
            double qMarks = question.getMarks() != null ? question.getMarks() : 1.0;
            double marksEarned = 0.0;

            if (selectedOptId != null && correctOption != null && Objects.equals(selectedOptId, correctOption.getId())) {
                isCorrect = true;
                marksEarned = qMarks;
                earnedMarks += marksEarned;
            }

            feedbacks.add(QuestionFeedbackDto.builder()
                    .questionId(questionId)
                    .questionTextEn(question.getQuestionTextEn())
                    .questionTextTe(question.getQuestionTextTe())
                    .selectedOptionId(selectedOptId)
                    .correctOptionId(correctOption != null ? correctOption.getId() : null)
                    .isCorrect(isCorrect)
                    .explanationEn(question.getExplanationEn())
                    .explanationTe(question.getExplanationTe())
                    .build());
        }

        double percentage = Math.round(((earnedMarks / totalMarksPossible) * 100.0) * 10.0) / 10.0;

        String attemptType = submission.getAttemptType() != null ? submission.getAttemptType() : "INITIAL";
        if (Boolean.TRUE.equals(quiz.getIsEvaluationTest()) && "PRE_TEST".equalsIgnoreCase(quiz.getTestCategory())) {
            attemptType = "PRE_TEST";
        }

        // 1. Save Quiz Attempt
        QuizAttempt attempt = QuizAttempt.builder()
                .studentId(studentId)
                .quizId(quiz.getId())
                .topicId(quiz.getTopicId())
                .score(earnedMarks)
                .totalMarks(totalMarksPossible)
                .percentage(percentage)
                .attemptType(attemptType)
                .build();
        QuizAttempt savedAttempt = attemptRepository.save(attempt);

        // 2. Save individual answers
        for (QuestionFeedbackDto fb : feedbacks) {
            StudentAnswer sa = StudentAnswer.builder()
                    .attemptId(savedAttempt.getId())
                    .questionId(fb.getQuestionId())
                    .selectedOptionId(fb.getSelectedOptionId())
                    .isCorrect(fb.getIsCorrect())
                    .marksEarned(fb.getIsCorrect() ? 1.0 : 0.0)
                    .build();
            answerRepository.save(sa);
        }

        // 3. Trigger Core Research Engine: Weak Topic Detection
        TopicPerformance topicPerf = weakTopicDetectionService.recordOrUpdatePerformance(studentId, quiz.getTopicId(), percentage);

        // 4. Trigger Core Research Engine: Recommendation Engine if weak or needs improvement
        Recommendation rec = null;
        RecommendationDto recDto = null;
        if ("WEAK".equals(topicPerf.getStatus()) || "NEEDS_IMPROVEMENT".equals(topicPerf.getStatus())) {
            rec = recommendationEngineService.generateRecommendation(studentId, quiz.getTopicId(), percentage);
            recDto = recommendationEngineService.convertToDto(rec);
        }

        // 5. Research Evaluation Pre-Test / Post-Test Tracker
        if ("PRE_TEST".equalsIgnoreCase(attemptType)) {
            evaluationService.recordOrUpdateEvaluation(studentId, quiz.getTopicId(), percentage, null);
        } else if ("RETEST".equalsIgnoreCase(attemptType) || "POST_TEST".equalsIgnoreCase(attemptType)) {
            // Retrieve pre-test score or earliest score
            List<QuizAttempt> prevAttempts = attemptRepository.findByStudentIdAndTopicId(studentId, quiz.getTopicId());
            Double preTestScore = prevAttempts.stream()
                    .filter(a -> !a.getId().equals(savedAttempt.getId()))
                    .map(QuizAttempt::getPercentage)
                    .findFirst()
                    .orElse(percentage);

            evaluationService.recordOrUpdateEvaluation(studentId, quiz.getTopicId(), preTestScore, percentage);

            // Update pending recommendations as RETESTED
            List<Recommendation> recs = recommendationRepository.findByStudentIdAndTopicId(studentId, quiz.getTopicId());
            for (Recommendation r : recs) {
                if ("PENDING".equals(r.getStatus())) {
                    r.setStatus("RETESTED");
                    r.setRetestScore(percentage);
                    recommendationRepository.save(r);
                }
            }
        }

        Topic topic = topicRepository.findById(quiz.getTopicId()).orElse(null);

        return QuizResultDto.builder()
                .attemptId(savedAttempt.getId())
                .quizId(quiz.getId())
                .quizTitle(quiz.getTitleEn())
                .topicId(quiz.getTopicId())
                .topicNameEn(topic != null ? topic.getNameEn() : "Topic")
                .topicNameTe(topic != null ? topic.getNameTe() : "అంశం")
                .score(earnedMarks)
                .totalMarks(totalMarksPossible)
                .percentage(percentage)
                .status(topicPerf.getStatus())
                .weakTopicDetected("WEAK".equals(topicPerf.getStatus()) || "NEEDS_IMPROVEMENT".equals(topicPerf.getStatus()))
                .recommendation(recDto)
                .questionFeedback(feedbacks)
                .build();
    }
}
