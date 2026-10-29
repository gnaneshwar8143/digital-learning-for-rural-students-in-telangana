package com.telangana.digitallearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long classId;

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameTe;

    private String code;
    private String iconName;

    public Subject() {}

    public Subject(Long id, Long classId, String nameEn, String nameTe, String code, String iconName) {
        this.id = id;
        this.classId = classId;
        this.nameEn = nameEn;
        this.nameTe = nameTe;
        this.code = code;
        this.iconName = iconName;
    }

    public static SubjectBuilder builder() { return new SubjectBuilder(); }

    public static class SubjectBuilder {
        private Long id;
        private Long classId;
        private String nameEn;
        private String nameTe;
        private String code;
        private String iconName;

        public SubjectBuilder id(Long id) { this.id = id; return this; }
        public SubjectBuilder classId(Long classId) { this.classId = classId; return this; }
        public SubjectBuilder nameEn(String nameEn) { this.nameEn = nameEn; return this; }
        public SubjectBuilder nameTe(String nameTe) { this.nameTe = nameTe; return this; }
        public SubjectBuilder code(String code) { this.code = code; return this; }
        public SubjectBuilder iconName(String iconName) { this.iconName = iconName; return this; }
        public SubjectBuilder icon(String icon) { this.iconName = icon; return this; }

        public Subject build() {
            return new Subject(id, classId, nameEn, nameTe, code, iconName);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public String getNameTe() { return nameTe; }
    public void setNameTe(String nameTe) { this.nameTe = nameTe; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getIconName() { return iconName; }
    public void setIconName(String iconName) { this.iconName = iconName; }
}
