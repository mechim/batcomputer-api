package com.mechim.batcomputer.repositories;

import com.mechim.batcomputer.domain.CrimeTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeTypeRepository extends CrudRepository<CrimeTypeEntity, Integer> {
}
