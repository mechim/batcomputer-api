package com.mechim.batcomputer.controllers;

import com.mechim.batcomputer.domain.CriminalEntity;
import com.mechim.batcomputer.services.CriminalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CriminalController {
    CriminalService criminalService;
    public CriminalController(CriminalService criminalService){
        this.criminalService = criminalService;
    }

    //CREATE
    @Operation(summary = "Create a criminal")
    @PostMapping(path = "/criminals")
    public ResponseEntity<CriminalEntity> createCriminal(@RequestBody CriminalEntity criminalEntity){
        CriminalEntity savedCriminal = criminalService.save(criminalEntity);
        return new ResponseEntity<>(savedCriminal, HttpStatus.CREATED) ;
    }

    // GET ALL
    @Operation(summary = "Get all criminals")
    @GetMapping(path = "/criminals")
    public List<CriminalEntity> listCriminals(){
        return criminalService.findAll();
    }

    // GET ONE
    @Operation(summary = "Get a criminal by ID")
    @GetMapping(path = "/criminals/{id}")
    public ResponseEntity<CriminalEntity> findCriminal(@PathVariable("id") Long id){
        Optional<CriminalEntity> foundCriminal = criminalService.findOne(id);
        return foundCriminal.map(criminalEntity -> new ResponseEntity<>(criminalEntity, HttpStatus.OK))
                .orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
            );
    }
    // FULL UPDATE
    @Operation(summary = "Fully update a criminal")
    @PutMapping(path = "/criminals/{id}")
    public ResponseEntity<CriminalEntity> fullUpdateCriminal(
            @PathVariable("id") Long id,
            @RequestBody CriminalEntity criminalEntity
            ){
        if (!criminalService.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        criminalEntity.setId(id);
        CriminalEntity savedCriminal = criminalService.save(criminalEntity);
        return new ResponseEntity<>(savedCriminal, HttpStatus.OK);
    }
    // PARTIAL UPDATE
    @Operation(summary = "Partially update a criminal")
    @PatchMapping(path = "/criminals/{id}")
    public ResponseEntity<CriminalEntity> partialUpdateCriminal(
            @PathVariable("id") Long id,
            @RequestBody CriminalEntity criminalEntity
    ){
        if (!criminalService.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CriminalEntity updatedCriminal = criminalService.partialUpdate(id, criminalEntity);
        return new ResponseEntity<>(updatedCriminal, HttpStatus.OK);
    }

    // DELETE
    @Operation(summary = "Delete a criminal by ID")
    @DeleteMapping(path = "/criminals/{id}")
    public ResponseEntity deleteCriminal(@PathVariable("id") Long id){
        criminalService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
