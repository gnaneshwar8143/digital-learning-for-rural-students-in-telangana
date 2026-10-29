package com.telangana.digitallearning.dto;

import java.time.LocalDateTime;

public class RecommendationDto {
    private Long id;
    private Long topicId;
    private String topicNameEn;
    private String topicNameTe;
    private Double previousScore;
    private String recommendationType;
    private String messageEn;
    private String messageTe;
    private Long recommendedMaterialId;
    private String recommendedMaterialTitleEn;
    private String recommendedMaterialTitleTe;
    private String materialType;
    private String status;
    private LocalDateTime createdAt;
    private Double retestScore;

    public RecommendationDto() {}

    public RecommendationDto(Long id, Long topicId, String topicNameEn, String topicNameTe, Double previousScore, String recommendationType, String messageEn, String messageTe, Long recommendedMaterialId, String recommendedMaterialTitleEn, String recommendedMaterialTitleTe, String materialType, String status, LocalDateTime createdAt, Double retestScore) {
        this.id = id;
        this.topicId = topicId;
        this.topicNameEn = topicNameEn;
        this.topicNameTe = topicNameTe;
        this.previousScore = previousScore;
        this.recommendationType = recommendationType;
        this.messageEn = messageEn;
        this.messageTe = messageTe;
        this.recommendedMaterialId = recommendedMaterialId;
        this.recommendedMaterialTitleEn = recommendedMaterialTitleEn;
        this.recommendedMaterialTitleTe = recommendedMaterialTitleTe;
        this.materialType = materialType;
        this.status = status;
        this.createdAt = createdAt;
        this.retestScore = retestScore;
    }

    public static RecommendationDtoBuilder builder() { return new RecommendationDtoBuilder(); }

    public static class RecommendationDtoBuilder {
        private Long id;
        private Long topicId;
        private String topicNameEn;
        private String topicNameTe;
        private Double previousScore;
        private String recommendationType;
        private String messageEn;
        private String messageTe;
        private Long recommendedMaterialId;
        private String recommendedMaterialTitleEn;
        private String recommendedMaterialTitleTe;
        private String materialType;
        private String status;
        private LocalDateTime createdAt;
        private Double retestScore;

        public RecommendationDtoBuilder id(Long id) { this.id = id; return this; }
        public RecommendationDtoBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public RecommendationDtoBuilder topicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; return this; }
        public RecommendationDtoBuilder topicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; return this; }
        public RecommendationDtoBuilder previousScore(Double previousScore) { this.previousScore = previousScore; return this; }
        public RecommendationDtoBuilder recommendationType(String recommendationType) { this.recommendationType = recommendationType; return this; }
        public RecommendationDtoBuilder messageEn(String messageEn) { this.messageEn = messageEn; return this; }
        public RecommendationDtoBuilder messageTe(String messageTe) { this.messageTe = messageTe; return this; }
        public RecommendationDtoBuilder recommendationMessageEn(String recommendationMessageEn) { this.messageEn = recommendationMessageEn; return this; }
        public RecommendationDtoBuilder recommendationMessageTe(String recommendationMessageTe) { this.messageTe = recommendationMessageTe; return this; }
        public RecommendationDtoBuilder recommendedMaterialId(Long recommendedMaterialId) { this.recommendedMaterialId = recommendedMaterialId; return this; }
        public RecommendationDtoBuilder recommendedMaterialTitleEn(String recommendedMaterialTitleEn) { this.recommendedMaterialTitleEn = recommendedMaterialTitleEn; return this; }
        public RecommendationDtoBuilder recommendedMaterialTitleTe(String recommendedMaterialTitleTe) { this.recommendedMaterialTitleTe = recommendedMaterialTitleTe; return this; }
        public RecommendationDtoBuilder materialType(String materialType) { this.materialType = materialType; return this; }
        public RecommendationDtoBuilder status(String status) { this.status = status; return this; }
        public RecommendationDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public RecommendationDtoBuilder retestScore(Double retestScore) { this.retestScore = retestScore; return this; }

        public RecommendationDto build() {
            return new RecommendationDto(id, topicId, topicNameEn, topicNameTe, previousScore, recommendationType, messageEn, messageTe, recommendedMaterialId, recommendedMaterialTitleEn, recommendedMaterialTitleTe, materialType, status, createdAt, retestScore);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTopicNameEn() { return topicNameEn; }
    public void setTopicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; }

    public String getTopicNameTe() { return topicNameTe; }
    public void setTopicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; }

    public Double getPreviousScore() { return previousScore; }
    public void setPreviousScore(Double previousScore) { this.previousScore = previousScore; }

    public String getRecommendationType() { return recommendationType; }
    public void setRecommendationType(String recommendationType) { this.recommendationType = recommendationType; }

    public String getMessageEn() { return messageEn; }
    public void setMessageEn(String messageEn) { this.messageEn = messageEn; }

    public String getMessageTe() { return messageTe; }
    public void setMessageTe(String messageTe) { this.messageTe = messageTe; }

    public Long getRecommendedMaterialId() { return recommendedMaterialId; }
    public void setRecommendedMaterialId(Long recommendedMaterialId) { this.recommendedMaterialId = recommendedMaterialId; }

    public String getRecommendedMaterialTitleEn() { return recommendedMaterialTitleEn; }
    public void setRecommendedMaterialTitleEn(String recommendedMaterialTitleEn) { this.recommendedMaterialTitleEn = recommendedMaterialTitleEn; }

    public String getRecommendedMaterialTitleTe() { return recommendedMaterialTitleTe; }
    public void setRecommendedMaterialTitleTe(String recommendedMaterialTitleTe) { this.recommendedMaterialTitleTe = recommendedMaterialTitleTe; }

    public String getMaterialType() { return materialType; }
    public void setMaterialType(String materialType) { this.materialType = materialType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Double getRetestScore() { return retestScore; }
    public void setRetestScore(Double retestScore) { this.retestScore = retestScore; }
}
