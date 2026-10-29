package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.*;
import com.telangana.digitallearning.entity.*;
import com.telangana.digitallearning.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final LearningMaterialRepository materialRepository;
    private final QuizAttemptRepository attemptRepository;
    private final TopicPerformanceRepository topicPerformanceRepository;
    private final TopicRepository topicRepository;
    private final SubjectRepository subjectRepository;
    private final WeakTopicDetectionService weakTopicDetectionService;
    private final EvaluationService evaluationService;

    public TeacherDashboardDto getTeacherDashboard() {
        List<User> students = userRepository.findByRole(Role.ROLE_STUDENT);
        long totalQuizzes = quizRepository.count();
        long totalMaterials = materialRepository.count();

        List<QuizAttempt> allAttempts = attemptRepository.findAll();
        double avgClassScore = allAttempts.isEmpty() ? 0.0 :
                allAttempts.stream().mapToDouble(QuizAttempt::getPercentage).average().orElse(0.0);
        avgClassScore = Math.round(avgClassScore * 10.0) / 10.0;

        List<TopicPerformance> weakPerfs = topicPerformanceRepository.findAll().stream()
                .filter(p -> "WEAK".equals(p.getStatus()) || "NEEDS_IMPROVEMENT".equals(p.getStatus()))
                .collect(Collectors.toList());

        long studentsNeedingHelp = weakPerfs.stream().map(TopicPerformance::getStudentId).distinct().count();

        Map<Long, Long> topicCountMap = weakPerfs.stream()
                .collect(Collectors.groupingBy(TopicPerformance::getTopicId, Collectors.counting()));

        List<WeakTopicSummaryDto> topWeakTopics = topicCountMap.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .map(e -> {
                    Topic topic = topicRepository.findById(e.getKey()).orElse(null);
                    return WeakTopicSummaryDto.builder()
                            .topicId(e.getKey())
                            .topicNameEn(topic != null ? topic.getNameEn() : "Topic #" + e.getKey())
                            .topicNameTe(topic != null ? topic.getNameTe() : "అంశం #" + e.getKey())
                            .subjectNameEn("Telangana State Board")
                            .studentCount(e.getValue())
                            .build();
                })
                .collect(Collectors.toList());

        List<StudentPerformanceDto> recentStudents = students.stream().limit(10).map(s -> {
            List<WeakTopicDto> studentWeakTopics = weakTopicDetectionService.getWeakTopicsForStudent(s.getId());
            List<EvaluationSummaryDto> evals = evaluationService.getStudentEvaluationRecords(s.getId());
            List<QuizAttempt> sAttempts = attemptRepository.findByStudentId(s.getId());
            double sAvg = sAttempts.isEmpty() ? 0.0 : sAttempts.stream().mapToDouble(QuizAttempt::getPercentage).average().orElse(0.0);

            return StudentPerformanceDto.builder()
                    .studentId(s.getId())
                    .studentName(s.getName())
                    .email(s.getEmail())
                    .classLevel(s.getClassLevel())
                    .averageScore(Math.round(sAvg * 10.0) / 10.0)
                    .weakTopicsCount(studentWeakTopics.size())
                    .weakTopics(studentWeakTopics)
                    .evaluationHistory(evals)
                    .build();
        }).collect(Collectors.toList());

        return TeacherDashboardDto.builder()
                .totalStudents((long) students.size())
                .averageClassScore(avgClassScore)
                .studentsNeedingHelp(studentsNeedingHelp)
                .totalQuizzes(totalQuizzes)
                .totalMaterials(totalMaterials)
                .mostCommonWeakTopics(topWeakTopics)
                .recentStudentActivities(recentStudents)
                .build();
    }
}
