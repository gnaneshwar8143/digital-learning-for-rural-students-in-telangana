package com.telangana.digitallearning.dto;

import java.util.List;

public class TeacherDashboardDto {
    private Long totalStudents;
    private Double averageClassScore;
    private Long studentsNeedingHelp;
    private Long totalQuizzes;
    private Long totalMaterials;
    private List<WeakTopicSummaryDto> mostCommonWeakTopics;
    private List<StudentPerformanceDto> recentStudentActivities;

    public TeacherDashboardDto() {}

    public TeacherDashboardDto(Long totalStudents, Double averageClassScore, Long studentsNeedingHelp, Long totalQuizzes, Long totalMaterials, List<WeakTopicSummaryDto> mostCommonWeakTopics, List<StudentPerformanceDto> recentStudentActivities) {
        this.totalStudents = totalStudents;
        this.averageClassScore = averageClassScore;
        this.studentsNeedingHelp = studentsNeedingHelp;
        this.totalQuizzes = totalQuizzes;
        this.totalMaterials = totalMaterials;
        this.mostCommonWeakTopics = mostCommonWeakTopics;
        this.recentStudentActivities = recentStudentActivities;
    }

    public static TeacherDashboardDtoBuilder builder() { return new TeacherDashboardDtoBuilder(); }

    public static class TeacherDashboardDtoBuilder {
        private Long totalStudents;
        private Double averageClassScore;
        private Long studentsNeedingHelp;
        private Long totalQuizzes;
        private Long totalMaterials;
        private List<WeakTopicSummaryDto> mostCommonWeakTopics;
        private List<StudentPerformanceDto> recentStudentActivities;

        public TeacherDashboardDtoBuilder totalStudents(Long totalStudents) { this.totalStudents = totalStudents; return this; }
        public TeacherDashboardDtoBuilder averageClassScore(Double averageClassScore) { this.averageClassScore = averageClassScore; return this; }
        public TeacherDashboardDtoBuilder studentsNeedingHelp(Long studentsNeedingHelp) { this.studentsNeedingHelp = studentsNeedingHelp; return this; }
        public TeacherDashboardDtoBuilder totalQuizzes(Long totalQuizzes) { this.totalQuizzes = totalQuizzes; return this; }
        public TeacherDashboardDtoBuilder totalMaterials(Long totalMaterials) { this.totalMaterials = totalMaterials; return this; }
        public TeacherDashboardDtoBuilder mostCommonWeakTopics(List<WeakTopicSummaryDto> mostCommonWeakTopics) { this.mostCommonWeakTopics = mostCommonWeakTopics; return this; }
        public TeacherDashboardDtoBuilder recentStudentActivities(List<StudentPerformanceDto> recentStudentActivities) { this.recentStudentActivities = recentStudentActivities; return this; }

        public TeacherDashboardDto build() {
            return new TeacherDashboardDto(totalStudents, averageClassScore, studentsNeedingHelp, totalQuizzes, totalMaterials, mostCommonWeakTopics, recentStudentActivities);
        }
    }

    public Long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(Long totalStudents) { this.totalStudents = totalStudents; }

    public Double getAverageClassScore() { return averageClassScore; }
    public void setAverageClassScore(Double averageClassScore) { this.averageClassScore = averageClassScore; }

    public Long getStudentsNeedingHelp() { return studentsNeedingHelp; }
    public void setStudentsNeedingHelp(Long studentsNeedingHelp) { this.studentsNeedingHelp = studentsNeedingHelp; }

    public Long getTotalQuizzes() { return totalQuizzes; }
    public void setTotalQuizzes(Long totalQuizzes) { this.totalQuizzes = totalQuizzes; }

    public Long getTotalMaterials() { return totalMaterials; }
    public void setTotalMaterials(Long totalMaterials) { this.totalMaterials = totalMaterials; }

    public List<WeakTopicSummaryDto> getMostCommonWeakTopics() { return mostCommonWeakTopics; }
    public void setMostCommonWeakTopics(List<WeakTopicSummaryDto> mostCommonWeakTopics) { this.mostCommonWeakTopics = mostCommonWeakTopics; }

    public List<StudentPerformanceDto> getRecentStudentActivities() { return recentStudentActivities; }
    public void setRecentStudentActivities(List<StudentPerformanceDto> recentStudentActivities) { this.recentStudentActivities = recentStudentActivities; }
}
