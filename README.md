<<<<<<< HEAD
# Digital Learning Platform for Rural School Students in Telangana

> **Final-Year Computer Science & Engineering Major Project**  
> *Research Domain: Educational Technology, Weak Topic Detection & Adaptive Recommendation Engines*

---

## 1. Project Overview & Research Context

This project delivers a production-quality, research-driven digital learning platform tailored for school students in rural Telangana (Classes 6 to 10). Based on the academic background established by **Chapke et al. (IJERT, 2026)**, the system addresses the critical research gap in existing LMS solutions by providing:

1. **Automated Weak Topic Detection**: Analyzes topic-wise student quiz performance and automatically flags conceptual weaknesses (`<40%` WEAK, `40–60%` NEEDS IMPROVEMENT).
2. **Personalized Learning Recommendation Engine**: Automatically pairs weak topics with targeted remedial study notes, low-bandwidth video lessons, and practice question sets.
3. **Telangana State Board Curriculum Alignment**: Pre-seeded with Class 6–10 subjects (Mathematics, Science, Social Studies, English, Telugu), chapters, and topics.
4. **Bilingual Multilingual Engine (English | తెలుగు)**: Centralized translation switcher catering to Telugu-medium rural learners across student, quiz, and recommendation modules.
5. **Empirical Pre/Post Test Research Evaluation**: Built-in evaluation dashboard comparing baseline scores against retest scores to measure percentage learning gain (+40%).
6. **Low-Bandwidth Rural Optimization**: 2G/3G low-data stream badges and single-click offline text note downloads.

---

## 2. Technology Stack & Decoupled Architecture

```
                                  +------------------------------------+
                                  |   React + Vite Frontend            |
                                  |   (Tailwind CSS, Recharts, i18n)   |
                                  +-----------------+------------------+
                                                    | REST / JWT
                                                    v
                                  +------------------------------------+
                                  |   Spring Boot Backend Service      |
                                  |   - Spring Security & JWT Filter   |
                                  |   - Weak Topic Detection Service   |
                                  |   - Recommendation Engine Service  |
                                  |   - Pre/Post Test Evaluation Module|
                                  +-----------------+------------------+
                                                    | JPA / Hibernate
                                                    v
                                  +------------------------------------+
                                  |   MySQL / H2 Database              |
                                  |   (Auto-Seeded Telangana Data)     |
                                  +------------------------------------+
```

- **Frontend**: React 18, Vite, Tailwind CSS, Lucide Icons, Recharts Analytics, Axios, React Router v6, English | తెలుగు Language Context.
- **Backend**: Java 17, Spring Boot 3.2, Spring Security, JWT (`jjwt`), Spring Data JPA, H2 (out-of-the-box runtime) + MySQL 8 dialect setup script.
- **Testing**: JUnit 5 unit test suite for weak topic classification thresholds.

---

## 3. Demo Credentials (For Presentation & Evaluation)

| Role | Email Address | Password | Description / Account Context |
| :--- | :--- | :--- | :--- |
| **Student** | `student@telangana.edu` | `student123` | **Rahul Kumar** (Class 10 Student - Fractions Weak Topic pre-seeded) |
| **Teacher** | `teacher@telangana.edu` | `teacher123` | **Srinivas Rao** (High School Teacher - Class performance matrix) |
| **Admin** | `admin@telangana.edu` | `admin123` | **Telangana System Administrator** (User & System Analytics control) |

---

## 4. End-to-End Core Demonstration Flow

```
Student Login -> Dashboard -> Subject (Math) -> Chapter (Fractions) -> Learning Material -> 
Quiz -> Submit -> Automatic Grading -> Weak Topic Detection (<40%) -> 
Personalized Recommendation -> Study Recommended Notes -> Retest -> 
Score Improvement (+40%) -> Research Evaluation Dashboard Update
```

---

## 5. How to Run Locally

### Prerequisites
- **Java 17+** (Verified Java 21 LTS installed)
- **Node.js 18+** & **npm 10+** (Verified Node v22 installed)
- Standalone Apache Maven 3.9 included in `./tools/apache-maven-3.9.6/`

### Step 1: Run Spring Boot Backend (Port 8080)
```bash
cd backend
..\tools\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run
```
*Backend initializes H2 in-memory database and auto-seeds Telangana curriculum, demo accounts, quizzes, and initial weak topic records.*

### Step 2: Run React Frontend (Port 5173)
```bash
cd frontend
npm run dev
```
Open browser at `http://localhost:5173`

---

## 6. MySQL Database Configuration (Optional Deployment)

By default, the backend runs seamlessly on H2 in-memory DB. To switch to MySQL:
1. Create a MySQL database named `telangana_learning_db`.
2. Open `backend/src/main/resources/application.yml`.
3. Uncomment the MySQL datasource block:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/telangana_learning_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
       username: root
       password: your_mysql_password
       driver-class-name: com.mysql.cj.jdbc.Driver
     jpa:
       database-platform: org.hibernate.dialect.MySQL8Dialect
   ```

---

## 7. Project Documentation

Comprehensive major project technical documentation is located in the `docs/` folder:
- [docs/architecture.md](file:///c:/Users/pocha/Downloads/majorproject/docs/architecture.md) - System architecture design document.
- [docs/database.md](file:///c:/Users/pocha/Downloads/majorproject/docs/database.md) - Database ER design and table definitions.
- [docs/api.md](file:///c:/Users/pocha/Downloads/majorproject/docs/api.md) - REST API specification.
- [docs/research-gap.md](file:///c:/Users/pocha/Downloads/majorproject/docs/research-gap.md) - Academic research gap analysis.
- [docs/testing.md](file:///c:/Users/pocha/Downloads/majorproject/docs/testing.md) - Testing execution guide.
=======
# digital-learning-for-rural-students-in-telangana
>>>>>>> 630cc925cdc8852a4ef70e3709c9cc6661534be6
