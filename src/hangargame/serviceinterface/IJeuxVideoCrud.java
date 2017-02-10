/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.JeuxVideo;
import java.sql.Date;

/**
 *
 * @author mishka
 */
public interface IJeuxVideoCrud {
    public void ajouterJeuxVideo(String nom, String genre, String date_sortie, String description, String image, String nom_console, String video_ba);
    public void supprimerJeuxVideo(JeuxVideo j);
    public void modifierJeuxVideo(JeuxVideo j);
    public void afficherJeuxVideo();
}
