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
public class Actualite {
    
    private int id ; 
    private String titre ; 
    private String text ; 
    private Date date_debut ; 
    private Date date_fin ; 
    private String image ; 
    private String Video ; 

    public Actualite(String titre, String text, Date date_debut, Date date_fin, String image, String Video) {
        this.titre = titre;
        this.text = text;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.Video = Video;
    }

    public String getTitre() {
        return titre;
    }

    public String getText() {
        return text;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return Video;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVideo(String Video) {
        this.Video = Video;
    }

    @Override
    public String toString() {
        return "Actualite{" + "titre=" + titre + ", text=" + text + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", image=" + image + ", Video=" + Video + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.titre);
        hash = 67 * hash + Objects.hashCode(this.text);
        hash = 67 * hash + Objects.hashCode(this.date_debut);
        hash = 67 * hash + Objects.hashCode(this.date_fin);
        hash = 67 * hash + Objects.hashCode(this.image);
        hash = 67 * hash + Objects.hashCode(this.Video);
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
        final Actualite other = (Actualite) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.Video, other.Video)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }
    
    
}
