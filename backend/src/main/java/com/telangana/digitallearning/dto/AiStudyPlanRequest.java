package com.telangana.digitallearning.dto;

public class AiStudyPlanRequest {
    private Long studentId;
    private Integer classLevel;
    private String preferredLanguage;

    public AiStudyPlanRequest() {}

    public AiStudyPlanRequest(Long studentId, Integer classLevel, String preferredLanguage) {
        this.studentId = studentId;
        this.classLevel = classLevel;
        this.preferredLanguage = preferredLanguage;
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
}
