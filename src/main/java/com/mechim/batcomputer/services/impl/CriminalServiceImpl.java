package com.mechim.batcomputer.services.impl;

import com.mechim.batcomputer.domain.CriminalEntity;
import com.mechim.batcomputer.repositories.CriminalRepository;
import com.mechim.batcomputer.services.CriminalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class CriminalServiceImpl implements CriminalService {
    private CriminalRepository criminalRepository;
    public CriminalServiceImpl(CriminalRepository criminalRepository){
        this.criminalRepository = criminalRepository;
    }

    @Override
    public CriminalEntity save(CriminalEntity criminalEntity) {
        return criminalRepository.save(criminalEntity);
    }

    @Override
    public List<CriminalEntity> findAll(){
        return StreamSupport.stream(criminalRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<CriminalEntity> findOne(Long id) {
        return criminalRepository.findById(id);
    }

    @Override
    public boolean exists(Long id) {
        return criminalRepository.existsById(id);
    }

    @Override
    public CriminalEntity partialUpdate(Long id, CriminalEntity criminalEntity) {
        criminalEntity.setId(id);

        return criminalRepository.findById(id).map(existing -> {
            Optional.ofNullable(criminalEntity.getName()).ifPresent(existing::setName);
            Optional.ofNullable(criminalEntity.getAge()).ifPresent(existing::setAge);
            Optional.ofNullable(criminalEntity.getAlias()).ifPresent(existing::setAlias);
            return criminalRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Criminal does not exist"));
    }

    @Override
    public void delete(Long id) {
        criminalRepository.deleteById(id);
    }
}
