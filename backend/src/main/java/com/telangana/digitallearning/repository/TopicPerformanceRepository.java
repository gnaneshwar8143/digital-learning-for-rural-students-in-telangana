package com.telangana.digitallearning.repository;

import com.telangana.digitallearning.entity.TopicPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicPerformanceRepository extends JpaRepository<TopicPerformance, Long> {
    List<TopicPerformance> findByStudentId(Long studentId);
    List<TopicPerformance> findByStudentIdAndStatus(Long studentId, String status);
    Optional<TopicPerformance> findByStudentIdAndTopicId(Long studentId, Long topicId);
}
