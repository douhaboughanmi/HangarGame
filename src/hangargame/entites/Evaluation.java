/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.entites;

import java.util.Objects;

/**
 *
 * @author mishka
 */
public class Evaluation {
    private int id ; 
    private String nom;
    private String email_client ; 
    private String nom_jeu ; 
    private int note ; 
    

    public Evaluation(String nom,String email_client, String nom_jeu, int note) {
      this.nom=nom;
        this.email_client = email_client;
        this.nom_jeu = nom_jeu;
        this.note = note;
    }

    public Evaluation() {
    }

    public String getEmail_client() {
        return email_client;
    }
    public String getnom() {
        return nom;
    }

    public String getNom_jeu() {
        return nom_jeu;
    }

    public int getNote() {
        return note;
    }

    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }

    public void setNom_jeu(String nom_jeu) {
        this.nom_jeu = nom_jeu;
    }

    public void setNote(int note) {
        this.note = note;
    }
    public void setNom(String nom) {
        this.nom=nom;
    }

    @Override
    public String toString() {
        return "Evaluation{"+"nom="+nom + "email_client=" + email_client + ", nom_jeu=" + nom_jeu + ", note=" + note + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
         hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.email_client);
        hash = 67 * hash + Objects.hashCode(this.nom_jeu);
        hash = 67 * hash + this.note;
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
        final Evaluation other = (Evaluation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.note != other.note) {
            return false;
        }
        if (!Objects.equals(this.email_client, other.email_client)) {
            return false;
        }
        if (!Objects.equals(this.nom_jeu, other.nom_jeu)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
