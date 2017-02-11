/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.entites;


import java.sql.Date;

import java.util.Objects;

/**
 *
 * @author Louay
 */
public class Evenement {
    private int id ;
    private String nom ;
    private String description ;
    private String adresse ;
    private String datedebut ;
    private String datefin ;

    public Evenement() {
    }
    

    public Evenement(int id ,String nom, String description, String adresse, String datedebut, String datefin) {
        this.id = id ;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", adresse=" + adresse + ", datedebut=" + datedebut + ", datefin=" + datefin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
