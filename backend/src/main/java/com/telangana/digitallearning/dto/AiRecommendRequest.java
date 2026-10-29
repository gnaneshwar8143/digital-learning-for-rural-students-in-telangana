package com.telangana.digitallearning.dto;

public class AiRecommendRequest {
    private Long studentId;
    private Long topicId;
    private String topicName;
    private Double score;
    private Integer classLevel;
    private String preferredLanguage;
    private Integer wrongAnswersCount;
    private Integer previousAttemptsCount;

    public AiRecommendRequest() {}

    public AiRecommendRequest(Long studentId, Long topicId, String topicName, Double score, Integer classLevel, String preferredLanguage, Integer wrongAnswersCount, Integer previousAttemptsCount) {
        this.studentId = studentId;
        this.topicId = topicId;
        this.topicName = topicName;
        this.score = score;
        this.classLevel = classLevel;
        this.preferredLanguage = preferredLanguage;
        this.wrongAnswersCount = wrongAnswersCount;
        this.previousAttemptsCount = previousAttemptsCount;
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    public Integer getWrongAnswersCount() { return wrongAnswersCount; }
    public void setWrongAnswersCount(Integer wrongAnswersCount) { this.wrongAnswersCount = wrongAnswersCount; }

    public Integer getPreviousAttemptsCount() { return previousAttemptsCount; }
    public void setPreviousAttemptsCount(Integer previousAttemptsCount) { this.previousAttemptsCount = previousAttemptsCount; }
}
