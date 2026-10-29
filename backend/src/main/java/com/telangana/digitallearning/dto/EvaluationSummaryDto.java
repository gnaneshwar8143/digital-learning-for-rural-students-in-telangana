package com.telangana.digitallearning.dto;

import java.time.LocalDateTime;

public class EvaluationSummaryDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long topicId;
    private String topicNameEn;
    private String topicNameTe;
    private Double preTestScore;
    private Double postTestScore;
    private Double improvementPercentage;
    private LocalDateTime evaluatedAt;

    public EvaluationSummaryDto() {}

    public EvaluationSummaryDto(Long id, Long studentId, String studentName, Long topicId, String topicNameEn, String topicNameTe, Double preTestScore, Double postTestScore, Double improvementPercentage, LocalDateTime evaluatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.topicId = topicId;
        this.topicNameEn = topicNameEn;
        this.topicNameTe = topicNameTe;
        this.preTestScore = preTestScore;
        this.postTestScore = postTestScore;
        this.improvementPercentage = improvementPercentage;
        this.evaluatedAt = evaluatedAt;
    }

    public static EvaluationSummaryDtoBuilder builder() { return new EvaluationSummaryDtoBuilder(); }

    public static class EvaluationSummaryDtoBuilder {
        private Long id;
        private Long studentId;
        private String studentName;
        private Long topicId;
        private String topicNameEn;
        private String topicNameTe;
        private Double preTestScore;
        private Double postTestScore;
        private Double improvementPercentage;
        private LocalDateTime evaluatedAt;

        public EvaluationSummaryDtoBuilder id(Long id) { this.id = id; return this; }
        public EvaluationSummaryDtoBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public EvaluationSummaryDtoBuilder studentName(String studentName) { this.studentName = studentName; return this; }
        public EvaluationSummaryDtoBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public EvaluationSummaryDtoBuilder topicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; return this; }
        public EvaluationSummaryDtoBuilder topicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; return this; }
        public EvaluationSummaryDtoBuilder preTestScore(Double preTestScore) { this.preTestScore = preTestScore; return this; }
        public EvaluationSummaryDtoBuilder postTestScore(Double postTestScore) { this.postTestScore = postTestScore; return this; }
        public EvaluationSummaryDtoBuilder improvementPercentage(Double improvementPercentage) { this.improvementPercentage = improvementPercentage; return this; }
        public EvaluationSummaryDtoBuilder evaluatedAt(LocalDateTime evaluatedAt) { this.evaluatedAt = evaluatedAt; return this; }

        public EvaluationSummaryDto build() {
            return new EvaluationSummaryDto(id, studentId, studentName, topicId, topicNameEn, topicNameTe, preTestScore, postTestScore, improvementPercentage, evaluatedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTopicNameEn() { return topicNameEn; }
    public void setTopicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; }

    public String getTopicNameTe() { return topicNameTe; }
    public void setTopicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; }

    public Double getPreTestScore() { return preTestScore; }
    public void setPreTestScore(Double preTestScore) { this.preTestScore = preTestScore; }

    public Double getPostTestScore() { return postTestScore; }
    public void setPostTestScore(Double postTestScore) { this.postTestScore = postTestScore; }

    public Double getImprovementPercentage() { return improvementPercentage; }
    public void setImprovementPercentage(Double improvementPercentage) { this.improvementPercentage = improvementPercentage; }

    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
    public void setEvaluatedAt(LocalDateTime evaluatedAt) { this.evaluatedAt = evaluatedAt; }
}
