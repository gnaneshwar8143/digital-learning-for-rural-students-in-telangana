package com.telangana.digitallearning.service;

import com.telangana.digitallearning.entity.*;
import com.telangana.digitallearning.exception.ResourceNotFoundException;
import com.telangana.digitallearning.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurriculumService {

    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final ChapterRepository chapterRepository;
    private final TopicRepository topicRepository;
    private final LearningMaterialRepository materialRepository;

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    public List<Subject> getSubjectsByClass(Long classId) {
        return subjectRepository.findByClassId(classId);
    }

    public List<Chapter> getChaptersBySubject(Long subjectId) {
        return chapterRepository.findBySubjectId(subjectId);
    }

    public List<Topic> getTopicsByChapter(Long chapterId) {
        return topicRepository.findByChapterId(chapterId);
    }

    public Topic getTopicById(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found: " + topicId));
    }

    public List<LearningMaterial> getMaterialsByTopic(Long topicId) {
        return materialRepository.findByTopicId(topicId);
    }

    public List<LearningMaterial> getMaterialsByClass(Integer classLevel) {
        return materialRepository.findByClassLevel(classLevel);
    }
}
