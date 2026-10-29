package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.AdminDashboardDto;
import com.telangana.digitallearning.entity.Role;
import com.telangana.digitallearning.entity.User;
import com.telangana.digitallearning.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final LearningMaterialRepository materialRepository;
    private final QuizRepository quizRepository;
    private final QuizAttemptRepository attemptRepository;
    private final EvaluationService evaluationService;

    public AdminDashboardDto getAdminDashboard() {
        long students = userRepository.findByRole(Role.ROLE_STUDENT).size();
        long teachers = userRepository.findByRole(Role.ROLE_TEACHER).size();
        long classes = classRepository.count();
        long subjects = subjectRepository.count();
        long materials = materialRepository.count();
        long quizzes = quizRepository.count();
        long attempts = attemptRepository.count();
        double improvement = evaluationService.calculateOverallSystemImprovement();

        return AdminDashboardDto.builder()
                .totalStudents(students)
                .totalTeachers(teachers)
                .totalClasses(classes)
                .totalSubjects(subjects)
                .totalMaterials(materials)
                .totalQuizzes(quizzes)
                .totalAttempts(attempts)
                .systemAverageImprovement(improvement)
                .build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
