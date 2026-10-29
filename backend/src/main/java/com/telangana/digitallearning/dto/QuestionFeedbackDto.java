package com.telangana.digitallearning.dto;

public class QuestionFeedbackDto {
    private Long questionId;
    private String questionTextEn;
    private String questionTextTe;
    private Long selectedOptionId;
    private Long correctOptionId;
    private Boolean isCorrect;
    private String explanationEn;
    private String explanationTe;

    public QuestionFeedbackDto() {}

    public QuestionFeedbackDto(Long questionId, String questionTextEn, String questionTextTe, Long selectedOptionId, Long correctOptionId, Boolean isCorrect, String explanationEn, String explanationTe) {
        this.questionId = questionId;
        this.questionTextEn = questionTextEn;
        this.questionTextTe = questionTextTe;
        this.selectedOptionId = selectedOptionId;
        this.correctOptionId = correctOptionId;
        this.isCorrect = isCorrect;
        this.explanationEn = explanationEn;
        this.explanationTe = explanationTe;
    }

    public static QuestionFeedbackDtoBuilder builder() { return new QuestionFeedbackDtoBuilder(); }

    public static class QuestionFeedbackDtoBuilder {
        private Long questionId;
        private String questionTextEn;
        private String questionTextTe;
        private Long selectedOptionId;
        private Long correctOptionId;
        private Boolean isCorrect;
        private String explanationEn;
        private String explanationTe;

        public QuestionFeedbackDtoBuilder questionId(Long questionId) { this.questionId = questionId; return this; }
        public QuestionFeedbackDtoBuilder questionTextEn(String questionTextEn) { this.questionTextEn = questionTextEn; return this; }
        public QuestionFeedbackDtoBuilder questionTextTe(String questionTextTe) { this.questionTextTe = questionTextTe; return this; }
        public QuestionFeedbackDtoBuilder selectedOptionId(Long selectedOptionId) { this.selectedOptionId = selectedOptionId; return this; }
        public QuestionFeedbackDtoBuilder correctOptionId(Long correctOptionId) { this.correctOptionId = correctOptionId; return this; }
        public QuestionFeedbackDtoBuilder isCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; return this; }
        public QuestionFeedbackDtoBuilder explanationEn(String explanationEn) { this.explanationEn = explanationEn; return this; }
        public QuestionFeedbackDtoBuilder explanationTe(String explanationTe) { this.explanationTe = explanationTe; return this; }

        public QuestionFeedbackDto build() {
            return new QuestionFeedbackDto(questionId, questionTextEn, questionTextTe, selectedOptionId, correctOptionId, isCorrect, explanationEn, explanationTe);
        }
    }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public String getQuestionTextEn() { return questionTextEn; }
    public void setQuestionTextEn(String questionTextEn) { this.questionTextEn = questionTextEn; }

    public String getQuestionTextTe() { return questionTextTe; }
    public void setQuestionTextTe(String questionTextTe) { this.questionTextTe = questionTextTe; }

    public Long getSelectedOptionId() { return selectedOptionId; }
    public void setSelectedOptionId(Long selectedOptionId) { this.selectedOptionId = selectedOptionId; }

    public Long getCorrectOptionId() { return correctOptionId; }
    public void setCorrectOptionId(Long correctOptionId) { this.correctOptionId = correctOptionId; }

    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }

    public String getExplanationEn() { return explanationEn; }
    public void setExplanationEn(String explanationEn) { this.explanationEn = explanationEn; }

    public String getExplanationTe() { return explanationTe; }
    public void setExplanationTe(String explanationTe) { this.explanationTe = explanationTe; }
}
