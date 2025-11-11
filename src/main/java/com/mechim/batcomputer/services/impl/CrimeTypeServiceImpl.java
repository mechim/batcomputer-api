package com.mechim.batcomputer.services.impl;

import com.mechim.batcomputer.domain.CrimeTypeEntity;
import com.mechim.batcomputer.repositories.CrimeTypeRepository;
import com.mechim.batcomputer.services.CrimeTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class CrimeTypeServiceImpl implements CrimeTypeService {
    private CrimeTypeRepository crimeTypeRepository;
    public CrimeTypeServiceImpl(CrimeTypeRepository crimeTypeRepository){
        this.crimeTypeRepository = crimeTypeRepository;
    }
    @Override
    public List<CrimeTypeEntity> listCrimeTypes() {
        return StreamSupport.stream(crimeTypeRepository.findAll().spliterator(), false).collect(Collectors.toList()) ;
    }

    @Override
    public CrimeTypeEntity save(CrimeTypeEntity crimeTypeEntity) {
        return crimeTypeRepository.save(crimeTypeEntity);
    }
}
