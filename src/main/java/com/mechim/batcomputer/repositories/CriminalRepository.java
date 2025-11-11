package com.mechim.batcomputer.repositories;

import com.mechim.batcomputer.domain.CriminalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalRepository extends CrudRepository<CriminalEntity, Long> {
}
