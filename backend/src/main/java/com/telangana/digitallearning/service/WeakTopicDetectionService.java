package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.WeakTopicDto;
import com.telangana.digitallearning.entity.Subject;
import com.telangana.digitallearning.entity.Topic;
import com.telangana.digitallearning.entity.TopicPerformance;
import com.telangana.digitallearning.repository.ChapterRepository;
import com.telangana.digitallearning.repository.SubjectRepository;
import com.telangana.digitallearning.repository.TopicPerformanceRepository;
import com.telangana.digitallearning.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeakTopicDetectionService {

    private final TopicPerformanceRepository topicPerformanceRepository;
    private final TopicRepository topicRepository;
    private final ChapterRepository chapterRepository;
    private final SubjectRepository subjectRepository;

    /**
     * Primary Research Rule Evaluation:
     * - Score < 40%  => WEAK
     * - Score 40-60% => NEEDS_IMPROVEMENT
     * - Score > 60%  => GOOD
     */
    public String evaluatePerformanceStatus(Double scorePercentage) {
        if (scorePercentage == null) return "NEEDS_IMPROVEMENT";
        if (scorePercentage < 40.0) {
            return "WEAK";
        } else if (scorePercentage <= 60.0) {
            return "NEEDS_IMPROVEMENT";
        } else {
            return "GOOD";
        }
    }

    public TopicPerformance recordOrUpdatePerformance(Long studentId, Long topicId, Double scorePercentage) {
        String status = evaluatePerformanceStatus(scorePercentage);

        Optional<TopicPerformance> existingOpt = topicPerformanceRepository.findByStudentIdAndTopicId(studentId, topicId);
        TopicPerformance perf;
        if (existingOpt.isPresent()) {
            perf = existingOpt.get();
            perf.setScorePercentage(scorePercentage);
            perf.setStatus(status);
        } else {
            perf = TopicPerformance.builder()
                    .studentId(studentId)
                    .topicId(topicId)
                    .scorePercentage(scorePercentage)
                    .status(status)
                    .build();
        }
        return topicPerformanceRepository.save(perf);
    }

    public List<WeakTopicDto> getWeakTopicsForStudent(Long studentId) {
        List<TopicPerformance> performances = topicPerformanceRepository.findByStudentId(studentId);
        return performances.stream()
                .filter(p -> "WEAK".equals(p.getStatus()) || "NEEDS_IMPROVEMENT".equals(p.getStatus()))
                .map(p -> {
                    Topic topic = topicRepository.findById(p.getTopicId()).orElse(null);
                    String topicNameEn = topic != null ? topic.getNameEn() : "Topic #" + p.getTopicId();
                    String topicNameTe = topic != null ? topic.getNameTe() : topicNameEn;

                    String subjectNameEn = "General";
                    String subjectNameTe = "సాధారణ";
                    if (topic != null) {
                        var chapter = chapterRepository.findById(topic.getChapterId()).orElse(null);
                        if (chapter != null) {
                            Subject subject = subjectRepository.findById(chapter.getSubjectId()).orElse(null);
                            if (subject != null) {
                                subjectNameEn = subject.getNameEn();
                                subjectNameTe = subject.getNameTe();
                            }
                        }
                    }

                    return WeakTopicDto.builder()
                            .topicId(p.getTopicId())
                            .topicNameEn(topicNameEn)
                            .topicNameTe(topicNameTe)
                            .subjectNameEn(subjectNameEn)
                            .subjectNameTe(subjectNameTe)
                            .scorePercentage(p.getScorePercentage())
                            .status(p.getStatus())
                            .lastAssessedAt(p.getLastAssessedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
