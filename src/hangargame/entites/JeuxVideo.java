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
    private int id_console ; 
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JeuxVideo() {
    }

    public JeuxVideo(int id, String nom, String genre, String date_sortie, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
        this.date_sortie = date_sortie;
        this.description = description;
        this.image = image;
    }

    public JeuxVideo(String nom, String genre, String date_sortie, String description, String image) {
        this.nom = nom;
        this.genre = genre;
        this.date_sortie = date_sortie;
        this.description = description;
        this.image = image;
    }

    
   

    public JeuxVideo(String nom, String genre, String date_sortie, String description, String image,  int id_console) {
        this.nom = nom;
        this.genre = genre;
        this.date_sortie = date_sortie;
        this.description = description;
        this.image = image;
        this.id_console = id_console;
        
    }
    
    public JeuxVideo(int id ,String nom, String genre, String date_sortie, String description, String image, int id_console) {
        this.nom = nom;
        this.id=id;
        this.genre = genre;
        this.date_sortie = date_sortie;
        this.description = description;
        this.image = image;
        this.id_console = id_console;
       
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

    public int getId_console() {
        return id_console;
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

    public void setId_console(int id_console) {
        this.id_console = id_console;
    }

    @Override
    public String toString() {
        return "JeuxVideo{" + "nom=" + nom + ", genre=" + genre + ", date_sortie=" + date_sortie + ", description=" + description + ", image=" + image + ", id_console=" + id_console +  '}';
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
        hash = 89 * hash + Objects.hashCode(this.id_console);
       
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
        if (!Objects.equals(this.id_console, other.id_console)) {
            return false;
        }
      
        
        if (!Objects.equals(this.date_sortie, other.date_sortie)) {
            return false;
        }
        return true;
    }
    
    
    
}
