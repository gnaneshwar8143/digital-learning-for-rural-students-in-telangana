package com.telangana.digitallearning.controller;

import com.telangana.digitallearning.dto.TeacherDashboardDto;
import com.telangana.digitallearning.entity.LearningMaterial;
import com.telangana.digitallearning.entity.Quiz;
import com.telangana.digitallearning.repository.LearningMaterialRepository;
import com.telangana.digitallearning.repository.QuizRepository;
import com.telangana.digitallearning.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeacherController {

    private final TeacherService teacherService;
    private final QuizRepository quizRepository;
    private final LearningMaterialRepository materialRepository;

    @GetMapping("/dashboard")
    public ResponseEntity<TeacherDashboardDto> getTeacherDashboard() {
        return ResponseEntity.ok(teacherService.getTeacherDashboard());
    }

    @PostMapping("/quizzes")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizRepository.save(quiz));
    }

    @PostMapping("/materials")
    public ResponseEntity<LearningMaterial> uploadMaterial(@RequestBody LearningMaterial material) {
        return ResponseEntity.ok(materialRepository.save(material));
    }
}
