package com.mechim.batcomputer.services;

import com.mechim.batcomputer.domain.CrimeTypeEntity;

import java.util.List;

public interface CrimeTypeService {
    public List<CrimeTypeEntity> listCrimeTypes();

    CrimeTypeEntity save(CrimeTypeEntity crimeTypeEntity);
}
