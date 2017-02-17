/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Actualite;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author mishka
 */
public interface IActualiteCrud {
    public void ajouterActualite(Actualite a );
    public void supprimerActualite (String titre );
    public void modifierActualite(Actualite a);
     public ObservableList<Actualite>  afficherActualite();
     public List<Actualite> reccuperer();
    
}

    