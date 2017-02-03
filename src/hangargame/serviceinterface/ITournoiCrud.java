/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Tournoi;

/**
 *
 * @author Louay
 */
public interface ITournoiCrud {
    public  void ajouterTournoi(Tournoi e);
    public void supprimerTournoi(Tournoi e);
    public void modifierTournoi(Tournoi e);
    public void  afficherTournoi();
}
