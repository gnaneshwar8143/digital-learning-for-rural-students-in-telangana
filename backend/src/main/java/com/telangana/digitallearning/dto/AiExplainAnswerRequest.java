package com.telangana.digitallearning.dto;

public class AiExplainAnswerRequest {
    private String questionText;
    private String studentAnswer;
    private String correctAnswer;
    private String topicName;
    private Integer classLevel;
    private String preferredLanguage;

    public AiExplainAnswerRequest() {}

    public AiExplainAnswerRequest(String questionText, String studentAnswer, String correctAnswer, String topicName, Integer classLevel, String preferredLanguage) {
        this.questionText = questionText;
        this.studentAnswer = studentAnswer;
        this.correctAnswer = correctAnswer;
        this.topicName = topicName;
        this.classLevel = classLevel;
        this.preferredLanguage = preferredLanguage;
    }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getStudentAnswer() { return studentAnswer; }
    public void setStudentAnswer(String studentAnswer) { this.studentAnswer = studentAnswer; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
}
