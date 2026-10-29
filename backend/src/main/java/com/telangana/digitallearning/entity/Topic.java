package com.telangana.digitallearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long chapterId;

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameTe;

    @Column(columnDefinition = "TEXT")
    private String descriptionEn;

    @Column(columnDefinition = "TEXT")
    private String descriptionTe;

    private Integer sequenceOrder;

    public Topic() {}

    public Topic(Long id, Long chapterId, String nameEn, String nameTe, String descriptionEn, String descriptionTe, Integer sequenceOrder) {
        this.id = id;
        this.chapterId = chapterId;
        this.nameEn = nameEn;
        this.nameTe = nameTe;
        this.descriptionEn = descriptionEn;
        this.descriptionTe = descriptionTe;
        this.sequenceOrder = sequenceOrder;
    }

    public static TopicBuilder builder() { return new TopicBuilder(); }

    public static class TopicBuilder {
        private Long id;
        private Long chapterId;
        private String nameEn;
        private String nameTe;
        private String descriptionEn;
        private String descriptionTe;
        private Integer sequenceOrder;

        public TopicBuilder id(Long id) { this.id = id; return this; }
        public TopicBuilder chapterId(Long chapterId) { this.chapterId = chapterId; return this; }
        public TopicBuilder nameEn(String nameEn) { this.nameEn = nameEn; return this; }
        public TopicBuilder nameTe(String nameTe) { this.nameTe = nameTe; return this; }
        public TopicBuilder descriptionEn(String descriptionEn) { this.descriptionEn = descriptionEn; return this; }
        public TopicBuilder descriptionTe(String descriptionTe) { this.descriptionTe = descriptionTe; return this; }
        public TopicBuilder sequenceOrder(Integer sequenceOrder) { this.sequenceOrder = sequenceOrder; return this; }

        public Topic build() {
            return new Topic(id, chapterId, nameEn, nameTe, descriptionEn, descriptionTe, sequenceOrder);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getChapterId() { return chapterId; }
    public void setChapterId(Long chapterId) { this.chapterId = chapterId; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public String getNameTe() { return nameTe; }
    public void setNameTe(String nameTe) { this.nameTe = nameTe; }

    public String getDescriptionEn() { return descriptionEn; }
    public void setDescriptionEn(String descriptionEn) { this.descriptionEn = descriptionEn; }

    public String getDescriptionTe() { return descriptionTe; }
    public void setDescriptionTe(String descriptionTe) { this.descriptionTe = descriptionTe; }

    public Integer getSequenceOrder() { return sequenceOrder; }
    public void setSequenceOrder(Integer sequenceOrder) { this.sequenceOrder = sequenceOrder; }
}
