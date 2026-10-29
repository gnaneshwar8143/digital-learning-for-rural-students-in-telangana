package com.telangana.digitallearning.dto;

public class AiAskRequest {
    private String question;
    private Integer classLevel;
    private String subjectName;
    private String topicName;
    private String preferredLanguage;
    private Double previousScore;

    public AiAskRequest() {}

    public AiAskRequest(String question, Integer classLevel, String subjectName, String topicName, String preferredLanguage, Double previousScore) {
        this.question = question;
        this.classLevel = classLevel;
        this.subjectName = subjectName;
        this.topicName = topicName;
        this.preferredLanguage = preferredLanguage;
        this.previousScore = previousScore;
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    public Double getPreviousScore() { return previousScore; }
    public void setPreviousScore(Double previousScore) { this.previousScore = previousScore; }
}
