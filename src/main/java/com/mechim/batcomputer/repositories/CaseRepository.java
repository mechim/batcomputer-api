package com.mechim.batcomputer.repositories;

import com.mechim.batcomputer.domain.CaseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaseRepository extends CrudRepository<CaseEntity, Long> {
    @Query("SELECT c FROM CaseEntity c LEFT JOIN FETCH c.suspects WHERE c.id = :id")
    Optional<CaseEntity> findByIdWithSuspects(@Param("id") Long id);
}
