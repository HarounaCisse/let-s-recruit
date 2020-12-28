package com.maliware.let.srecruit.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cv implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Embedded
    private Experience experience;
    @Embedded
    private Formation formation;
    @Enumerated(EnumType.STRING)
    private Langues langues;
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "offer_id")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cvs")
    @JsonIgnore
    private Set<Offer> offer = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cv cv = (Cv) o;
        return Objects.equals(id, cv.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
