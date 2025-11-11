package com.mechim.batcomputer.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriminalDto {
    private Long id;
    private String name;
    private String alias;
    private Integer age;
}
