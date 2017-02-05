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
 * @author lenovo
 */
public class Gamer {
    private final String login;
    private final String nom;
    private final String prenom;
    private final String adresse;
    private final String tel;
    private final String email;
   
    private final String password;
    private final Date dateInscription;
    private final String codeValidation;
    private final Date LastModifMdp;
    private final int validation;

    

    public Gamer(String login, String nom, String prenom, String adresse, String tel, String email, String password, Date dateInscription, String codeValidation, Date LastModifMdp,int validation) {
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

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public String getCodeValidation() {
        return codeValidation;
    }

    public Date getLastModifMdp() {
        return LastModifMdp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.login);
        return hash;
    }

    

   

    
   
}
