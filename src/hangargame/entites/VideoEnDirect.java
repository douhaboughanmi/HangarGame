package hangargame.entites;

import java.util.Objects;


public class VideoEnDirect {

    private int id_videoEnDirect;
    private String nom_videoEnDirect;
    private String url_videoEnDirect;
    private String description_videoEnDirect;

    public VideoEnDirect() {
    }
    
    

    public VideoEnDirect(String nom_videoEnDirect, String url_videoEnDirect, String description_videoEnDirect) {
        this.nom_videoEnDirect = nom_videoEnDirect;
        this.url_videoEnDirect = url_videoEnDirect;
        this.description_videoEnDirect = description_videoEnDirect;
    }

    public VideoEnDirect(int id_videoEnDirect, String nom_videoEnDirect, String url_videoEnDirect, String description_videoEnDirect) {
        this.id_videoEnDirect = id_videoEnDirect;
        this.nom_videoEnDirect = nom_videoEnDirect;
        this.url_videoEnDirect = url_videoEnDirect;
        this.description_videoEnDirect = description_videoEnDirect;
    }
    
    

    public int getId_videoEnDirect() {
        return id_videoEnDirect;
    }

    public void setId_videoEnDirect(int id_videoEnDirect) {
        this.id_videoEnDirect = id_videoEnDirect;
    }

    public String getNom_videoEnDirect() {
        return nom_videoEnDirect;
    }

    public void setNom_videoEnDirect(String nom_videoEnDirect) {
        this.nom_videoEnDirect = nom_videoEnDirect;
    }

    public String getUrl_videoEnDirect() {
        return url_videoEnDirect;
    }

    public void setUrl_videoEnDirect(String url_videoEnDirect) {
        this.url_videoEnDirect = url_videoEnDirect;
    }

    public String getDescription_videoEnDirect() {
        return description_videoEnDirect;
    }

    public void setDescription_videoEnDirect(String description_videoEnDirect) {
        this.description_videoEnDirect = description_videoEnDirect;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id_videoEnDirect;
        hash = 41 * hash + Objects.hashCode(this.nom_videoEnDirect);
        hash = 41 * hash + Objects.hashCode(this.url_videoEnDirect);
        hash = 41 * hash + Objects.hashCode(this.description_videoEnDirect);
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
        final VideoEnDirect other = (VideoEnDirect) obj;
        if (this.id_videoEnDirect != other.id_videoEnDirect) {
            return false;
        }
        if (!Objects.equals(this.nom_videoEnDirect, other.nom_videoEnDirect)) {
            return false;
        }
        if (!Objects.equals(this.url_videoEnDirect, other.url_videoEnDirect)) {
            return false;
        }
        if (!Objects.equals(this.description_videoEnDirect, other.description_videoEnDirect)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VideoEnDirect{" + "id_videoEnDirect=" + id_videoEnDirect + ", nom_videoEnDirect=" + nom_videoEnDirect + ", url_videoEnDirect=" + url_videoEnDirect + ", description_videoEnDirect=" + description_videoEnDirect + '}';
    }
    
     

   
    

}
