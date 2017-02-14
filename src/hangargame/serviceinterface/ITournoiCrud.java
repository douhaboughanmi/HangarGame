/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Tournoi;
import javafx.collections.ObservableList;

/**
 *
 * @author Louay
 */
public interface ITournoiCrud {
    public  void ajouterTournoi(Tournoi e);
    public void supprimerTournoi(int id);
    public void modifierTournoi(Tournoi e);
    public ObservableList<Tournoi> afficherTournoi();
        public ObservableList<Tournoi> RechercherTournoi(String nom);

}
