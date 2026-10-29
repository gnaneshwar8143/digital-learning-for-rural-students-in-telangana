package com.telangana.digitallearning.repository;

import com.telangana.digitallearning.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    Optional<ClassEntity> findByClassLevel(Integer classLevel);

    @Query("SELECT c FROM ClassEntity c WHERE c.classLevel = :classNumber")
    Optional<ClassEntity> findByClassNumber(@Param("classNumber") Integer classNumber);
}
