package com.telangana.digitallearning.repository;

import com.telangana.digitallearning.entity.AiInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiInteractionRepository extends JpaRepository<AiInteraction, Long> {
    List<AiInteraction> findByStudentId(Long studentId);
    List<AiInteraction> findByInteractionType(String interactionType);
}
