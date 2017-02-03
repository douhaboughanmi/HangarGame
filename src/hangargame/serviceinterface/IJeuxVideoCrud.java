/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.JeuxVideo;

/**
 *
 * @author mishka
 */
public interface IJeuxVideoCrud {
    public void ajouterJeuxVideo(JeuxVideo j);
    public void supprimerJeuxVideo(JeuxVideo j);
    public void modifierJeuxVideo(JeuxVideo j);
    public void afficherJeuxVideo();
}
