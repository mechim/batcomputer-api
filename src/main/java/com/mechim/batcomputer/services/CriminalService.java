package com.mechim.batcomputer.services;

import com.mechim.batcomputer.domain.CriminalEntity;

import java.util.List;
import java.util.Optional;

public interface CriminalService {
    public CriminalEntity save(CriminalEntity criminalEntity);
    public List<CriminalEntity> findAll();
    public Optional<CriminalEntity> findOne(Long id);
    public boolean exists(Long id);

    public CriminalEntity partialUpdate(Long id, CriminalEntity criminalEntity);

    public void delete(Long id);
}
