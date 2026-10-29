package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.*;
import com.telangana.digitallearning.entity.*;
import com.telangana.digitallearning.exception.ResourceNotFoundException;
import com.telangana.digitallearning.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final QuizAttemptRepository attemptRepository;
    private final QuizRepository quizRepository;
    private final TopicRepository topicRepository;
    private final WeakTopicDetectionService weakTopicDetectionService;
    private final RecommendationEngineService recommendationEngineService;
    private final EvaluationService evaluationService;

    public StudentDashboardDto getDashboardData(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + studentId));

        int classLevel = student.getClassLevel() != null ? student.getClassLevel() : 10;
        List<Subject> subjects = subjectRepository.findByClassId((long) (classLevel - 5)); // mapping Class 6->1, Class 10->5

        List<QuizAttempt> attempts = attemptRepository.findByStudentId(studentId);
        double avgScore = attempts.isEmpty() ? 0.0 :
                attempts.stream().mapToDouble(QuizAttempt::getPercentage).average().orElse(0.0);
        avgScore = Math.round(avgScore * 10.0) / 10.0;

        List<WeakTopicDto> weakTopics = weakTopicDetectionService.getWeakTopicsForStudent(studentId);
        List<RecommendationDto> activeRecs = recommendationEngineService.getRecommendationsForStudent(studentId);
        List<EvaluationSummaryDto> evals = evaluationService.getStudentEvaluationRecords(studentId);

        double totalImprovement = evals.isEmpty() ? 0.0 :
                evals.stream().mapToDouble(e -> e.getImprovementPercentage() != null ? e.getImprovementPercentage() : 0.0).sum();
        totalImprovement = Math.round(totalImprovement * 10.0) / 10.0;

        List<QuizResultSummaryDto> recentAttempts = attempts.stream()
                .sorted((a, b) -> b.getAttemptedAt().compareTo(a.getAttemptedAt()))
                .limit(5)
                .map(a -> {
                    Quiz quiz = quizRepository.findById(a.getQuizId()).orElse(null);
                    Topic topic = topicRepository.findById(a.getTopicId()).orElse(null);
                    return QuizResultSummaryDto.builder()
                            .attemptId(a.getId())
                            .quizTitleEn(quiz != null ? quiz.getTitleEn() : "Quiz #" + a.getQuizId())
                            .topicNameEn(topic != null ? topic.getNameEn() : "Topic #" + a.getTopicId())
                            .score(a.getScore())
                            .totalMarks(a.getTotalMarks())
                            .percentage(a.getPercentage())
                            .attemptType(a.getAttemptType())
                            .attemptedAt(a.getAttemptedAt())
                            .build();
                })
                .collect(Collectors.toList());

        return StudentDashboardDto.builder()
                .studentId(student.getId())
                .studentName(student.getName())
                .classLevel(classLevel)
                .totalSubjects(subjects.size() > 0 ? subjects.size() : 5)
                .completedLessons(attempts.size())
                .averageQuizScore(avgScore)
                .overallProgressPercentage(Math.min(100.0, attempts.size() * 15.0))
                .overallImprovementPercentage(totalImprovement)
                .weakTopics(weakTopics)
                .activeRecommendations(activeRecs)
                .recentQuizAttempts(recentAttempts)
                .build();
    }
}
