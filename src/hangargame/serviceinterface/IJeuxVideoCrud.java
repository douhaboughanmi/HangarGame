/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.JeuxVideo;
import java.sql.Date;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author mishka
 */
public interface IJeuxVideoCrud {
    public void ajouterJeuxVideo(String nom, String genre, String date_sortie, String description, String image, String nom_console, String video_ba);
    public void supprimerJeuxVideo(String nom);
    public void modifierJeuxVideo(JeuxVideo j);
    public ObservableList<JeuxVideo>  afficherJeuxVideo();
    
}
