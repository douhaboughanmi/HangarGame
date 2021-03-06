/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.entites;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Louay
 */
public class Tournoi {
    private int id ;
    private String nom ;
    private String nom_jeu ;
    private int nbr_max ;
    private LocalDate datedebut ;
    private LocalDate datefin ;
    

    public Tournoi() {
    }

    public Tournoi(int id , String nom, String nom_jeu, int nbr_max, LocalDate datedebut, LocalDate datefin) {
        this.id=id;
        this.nom = nom;
        this.nom_jeu = nom_jeu;
        this.nbr_max = nbr_max;
        this.datedebut = datedebut;
        this.datefin = datefin;
        
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNom_jeu() {
        return nom_jeu;
    }

    public int getNbr_max() {
        return nbr_max;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNom_jeu(String nom_jeu) {
        this.nom_jeu = nom_jeu;
    }

    public void setNbr_max(int nbr_max) {
        this.nbr_max = nbr_max;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nom=" + nom + ", nom_jeu=" + nom_jeu + ", nbr_max=" + nbr_max + ", datedebut=" + datedebut + ", datefin=" + datefin+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Tournoi other = (Tournoi) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
    
    
}
