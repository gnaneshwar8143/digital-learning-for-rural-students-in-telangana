# System Architecture Specification

## 1. Overview

The **Digital Learning Platform for Rural School Students in Telangana** is built as a modular, decoupled full-stack application following an Enterprise Multi-Layer Architecture:

```
[ React + Vite Frontend ] <--- REST (JSON over HTTP) ---> [ Spring Boot Backend ] <--- JPA / Hibernate ---> [ Database (MySQL / H2) ]
  - Tailwind CSS                                           - REST Controllers                                 - User & Role Schemas
  - Recharts Analytics                                     - Security & JWT Filter                            - Curriculum Hierarchy (6-10)
  - Bilingual Engine (EN/TE)                              - Weak Topic Detection Service                      - Weak Topic & Rec Tables
  - Low-Bandwidth Caching                                  - Recommendation Engine                             - Evaluation Records
                                                           - Pre/Post Test Tracker
```

---

## 2. Decoupled Layering

### Backend Layers (Spring Boot 3.2, Java 17)
1. **Controller Layer**: REST Endpoints exposing JSON DTO responses.
2. **Service Layer**: Core research business logic (`WeakTopicDetectionService`, `RecommendationEngineService`, `EvaluationService`).
3. **Repository Layer**: Spring Data JPA Repositories executing criteria & SQL queries.
4. **Entity Layer**: Normalized JPA Entities with Lifecycle Callbacks (`@PrePersist`, `@PreUpdate`).
5. **Security & Config Layer**: Stateless JWT authentication filter, BCrypt password encoder, role-based authorization rules.

### Frontend Layers (React 18, Vite)
1. **Context Providers**: `AuthContext` (JWT session), `LanguageContext` (Bilingual EN/TE context).
2. **Component Library**: Reusable UI primitives (`Navbar`, `Sidebar`, `StatCard`, `WeakTopicBadge`, `MaterialCard`).
3. **Page Views**: Student, Teacher, Admin, and Public Landing pages.
4. **API Gateway Service**: Axios HTTP client with Bearer Token header interceptor.
