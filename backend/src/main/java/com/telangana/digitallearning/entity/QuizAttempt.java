package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long quizId;

    @Column(nullable = false)
    private Long topicId;

    private Double score;
    private Double totalMarks;
    private Double percentage;

    private String attemptType;
    private LocalDateTime attemptedAt;

    public QuizAttempt() {}

    public QuizAttempt(Long id, Long studentId, Long quizId, Long topicId, Double score, Double totalMarks, Double percentage, String attemptType, LocalDateTime attemptedAt) {
        this.id = id;
        this.studentId = studentId;
        this.quizId = quizId;
        this.topicId = topicId;
        this.score = score;
        this.totalMarks = totalMarks;
        this.percentage = percentage;
        this.attemptType = attemptType;
        this.attemptedAt = attemptedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.attemptedAt = LocalDateTime.now();
        if (this.attemptType == null) this.attemptType = "INITIAL";
    }

    public static QuizAttemptBuilder builder() { return new QuizAttemptBuilder(); }

    public static class QuizAttemptBuilder {
        private Long id;
        private Long studentId;
        private Long quizId;
        private Long topicId;
        private Double score;
        private Double totalMarks;
        private Double percentage;
        private String attemptType;
        private LocalDateTime attemptedAt;

        public QuizAttemptBuilder id(Long id) { this.id = id; return this; }
        public QuizAttemptBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public QuizAttemptBuilder quizId(Long quizId) { this.quizId = quizId; return this; }
        public QuizAttemptBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public QuizAttemptBuilder score(Double score) { this.score = score; return this; }
        public QuizAttemptBuilder totalMarks(Double totalMarks) { this.totalMarks = totalMarks; return this; }
        public QuizAttemptBuilder percentage(Double percentage) { this.percentage = percentage; return this; }
        public QuizAttemptBuilder attemptType(String attemptType) { this.attemptType = attemptType; return this; }
        public QuizAttemptBuilder attemptedAt(LocalDateTime attemptedAt) { this.attemptedAt = attemptedAt; return this; }

        public QuizAttempt build() {
            return new QuizAttempt(id, studentId, quizId, topicId, score, totalMarks, percentage, attemptType, attemptedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { this.totalMarks = totalMarks; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public String getAttemptType() { return attemptType; }
    public void setAttemptType(String attemptType) { this.attemptType = attemptType; }

    public LocalDateTime getAttemptedAt() { return attemptedAt; }
    public void setAttemptedAt(LocalDateTime attemptedAt) { this.attemptedAt = attemptedAt; }
}
