package com.telangana.digitallearning.dto;

public class StudyPlanDayDto {
    private String dayName;
    private String taskTitleEn;
    private String taskTitleTe;
    private Integer durationMinutes;
    private String materialType;
    private Boolean completed;

    public StudyPlanDayDto() {}

    public StudyPlanDayDto(String dayName, String taskTitleEn, String taskTitleTe, Integer durationMinutes, String materialType, Boolean completed) {
        this.dayName = dayName;
        this.taskTitleEn = taskTitleEn;
        this.taskTitleTe = taskTitleTe;
        this.durationMinutes = durationMinutes;
        this.materialType = materialType;
        this.completed = completed;
    }

    public static StudyPlanDayDtoBuilder builder() { return new StudyPlanDayDtoBuilder(); }

    public static class StudyPlanDayDtoBuilder {
        private String dayName;
        private String taskTitleEn;
        private String taskTitleTe;
        private Integer durationMinutes;
        private String materialType;
        private Boolean completed;

        public StudyPlanDayDtoBuilder dayName(String dayName) { this.dayName = dayName; return this; }
        public StudyPlanDayDtoBuilder taskTitleEn(String taskTitleEn) { this.taskTitleEn = taskTitleEn; return this; }
        public StudyPlanDayDtoBuilder taskTitleTe(String taskTitleTe) { this.taskTitleTe = taskTitleTe; return this; }
        public StudyPlanDayDtoBuilder durationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; return this; }
        public StudyPlanDayDtoBuilder materialType(String materialType) { this.materialType = materialType; return this; }
        public StudyPlanDayDtoBuilder completed(Boolean completed) { this.completed = completed; return this; }

        public StudyPlanDayDto build() {
            return new StudyPlanDayDto(dayName, taskTitleEn, taskTitleTe, durationMinutes, materialType, completed);
        }
    }

    public String getDayName() { return dayName; }
    public void setDayName(String dayName) { this.dayName = dayName; }

    public String getTaskTitleEn() { return taskTitleEn; }
    public void setTaskTitleEn(String taskTitleEn) { this.taskTitleEn = taskTitleEn; }

    public String getTaskTitleTe() { return taskTitleTe; }
    public void setTaskTitleTe(String taskTitleTe) { this.taskTitleTe = taskTitleTe; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getMaterialType() { return materialType; }
    public void setMaterialType(String materialType) { this.materialType = materialType; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
}
