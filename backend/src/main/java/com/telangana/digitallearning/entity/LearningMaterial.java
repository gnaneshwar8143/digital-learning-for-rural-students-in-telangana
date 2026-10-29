package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "learning_materials")
public class LearningMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long topicId;

    @Column(nullable = false)
    private String titleEn;

    @Column(nullable = false)
    private String titleTe;

    @Column(nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String contentEn;

    @Column(columnDefinition = "TEXT")
    private String contentTe;

    private String fileUrl;
    private Long fileSizeKb;
    private Boolean isLowBandwidthOptimized;

    private LocalDateTime createdAt;

    public LearningMaterial() {}

    public LearningMaterial(Long id, Long topicId, String titleEn, String titleTe, String type, String contentEn, String contentTe, String fileUrl, Long fileSizeKb, Boolean isLowBandwidthOptimized, LocalDateTime createdAt) {
        this.id = id;
        this.topicId = topicId;
        this.titleEn = titleEn;
        this.titleTe = titleTe;
        this.type = type;
        this.contentEn = contentEn;
        this.contentTe = contentTe;
        this.fileUrl = fileUrl;
        this.fileSizeKb = fileSizeKb;
        this.isLowBandwidthOptimized = isLowBandwidthOptimized;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.isLowBandwidthOptimized == null) this.isLowBandwidthOptimized = true;
    }

    public static LearningMaterialBuilder builder() { return new LearningMaterialBuilder(); }

    public static class LearningMaterialBuilder {
        private Long id;
        private Long topicId;
        private String titleEn;
        private String titleTe;
        private String type;
        private String contentEn;
        private String contentTe;
        private String fileUrl;
        private Long fileSizeKb;
        private Boolean isLowBandwidthOptimized;
        private LocalDateTime createdAt;

        public LearningMaterialBuilder id(Long id) { this.id = id; return this; }
        public LearningMaterialBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public LearningMaterialBuilder titleEn(String titleEn) { this.titleEn = titleEn; return this; }
        public LearningMaterialBuilder titleTe(String titleTe) { this.titleTe = titleTe; return this; }
        public LearningMaterialBuilder type(String type) { this.type = type; return this; }
        public LearningMaterialBuilder materialType(String materialType) { this.type = materialType; return this; }
        public LearningMaterialBuilder contentEn(String contentEn) { this.contentEn = contentEn; return this; }
        public LearningMaterialBuilder contentTe(String contentTe) { this.contentTe = contentTe; return this; }
        public LearningMaterialBuilder contentTextEn(String contentTextEn) { this.contentEn = contentTextEn; return this; }
        public LearningMaterialBuilder contentTextTe(String contentTextTe) { this.contentTe = contentTextTe; return this; }
        public LearningMaterialBuilder descriptionEn(String descriptionEn) { this.contentEn = descriptionEn; return this; }
        public LearningMaterialBuilder descriptionTe(String descriptionTe) { this.contentTe = descriptionTe; return this; }
        public LearningMaterialBuilder fileUrl(String fileUrl) { this.fileUrl = fileUrl; return this; }
        public LearningMaterialBuilder fileSizeKb(Long fileSizeKb) { this.fileSizeKb = fileSizeKb; return this; }
        public LearningMaterialBuilder downloadable(Boolean downloadable) { this.isLowBandwidthOptimized = downloadable; return this; }
        public LearningMaterialBuilder isLowBandwidthOptimized(Boolean isLowBandwidthOptimized) { this.isLowBandwidthOptimized = isLowBandwidthOptimized; return this; }
        public LearningMaterialBuilder lowBandwidthOptimized(Boolean lowBandwidthOptimized) { this.isLowBandwidthOptimized = lowBandwidthOptimized; return this; }
        public LearningMaterialBuilder classLevel(Integer classLevel) { return this; }
        public LearningMaterialBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public LearningMaterial build() {
            return new LearningMaterial(id, topicId, titleEn, titleTe, type, contentEn, contentTe, fileUrl, fileSizeKb, isLowBandwidthOptimized, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String titleEn) { this.titleEn = titleEn; }

    public String getTitleTe() { return titleTe; }
    public void setTitleTe(String titleTe) { this.titleTe = titleTe; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getMaterialType() { return type; }

    public String getContentEn() { return contentEn; }
    public void setContentEn(String contentEn) { this.contentEn = contentEn; }

    public String getContentTe() { return contentTe; }
    public void setContentTe(String contentTe) { this.contentTe = contentTe; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public Long getFileSizeKb() { return fileSizeKb; }
    public void setFileSizeKb(Long fileSizeKb) { this.fileSizeKb = fileSizeKb; }

    public Boolean getIsLowBandwidthOptimized() { return isLowBandwidthOptimized; }
    public void setIsLowBandwidthOptimized(Boolean isLowBandwidthOptimized) { this.isLowBandwidthOptimized = isLowBandwidthOptimized; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
