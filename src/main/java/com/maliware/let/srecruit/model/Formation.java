package com.maliware.let.srecruit.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
public class Formation {
    private String etablissement;
    private String niveau;
    private String domaine;
    private String autreInformation;
    private Date dateDebut;
    private Date dateFin;
}
