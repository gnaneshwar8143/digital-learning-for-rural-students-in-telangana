package com.telangana.digitallearning.dto;

import java.util.List;

public class AiGenerateQuizResponse {
    private List<AiQuestionDto> questions;
    private Boolean isFallbackMode;
    private String modeLabel;

    public AiGenerateQuizResponse() {}

    public AiGenerateQuizResponse(List<AiQuestionDto> questions, Boolean isFallbackMode, String modeLabel) {
        this.questions = questions;
        this.isFallbackMode = isFallbackMode;
        this.modeLabel = modeLabel;
    }

    public static AiGenerateQuizResponseBuilder builder() { return new AiGenerateQuizResponseBuilder(); }

    public static class AiGenerateQuizResponseBuilder {
        private List<AiQuestionDto> questions;
        private Boolean isFallbackMode;
        private String modeLabel;

        public AiGenerateQuizResponseBuilder questions(List<AiQuestionDto> questions) { this.questions = questions; return this; }
        public AiGenerateQuizResponseBuilder isFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; return this; }
        public AiGenerateQuizResponseBuilder modeLabel(String modeLabel) { this.modeLabel = modeLabel; return this; }

        public AiGenerateQuizResponse build() {
            return new AiGenerateQuizResponse(questions, isFallbackMode, modeLabel);
        }
    }

    public List<AiQuestionDto> getQuestions() { return questions; }
    public void setQuestions(List<AiQuestionDto> questions) { this.questions = questions; }

    public Boolean getIsFallbackMode() { return isFallbackMode; }
    public void setIsFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; }

    public String getModeLabel() { return modeLabel; }
    public void setModeLabel(String modeLabel) { this.modeLabel = modeLabel; }
}
