package com.telangana.digitallearning.dto;

public class AuthResponse {
    private String token;
    private Long id;
    private String name;
    private String email;
    private String role;
    private Integer classLevel;
    private String preferredLanguage;

    public AuthResponse() {}

    public AuthResponse(String token, Long id, String name, String email, String role, Integer classLevel, String preferredLanguage) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.classLevel = classLevel;
        this.preferredLanguage = preferredLanguage;
    }

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public static class AuthResponseBuilder {
        private String token;
        private Long id;
        private String name;
        private String email;
        private String role;
        private Integer classLevel;
        private String preferredLanguage;

        public AuthResponseBuilder token(String token) { this.token = token; return this; }
        public AuthResponseBuilder id(Long id) { this.id = id; return this; }
        public AuthResponseBuilder name(String name) { this.name = name; return this; }
        public AuthResponseBuilder email(String email) { this.email = email; return this; }
        public AuthResponseBuilder role(String role) { this.role = role; return this; }
        public AuthResponseBuilder classLevel(Integer classLevel) { this.classLevel = classLevel; return this; }
        public AuthResponseBuilder preferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; return this; }

        public AuthResponse build() {
            return new AuthResponse(token, id, name, email, role, classLevel, preferredLanguage);
        }
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getClassLevel() { return classLevel; }
    public void setClassLevel(Integer classLevel) { this.classLevel = classLevel; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
}
