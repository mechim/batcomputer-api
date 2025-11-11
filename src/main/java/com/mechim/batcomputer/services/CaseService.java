package com.mechim.batcomputer.services;

import com.mechim.batcomputer.domain.CaseEntity;
import com.mechim.batcomputer.domain.dtos.CaseSummaryDto;

import java.util.List;
import java.util.Optional;

public interface CaseService {
    public List<CaseEntity> findAll();

    public CaseEntity save(CaseEntity caseEntity);

    Optional<CaseEntity> findOne(Long id);

    boolean exists(Long id);

    CaseEntity partialUpdate(Long id, CaseEntity caseEntity);

    void delete(Long id);

    public List<CaseSummaryDto> findAllCasesSummary();


}
