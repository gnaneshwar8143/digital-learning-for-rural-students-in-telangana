# Database Schema & Entity Design

## 1. Relational Database Overview

The system uses a fully normalized database schema designed for MySQL 8+ and H2 in-memory runtime.

```
users (id, name, email, password, role, class_level, preferred_language)
  │
  ├── topic_performance (id, student_id, topic_id, score_percentage, status)
  ├── recommendations (id, student_id, topic_id, previous_score, recommendation_type, recommended_material_id, status)
  ├── quiz_attempts (id, student_id, quiz_id, score, percentage, attempt_type)
  └── evaluation_records (id, student_id, topic_id, pre_test_score, post_test_score, improvement_percentage)

classes (id, class_number, name)
  └─ subjects (id, class_id, name_en, name_te, code)
      └─ chapters (id, subject_id, chapter_number, title_en, title_te)
          └─ topics (id, chapter_id, name_en, name_te, description_en, description_te)
              ├─ learning_materials (id, topic_id, title_en, title_te, material_type, file_url, content_text_en, content_text_te)
              └─ quizzes (id, topic_id, title_en, title_te, duration_minutes, total_marks, is_evaluation_test, test_category)
                  └─ questions (id, quiz_id, question_text_en, question_text_te, marks)
                      └─ question_options (id, question_id, option_text_en, option_text_te, is_correct)
```

---

## 2. Table Definitions

### `users`
- `id` (BIGINT, Primary Key, Auto Increment)
- `name` (VARCHAR, Not Null)
- `email` (VARCHAR, Unique, Not Null)
- `password` (VARCHAR, BCrypt Encrypted)
- `role` (VARCHAR: `ROLE_STUDENT`, `ROLE_TEACHER`, `ROLE_ADMIN`)
- `class_level` (INT: 6, 7, 8, 9, 10)
- `preferred_language` (VARCHAR: `en`, `te`)

### `topic_performance`
- `id` (BIGINT, Primary Key)
- `student_id` (BIGINT, Foreign Key -> `users.id`)
- `topic_id` (BIGINT, Foreign Key -> `topics.id`)
- `score_percentage` (DOUBLE)
- `status` (VARCHAR: `WEAK`, `NEEDS_IMPROVEMENT`, `GOOD`)

### `recommendations`
- `id` (BIGINT, Primary Key)
- `student_id` (BIGINT, Foreign Key -> `users.id`)
- `topic_id` (BIGINT, Foreign Key -> `topics.id`)
- `previous_score` (DOUBLE)
- `recommendation_type` (VARCHAR: `WEAK_REMEDIAL`, `REVISION`, `ADVANCED`)
- `recommended_material_id` (BIGINT, Foreign Key -> `learning_materials.id`)
- `status` (VARCHAR: `PENDING`, `COMPLETED`, `RETESTED`)

### `evaluation_records`
- `id` (BIGINT, Primary Key)
- `student_id` (BIGINT, Foreign Key -> `users.id`)
- `topic_id` (BIGINT, Foreign Key -> `topics.id`)
- `pre_test_score` (DOUBLE)
- `post_test_score` (DOUBLE)
- `improvement_percentage` (DOUBLE)
