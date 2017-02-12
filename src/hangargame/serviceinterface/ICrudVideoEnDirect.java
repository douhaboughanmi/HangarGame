/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.serviceinterface;

import hangargame.entites.VideoEnDirect;



/**
 *
 * @author DELL
 */
public interface ICrudVideoEnDirect {
    
      public void ajouter(VideoEnDirect v);

    public void supprimer(int id);

    public void modifier(VideoEnDirect v);

    public void afficher();

    
}
