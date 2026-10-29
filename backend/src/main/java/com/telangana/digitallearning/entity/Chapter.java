package com.telangana.digitallearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chapters")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long subjectId;

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameTe;

    private Integer sequenceOrder;

    public Chapter() {}

    public Chapter(Long id, Long subjectId, String nameEn, String nameTe, Integer sequenceOrder) {
        this.id = id;
        this.subjectId = subjectId;
        this.nameEn = nameEn;
        this.nameTe = nameTe;
        this.sequenceOrder = sequenceOrder;
    }

    public static ChapterBuilder builder() { return new ChapterBuilder(); }

    public static class ChapterBuilder {
        private Long id;
        private Long subjectId;
        private String nameEn;
        private String nameTe;
        private Integer sequenceOrder;

        public ChapterBuilder id(Long id) { this.id = id; return this; }
        public ChapterBuilder subjectId(Long subjectId) { this.subjectId = subjectId; return this; }
        public ChapterBuilder nameEn(String nameEn) { this.nameEn = nameEn; return this; }
        public ChapterBuilder nameTe(String nameTe) { this.nameTe = nameTe; return this; }
        public ChapterBuilder titleEn(String titleEn) { this.nameEn = titleEn; return this; }
        public ChapterBuilder titleTe(String titleTe) { this.nameTe = titleTe; return this; }
        public ChapterBuilder sequenceOrder(Integer sequenceOrder) { this.sequenceOrder = sequenceOrder; return this; }
        public ChapterBuilder chapterNumber(Integer chapterNumber) { this.sequenceOrder = chapterNumber; return this; }

        public Chapter build() {
            return new Chapter(id, subjectId, nameEn, nameTe, sequenceOrder);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public String getNameTe() { return nameTe; }
    public void setNameTe(String nameTe) { this.nameTe = nameTe; }

    public Integer getSequenceOrder() { return sequenceOrder; }
    public void setSequenceOrder(Integer sequenceOrder) { this.sequenceOrder = sequenceOrder; }
}
