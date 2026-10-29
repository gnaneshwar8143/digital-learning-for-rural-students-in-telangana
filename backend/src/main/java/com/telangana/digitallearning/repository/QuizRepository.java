package com.telangana.digitallearning.repository;

import com.telangana.digitallearning.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByTopicId(Long topicId);

    @Query("SELECT q FROM Quiz q WHERE q.classLevel = :classLevel")
    List<Quiz> findByClassLevel(@Param("classLevel") Integer classLevel);

    List<Quiz> findByTopicIdAndTestCategory(Long topicId, String testCategory);
}
