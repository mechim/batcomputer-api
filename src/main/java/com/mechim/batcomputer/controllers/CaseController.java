package com.mechim.batcomputer.controllers;

import com.mechim.batcomputer.domain.CaseEntity;
import com.mechim.batcomputer.domain.CriminalEntity;
import com.mechim.batcomputer.domain.dtos.CaseSummaryDto;
import com.mechim.batcomputer.services.CaseService;
import com.mechim.batcomputer.services.CriminalService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CaseController {
    private CaseService caseService;
    private CriminalService criminalService;
    public CaseController(CaseService caseService, CriminalService criminalService){
        this.caseService = caseService;
        this.criminalService = criminalService;
    }

    //Get all
    @Operation(summary = "List all criminal cases")
    @GetMapping(path = "/cases")
    public List<CaseSummaryDto> listCases(){
        List<CaseSummaryDto> summaries = caseService.findAllCasesSummary();
        return summaries;
    }

    // Create one
    @Operation(summary = "Create a criminal case")
    @PostMapping(path = "/cases")
    public ResponseEntity<CaseEntity> createCase(@RequestBody CaseEntity caseEntity){
        CaseEntity savedCase = caseService.save(caseEntity);
        return new ResponseEntity<>(savedCase, HttpStatus.CREATED);
    }

    // Get One
    @Operation(summary = "Get a criminal case by ID")
    @GetMapping(path = "/cases/{id}")
    public ResponseEntity<CaseEntity> getCase(@PathVariable("id") Long id){
        Optional<CaseEntity> foundCase = caseService.findOne(id);
        return foundCase.map(existingCase ->
                new ResponseEntity<>(existingCase, HttpStatus.OK)

        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PARTIAL UPDATE
    @Operation(summary = "Partially update a criminal case")
    @PatchMapping(path = "/cases/{id}")
    public ResponseEntity<CaseEntity> partialUpdate(@PathVariable("id") Long id, @RequestBody CaseEntity caseEntity){
        if(!caseService.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CaseEntity updatedCase = caseService.partialUpdate(id, caseEntity);
        return new ResponseEntity<>(updatedCase, HttpStatus.OK);
    }

    // FULL UPDATE
    @Operation(summary = "Fully update a criminal case")
    @PutMapping(path = "/cases/{id}")
    public ResponseEntity<CaseEntity> fullUpdate(@PathVariable("id") Long id, @RequestBody CaseEntity caseEntity){
        if(!caseService.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        caseEntity.setId(id);
        CaseEntity updatedCase = caseService.save(caseEntity);
        return new ResponseEntity<>(updatedCase, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping(path = "/cases/{id}")
    @Operation(summary = "Delete a criminal case")
    public void delete(@PathVariable("id") Long id){
        caseService.delete(id);
    }


    @Transactional
    @Operation(summary = "Add a suspect to a criminal case")
    @PutMapping(path = "/cases/{caseId}/criminals/{criminalId}")
    public ResponseEntity<CaseEntity> addSuspectToCase(
            @PathVariable("caseId") Long caseId,
            @PathVariable("criminalId") Long criminalId
    ){
        if (!criminalService.exists(criminalId) || !caseService.exists(caseId)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CriminalEntity criminalEntity = criminalService.findOne(criminalId).orElseThrow(() -> new RuntimeException("Criminal Not Found"));
        CaseEntity caseEntity = caseService.findOne(caseId).orElseThrow(() -> new RuntimeException("Case Not Found"));

        caseEntity.addSuspect(criminalEntity);

        CaseEntity updatedCase = caseService.save(caseEntity);

        return new ResponseEntity<>(updatedCase, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "Remove a suspect from a criminal case")
    @DeleteMapping(path = "/cases/{caseId}/criminals/{criminalId}")
    public ResponseEntity<CaseEntity> removeSuspectFromCase(
            @PathVariable("caseId") Long caseId,
            @PathVariable("criminalId") Long criminalId
    ){
        if (!criminalService.exists(criminalId) || !caseService.exists(caseId)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CriminalEntity criminalEntity = criminalService.findOne(criminalId).orElseThrow(() -> new RuntimeException("Criminal Not Found"));
        CaseEntity caseEntity = caseService.findOne(caseId).orElseThrow(() -> new RuntimeException("Case Not Found"));

        caseEntity.removeSuspect(criminalEntity);

        CaseEntity updatedCase = caseService.save(caseEntity);

        return new ResponseEntity<>(updatedCase, HttpStatus.OK);
    }

}
