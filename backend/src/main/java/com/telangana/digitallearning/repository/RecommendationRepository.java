package com.telangana.digitallearning.repository;

import com.telangana.digitallearning.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByStudentId(Long studentId);
    List<Recommendation> findByStudentIdAndStatus(Long studentId, String status);
    List<Recommendation> findByStudentIdAndTopicId(Long studentId, Long topicId);
}
