package com.maliware.let.srecruit.model;


import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
public class Experience {
    private String titre;
    private String nomEmployeur;
    private String lieu;
    private boolean toujoursAEmploi;
    private Date finEmploi;
    private String responsability;
}
