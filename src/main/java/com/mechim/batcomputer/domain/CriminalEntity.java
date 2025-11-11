package com.mechim.batcomputer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "suspectInCases")
@EqualsAndHashCode(exclude = "suspectInCases")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="criminals")
public class CriminalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "criminal_id_seq")
    private Long id;
    private String name;
    private Integer age;
    private String alias;

    @ManyToMany (mappedBy = "suspects")
    @JsonIgnore
    private Set<CaseEntity> suspectInCases = new HashSet<>();
}
