package com.telangana.digitallearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer classLevel;

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameTe;

    public ClassEntity() {}

    public ClassEntity(Long id, Integer classLevel, String nameEn, String nameTe) {
        this.id = id;
        this.classLevel = classLevel;
        this.nameEn = nameEn;
        this.nameTe = nameTe;
    }

    public static ClassEntityBuilder builder() { return new ClassEntityBuilder(); }

    public static class ClassEntityBuilder {
        private Long id;
        private Integer classLevel;
        private String nameEn;
        private String nameTe;

        public ClassEntityBuilder id(Long id) { this.id = id; return this; }
        public ClassEntityBuilder classLevel(Integer classLevel) { this.classLevel = classLevel; return this; }
        public ClassEntityBuilder classNumber(Integer classNumber) { this.classLevel = classNumber; return this; }
        public ClassEntityBuilder nameEn(String nameEn) { this.nameEn = nameEn; return this; }
        public ClassEntityBuilder nameTe(String nameTe) { this.nameTe = nameTe; return this; }
        public ClassEntityBuilder name(String name) { this.nameEn = name; this.nameTe = name; return this; }

        public ClassEntity build() {
            return new ClassEntity(id, classLevel, nameEn, nameTe);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public String getNameTe() { return nameTe; }
    public void setNameTe(String nameTe) { this.nameTe = nameTe; }
}
