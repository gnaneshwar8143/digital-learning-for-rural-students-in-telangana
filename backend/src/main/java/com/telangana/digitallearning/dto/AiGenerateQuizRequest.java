package com.telangana.digitallearning.dto;

public class AiGenerateQuizRequest {
    private Integer classLevel;
    private String subjectName;
    private String chapterName;
    private String topicName;
    private String difficulty;
    private Integer numberOfQuestions;
    private String preferredLanguage;

    public AiGenerateQuizRequest() {}

    public AiGenerateQuizRequest(Integer classLevel, String subjectName, String chapterName, String topicName, String difficulty, Integer numberOfQuestions, String preferredLanguage) {
        this.classLevel = classLevel;
        this.subjectName = subjectName;
        this.chapterName = chapterName;
        this.topicName = topicName;
        this.difficulty = difficulty;
        this.numberOfQuestions = numberOfQuestions;
        this.preferredLanguage = preferredLanguage;
    }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getChapterName() { return chapterName; }
    public void setChapterName(String chapterName) { this.chapterName = chapterName; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Integer getNumberOfQuestions() { return numberOfQuestions; }
    public void setNumberOfQuestions(Integer numberOfQuestions) { this.numberOfQuestions = numberOfQuestions; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
}
