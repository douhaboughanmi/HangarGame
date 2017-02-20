
package hangargame.entites;

import java.sql.Timestamp;
import java.util.Objects;


public class VideoTest {
    
    private int id_videoTest;
    private String nom_videoTest;
    private String url_videoTest;      
    private String description_videoTest;
    private Timestamp date_videoTest;
    private String genre_videoTest;
    private String console_videoTest;
    private String user_videoTest;

    public VideoTest() {
    }

    public VideoTest(int id_videoTest, String nom_videoTest, String description_videoTest, String genre_videoTest, String console_videoTest) {
        this.id_videoTest = id_videoTest;
        this.nom_videoTest = nom_videoTest;
        this.description_videoTest = description_videoTest;
        this.genre_videoTest = genre_videoTest;
        this.console_videoTest = console_videoTest;
    }

    
    
    

    public VideoTest(int id_videoTest, String nom_videoTest, String url_videoTest, String description_videoTest, Timestamp date_videoTest, String genre_videoTest, String console_videoTest) {
        this.id_videoTest = id_videoTest;
        this.nom_videoTest = nom_videoTest;
        this.url_videoTest = url_videoTest;
        this.description_videoTest = description_videoTest;
        this.date_videoTest = date_videoTest;
        this.genre_videoTest = genre_videoTest;
        this.console_videoTest = console_videoTest;
    }
    
    

    public VideoTest(String nom_videoTest, String url_videoTest, String description_videoTest, String genre_videoTest, String console_videoTest, String user_videoTest) {
        this.nom_videoTest = nom_videoTest;
        this.url_videoTest = url_videoTest;
        this.description_videoTest = description_videoTest;
        this.genre_videoTest = genre_videoTest;
        this.console_videoTest = console_videoTest;
        this.user_videoTest = user_videoTest;
    }

    public VideoTest(int id_videoTest, String nom_videoTest, String url_videoTest, String description_videoTest, Timestamp date_videoTest, String genre_videoTest, String console_videoTest, String user_videoTest) {
        this.id_videoTest = id_videoTest;
        this.nom_videoTest = nom_videoTest;
        this.url_videoTest = url_videoTest;
        this.description_videoTest = description_videoTest;
        this.date_videoTest = date_videoTest;
        this.genre_videoTest = genre_videoTest;
        this.console_videoTest = console_videoTest;
        this.user_videoTest = user_videoTest;
    }

    public VideoTest(String nom_videoTest, String url_videoTest, String description_videoTest, Timestamp date_videoTest, String genre_videoTest, String console_videoTest, String user_videoTest) {
        this.nom_videoTest = nom_videoTest;
        this.url_videoTest = url_videoTest;
        this.description_videoTest = description_videoTest;
        this.date_videoTest = date_videoTest;
        this.genre_videoTest = genre_videoTest;
        this.console_videoTest = console_videoTest;
        this.user_videoTest = user_videoTest;
    }

    public int getId_videoTest() {
        return id_videoTest;
    }

    public void setId_videoTest(int id_videoTest) {
        this.id_videoTest = id_videoTest;
    }

    public String getNom_videoTest() {
        return nom_videoTest;
    }

    public void setNom_videoTest(String nom_videoTest) {
        this.nom_videoTest = nom_videoTest;
    }

    public String getUrl_videoTest() {
        return url_videoTest;
    }

    public void setUrl_videoTest(String url_videoTest) {
        this.url_videoTest = url_videoTest;
    }

    public String getDescription_videoTest() {
        return description_videoTest;
    }

    public void setDescription_videoTest(String description_videoTest) {
        this.description_videoTest = description_videoTest;
    }

    public Timestamp getDate_videoTest() {
        return date_videoTest;
    }

    public void setDate_videoTest(Timestamp date_videoTest) {
        this.date_videoTest = date_videoTest;
    }

    public String getGenre_videoTest() {
        return genre_videoTest;
    }

    public void setGenre_videoTest(String genre_videoTest) {
        this.genre_videoTest = genre_videoTest;
    }

    public String getConsole_videoTest() {
        return console_videoTest;
    }

    public void setConsole_videoTest(String console_videoTest) {
        this.console_videoTest = console_videoTest;
    }

    public String getUser_videoTest() {
        return user_videoTest;
    }

    public void setUser_videoTest(String user_videoTest) {
        this.user_videoTest = user_videoTest;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_videoTest;
        hash = 53 * hash + Objects.hashCode(this.nom_videoTest);
        hash = 53 * hash + Objects.hashCode(this.url_videoTest);
        hash = 53 * hash + Objects.hashCode(this.description_videoTest);
        hash = 53 * hash + Objects.hashCode(this.date_videoTest);
        hash = 53 * hash + Objects.hashCode(this.genre_videoTest);
        hash = 53 * hash + Objects.hashCode(this.console_videoTest);
        hash = 53 * hash + Objects.hashCode(this.user_videoTest);
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
        final VideoTest other = (VideoTest) obj;
        if (this.id_videoTest != other.id_videoTest) {
            return false;
        }
        if (!Objects.equals(this.nom_videoTest, other.nom_videoTest)) {
            return false;
        }
        if (!Objects.equals(this.url_videoTest, other.url_videoTest)) {
            return false;
        }
        if (!Objects.equals(this.description_videoTest, other.description_videoTest)) {
            return false;
        }
        if (!Objects.equals(this.date_videoTest, other.date_videoTest)) {
            return false;
        }
        if (!Objects.equals(this.genre_videoTest, other.genre_videoTest)) {
            return false;
        }
        if (!Objects.equals(this.console_videoTest, other.console_videoTest)) {
            return false;
        }
        if (!Objects.equals(this.user_videoTest, other.user_videoTest)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VideoTest{" + "id_videoTest=" + id_videoTest + ", nom_videoTest=" + nom_videoTest + ", url_videoTest=" + url_videoTest + ", description_videoTest=" + description_videoTest + ", date_videoTest=" + date_videoTest + ", genre_videoTest=" + genre_videoTest + ", console_videoTest=" + console_videoTest + ", user_videoTest=" + user_videoTest + '}';
    }

   





    
    
    
}
