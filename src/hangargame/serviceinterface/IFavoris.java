/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.serviceinterface;

import hangargame.entites.Annonces;
import java.util.List;

/**
 *
 * @author mayss
 */
public interface IFavoris {
     public String ajouterAnnoncesFavoris();
      public List<Annonces> afficherFavoris() ;
}
