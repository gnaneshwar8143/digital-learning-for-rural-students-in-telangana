package com.telangana.digitallearning.dto;

import java.util.List;

public class QuizSubmissionDto {
    private Long quizId;
    private String attemptType;
    private List<AnswerSubmissionDto> answers;

    public QuizSubmissionDto() {}

    public QuizSubmissionDto(Long quizId, String attemptType, List<AnswerSubmissionDto> answers) {
        this.quizId = quizId;
        this.attemptType = attemptType;
        this.answers = answers;
    }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public String getAttemptType() { return attemptType; }
    public void setAttemptType(String attemptType) { this.attemptType = attemptType; }

    public List<AnswerSubmissionDto> getAnswers() { return answers; }
    public void setAnswers(List<AnswerSubmissionDto> answers) { this.answers = answers; }
}
