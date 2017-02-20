/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.entites;

import java.util.Objects;

/**
 *
 * @author DELL
 */
public class commentaireVideoTest {
    
    private int id ;
    private String Commentaire ;
    private String user;
    private String idVideo;

    public commentaireVideoTest() {
    }

  
    
    

    public commentaireVideoTest(int id, String Commentaire, String user, String idVideo) {
        this.id = id;
        this.Commentaire = Commentaire;
        this.user = user;
        this.idVideo = idVideo;
    }

    public commentaireVideoTest(String Commentaire, String user, String idVideo) {
        this.Commentaire = Commentaire;
        this.user = user;
        this.idVideo = idVideo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

   

    @Override
    public String toString() {
        return "commentaireVideoTest{" + "id=" + id + ", Commentaire=" + Commentaire + ", user=" + user + ", idVideo=" + idVideo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.Commentaire);
        hash = 67 * hash + Objects.hashCode(this.user);
        hash = 67 * hash + Objects.hashCode(this.idVideo);
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
        final commentaireVideoTest other = (commentaireVideoTest) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.Commentaire, other.Commentaire)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.idVideo, other.idVideo)) {
            return false;
        }
        return true;
    }

   
    
}
