package com.telangana.digitallearning.dto;

import java.util.List;

public class StudentDashboardDto {
    private Long studentId;
    private String studentName;
    private Integer classLevel;
    private Integer totalSubjects;
    private Double averageScore;
    private Double overallImprovement;
    private Double overallProgressPercentage;
    private Integer totalCompletedQuizzes;
    private List<WeakTopicDto> weakTopics;
    private List<RecommendationDto> activeRecommendations;
    private List<QuizResultSummaryDto> recentQuizAttempts;

    public StudentDashboardDto() {}

    public StudentDashboardDto(Long studentId, String studentName, Integer classLevel, Integer totalSubjects, Double averageScore, Double overallImprovement, Double overallProgressPercentage, Integer totalCompletedQuizzes, List<WeakTopicDto> weakTopics, List<RecommendationDto> activeRecommendations, List<QuizResultSummaryDto> recentQuizAttempts) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.classLevel = classLevel;
        this.totalSubjects = totalSubjects;
        this.averageScore = averageScore;
        this.overallImprovement = overallImprovement;
        this.overallProgressPercentage = overallProgressPercentage;
        this.totalCompletedQuizzes = totalCompletedQuizzes;
        this.weakTopics = weakTopics;
        this.activeRecommendations = activeRecommendations;
        this.recentQuizAttempts = recentQuizAttempts;
    }

    public static StudentDashboardDtoBuilder builder() { return new StudentDashboardDtoBuilder(); }

    public static class StudentDashboardDtoBuilder {
        private Long studentId;
        private String studentName;
        private Integer classLevel;
        private Integer totalSubjects;
        private Double averageScore;
        private Double overallImprovement;
        private Double overallProgressPercentage;
        private Integer totalCompletedQuizzes;
        private List<WeakTopicDto> weakTopics;
        private List<RecommendationDto> activeRecommendations;
        private List<QuizResultSummaryDto> recentQuizAttempts;

        public StudentDashboardDtoBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public StudentDashboardDtoBuilder studentName(String studentName) { this.studentName = studentName; return this; }
        public StudentDashboardDtoBuilder classLevel(Integer classLevel) { this.classLevel = classLevel; return this; }
        public StudentDashboardDtoBuilder totalSubjects(Integer totalSubjects) { this.totalSubjects = totalSubjects; return this; }
        public StudentDashboardDtoBuilder averageScore(Double averageScore) { this.averageScore = averageScore; return this; }
        public StudentDashboardDtoBuilder averageQuizScore(Double averageQuizScore) { this.averageScore = averageQuizScore; return this; }
        public StudentDashboardDtoBuilder overallImprovement(Double overallImprovement) { this.overallImprovement = overallImprovement; return this; }
        public StudentDashboardDtoBuilder overallImprovementPercentage(Double overallImprovementPercentage) { this.overallImprovement = overallImprovementPercentage; return this; }
        public StudentDashboardDtoBuilder overallProgressPercentage(Double overallProgressPercentage) { this.overallProgressPercentage = overallProgressPercentage; return this; }
        public StudentDashboardDtoBuilder totalCompletedQuizzes(Integer totalCompletedQuizzes) { this.totalCompletedQuizzes = totalCompletedQuizzes; return this; }
        public StudentDashboardDtoBuilder completedLessons(Integer completedLessons) { this.totalCompletedQuizzes = completedLessons; return this; }
        public StudentDashboardDtoBuilder weakTopics(List<WeakTopicDto> weakTopics) { this.weakTopics = weakTopics; return this; }
        public StudentDashboardDtoBuilder activeRecommendations(List<RecommendationDto> activeRecommendations) { this.activeRecommendations = activeRecommendations; return this; }
        public StudentDashboardDtoBuilder recentQuizAttempts(List<QuizResultSummaryDto> recentQuizAttempts) { this.recentQuizAttempts = recentQuizAttempts; return this; }

        public StudentDashboardDto build() {
            return new StudentDashboardDto(studentId, studentName, classLevel, totalSubjects, averageScore, overallImprovement, overallProgressPercentage, totalCompletedQuizzes, weakTopics, activeRecommendations, recentQuizAttempts);
        }
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public Integer getTotalSubjects() { return totalSubjects; }
    public void setTotalSubjects(Integer totalSubjects) { this.totalSubjects = totalSubjects; }

    public Double getAverageScore() { return averageScore; }
    public void setAverageScore(Double averageScore) { this.averageScore = averageScore; }

    public Double getOverallImprovement() { return overallImprovement; }
    public void setOverallImprovement(Double overallImprovement) { this.overallImprovement = overallImprovement; }

    public Double getOverallProgressPercentage() { return overallProgressPercentage; }
    public void setOverallProgressPercentage(Double overallProgressPercentage) { this.overallProgressPercentage = overallProgressPercentage; }

    public Integer getTotalCompletedQuizzes() { return totalCompletedQuizzes; }
    public void setTotalCompletedQuizzes(Integer totalCompletedQuizzes) { this.totalCompletedQuizzes = totalCompletedQuizzes; }

    public List<WeakTopicDto> getWeakTopics() { return weakTopics; }
    public void setWeakTopics(List<WeakTopicDto> weakTopics) { this.weakTopics = weakTopics; }

    public List<RecommendationDto> getActiveRecommendations() { return activeRecommendations; }
    public void setActiveRecommendations(List<RecommendationDto> activeRecommendations) { this.activeRecommendations = activeRecommendations; }

    public List<QuizResultSummaryDto> getRecentQuizAttempts() { return recentQuizAttempts; }
    public void setRecentQuizAttempts(List<QuizResultSummaryDto> recentQuizAttempts) { this.recentQuizAttempts = recentQuizAttempts; }
}
