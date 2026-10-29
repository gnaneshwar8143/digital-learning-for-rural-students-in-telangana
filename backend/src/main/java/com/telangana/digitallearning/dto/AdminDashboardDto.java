package com.telangana.digitallearning.dto;

public class AdminDashboardDto {
    private Long totalStudents;
    private Long totalTeachers;
    private Long totalClasses;
    private Long totalSubjects;
    private Long totalQuizzes;
    private Long totalMaterials;
    private Long totalAttempts;
    private Double averageSystemScoreGain;
    private Double methodologyValidityPercentage;

    public AdminDashboardDto() {}

    public AdminDashboardDto(Long totalStudents, Long totalTeachers, Long totalClasses, Long totalSubjects, Long totalQuizzes, Long totalMaterials, Long totalAttempts, Double averageSystemScoreGain, Double methodologyValidityPercentage) {
        this.totalStudents = totalStudents;
        this.totalTeachers = totalTeachers;
        this.totalClasses = totalClasses;
        this.totalSubjects = totalSubjects;
        this.totalQuizzes = totalQuizzes;
        this.totalMaterials = totalMaterials;
        this.totalAttempts = totalAttempts;
        this.averageSystemScoreGain = averageSystemScoreGain;
        this.methodologyValidityPercentage = methodologyValidityPercentage;
    }

    public static AdminDashboardDtoBuilder builder() { return new AdminDashboardDtoBuilder(); }

    public static class AdminDashboardDtoBuilder {
        private Long totalStudents;
        private Long totalTeachers;
        private Long totalClasses;
        private Long totalSubjects;
        private Long totalQuizzes;
        private Long totalMaterials;
        private Long totalAttempts;
        private Double averageSystemScoreGain;
        private Double methodologyValidityPercentage;

        public AdminDashboardDtoBuilder totalStudents(Long totalStudents) { this.totalStudents = totalStudents; return this; }
        public AdminDashboardDtoBuilder totalTeachers(Long totalTeachers) { this.totalTeachers = totalTeachers; return this; }
        public AdminDashboardDtoBuilder totalClasses(Long totalClasses) { this.totalClasses = totalClasses; return this; }
        public AdminDashboardDtoBuilder totalSubjects(Long totalSubjects) { this.totalSubjects = totalSubjects; return this; }
        public AdminDashboardDtoBuilder totalQuizzes(Long totalQuizzes) { this.totalQuizzes = totalQuizzes; return this; }
        public AdminDashboardDtoBuilder totalMaterials(Long totalMaterials) { this.totalMaterials = totalMaterials; return this; }
        public AdminDashboardDtoBuilder totalAttempts(Long totalAttempts) { this.totalAttempts = totalAttempts; return this; }
        public AdminDashboardDtoBuilder averageSystemScoreGain(Double averageSystemScoreGain) { this.averageSystemScoreGain = averageSystemScoreGain; return this; }
        public AdminDashboardDtoBuilder systemAverageImprovement(Double systemAverageImprovement) { this.averageSystemScoreGain = systemAverageImprovement; return this; }
        public AdminDashboardDtoBuilder methodologyValidityPercentage(Double methodologyValidityPercentage) { this.methodologyValidityPercentage = methodologyValidityPercentage; return this; }

        public AdminDashboardDto build() {
            return new AdminDashboardDto(totalStudents, totalTeachers, totalClasses, totalSubjects, totalQuizzes, totalMaterials, totalAttempts, averageSystemScoreGain, methodologyValidityPercentage);
        }
    }

    public Long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(Long totalStudents) { this.totalStudents = totalStudents; }

    public Long getTotalTeachers() { return totalTeachers; }
    public void setTotalTeachers(Long totalTeachers) { this.totalTeachers = totalTeachers; }

    public Long getTotalClasses() { return totalClasses; }
    public void setTotalClasses(Long totalClasses) { this.totalClasses = totalClasses; }

    public Long getTotalSubjects() { return totalSubjects; }
    public void setTotalSubjects(Long totalSubjects) { this.totalSubjects = totalSubjects; }

    public Long getTotalQuizzes() { return totalQuizzes; }
    public void setTotalQuizzes(Long totalQuizzes) { this.totalQuizzes = totalQuizzes; }

    public Long getTotalMaterials() { return totalMaterials; }
    public void setTotalMaterials(Long totalMaterials) { this.totalMaterials = totalMaterials; }

    public Long getTotalAttempts() { return totalAttempts; }
    public void setTotalAttempts(Long totalAttempts) { this.totalAttempts = totalAttempts; }

    public Double getAverageSystemScoreGain() { return averageSystemScoreGain; }
    public void setAverageSystemScoreGain(Double averageSystemScoreGain) { this.averageSystemScoreGain = averageSystemScoreGain; }

    public Double getMethodologyValidityPercentage() { return methodologyValidityPercentage; }
    public void setMethodologyValidityPercentage(Double methodologyValidityPercentage) { this.methodologyValidityPercentage = methodologyValidityPercentage; }
}
