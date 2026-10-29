package com.telangana.digitallearning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_interactions")
public class AiInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String interactionType;
    private Long topicId;

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String response;

    private String language;
    private Boolean isFallbackMode;

    private LocalDateTime createdAt;

    public AiInteraction() {}

    public AiInteraction(Long id, Long studentId, String interactionType, Long topicId, String question, String response, String language, Boolean isFallbackMode, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.interactionType = interactionType;
        this.topicId = topicId;
        this.question = question;
        this.response = response;
        this.language = language;
        this.isFallbackMode = isFallbackMode;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public static AiInteractionBuilder builder() {
        return new AiInteractionBuilder();
    }

    public static class AiInteractionBuilder {
        private Long id;
        private Long studentId;
        private String interactionType;
        private Long topicId;
        private String question;
        private String response;
        private String language;
        private Boolean isFallbackMode;
        private LocalDateTime createdAt;

        public AiInteractionBuilder id(Long id) { this.id = id; return this; }
        public AiInteractionBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public AiInteractionBuilder interactionType(String interactionType) { this.interactionType = interactionType; return this; }
        public AiInteractionBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public AiInteractionBuilder question(String question) { this.question = question; return this; }
        public AiInteractionBuilder response(String response) { this.response = response; return this; }
        public AiInteractionBuilder language(String language) { this.language = language; return this; }
        public AiInteractionBuilder isFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; return this; }
        public AiInteractionBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public AiInteraction build() {
            return new AiInteraction(id, studentId, interactionType, topicId, question, response, language, isFallbackMode, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getInteractionType() { return interactionType; }
    public void setInteractionType(String interactionType) { this.interactionType = interactionType; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public Boolean getIsFallbackMode() { return isFallbackMode; }
    public void setIsFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
