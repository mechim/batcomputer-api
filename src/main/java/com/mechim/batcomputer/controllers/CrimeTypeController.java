package com.mechim.batcomputer.controllers;

import com.mechim.batcomputer.domain.CrimeTypeEntity;
import com.mechim.batcomputer.services.CrimeTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrimeTypeController {

    private CrimeTypeService crimeTypeService;
    public CrimeTypeController(CrimeTypeService crimeTypeService){
        this.crimeTypeService = crimeTypeService;
    }

    @GetMapping(path = "/crime-types")
    @Operation(summary = "Get all crime types")
    public List<CrimeTypeEntity> listCrimeTypes(){
        return crimeTypeService.listCrimeTypes();
    }

    @PostMapping(path = "/crime-types")
    @Operation(summary = "Create a crime type")
    public ResponseEntity<CrimeTypeEntity> createCrimeType(@RequestBody CrimeTypeEntity crimeTypeEntity){
        CrimeTypeEntity savedCrimeType = crimeTypeService.save(crimeTypeEntity);
        return new ResponseEntity<>(savedCrimeType, HttpStatus.CREATED);
    }
}
