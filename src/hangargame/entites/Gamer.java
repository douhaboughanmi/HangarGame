/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.entites;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author lenovo
 */
public class Gamer {
    private  String login;
    private String nom;
    private  String prenom;
    private  String adresse;
    private int tel;
    private  String email;
   
    private String password;
    private  Timestamp dateInscription;
    private  String codeValidation;
    private  Timestamp LastModifMdp;
    private  int validation;
    private Blob image;

    public Gamer() {
    }

    

    public Gamer(String login, String nom, String prenom, String adresse,int tel, String email, String password, Timestamp dateInscription, String codeValidation, Timestamp LastModifMdp,int validation, Blob image) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.dateInscription = dateInscription;
        this.codeValidation = codeValidation;
        this.LastModifMdp = LastModifMdp;
        this.validation=validation;
        this.image=image;
    }

    public Gamer(String nom, String prenom, String adresse, int tel, String email, Timestamp dateInscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.dateInscription = dateInscription;
    }

    public Gamer(String nom, String prenom, String adresse, int tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
         this.image=image;

    }
  

    public int getValidation() {
        return validation;
    } 

    public String getLogin() {
        return login;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getDateInscription() {
        return dateInscription;
    }

    public String getCodeValidation() {
        return codeValidation;
    }

    public Timestamp getLastModifMdp() {
        return LastModifMdp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.login);
        return hash;
    }

    

   

    
   
}
