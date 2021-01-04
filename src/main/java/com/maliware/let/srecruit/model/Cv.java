package com.maliware.let.srecruit.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cv", orphanRemoval = true)
    @JsonIgnore
    private List<Formation> formations = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Langues langues;
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cvs")
    @JsonIgnore
    private Set<Offer> offer = new HashSet<>();

    public void addFormation(Formation formation) {
        this.formations.add(formation);
        formation.setCv(this);
    }
    public void removeFormation(Formation formation) {
        formation.setCv(null);
        this.formations.remove(formation);
    }
    public void removeFormations() {
        Iterator<Formation> iterator = this.formations.iterator();
        while (iterator.hasNext()) {
            Formation book = iterator.next();
            book.setCv(null);
            iterator.remove();
        }
    }


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
