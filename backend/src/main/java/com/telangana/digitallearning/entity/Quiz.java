package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long topicId;

    @Column(nullable = false)
    private String titleEn;

    @Column(nullable = false)
    private String titleTe;

    private Integer classLevel;
    private Integer durationMinutes;
    private Double totalMarks;
    private Double passingMarks;

    private Boolean isEvaluationTest;
    private String testCategory;

    private LocalDateTime createdAt;

    public Quiz() {}

    public Quiz(Long id, Long topicId, String titleEn, String titleTe, Integer classLevel, Integer durationMinutes, Double totalMarks, Double passingMarks, Boolean isEvaluationTest, String testCategory, LocalDateTime createdAt) {
        this.id = id;
        this.topicId = topicId;
        this.titleEn = titleEn;
        this.titleTe = titleTe;
        this.classLevel = classLevel;
        this.durationMinutes = durationMinutes;
        this.totalMarks = totalMarks;
        this.passingMarks = passingMarks;
        this.isEvaluationTest = isEvaluationTest;
        this.testCategory = testCategory;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.isEvaluationTest == null) this.isEvaluationTest = false;
        if (this.durationMinutes == null) this.durationMinutes = 15;
    }

    public static QuizBuilder builder() { return new QuizBuilder(); }

    public static class QuizBuilder {
        private Long id;
        private Long topicId;
        private String titleEn;
        private String titleTe;
        private Integer classLevel;
        private Integer durationMinutes;
        private Double totalMarks;
        private Double passingMarks;
        private Boolean isEvaluationTest;
        private String testCategory;
        private LocalDateTime createdAt;

        public QuizBuilder id(Long id) { this.id = id; return this; }
        public QuizBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public QuizBuilder titleEn(String titleEn) { this.titleEn = titleEn; return this; }
        public QuizBuilder titleTe(String titleTe) { this.titleTe = titleTe; return this; }
        public QuizBuilder classLevel(Integer classLevel) { this.classLevel = classLevel; return this; }
        public QuizBuilder durationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; return this; }
        public QuizBuilder totalMarks(Double totalMarks) { this.totalMarks = totalMarks; return this; }
        public QuizBuilder passingMarks(Double passingMarks) { this.passingMarks = passingMarks; return this; }
        public QuizBuilder isEvaluationTest(Boolean isEvaluationTest) { this.isEvaluationTest = isEvaluationTest; return this; }
        public QuizBuilder testCategory(String testCategory) { this.testCategory = testCategory; return this; }
        public QuizBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Quiz build() {
            return new Quiz(id, topicId, titleEn, titleTe, classLevel, durationMinutes, totalMarks, passingMarks, isEvaluationTest, testCategory, createdAt);
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

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { this.totalMarks = totalMarks; }

    public Double getPassingMarks() { return passingMarks; }
    public void setPassingMarks(Double passingMarks) { this.passingMarks = passingMarks; }

    public Boolean getIsEvaluationTest() { return isEvaluationTest; }
    public void setIsEvaluationTest(Boolean isEvaluationTest) { this.isEvaluationTest = isEvaluationTest; }

    public String getTestCategory() { return testCategory; }
    public void setTestCategory(String testCategory) { this.testCategory = testCategory; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
