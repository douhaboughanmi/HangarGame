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
 * @author mishka
 */
public class JeuxVideo {
    
    private int id ;
    private String nom ; 
    private String genre ; 
    private String date_sortie ; 
    private String description ;
    private String image ; 
    private String nom_console ; 
    private String video_ba ; 

    public JeuxVideo() {
    }
    

    public JeuxVideo(String nom, String genre, String date_sortie, String description, String image, String nom_console, String video_ba) {
        this.nom = nom;
        this.genre = genre;
        this.date_sortie = date_sortie;
        this.description = description;
        this.image = image;
        this.nom_console = nom_console;
        this.video_ba = video_ba;
    }

    public String getNom() {
        return nom;
    }

    public String getGenre() {
        return genre;
    }

    public String getDate_sortie() {
        return date_sortie;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getNom_console() {
        return nom_console;
    }

    public String getVideo_ba() {
        return video_ba;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNom_console(String nom_console) {
        this.nom_console = nom_console;
    }

    public void setVideo_ba(String video_ba) {
        this.video_ba = video_ba;
    }

    @Override
    public String toString() {
        return "JeuxVideo{" + "nom=" + nom + ", genre=" + genre + ", date_sortie=" + date_sortie + ", description=" + description + ", image=" + image + ", nom_console=" + nom_console + ", video_ba=" + video_ba + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nom);
        hash = 89 * hash + Objects.hashCode(this.genre);
        hash = 89 * hash + Objects.hashCode(this.date_sortie);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.image);
        hash = 89 * hash + Objects.hashCode(this.nom_console);
        hash = 89 * hash + Objects.hashCode(this.video_ba);
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
        final JeuxVideo other = (JeuxVideo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.nom_console, other.nom_console)) {
            return false;
        }
        if (!Objects.equals(this.video_ba, other.video_ba)) {
            return false;
        }
        if (!Objects.equals(this.date_sortie, other.date_sortie)) {
            return false;
        }
        return true;
    }
    
    
    
}
