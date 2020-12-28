package com.maliware.let.srecruit.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date expireDate;
    private String fonctionPrincipales;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "offer_cv",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "cv_id")
    )
    private Set<Cv> cvs = new HashSet<>();
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer", orphanRemoval = true)
//    private List<Cv> cvs = new ArrayList<>();


    public void addCv(Cv cv){
        cvs.add(cv);
        cv.getOffer().add(this);
    }
    public void removeCv(Cv cv){
        cv.getOffer().remove(this);
        this.cvs.remove(cv);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id.equals(offer.id) &&
                Objects.equals(cvs, offer.cvs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvs);
    }
}
