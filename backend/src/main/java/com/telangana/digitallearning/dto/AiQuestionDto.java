package com.telangana.digitallearning.dto;

import java.util.List;

public class AiQuestionDto {
    private String id;
    private String questionTextEn;
    private String questionTextTe;
    private List<String> optionsEn;
    private List<String> optionsTe;
    private Integer correctOptionIndex;
    private String explanationEn;
    private String explanationTe;
    private Double marks;
    private String difficulty;
    private Boolean approved;

    public AiQuestionDto() {}

    public AiQuestionDto(String id, String questionTextEn, String questionTextTe, List<String> optionsEn, List<String> optionsTe, Integer correctOptionIndex, String explanationEn, String explanationTe, Double marks, String difficulty, Boolean approved) {
        this.id = id;
        this.questionTextEn = questionTextEn;
        this.questionTextTe = questionTextTe;
        this.optionsEn = optionsEn;
        this.optionsTe = optionsTe;
        this.correctOptionIndex = correctOptionIndex;
        this.explanationEn = explanationEn;
        this.explanationTe = explanationTe;
        this.marks = marks;
        this.difficulty = difficulty;
        this.approved = approved;
    }

    public static AiQuestionDtoBuilder builder() { return new AiQuestionDtoBuilder(); }

    public static class AiQuestionDtoBuilder {
        private String id;
        private String questionTextEn;
        private String questionTextTe;
        private List<String> optionsEn;
        private List<String> optionsTe;
        private Integer correctOptionIndex;
        private String explanationEn;
        private String explanationTe;
        private Double marks;
        private String difficulty;
        private Boolean approved;

        public AiQuestionDtoBuilder id(String id) { this.id = id; return this; }
        public AiQuestionDtoBuilder questionTextEn(String questionTextEn) { this.questionTextEn = questionTextEn; return this; }
        public AiQuestionDtoBuilder questionTextTe(String questionTextTe) { this.questionTextTe = questionTextTe; return this; }
        public AiQuestionDtoBuilder optionsEn(List<String> optionsEn) { this.optionsEn = optionsEn; return this; }
        public AiQuestionDtoBuilder optionsTe(List<String> optionsTe) { this.optionsTe = optionsTe; return this; }
        public AiQuestionDtoBuilder correctOptionIndex(Integer correctOptionIndex) { this.correctOptionIndex = correctOptionIndex; return this; }
        public AiQuestionDtoBuilder explanationEn(String explanationEn) { this.explanationEn = explanationEn; return this; }
        public AiQuestionDtoBuilder explanationTe(String explanationTe) { this.explanationTe = explanationTe; return this; }
        public AiQuestionDtoBuilder marks(Double marks) { this.marks = marks; return this; }
        public AiQuestionDtoBuilder difficulty(String difficulty) { this.difficulty = difficulty; return this; }
        public AiQuestionDtoBuilder approved(Boolean approved) { this.approved = approved; return this; }

        public AiQuestionDto build() {
            return new AiQuestionDto(id, questionTextEn, questionTextTe, optionsEn, optionsTe, correctOptionIndex, explanationEn, explanationTe, marks, difficulty, approved);
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getQuestionTextEn() { return questionTextEn; }
    public void setQuestionTextEn(String questionTextEn) { this.questionTextEn = questionTextEn; }

    public String getQuestionTextTe() { return questionTextTe; }
    public void setQuestionTextTe(String questionTextTe) { this.questionTextTe = questionTextTe; }

    public List<String> getOptionsEn() { return optionsEn; }
    public void setOptionsEn(List<String> optionsEn) { this.optionsEn = optionsEn; }

    public List<String> getOptionsTe() { return optionsTe; }
    public void setOptionsTe(List<String> optionsTe) { this.optionsTe = optionsTe; }

    public Integer getCorrectOptionIndex() { return correctOptionIndex; }
    public void setCorrectOptionIndex(Integer correctOptionIndex) { this.correctOptionIndex = correctOptionIndex; }

    public String getExplanationEn() { return explanationEn; }
    public void setExplanationEn(String explanationEn) { this.explanationEn = explanationEn; }

    public String getExplanationTe() { return explanationTe; }
    public void setExplanationTe(String explanationTe) { this.explanationTe = explanationTe; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Boolean getApproved() { return approved; }
    public void setApproved(Boolean approved) { this.approved = approved; }
}
