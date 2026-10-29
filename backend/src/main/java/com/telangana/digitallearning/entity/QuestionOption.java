package com.telangana.digitallearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question_options")
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private String optionTextEn;

    @Column(nullable = false)
    private String optionTextTe;

    @Column(nullable = false)
    private Boolean isCorrect;

    public QuestionOption() {}

    public QuestionOption(Long id, Long questionId, String optionTextEn, String optionTextTe, Boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.optionTextEn = optionTextEn;
        this.optionTextTe = optionTextTe;
        this.isCorrect = isCorrect;
    }

    public static QuestionOptionBuilder builder() { return new QuestionOptionBuilder(); }

    public static class QuestionOptionBuilder {
        private Long id;
        private Long questionId;
        private String optionTextEn;
        private String optionTextTe;
        private Boolean isCorrect;

        public QuestionOptionBuilder id(Long id) { this.id = id; return this; }
        public QuestionOptionBuilder questionId(Long questionId) { this.questionId = questionId; return this; }
        public QuestionOptionBuilder optionTextEn(String optionTextEn) { this.optionTextEn = optionTextEn; return this; }
        public QuestionOptionBuilder optionTextTe(String optionTextTe) { this.optionTextTe = optionTextTe; return this; }
        public QuestionOptionBuilder isCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; return this; }

        public QuestionOption build() {
            return new QuestionOption(id, questionId, optionTextEn, optionTextTe, isCorrect);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public String getOptionTextEn() { return optionTextEn; }
    public void setOptionTextEn(String optionTextEn) { this.optionTextEn = optionTextEn; }

    public String getOptionTextTe() { return optionTextTe; }
    public void setOptionTextTe(String optionTextTe) { this.optionTextTe = optionTextTe; }

    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }
}
