package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.EvaluationSummaryDto;
import com.telangana.digitallearning.entity.EvaluationRecord;
import com.telangana.digitallearning.entity.Topic;
import com.telangana.digitallearning.entity.User;
import com.telangana.digitallearning.repository.EvaluationRecordRepository;
import com.telangana.digitallearning.repository.TopicRepository;
import com.telangana.digitallearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRecordRepository evaluationRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    /**
     * Research Evaluation Core logic:
     * Calculates Pre-Test score, Post-Test score, and Improvement Percentage:
     * Improvement % = Post-Test % - Pre-Test %
     */
    public EvaluationRecord recordOrUpdateEvaluation(Long studentId, Long topicId, Double preTestScore, Double postTestScore) {
        Double improvementPct = (postTestScore != null && preTestScore != null) ? (postTestScore - preTestScore) : 0.0;

        Optional<EvaluationRecord> existingOpt = evaluationRepository.findByStudentIdAndTopicId(studentId, topicId);
        EvaluationRecord record;
        if (existingOpt.isPresent()) {
            record = existingOpt.get();
            if (preTestScore != null) record.setPreTestScore(preTestScore);
            if (postTestScore != null) record.setPostTestScore(postTestScore);
            record.setImprovementPercentage(record.getPostTestScore() - (record.getPreTestScore() != null ? record.getPreTestScore() : 0.0));
        } else {
            record = EvaluationRecord.builder()
                    .studentId(studentId)
                    .topicId(topicId)
                    .preTestScore(preTestScore)
                    .postTestScore(postTestScore)
                    .improvementPercentage(improvementPct)
                    .build();
        }
        return evaluationRepository.save(record);
    }

    public List<EvaluationSummaryDto> getStudentEvaluationRecords(Long studentId) {
        List<EvaluationRecord> records = evaluationRepository.findByStudentId(studentId);
        return records.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<EvaluationSummaryDto> getAllEvaluationRecords() {
        List<EvaluationRecord> records = evaluationRepository.findAll();
        return records.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Double calculateOverallSystemImprovement() {
        List<EvaluationRecord> records = evaluationRepository.findAll();
        if (records.isEmpty()) return 0.0;
        double sum = records.stream()
                .filter(r -> r.getImprovementPercentage() != null)
                .mapToDouble(EvaluationRecord::getImprovementPercentage)
                .sum();
        return Math.round((sum / records.size()) * 100.0) / 100.0;
    }

    private EvaluationSummaryDto convertToDto(EvaluationRecord record) {
        Topic topic = topicRepository.findById(record.getTopicId()).orElse(null);
        User student = userRepository.findById(record.getStudentId()).orElse(null);

        return EvaluationSummaryDto.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .studentName(student != null ? student.getName() : "Student #" + record.getStudentId())
                .topicId(record.getTopicId())
                .topicNameEn(topic != null ? topic.getNameEn() : "Topic #" + record.getTopicId())
                .topicNameTe(topic != null ? topic.getNameTe() : "అంశం #" + record.getTopicId())
                .preTestScore(record.getPreTestScore())
                .postTestScore(record.getPostTestScore())
                .improvementPercentage(record.getImprovementPercentage())
                .evaluatedAt(record.getEvaluatedAt())
                .build();
    }
}
