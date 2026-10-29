# REST API Documentation

## 1. Authentication Endpoints (`/api/auth`)

### `POST /api/auth/login`
- **Request Body**:
  ```json
  { "email": "student@telangana.edu", "password": "student123" }
  ```
- **Response** (`200 OK`):
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "id": 3,
    "name": "Rahul Kumar",
    "email": "student@telangana.edu",
    "role": "ROLE_STUDENT",
    "classLevel": 10,
    "preferredLanguage": "te"
  }
  ```

### `POST /api/auth/register`
- **Request Body**:
  ```json
  {
    "name": "Rahul Kumar",
    "email": "student@telangana.edu",
    "password": "student123",
    "role": "STUDENT",
    "classLevel": 10,
    "preferredLanguage": "te"
  }
  ```

---

## 2. Student Endpoints (`/api/student`)

- `GET /api/student/dashboard`: Returns overall student stats, weak topic alerts, and active recommendations.
- `GET /api/student/weak-topics`: Returns all identified weak topics (`<40%` or `40-60%`).
- `GET /api/student/recommendations`: Returns active personalized learning recommendations.
- `GET /api/student/evaluation`: Returns Pre-Test vs Post-Test performance pairs.
- `POST /api/student/quizzes/submit`: Submits quiz attempt, triggers weak topic detection engine, and returns diagnostic results & recommendations.

---

## 3. Curriculum Endpoints (`/api/curriculum`)

- `GET /api/curriculum/classes`: List all Telangana Board classes (6-10).
- `GET /api/curriculum/classes/{classId}/subjects`: List subjects for a class level.
- `GET /api/curriculum/subjects/{subjectId}/chapters`: List chapters for a subject.
- `GET /api/curriculum/chapters/{chapterId}/topics`: List topics for a chapter.
- `GET /api/curriculum/topics/{topicId}/materials`: Get notes, video lessons, and practice sets.

---

## 4. Teacher & Admin Endpoints

- `GET /api/teacher/dashboard`: Educator dashboard with class averages & weak topic matrix.
- `POST /api/teacher/quizzes`: Create new Telangana Board quiz.
- `GET /api/admin/dashboard`: System administration stats.
- `GET /api/admin/users`: User control list.
