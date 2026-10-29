package com.telangana.digitallearning.dto;

import java.util.List;

public class QuizResultDto {
    private Long attemptId;
    private Long quizId;
    private String quizTitle;
    private Long topicId;
    private String topicNameEn;
    private String topicNameTe;
    private Double score;
    private Double totalMarks;
    private Double percentage;
    private String status;
    private Boolean weakTopicDetected;
    private RecommendationDto recommendation;
    private List<QuestionFeedbackDto> questionFeedback;

    public QuizResultDto() {}

    public QuizResultDto(Long attemptId, Long quizId, String quizTitle, Long topicId, String topicNameEn, String topicNameTe, Double score, Double totalMarks, Double percentage, String status, Boolean weakTopicDetected, RecommendationDto recommendation, List<QuestionFeedbackDto> questionFeedback) {
        this.attemptId = attemptId;
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.topicId = topicId;
        this.topicNameEn = topicNameEn;
        this.topicNameTe = topicNameTe;
        this.score = score;
        this.totalMarks = totalMarks;
        this.percentage = percentage;
        this.status = status;
        this.weakTopicDetected = weakTopicDetected;
        this.recommendation = recommendation;
        this.questionFeedback = questionFeedback;
    }

    public static QuizResultDtoBuilder builder() { return new QuizResultDtoBuilder(); }

    public static class QuizResultDtoBuilder {
        private Long attemptId;
        private Long quizId;
        private String quizTitle;
        private Long topicId;
        private String topicNameEn;
        private String topicNameTe;
        private Double score;
        private Double totalMarks;
        private Double percentage;
        private String status;
        private Boolean weakTopicDetected;
        private RecommendationDto recommendation;
        private List<QuestionFeedbackDto> questionFeedback;

        public QuizResultDtoBuilder attemptId(Long attemptId) { this.attemptId = attemptId; return this; }
        public QuizResultDtoBuilder quizId(Long quizId) { this.quizId = quizId; return this; }
        public QuizResultDtoBuilder quizTitle(String quizTitle) { this.quizTitle = quizTitle; return this; }
        public QuizResultDtoBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public QuizResultDtoBuilder topicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; return this; }
        public QuizResultDtoBuilder topicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; return this; }
        public QuizResultDtoBuilder score(Double score) { this.score = score; return this; }
        public QuizResultDtoBuilder totalMarks(Double totalMarks) { this.totalMarks = totalMarks; return this; }
        public QuizResultDtoBuilder percentage(Double percentage) { this.percentage = percentage; return this; }
        public QuizResultDtoBuilder status(String status) { this.status = status; return this; }
        public QuizResultDtoBuilder weakTopicDetected(Boolean weakTopicDetected) { this.weakTopicDetected = weakTopicDetected; return this; }
        public QuizResultDtoBuilder recommendation(RecommendationDto recommendation) { this.recommendation = recommendation; return this; }
        public QuizResultDtoBuilder questionFeedback(List<QuestionFeedbackDto> questionFeedback) { this.questionFeedback = questionFeedback; return this; }

        public QuizResultDto build() {
            return new QuizResultDto(attemptId, quizId, quizTitle, topicId, topicNameEn, topicNameTe, score, totalMarks, percentage, status, weakTopicDetected, recommendation, questionFeedback);
        }
    }

    public Long getAttemptId() { return attemptId; }
    public void setAttemptId(Long attemptId) { this.attemptId = attemptId; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public String getQuizTitle() { return quizTitle; }
    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTopicNameEn() { return topicNameEn; }
    public void setTopicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; }

    public String getTopicNameTe() { return topicNameTe; }
    public void setTopicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { this.totalMarks = totalMarks; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getWeakTopicDetected() { return weakTopicDetected; }
    public void setWeakTopicDetected(Boolean weakTopicDetected) { this.weakTopicDetected = weakTopicDetected; }

    public RecommendationDto getRecommendation() { return recommendation; }
    public void setRecommendation(RecommendationDto recommendation) { this.recommendation = recommendation; }

    public List<QuestionFeedbackDto> getQuestionFeedback() { return questionFeedback; }
    public void setQuestionFeedback(List<QuestionFeedbackDto> questionFeedback) { this.questionFeedback = questionFeedback; }
}
