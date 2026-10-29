package com.telangana.digitallearning.dto;

import java.util.List;

public class StudentPerformanceDto {
    private Long studentId;
    private String studentName;
    private String email;
    private Integer classLevel;
    private Double averageScore;
    private Integer weakTopicsCount;
    private List<WeakTopicDto> weakTopics;
    private List<EvaluationSummaryDto> evaluationHistory;

    public StudentPerformanceDto() {}

    public StudentPerformanceDto(Long studentId, String studentName, String email, Integer classLevel, Double averageScore, Integer weakTopicsCount, List<WeakTopicDto> weakTopics, List<EvaluationSummaryDto> evaluationHistory) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.classLevel = classLevel;
        this.averageScore = averageScore;
        this.weakTopicsCount = weakTopicsCount;
        this.weakTopics = weakTopics;
        this.evaluationHistory = evaluationHistory;
    }

    public static StudentPerformanceDtoBuilder builder() { return new StudentPerformanceDtoBuilder(); }

    public static class StudentPerformanceDtoBuilder {
        private Long studentId;
        private String studentName;
        private String email;
        private Integer classLevel;
        private Double averageScore;
        private Integer weakTopicsCount;
        private List<WeakTopicDto> weakTopics;
        private List<EvaluationSummaryDto> evaluationHistory;

        public StudentPerformanceDtoBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public StudentPerformanceDtoBuilder studentName(String studentName) { this.studentName = studentName; return this; }
        public StudentPerformanceDtoBuilder email(String email) { this.email = email; return this; }
        public StudentPerformanceDtoBuilder classLevel(Integer classLevel) { this.classLevel = classLevel; return this; }
        public StudentPerformanceDtoBuilder averageScore(Double averageScore) { this.averageScore = averageScore; return this; }
        public StudentPerformanceDtoBuilder weakTopicsCount(Integer weakTopicsCount) { this.weakTopicsCount = weakTopicsCount; return this; }
        public StudentPerformanceDtoBuilder weakTopics(List<WeakTopicDto> weakTopics) { this.weakTopics = weakTopics; return this; }
        public StudentPerformanceDtoBuilder evaluationHistory(List<EvaluationSummaryDto> evaluationHistory) { this.evaluationHistory = evaluationHistory; return this; }

        public StudentPerformanceDto build() {
            return new StudentPerformanceDto(studentId, studentName, email, classLevel, averageScore, weakTopicsCount, weakTopics, evaluationHistory);
        }
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public Double getAverageScore() { return averageScore; }
    public void setAverageScore(Double averageScore) { this.averageScore = averageScore; }

    public Integer getWeakTopicsCount() { return weakTopicsCount; }
    public void setWeakTopicsCount(Integer weakTopicsCount) { this.weakTopicsCount = weakTopicsCount; }

    public List<WeakTopicDto> getWeakTopics() { return weakTopics; }
    public void setWeakTopics(List<WeakTopicDto> weakTopics) { this.weakTopics = weakTopics; }

    public List<EvaluationSummaryDto> getEvaluationHistory() { return evaluationHistory; }
    public void setEvaluationHistory(List<EvaluationSummaryDto> evaluationHistory) { this.evaluationHistory = evaluationHistory; }
}
