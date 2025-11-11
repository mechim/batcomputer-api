package com.mechim.batcomputer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "suspects")
@EqualsAndHashCode(exclude = "suspects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cases")
public class CaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_id_seq")
    private Long id;
    private String title;
    private String description;
    private Boolean solved;
    @ManyToOne
    @JoinColumn(name = "crime_type")
    private CrimeTypeEntity type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "suspicions",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "suspect_id")
    )
    private Set<CriminalEntity> suspects = new HashSet<>();

    public void addSuspect(CriminalEntity criminalEntity){
        if (this.suspects == null){
            this.suspects = new HashSet<>();
        }

        this.suspects.add(criminalEntity);

        if (criminalEntity.getSuspectInCases() == null){
            criminalEntity.setSuspectInCases(new HashSet<>());
        }
        criminalEntity.getSuspectInCases().add(this);
    }

    public void removeSuspect(CriminalEntity criminal) {
        this.suspects.remove(criminal);
        criminal.getSuspectInCases().remove(this);
    }
}
