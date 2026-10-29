package com.telangana.digitallearning.dto;

public class AnswerSubmissionDto {
    private Long questionId;
    private Long selectedOptionId;

    public AnswerSubmissionDto() {}

    public AnswerSubmissionDto(Long questionId, Long selectedOptionId) {
        this.questionId = questionId;
        this.selectedOptionId = selectedOptionId;
    }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public Long getSelectedOptionId() { return selectedOptionId; }
    public void setSelectedOptionId(Long selectedOptionId) { this.selectedOptionId = selectedOptionId; }
}
