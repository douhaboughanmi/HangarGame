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
 * @author Hamza
 */
public class Sujet {

    private int idSjt;
    private String nomSjt;
    private Date datePub;
    private String gamer;
    private String txtSjt;
    private String categorie;
    static private int etat;

    public Sujet(String s, String st, String cat) {
       // this.gamer = g;
//   this.datePub =d;
//this.id=i;
        this.txtSjt = st;
        this.nomSjt = s;
        this.categorie = cat;
        // Sujet.etat=e = 0;
    }

    public Sujet() {

    }

    public int getidSjt() {
        return this.idSjt;
    }

    public Date getdateSjt() {
        return this.datePub;
    }

    public String getNomSujet() {
        return this.nomSjt;
    }

    public String gettextSujet() {
        return this.txtSjt;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public int getetat() {
        return this.etat;
    }

    public void setIdSjt(int id) {
        this.idSjt = id;
    }

    public String getGamer() {
        return this.gamer;
    }

    public void setNomSjt(String nmsjt) {
        this.nomSjt = nmsjt;
    }

    public void setDateSjt(Date dsjt) {
        this.datePub = dsjt;
    }

    public void setEtatSJt(int etatsjt) {
        this.etat = etatsjt;
    }

    public void setCategoriForum(String ctfrm) {
        this.categorie = ctfrm;
    }

    public void setGamer(String gmr) {
        this.gamer = gmr;
    }

    public void setTxtSujet(String txt) {
        this.txtSjt = txt;
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
        final Sujet other = (Sujet) obj;

        if (!Objects.equals(this.nomSjt, other.getNomSujet())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.idSjt;
    }
}
