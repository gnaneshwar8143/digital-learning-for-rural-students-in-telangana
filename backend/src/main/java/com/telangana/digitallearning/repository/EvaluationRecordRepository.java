package com.telangana.digitallearning.repository;

import com.telangana.digitallearning.entity.EvaluationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRecordRepository extends JpaRepository<EvaluationRecord, Long> {
    List<EvaluationRecord> findByStudentId(Long studentId);
    Optional<EvaluationRecord> findByStudentIdAndTopicId(Long studentId, Long topicId);
}
