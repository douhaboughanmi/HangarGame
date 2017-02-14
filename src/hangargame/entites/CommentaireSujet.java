/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.entites;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 *
 * @author Hamza
 */
public class CommentaireSujet {

    private int idComentaire;
    private String nomComnt;
    private Timestamp datePubComnt;
    private String sujet;
    private String txtComnt;
    private int etatCommentaire;
    /*Instant dpc,*/

    public CommentaireSujet(String nomcomment, String textcomment,
            String sujet, int etatcmmentaire) {
        //this.idComentaire = idc;
        //  this.datePubComnt = dpc;
        this.nomComnt = nomcomment;
        this.txtComnt = textcomment;
        this.sujet = sujet;
        this.etatCommentaire = etatcmmentaire;
    }

    public CommentaireSujet() {

    }

    public CommentaireSujet(String s, String sjt) {
        this.txtComnt = s;
        //this.datePubComnt = t;
        this.sujet = sjt;
    }
     public CommentaireSujet(String s, Timestamp t) {
        this.txtComnt = s;
        this.datePubComnt = t;
//        this.sujet = sjt;
    }

    public int getidCommentaire() {
        return this.idComentaire;
    }

    public void setidCommentaire(int idcomnt) {
        this.idComentaire = idcomnt;
    }

    public Timestamp getdatecoment() {
        return this.datePubComnt;
    }

    public void setdateCommentaire(Timestamp datecomnt) {
        this.datePubComnt = datecomnt;
    }

    public String getNomCmnt() {
        return this.nomComnt;
    }

    public void setNomComnt(String nmcmnt) {
        this.nomComnt = nmcmnt;
    }

    public String gettextCommnt() {
        return this.txtComnt;
    }

    public void setTextCmnt(String txtcmnt) {
        this.txtComnt = txtcmnt;
    }

    public String getsujet() {
        return this.sujet;
    }

    public void setSujet(String sjt) {
        this.sujet = sjt;
    }

    public int getetatCmnt() {
        return this.etatCommentaire;
    }

    public void setetatCmnt(int etatcmnt) {
        this.etatCommentaire = etatcmnt;
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
        final CommentaireSujet other = (CommentaireSujet) obj;

        if (!Objects.equals(this.idComentaire, other.getidCommentaire())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.idComentaire;
    }
}
