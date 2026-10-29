package com.telangana.digitallearning.dto;

public class WeakTopicSummaryDto {
    private Long topicId;
    private String topicNameEn;
    private String topicNameTe;
    private String subjectNameEn;
    private Long studentCount;

    public WeakTopicSummaryDto() {}

    public WeakTopicSummaryDto(Long topicId, String topicNameEn, String topicNameTe, String subjectNameEn, Long studentCount) {
        this.topicId = topicId;
        this.topicNameEn = topicNameEn;
        this.topicNameTe = topicNameTe;
        this.subjectNameEn = subjectNameEn;
        this.studentCount = studentCount;
    }

    public static WeakTopicSummaryDtoBuilder builder() { return new WeakTopicSummaryDtoBuilder(); }

    public static class WeakTopicSummaryDtoBuilder {
        private Long topicId;
        private String topicNameEn;
        private String topicNameTe;
        private String subjectNameEn;
        private Long studentCount;

        public WeakTopicSummaryDtoBuilder topicId(Long topicId) { this.topicId = topicId; return this; }
        public WeakTopicSummaryDtoBuilder topicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; return this; }
        public WeakTopicSummaryDtoBuilder topicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; return this; }
        public WeakTopicSummaryDtoBuilder subjectNameEn(String subjectNameEn) { this.subjectNameEn = subjectNameEn; return this; }
        public WeakTopicSummaryDtoBuilder studentCount(Long studentCount) { this.studentCount = studentCount; return this; }

        public WeakTopicSummaryDto build() {
            return new WeakTopicSummaryDto(topicId, topicNameEn, topicNameTe, subjectNameEn, studentCount);
        }
    }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTopicNameEn() { return topicNameEn; }
    public void setTopicNameEn(String topicNameEn) { this.topicNameEn = topicNameEn; }

    public String getTopicNameTe() { return topicNameTe; }
    public void setTopicNameTe(String topicNameTe) { this.topicNameTe = topicNameTe; }

    public String getSubjectNameEn() { return subjectNameEn; }
    public void setSubjectNameEn(String subjectNameEn) { this.subjectNameEn = subjectNameEn; }

    public Long getStudentCount() { return studentCount; }
    public void setStudentCount(Long studentCount) { this.studentCount = studentCount; }
}
