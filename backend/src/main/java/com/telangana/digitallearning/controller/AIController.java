package com.telangana.digitallearning.controller;

import com.telangana.digitallearning.dto.*;
import com.telangana.digitallearning.security.UserPrincipal;
import com.telangana.digitallearning.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AIController {

    private final AIService aiService;

    @PostMapping("/ask")
    public ResponseEntity<AiAskResponse> askStudentQuestion(@RequestBody AiAskRequest request) {
        return ResponseEntity.ok(aiService.answerStudentQuestion(request));
    }

    @PostMapping("/explain-answer")
    public ResponseEntity<AiExplainAnswerResponse> explainWrongAnswer(@RequestBody AiExplainAnswerRequest request) {
        return ResponseEntity.ok(aiService.explainWrongAnswer(request));
    }

    @PostMapping("/recommend")
    public ResponseEntity<AiRecommendResponse> generatePersonalizedRecommendation(@RequestBody AiRecommendRequest request) {
        return ResponseEntity.ok(aiService.generatePersonalizedRecommendation(request));
    }

    @PostMapping("/generate-quiz")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<AiGenerateQuizResponse> generateQuizQuestions(@RequestBody AiGenerateQuizRequest request) {
        return ResponseEntity.ok(aiService.generateQuizQuestions(request));
    }

    @PostMapping("/study-plan")
    public ResponseEntity<AiStudyPlanResponse> generateStudyPlan(@AuthenticationPrincipal UserPrincipal currentUser,
                                                                 @RequestBody(required = false) AiStudyPlanRequest request) {
        if (request == null) {
            request = new AiStudyPlanRequest();
        }
        if (currentUser != null) {
            request.setStudentId(currentUser.getId());
        }
        return ResponseEntity.ok(aiService.generateStudyPlan(request));
    }
}
