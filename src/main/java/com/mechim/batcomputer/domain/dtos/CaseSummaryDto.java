package com.mechim.batcomputer.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CaseSummaryDto {
    private Long id;
    private String title;
    private String description;
    private Boolean solved;
}
