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
public class Console {
    
    private int id ;
    private String nom ;
    private String image ; 
    private String description ;
   

    
    private String date_sortie ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Console(int id, String nom, String image, String description, String date_sortie) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.date_sortie = date_sortie;
    }
    
    
    public Console()
    {
    
    }
    
    public Console(String nom,String image,String description,String date_sortie)
    {
    this.nom = nom;
    this.image =image ; 
    this.description=description;
    
    this.date_sortie=date_sortie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

  

    public String getDate_sortie() {
        return date_sortie;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.image);
        hash = 59 * hash + Objects.hashCode(this.description);
       
        hash = 59 * hash + Objects.hashCode(this.date_sortie);
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
        final Console other = (Console) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
     
        }
        if (!Objects.equals(this.date_sortie, other.date_sortie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Console{" + "nom=" + nom + ", image=" + image + ", description=" + description + ",  date_sortie=" + date_sortie + '}';
    }
}
