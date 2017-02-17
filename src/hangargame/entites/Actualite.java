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
     private String image ; 

    public int getId() {
        return id;
    }

    public Actualite() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Actualite(int id, String titre, String text, String image) {
        this.id = id;
        this.titre = titre;
        this.text = text;
        this.image = image;
    }
   
   

    @Override
    public String toString() {
        return "Actualite{" + "titre=" + titre + ", text=" + text + ", image=" + image + '}';
    }
   

    public Actualite(String titre, String text,  String image) {
        this.titre = titre;
        this.text = text;
        
        this.image = image;
      
    }

    public String getTitre() {
        return titre;
    }

    public String getText() {
        return text;
    }

   

    public String getImage() {
        return image;
    }

  

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setText(String text) {
        this.text = text;
    }

    

   
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.titre);
        hash = 17 * hash + Objects.hashCode(this.text);
        hash = 17 * hash + Objects.hashCode(this.image);
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
        return true;
    }
}
 

   