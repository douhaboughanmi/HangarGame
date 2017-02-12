/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Actualite;
import javafx.collections.ObservableList;


/**
 *
 * @author mishka
 */
public interface IActualiteCrud {
    public void ajouterActualite(Actualite a );
    public void supprimerActualite (Actualite a );
    public void modifierActualite(Actualite a);
     public ObservableList<Actualite>  afficherActualite();
    
}

    