/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Sujet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Hamza
 */
public interface ISujetCrud {

    public void ajoutersujet(Sujet s);

    public void supprimerSujet(Sujet s);

    public ObservableList<Sujet> rechercherSujet(String s);

    public ObservableList<Sujet> AffichageSuhetSujetCategorie();

    public Sujet consulterSujet(String s);
    
      public ArrayList<Sujet> afficherHistoriquePersonnel(String s);
    public int tolalJaime(String s);
    public int totalSignal(String s);
}
