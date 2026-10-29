package com.telangana.digitallearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeakTopicDto {
    private Long topicId;
    private String topicNameEn;
    private String topicNameTe;
    private String subjectNameEn;
    private String subjectNameTe;
    private Double scorePercentage;
    private String status;
    private Integer attemptsCount;
    private LocalDateTime lastAssessedAt;

    public static WeakTopicDtoBuilder builder() {
        return new WeakTopicDtoBuilder();
    }

    public static class WeakTopicDtoBuilder {
        private Long topicId;
        private String topicNameEn;
        private String topicNameTe;
        private String subjectNameEn;
        private String subjectNameTe;
        private Double scorePercentage;
        private String status;
        private Integer attemptsCount;
        private LocalDateTime lastAssessedAt;

        public WeakTopicDtoBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public WeakTopicDtoBuilder topicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; return this; }
        public WeakTopicDtoBuilder topicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; return this; }
        public WeakTopicDtoBuilder subjectNameEn(String subjectNameEn) { this.subjectNameEn = subjectNameEn; return this; }
        public WeakTopicDtoBuilder subjectNameTe(String subjectNameTe) { this.subjectNameTe = subjectNameTe; return this; }
        public WeakTopicDtoBuilder scorePercentage(Double scorePercentage) { this.scorePercentage = scorePercentage; return this; }
        public WeakTopicDtoBuilder status(String status) { this.status = status; return this; }
        public WeakTopicDtoBuilder attemptsCount(Integer attemptsCount) { this.attemptsCount = attemptsCount; return this; }
        public WeakTopicDtoBuilder lastAssessedAt(LocalDateTime lastAssessedAt) { this.lastAssessedAt = lastAssessedAt; return this; }

        public WeakTopicDto build() {
            return new WeakTopicDto(topicId, topicNameEn, topicNameTe, subjectNameEn, subjectNameTe, scorePercentage, status, attemptsCount, lastAssessedAt);
        }
    }
}
