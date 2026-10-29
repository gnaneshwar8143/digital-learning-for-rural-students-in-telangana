package com.telangana.digitallearning.dto;

import java.util.List;

public class AiStudyPlanResponse {
    private String studentName;
    private String planTitleEn;
    private String planTitleTe;
    private List<StudyPlanDayDto> days;
    private Boolean isFallbackMode;
    private String modeLabel;

    public AiStudyPlanResponse() {}

    public AiStudyPlanResponse(String studentName, String planTitleEn, String planTitleTe, List<StudyPlanDayDto> days, Boolean isFallbackMode, String modeLabel) {
        this.studentName = studentName;
        this.planTitleEn = planTitleEn;
        this.planTitleTe = planTitleTe;
        this.days = days;
        this.isFallbackMode = isFallbackMode;
        this.modeLabel = modeLabel;
    }

    public static AiStudyPlanResponseBuilder builder() { return new AiStudyPlanResponseBuilder(); }

    public static class AiStudyPlanResponseBuilder {
        private String studentName;
        private String planTitleEn;
        private String planTitleTe;
        private List<StudyPlanDayDto> days;
        private Boolean isFallbackMode;
        private String modeLabel;

        public AiStudyPlanResponseBuilder studentName(String studentName) { this.studentName = studentName; return this; }
        public AiStudyPlanResponseBuilder planTitleEn(String planTitleEn) { this.planTitleEn = planTitleEn; return this; }
        public AiStudyPlanResponseBuilder planTitleTe(String planTitleTe) { this.planTitleTe = planTitleTe; return this; }
        public AiStudyPlanResponseBuilder days(List<StudyPlanDayDto> days) { this.days = days; return this; }
        public AiStudyPlanResponseBuilder isFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; return this; }
        public AiStudyPlanResponseBuilder modeLabel(String modeLabel) { this.modeLabel = modeLabel; return this; }

        public AiStudyPlanResponse build() {
            return new AiStudyPlanResponse(studentName, planTitleEn, planTitleTe, days, isFallbackMode, modeLabel);
        }
    }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getPlanTitleEn() { return planTitleEn; }
    public void setPlanTitleEn(String planTitleEn) { this.planTitleEn = planTitleEn; }

    public String getPlanTitleTe() { return planTitleTe; }
    public void setPlanTitleTe(String planTitleTe) { this.planTitleTe = planTitleTe; }

    public List<StudyPlanDayDto> getDays() { return days; }
    public void setDays(List<StudyPlanDayDto> days) { this.days = days; }

    public Boolean getIsFallbackMode() { return isFallbackMode; }
    public void setIsFallbackMode(Boolean isFallbackMode) { this.isFallbackMode = isFallbackMode; }

    public String getModeLabel() { return modeLabel; }
    public void setModeLabel(String modeLabel) { this.modeLabel = modeLabel; }
}
