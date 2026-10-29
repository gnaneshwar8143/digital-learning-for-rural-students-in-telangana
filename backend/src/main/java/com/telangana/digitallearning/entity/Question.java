package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quizId;

    @Column(nullable = false)
    private Long topicId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String questionTextEn;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String questionTextTe;

    @Column(columnDefinition = "TEXT")
    private String explanationEn;

    @Column(columnDefinition = "TEXT")
    private String explanationTe;

    private Double marks;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuestionOption> options = new ArrayList<>();

    public Question() {}

    public Question(Long id, Long quizId, Long topicId, String questionTextEn, String questionTextTe, String explanationEn, String explanationTe, Double marks, List<QuestionOption> options) {
        this.id = id;
        this.quizId = quizId;
        this.topicId = topicId;
        this.questionTextEn = questionTextEn;
        this.questionTextTe = questionTextTe;
        this.explanationEn = explanationEn;
        this.explanationTe = explanationTe;
        this.marks = marks;
        if (options != null) this.options = options;
    }

    public static QuestionBuilder builder() { return new QuestionBuilder(); }

    public static class QuestionBuilder {
        private Long id;
        private Long quizId;
        private Long topicId;
        private String questionTextEn;
        private String questionTextTe;
        private String explanationEn;
        private String explanationTe;
        private Double marks;
        private List<QuestionOption> options = new ArrayList<>();

        public QuestionBuilder id(Long id) { this.id = id; return this; }
        public QuestionBuilder quizId(Long quizId) { this.quizId = quizId; return this; }
        public QuestionBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public QuestionBuilder questionTextEn(String questionTextEn) { this.questionTextEn = questionTextEn; return this; }
        public QuestionBuilder questionTextTe(String questionTextTe) { this.questionTextTe = questionTextTe; return this; }
        public QuestionBuilder explanationEn(String explanationEn) { this.explanationEn = explanationEn; return this; }
        public QuestionBuilder explanationTe(String explanationTe) { this.explanationTe = explanationTe; return this; }
        public QuestionBuilder marks(Double marks) { this.marks = marks; return this; }
        public QuestionBuilder options(List<QuestionOption> options) { this.options = options; return this; }

        public Question build() {
            return new Question(id, quizId, topicId, questionTextEn, questionTextTe, explanationEn, explanationTe, marks, options);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getQuestionTextEn() { return questionTextEn; }
    public void setQuestionTextEn(String questionTextEn) { this.questionTextEn = questionTextEn; }

    public String getQuestionTextTe() { return questionTextTe; }
    public void setQuestionTextTe(String questionTextTe) { this.questionTextTe = questionTextTe; }

    public String getExplanationEn() { return explanationEn; }
    public void setExplanationEn(String explanationEn) { this.explanationEn = explanationEn; }

    public String getExplanationTe() { return explanationTe; }
    public void setExplanationTe(String explanationTe) { this.explanationTe = explanationTe; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }

    public List<QuestionOption> getOptions() { return options; }
    public void setOptions(List<QuestionOption> options) { this.options = options; }
}
