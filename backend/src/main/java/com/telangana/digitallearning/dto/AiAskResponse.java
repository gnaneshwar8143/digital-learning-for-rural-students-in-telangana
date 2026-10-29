package com.telangana.digitallearning.dto;

public class AiAskResponse {
    private String answer;
    private String language;
    private String suggestedTopic;
    private Boolean isFallbackMode;
    private String modeLabel;

    public AiAskResponse() {}

    public AiAskResponse(String answer, String language, String suggestedTopic, Boolean isFallbackMode, String modeLabel) {
        this.answer = answer;
        this.language = language;
        this.suggestedTopic = suggestedTopic;
        this.isFallbackMode = isFallbackMode;
        this.modeLabel = modeLabel;
    }

    public static AiAskResponseBuilder builder() { return new AiAskResponseBuilder(); }

    public static class AiAskResponseBuilder {
        private String answer;
        private String language;
        private String suggestedTopic;
        private Boolean isFallbackMode;
        private String modeLabel;

        public AiAskResponseBuilder answer(String answer) { this.answer = answer; return this; }
        public AiAskResponseBuilder language(String language) { this.language = language; return this; }
        public AiAskResponseBuilder suggestedTopic(String suggestedTopic) { this.suggestedTopic = suggestedTopic; return this; }
        public AiAskResponseBuilder isFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; return this; }
        public AiAskResponseBuilder modeLabel(String modeLabel) { this.modeLabel = modeLabel; return this; }

        public AiAskResponse build() {
            return new AiAskResponse(answer, language, suggestedTopic, isFallbackMode, modeLabel);
        }
    }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getSuggestedTopic() { return suggestedTopic; }
    public void setSuggestedTopic(String suggestedTopic) { this.suggestedTopic = suggestedTopic; }

    public Boolean getIsFallbackMode() { return isFallbackMode; }
    public void setIsFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; }

    public String getModeLabel() { return modeLabel; }
    public void setModeLabel(String modeLabel) { this.modeLabel = modeLabel; }
}
