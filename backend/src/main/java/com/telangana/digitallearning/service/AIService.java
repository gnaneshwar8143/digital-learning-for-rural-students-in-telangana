package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.*;

public interface AIService {
    AiAskResponse answerStudentQuestion(AiAskRequest request);
    AiExplainAnswerResponse explainWrongAnswer(AiExplainAnswerRequest request);
    AiRecommendResponse generatePersonalizedRecommendation(AiRecommendRequest request);
    AiGenerateQuizResponse generateQuizQuestions(AiGenerateQuizRequest request);
    AiStudyPlanResponse generateStudyPlan(AiStudyPlanRequest request);
}
