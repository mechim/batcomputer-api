package com.mechim.batcomputer.services.impl;

import com.mechim.batcomputer.domain.CaseEntity;
import com.mechim.batcomputer.domain.dtos.CaseSummaryDto;
import com.mechim.batcomputer.repositories.CaseRepository;
import com.mechim.batcomputer.services.CaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CaseServiceImpl implements CaseService {
    private CaseRepository caseRepository;
    public CaseServiceImpl(CaseRepository caseRepository){
        this.caseRepository = caseRepository;
    }


    @Override
    public List<CaseEntity> findAll() {
        return StreamSupport.stream(caseRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public CaseEntity save(CaseEntity caseEntity) {
        return caseRepository.save(caseEntity);
    }

    @Override
    public Optional<CaseEntity> findOne(Long id) {
        return caseRepository.findByIdWithSuspects(id);
    }

    @Override
    public boolean exists(Long id) {
        return caseRepository.existsById(id);
    }

    @Override
    public CaseEntity partialUpdate(Long id, CaseEntity caseEntity) {
        caseEntity.setId(id);
        return caseRepository.findByIdWithSuspects(id).map(existingCase ->{
            Optional.ofNullable(caseEntity.getDescription()).ifPresent(existingCase::setDescription);
            Optional.ofNullable(caseEntity.getTitle()).ifPresent(existingCase::setTitle);
            Optional.ofNullable(caseEntity.getType()).ifPresent(existingCase::setType);
            Optional.ofNullable(caseEntity.getSolved()).ifPresent(existingCase::setSolved);
            return caseRepository.save(existingCase);
        }).orElseThrow(() -> new RuntimeException("Case Does not Exist"));
    }

    @Override
    public void delete(Long id) {
        caseRepository.deleteById(id);
    }

    @Override
    public List<CaseSummaryDto> findAllCasesSummary() {
        return StreamSupport.stream(caseRepository.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CaseSummaryDto convertToDto(CaseEntity caseEntity){
        return new CaseSummaryDto(
                caseEntity.getId(),
                caseEntity.getTitle(),
                caseEntity.getDescription(),
                caseEntity.getSolved()
        );
    }
}
