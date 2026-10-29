package com.telangana.digitallearning.repository;

import com.telangana.digitallearning.entity.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Long> {
    List<LearningMaterial> findByTopicId(Long topicId);

    @Query("SELECT m FROM LearningMaterial m WHERE m.topicId IN (SELECT t.id FROM Topic t WHERE t.chapterId IN (SELECT c.id FROM Chapter c WHERE c.subjectId IN (SELECT s.id FROM Subject s WHERE s.classId IN (SELECT cl.id FROM ClassEntity cl WHERE cl.classLevel = :classLevel))))")
    List<LearningMaterial> findByClassLevel(@Param("classLevel") Integer classLevel);

    List<LearningMaterial> findByTopicIdAndType(Long topicId, String type);

    @Query("SELECT m FROM LearningMaterial m WHERE m.topicId = :topicId AND m.type = :materialType")
    List<LearningMaterial> findByTopicIdAndMaterialType(@Param("topicId") Long topicId, @Param("materialType") String materialType);
}
