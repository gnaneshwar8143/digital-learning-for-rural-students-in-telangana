package com.telangana.digitallearning.dto;

import java.time.LocalDateTime;

public class QuizResultSummaryDto {
    private Long attemptId;
    private Long quizId;
    private String quizTitle;
    private String topicNameEn;
    private String topicNameTe;
    private Double percentage;
    private Double score;
    private Double totalMarks;
    private String attemptType;
    private LocalDateTime attemptedAt;

    public QuizResultSummaryDto() {}

    public QuizResultSummaryDto(Long attemptId, Long quizId, String quizTitle, String topicNameEn, String topicNameTe, Double percentage, Double score, Double totalMarks, String attemptType, LocalDateTime attemptedAt) {
        this.attemptId = attemptId;
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.topicNameEn = topicNameEn;
        this.topicNameTe = topicNameTe;
        this.percentage = percentage;
        this.score = score;
        this.totalMarks = totalMarks;
        this.attemptType = attemptType;
        this.attemptedAt = attemptedAt;
    }

    public static QuizResultSummaryDtoBuilder builder() { return new QuizResultSummaryDtoBuilder(); }

    public static class QuizResultSummaryDtoBuilder {
        private Long attemptId;
        private Long quizId;
        private String quizTitle;
        private String topicNameEn;
        private String topicNameTe;
        private Double percentage;
        private Double score;
        private Double totalMarks;
        private String attemptType;
        private LocalDateTime attemptedAt;

        public QuizResultSummaryDtoBuilder attemptId(Long attemptId) { this.attemptId = attemptId; return this; }
        public QuizResultSummaryDtoBuilder quizId(Long quizId) { this.quizId = quizId; return this; }
        public QuizResultSummaryDtoBuilder quizTitle(String quizTitle) { this.quizTitle = quizTitle; return this; }
        public QuizResultSummaryDtoBuilder quizTitleEn(String quizTitleEn) { this.quizTitle = quizTitleEn; return this; }
        public QuizResultSummaryDtoBuilder topicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; return this; }
        public QuizResultSummaryDtoBuilder topicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; return this; }
        public QuizResultSummaryDtoBuilder percentage(Double percentage) { this.percentage = percentage; return this; }
        public QuizResultSummaryDtoBuilder score(Double score) { this.score = score; return this; }
        public QuizResultSummaryDtoBuilder totalMarks(Double totalMarks) { this.totalMarks = totalMarks; return this; }
        public QuizResultSummaryDtoBuilder attemptType(String attemptType) { this.attemptType = attemptType; return this; }
        public QuizResultSummaryDtoBuilder attemptedAt(LocalDateTime attemptedAt) { this.attemptedAt = attemptedAt; return this; }

        public QuizResultSummaryDto build() {
            return new QuizResultSummaryDto(attemptId, quizId, quizTitle, topicNameEn, topicNameTe, percentage, score, totalMarks, attemptType, attemptedAt);
        }
    }

    public Long getAttemptId() { return attemptId; }
    public void setAttemptId(Long attemptId) { this.attemptId = attemptId; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public String getQuizTitle() { return quizTitle; }
    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }

    public String getTopicNameEn() { return topicNameEn; }
    public void setTopicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; }

    public String getTopicNameTe() { return topicNameTe; }
    public void setTopicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { this.totalMarks = totalMarks; }

    public String getAttemptType() { return attemptType; }
    public void setAttemptType(String attemptType) { this.attemptType = attemptType; }

    public LocalDateTime getAttemptedAt() { return attemptedAt; }
    public void setAttemptedAt(LocalDateTime attemptedAt) { this.attemptedAt = attemptedAt; }
}
