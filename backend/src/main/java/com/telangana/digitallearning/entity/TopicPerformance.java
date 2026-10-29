package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topic_performances")
public class TopicPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long topicId;

    private Double scorePercentage;
    private String status;
    private Integer attemptsCount;
    private LocalDateTime lastAttemptAt;

    public TopicPerformance() {}

    public TopicPerformance(Long id, Long studentId, Long topicId, Double scorePercentage, String status, Integer attemptsCount, LocalDateTime lastAttemptAt) {
        this.id = id;
        this.studentId = studentId;
        this.topicId = topicId;
        this.scorePercentage = scorePercentage;
        this.status = status;
        this.attemptsCount = attemptsCount;
        this.lastAttemptAt = lastAttemptAt;
    }

    public static TopicPerformanceBuilder builder() { return new TopicPerformanceBuilder(); }

    public static class TopicPerformanceBuilder {
        private Long id;
        private Long studentId;
        private Long topicId;
        private Double scorePercentage;
        private String status;
        private Integer attemptsCount;
        private LocalDateTime lastAttemptAt;

        public TopicPerformanceBuilder id(Long id) { this.id = id; return this; }
        public TopicPerformanceBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public TopicPerformanceBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public TopicPerformanceBuilder scorePercentage(Double scorePercentage) { this.scorePercentage = scorePercentage; return this; }
        public TopicPerformanceBuilder status(String status) { this.status = status; return this; }
        public TopicPerformanceBuilder attemptsCount(Integer attemptsCount) { this.attemptsCount = attemptsCount; return this; }
        public TopicPerformanceBuilder lastAttemptAt(LocalDateTime lastAttemptAt) { this.lastAttemptAt = lastAttemptAt; return this; }

        public TopicPerformance build() {
            return new TopicPerformance(id, studentId, topicId, scorePercentage, status, attemptsCount, lastAttemptAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public Double getScorePercentage() { return scorePercentage; }
    public void setScorePercentage(Double scorePercentage) { this.scorePercentage = scorePercentage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getAttemptsCount() { return attemptsCount; }
    public void setAttemptsCount(Integer attemptsCount) { this.attemptsCount = attemptsCount; }

    public LocalDateTime getLastAttemptAt() { return lastAttemptAt; }
    public void setLastAttemptAt(LocalDateTime lastAttemptAt) { this.lastAttemptAt = lastAttemptAt; }
    public LocalDateTime getLastAssessedAt() { return lastAttemptAt; }
}
