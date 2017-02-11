/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Evenement;

/**
 *
 * @author Louay
 */
public interface IEvenementCrud {
    public  void ajouterEvenement(Evenement e);
    public void supprimerEvenement(int id);
    public void modifierEvenement(Evenement e);
    public void  afficherEvenement();
}
