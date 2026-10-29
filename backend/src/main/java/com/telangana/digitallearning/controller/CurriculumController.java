package com.telangana.digitallearning.controller;

import com.telangana.digitallearning.entity.*;
import com.telangana.digitallearning.service.CurriculumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curriculum")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CurriculumController {

    private final CurriculumService curriculumService;

    @GetMapping("/classes")
    public ResponseEntity<List<ClassEntity>> getAllClasses() {
        return ResponseEntity.ok(curriculumService.getAllClasses());
    }

    @GetMapping("/classes/{classId}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(curriculumService.getSubjectsByClass(classId));
    }

    @GetMapping("/subjects/{subjectId}/chapters")
    public ResponseEntity<List<Chapter>> getChaptersBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(curriculumService.getChaptersBySubject(subjectId));
    }

    @GetMapping("/chapters/{chapterId}/topics")
    public ResponseEntity<List<Topic>> getTopicsByChapter(@PathVariable Long chapterId) {
        return ResponseEntity.ok(curriculumService.getTopicsByChapter(chapterId));
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long topicId) {
        return ResponseEntity.ok(curriculumService.getTopicById(topicId));
    }

    @GetMapping("/topics/{topicId}/materials")
    public ResponseEntity<List<LearningMaterial>> getMaterialsByTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(curriculumService.getMaterialsByTopic(topicId));
    }
}
