package com.mechim.batcomputer.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CaseDetailsDto {
    private Long id;
    private String title;
    private String description;
    private Boolean solved;

    private Integer crimeTypeId;
    private String crimeTypeName;

    private Set<CriminalDto> suspects;

}
