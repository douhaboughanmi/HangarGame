/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.entites;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class CommentaireVideoTest {

    private int id_commentaireVideoTest;
    private String nom_CommentaireVideoTest;
    private String url_CommentaireVideoTest;
    private String description_CommentaireVideoTest;
    private Timestamp date_CommentaireVideoTest;

    public CommentaireVideoTest(int id_commentaireVideoTest, String nom_CommentaireVideoTest, String url_CommentaireVideoTest, String description_CommentaireVideoTest, Timestamp date_CommentaireVideoTest) {
        this.id_commentaireVideoTest = id_commentaireVideoTest;
        this.nom_CommentaireVideoTest = nom_CommentaireVideoTest;
        this.url_CommentaireVideoTest = url_CommentaireVideoTest;
        this.description_CommentaireVideoTest = description_CommentaireVideoTest;
        this.date_CommentaireVideoTest = date_CommentaireVideoTest;
    }

    public int getId_commentaireVideoTest() {
        return id_commentaireVideoTest;
    }

    public void setId_commentaireVideoTest(int id_commentaireVideoTest) {
        this.id_commentaireVideoTest = id_commentaireVideoTest;
    }

    public String getNom_CommentaireVideoTest() {
        return nom_CommentaireVideoTest;
    }

    public void setNom_CommentaireVideoTest(String nom_CommentaireVideoTest) {
        this.nom_CommentaireVideoTest = nom_CommentaireVideoTest;
    }

    public String getUrl_CommentaireVideoTest() {
        return url_CommentaireVideoTest;
    }

    public void setUrl_CommentaireVideoTest(String url_CommentaireVideoTest) {
        this.url_CommentaireVideoTest = url_CommentaireVideoTest;
    }

    public String getDescription_CommentaireVideoTest() {
        return description_CommentaireVideoTest;
    }

    public void setDescription_CommentaireVideoTest(String description_CommentaireVideoTest) {
        this.description_CommentaireVideoTest = description_CommentaireVideoTest;
    }

    public Timestamp getDate_CommentaireVideoTest() {
        return date_CommentaireVideoTest;
    }

    public void setDate_CommentaireVideoTest(Timestamp date_CommentaireVideoTest) {
        this.date_CommentaireVideoTest = date_CommentaireVideoTest;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id_commentaireVideoTest;
        hash = 11 * hash + Objects.hashCode(this.nom_CommentaireVideoTest);
        hash = 11 * hash + Objects.hashCode(this.url_CommentaireVideoTest);
        hash = 11 * hash + Objects.hashCode(this.description_CommentaireVideoTest);
        hash = 11 * hash + Objects.hashCode(this.date_CommentaireVideoTest);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CommentaireVideoTest other = (CommentaireVideoTest) obj;
        if (this.id_commentaireVideoTest != other.id_commentaireVideoTest) {
            return false;
        }
        if (!Objects.equals(this.nom_CommentaireVideoTest, other.nom_CommentaireVideoTest)) {
            return false;
        }
        if (!Objects.equals(this.url_CommentaireVideoTest, other.url_CommentaireVideoTest)) {
            return false;
        }
        if (!Objects.equals(this.description_CommentaireVideoTest, other.description_CommentaireVideoTest)) {
            return false;
        }
        if (!Objects.equals(this.date_CommentaireVideoTest, other.date_CommentaireVideoTest)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentaireVideoTest{" + "id_commentaireVideoTest=" + id_commentaireVideoTest + ", nom_CommentaireVideoTest=" + nom_CommentaireVideoTest + ", url_CommentaireVideoTest=" + url_CommentaireVideoTest + ", description_CommentaireVideoTest=" + description_CommentaireVideoTest + ", date_CommentaireVideoTest=" + date_CommentaireVideoTest + '}';
    }
    
    
    
}
