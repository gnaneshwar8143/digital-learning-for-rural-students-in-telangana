package com.telangana.digitallearning.dto;

import java.util.List;

public class AiRecommendResponse {
    private String personalizedRecommendationMessage;
    private List<String> focusAreas;
    private Boolean isFallbackMode;
    private String modeLabel;

    public AiRecommendResponse() {}

    public AiRecommendResponse(String personalizedRecommendationMessage, List<String> focusAreas, Boolean isFallbackMode, String modeLabel) {
        this.personalizedRecommendationMessage = personalizedRecommendationMessage;
        this.focusAreas = focusAreas;
        this.isFallbackMode = isFallbackMode;
        this.modeLabel = modeLabel;
    }

    public static AiRecommendResponseBuilder builder() { return new AiRecommendResponseBuilder(); }

    public static class AiRecommendResponseBuilder {
        private String personalizedRecommendationMessage;
        private List<String> focusAreas;
        private Boolean isFallbackMode;
        private String modeLabel;

        public AiRecommendResponseBuilder personalizedRecommendationMessage(String personalizedRecommendationMessage) { this.personalizedRecommendationMessage = personalizedRecommendationMessage; return this; }
        public AiRecommendResponseBuilder focusAreas(List<String> focusAreas) { this.focusAreas = focusAreas; return this; }
        public AiRecommendResponseBuilder isFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; return this; }
        public AiRecommendResponseBuilder modeLabel(String modeLabel) { this.modeLabel = modeLabel; return this; }

        public AiRecommendResponse build() {
            return new AiRecommendResponse(personalizedRecommendationMessage, focusAreas, isFallbackMode, modeLabel);
        }
    }

    public String getPersonalizedRecommendationMessage() { return personalizedRecommendationMessage; }
    public void setPersonalizedRecommendationMessage(String personalizedRecommendationMessage) { this.personalizedRecommendationMessage = personalizedRecommendationMessage; }

    public List<String> getFocusAreas() { return focusAreas; }
    public void setFocusAreas(List<String> focusAreas) { this.focusAreas = focusAreas; }

    public Boolean getIsFallbackMode() { return isFallbackMode; }
    public void setIsFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; }

    public String getModeLabel() { return modeLabel; }
    public void setModeLabel(String modeLabel) { this.modeLabel = modeLabel; }
}
