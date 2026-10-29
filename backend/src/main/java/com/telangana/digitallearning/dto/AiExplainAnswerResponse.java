package com.telangana.digitallearning.dto;

public class AiExplainAnswerResponse {
    private String whyWrong;
    private String correctConcept;
    private String stepByStepSolution;
    private String similarExample;
    private Boolean isFallbackMode;
    private String modeLabel;

    public AiExplainAnswerResponse() {}

    public AiExplainAnswerResponse(String whyWrong, String correctConcept, String stepByStepSolution, String similarExample, Boolean isFallbackMode, String modeLabel) {
        this.whyWrong = whyWrong;
        this.correctConcept = correctConcept;
        this.stepByStepSolution = stepByStepSolution;
        this.similarExample = similarExample;
        this.isFallbackMode = isFallbackMode;
        this.modeLabel = modeLabel;
    }

    public static AiExplainAnswerResponseBuilder builder() { return new AiExplainAnswerResponseBuilder(); }

    public static class AiExplainAnswerResponseBuilder {
        private String whyWrong;
        private String correctConcept;
        private String stepByStepSolution;
        private String similarExample;
        private Boolean isFallbackMode;
        private String modeLabel;

        public AiExplainAnswerResponseBuilder whyWrong(String whyWrong) { this.whyWrong = whyWrong; return this; }
        public AiExplainAnswerResponseBuilder correctConcept(String correctConcept) { this.correctConcept = correctConcept; return this; }
        public AiExplainAnswerResponseBuilder stepByStepSolution(String stepByStepSolution) { this.stepByStepSolution = stepByStepSolution; return this; }
        public AiExplainAnswerResponseBuilder similarExample(String similarExample) { this.similarExample = similarExample; return this; }
        public AiExplainAnswerResponseBuilder isFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; return this; }
        public AiExplainAnswerResponseBuilder modeLabel(String modeLabel) { this.modeLabel = modeLabel; return this; }

        public AiExplainAnswerResponse build() {
            return new AiExplainAnswerResponse(whyWrong, correctConcept, stepByStepSolution, similarExample, isFallbackMode, modeLabel);
        }
    }

    public String getWhyWrong() { return whyWrong; }
    public void setWhyWrong(String whyWrong) { this.whyWrong = whyWrong; }

    public String getCorrectConcept() { return correctConcept; }
    public void setCorrectConcept(String correctConcept) { this.correctConcept = correctConcept; }

    public String getStepByStepSolution() { return stepByStepSolution; }
    public void setStepByStepSolution(String stepByStepSolution) { this.stepByStepSolution = stepByStepSolution; }

    public String getSimilarExample() { return similarExample; }
    public void setSimilarExample(String similarExample) { this.similarExample = similarExample; }

    public Boolean getIsFallbackMode() { return isFallbackMode; }
    public void setIsFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; }

    public String getModeLabel() { return modeLabel; }
    public void setModeLabel(String modeLabel) { this.modeLabel = modeLabel; }
}
