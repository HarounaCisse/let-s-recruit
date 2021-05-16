package com.maliware.let.srecruit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Data
@Entity
public class Competence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Lob
    //@SOUSSOKO2
    @NotNull(message = "Competence can not be null")
    private String competence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cv cv;

    public Competence(){}
    public Competence(String competence){
        this.competence = competence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return id.equals(that.id) &&
                Objects.equals(cv, that.cv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cv);
    }

    @Override
    public String toString() {
        return "Competence{" +
                "id=" + id +
                ", competence='" + competence + '\'' +
                '}';
    }
}
