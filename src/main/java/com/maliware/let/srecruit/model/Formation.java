package com.maliware.let.srecruit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long etablissementId;
    private String etablissement;
    private String niveau;
    private String domaine;
    private String autreInformation;
    private Date dateDebut;
    private Date dateFin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cv cv;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formation formation = (Formation) o;
        return Objects.equals(etablissementId, formation.etablissementId) &&
                Objects.equals(cv, formation.cv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(etablissementId, cv);
    }
}
//ALT + INSERT = GENERATOR
//ALT + CTRL + INSERT = NEW CLASS
//ALT + SHIFT + UP/DOWN-> = MOVE a line btw Up or Down
//ALT + LEFT/RIGHT = MOVE BETWEEN OPEN FILES