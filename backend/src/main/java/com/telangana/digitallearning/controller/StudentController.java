package com.telangana.digitallearning.controller;

import com.telangana.digitallearning.dto.*;
import com.telangana.digitallearning.entity.Question;
import com.telangana.digitallearning.entity.Quiz;
import com.telangana.digitallearning.security.UserPrincipal;
import com.telangana.digitallearning.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;
    private final QuizService quizService;
    private final WeakTopicDetectionService weakTopicDetectionService;
    private final RecommendationEngineService recommendationEngineService;
    private final EvaluationService evaluationService;

    @GetMapping("/dashboard")
    public ResponseEntity<StudentDashboardDto> getDashboard(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(studentService.getDashboardData(currentUser.getId()));
    }

    @GetMapping("/weak-topics")
    public ResponseEntity<List<WeakTopicDto>> getWeakTopics(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(weakTopicDetectionService.getWeakTopicsForStudent(currentUser.getId()));
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<RecommendationDto>> getRecommendations(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(recommendationEngineService.getRecommendationsForStudent(currentUser.getId()));
    }

    @GetMapping("/evaluation")
    public ResponseEntity<List<EvaluationSummaryDto>> getEvaluations(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(evaluationService.getStudentEvaluationRecords(currentUser.getId()));
    }

    @GetMapping("/topics/{topicId}/quizzes")
    public ResponseEntity<List<Quiz>> getQuizzesByTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(quizService.getQuizzesByTopic(topicId));
    }

    @GetMapping("/quizzes/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }

    @GetMapping("/quizzes/{quizId}/questions")
    public ResponseEntity<List<Question>> getQuestionsForQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuestionsByQuizId(quizId));
    }

    @PostMapping("/quizzes/submit")
    public ResponseEntity<QuizResultDto> submitQuiz(@AuthenticationPrincipal UserPrincipal currentUser,
                                                     @RequestBody QuizSubmissionDto submission) {
        return ResponseEntity.ok(quizService.submitQuiz(currentUser.getId(), submission));
    }
}
