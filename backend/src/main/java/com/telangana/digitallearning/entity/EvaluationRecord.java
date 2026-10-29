package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluation_records")
public class EvaluationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long topicId;

    private Double preTestScore;
    private Double postTestScore;
    private Double improvementPercentage;

    private LocalDateTime evaluatedAt;

    public EvaluationRecord() {}

    public EvaluationRecord(Long id, Long studentId, Long topicId, Double preTestScore, Double postTestScore, Double improvementPercentage, LocalDateTime evaluatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.topicId = topicId;
        this.preTestScore = preTestScore;
        this.postTestScore = postTestScore;
        this.improvementPercentage = improvementPercentage;
        this.evaluatedAt = evaluatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.evaluatedAt = LocalDateTime.now();
    }

    public static EvaluationRecordBuilder builder() {
        return new EvaluationRecordBuilder();
    }

    public static class EvaluationRecordBuilder {
        private Long id;
        private Long studentId;
        private Long topicId;
        private Double preTestScore;
        private Double postTestScore;
        private Double improvementPercentage;
        private LocalDateTime evaluatedAt;

        public EvaluationRecordBuilder id(Long id) { this.id = id; return this; }
        public EvaluationRecordBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public EvaluationRecordBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public EvaluationRecordBuilder preTestScore(Double preTestScore) { this.preTestScore = preTestScore; return this; }
        public EvaluationRecordBuilder postTestScore(Double postTestScore) { this.postTestScore = postTestScore; return this; }
        public EvaluationRecordBuilder improvementPercentage(Double improvementPercentage) { this.improvementPercentage = improvementPercentage; return this; }
        public EvaluationRecordBuilder evaluatedAt(LocalDateTime evaluatedAt) { this.evaluatedAt = evaluatedAt; return this; }

        public EvaluationRecord build() {
            return new EvaluationRecord(id, studentId, topicId, preTestScore, postTestScore, improvementPercentage, evaluatedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public Double getPreTestScore() { return preTestScore; }
    public void setPreTestScore(Double preTestScore) { this.preTestScore = preTestScore; }

    public Double getPostTestScore() { return postTestScore; }
    public void setPostTestScore(Double postTestScore) { this.postTestScore = postTestScore; }

    public Double getImprovementPercentage() { return improvementPercentage; }
    public void setImprovementPercentage(Double improvementPercentage) { this.improvementPercentage = improvementPercentage; }

    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
    public void setEvaluatedAt(LocalDateTime evaluatedAt) { this.evaluatedAt = evaluatedAt; }
}
