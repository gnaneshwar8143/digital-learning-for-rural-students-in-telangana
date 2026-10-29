# Testing & Verification Guide

## 1. Automated Unit & Integration Testing

### Spring Boot Backend Tests
Execute unit tests validating weak topic classification rules and evaluation math:

```bash
# In backend directory using extracted Maven binary
..\tools\apache-maven-3.9.6\bin\mvn.cmd test
```

### Test Suite Output
- `WeakTopicDetectionServiceTest`: Validates `<40%` WEAK classification, `40-60%` NEEDS IMPROVEMENT classification, and `>60%` GOOD classification.
- Build Status: `BUILD SUCCESS` (0 failures, 0 errors).

---

## 2. Frontend Production Build Verification

Execute Vite production bundle verification:

```bash
cd frontend
npm run build
```

Expected output:
- `dist/index.html` compiled cleanly.
- Zero TypeScript/JSX syntax errors.

---

## 3. End-to-End Workflow Verification Steps

1. **Launch Spring Boot Backend**:
   ```bash
   cd backend
   ..\tools\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run
   ```
2. **Launch React Frontend**:
   ```bash
   cd frontend
   npm run dev
   ```
3. **Login as Demo Student**:
   - Click "Student Demo" on Landing Page or enter `student@telangana.edu` / `student123`.
4. **Observe Weak Topic Alert**:
   - View "Fractions & Operations" flagged as WEAK (35%).
5. **Study Recommended Material**:
   - Click "Study Material" -> Download Fractions Notes & view low-data lesson.
6. **Take Retest**:
   - Take Retest Quiz on Fractions and score >60%.
7. **Verify Pre/Post Evaluation Gain**:
   - Navigate to "Research Evaluation" page -> Verify Pre-Test 35%, Post-Test 75%, Improvement +40%.
8. **Verify Teacher Dashboard**:
   - Switch language to "తెలుగు" -> Login as `teacher@telangana.edu` / `teacher123` -> Inspect class performance matrix.
