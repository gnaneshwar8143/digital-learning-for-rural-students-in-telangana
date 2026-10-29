package com.telangana.digitallearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_answers")
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long attemptId;

    @Column(nullable = false)
    private Long questionId;

    private Long selectedOptionId;
    private Boolean isCorrect;
    private Double marksEarned;

    public StudentAnswer() {}

    public StudentAnswer(Long id, Long attemptId, Long questionId, Long selectedOptionId, Boolean isCorrect, Double marksEarned) {
        this.id = id;
        this.attemptId = attemptId;
        this.questionId = questionId;
        this.selectedOptionId = selectedOptionId;
        this.isCorrect = isCorrect;
        this.marksEarned = marksEarned;
    }

    public static StudentAnswerBuilder builder() { return new StudentAnswerBuilder(); }

    public static class StudentAnswerBuilder {
        private Long id;
        private Long attemptId;
        private Long questionId;
        private Long selectedOptionId;
        private Boolean isCorrect;
        private Double marksEarned;

        public StudentAnswerBuilder id(Long id) { this.id = id; return this; }
        public StudentAnswerBuilder attemptId(Long attemptId) { this.attemptId = attemptId; return this; }
        public StudentAnswerBuilder questionId(Long questionId) { this.questionId = questionId; return this; }
        public StudentAnswerBuilder selectedOptionId(Long selectedOptionId) { this.selectedOptionId = selectedOptionId; return this; }
        public StudentAnswerBuilder isCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; return this; }
        public StudentAnswerBuilder marksEarned(Double marksEarned) { this.marksEarned = marksEarned; return this; }

        public StudentAnswer build() {
            return new StudentAnswer(id, attemptId, questionId, selectedOptionId, isCorrect, marksEarned);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAttemptId() { return attemptId; }
    public void setAttemptId(Long attemptId) { this.attemptId = attemptId; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public Long getSelectedOptionId() { return selectedOptionId; }
    public void setSelectedOptionId(Long selectedOptionId) { this.selectedOptionId = selectedOptionId; }

    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }

    public Double getMarksEarned() { return marksEarned; }
    public void setMarksEarned(Double marksEarned) { this.marksEarned = marksEarned; }
}
