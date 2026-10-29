package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long topicId;

    @Column(nullable = false)
    private Long recommendedMaterialId;

    private Double previousScore;
    private String recommendationType;

    @Column(columnDefinition = "TEXT")
    private String messageEn;

    @Column(columnDefinition = "TEXT")
    private String messageTe;

    private String status;
    private Double retestScore;
    private LocalDateTime generatedAt;

    public Recommendation() {}

    public Recommendation(Long id, Long studentId, Long topicId, Long recommendedMaterialId, Double previousScore, String recommendationType, String messageEn, String messageTe, String status, Double retestScore, LocalDateTime generatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.topicId = topicId;
        this.recommendedMaterialId = recommendedMaterialId;
        this.previousScore = previousScore;
        this.recommendationType = recommendationType;
        this.messageEn = messageEn;
        this.messageTe = messageTe;
        this.status = status;
        this.retestScore = retestScore;
        this.generatedAt = generatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.generatedAt = LocalDateTime.now();
        if (this.status == null) this.status = "PENDING";
    }

    public static RecommendationBuilder builder() { return new RecommendationBuilder(); }

    public static class RecommendationBuilder {
        private Long id;
        private Long studentId;
        private Long topicId;
        private Long recommendedMaterialId;
        private Double previousScore;
        private String recommendationType;
        private String messageEn;
        private String messageTe;
        private String status;
        private Double retestScore;
        private LocalDateTime generatedAt;

        public RecommendationBuilder id(Long id) { this.id = id; return this; }
        public RecommendationBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public RecommendationBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public RecommendationBuilder recommendedMaterialId(Long recommendedMaterialId) { this.recommendedMaterialId = recommendedMaterialId; return this; }
        public RecommendationBuilder previousScore(Double previousScore) { this.previousScore = previousScore; return this; }
        public RecommendationBuilder recommendationType(String recommendationType) { this.recommendationType = recommendationType; return this; }
        public RecommendationBuilder messageEn(String messageEn) { this.messageEn = messageEn; return this; }
        public RecommendationBuilder messageTe(String messageTe) { this.messageTe = messageTe; return this; }
        public RecommendationBuilder recommendationMessageEn(String recommendationMessageEn) { this.messageEn = recommendationMessageEn; return this; }
        public RecommendationBuilder recommendationMessageTe(String recommendationMessageTe) { this.messageTe = recommendationMessageTe; return this; }
        public RecommendationBuilder status(String status) { this.status = status; return this; }
        public RecommendationBuilder retestScore(Double retestScore) { this.retestScore = retestScore; return this; }
        public RecommendationBuilder generatedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; return this; }

        public Recommendation build() {
            return new Recommendation(id, studentId, topicId, recommendedMaterialId, previousScore, recommendationType, messageEn, messageTe, status, retestScore, generatedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public Long getRecommendedMaterialId() { return recommendedMaterialId; }
    public void setRecommendedMaterialId(Long recommendedMaterialId) { this.recommendedMaterialId = recommendedMaterialId; }

    public Double getPreviousScore() { return previousScore; }
    public void setPreviousScore(Double previousScore) { this.previousScore = previousScore; }

    public String getRecommendationType() { return recommendationType; }
    public void setRecommendationType(String recommendationType) { this.recommendationType = recommendationType; }

    public String getMessageEn() { return messageEn; }
    public void setMessageEn(String messageEn) { this.messageEn = messageEn; }

    public String getMessageTe() { return messageTe; }
    public void setMessageTe(String messageTe) { this.messageTe = messageTe; }

    public String getRecommendationMessageEn() { return messageEn; }
    public String getRecommendationMessageTe() { return messageTe; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getRetestScore() { return retestScore; }
    public void setRetestScore(Double retestScore) { this.retestScore = retestScore; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
    public LocalDateTime getCreatedAt() { return generatedAt; }
}
