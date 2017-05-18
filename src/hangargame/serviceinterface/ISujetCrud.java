/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Sujet;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Hamza
 */
public interface ISujetCrud {

    public void ajoutersujet(Sujet s);

    public void supprimerSujet(String s);

    public ArrayList<Sujet> rechercherSujet(String s);

    public ArrayList<Sujet> AffichageSuhetSujetCategorie();

    public Sujet consulterSujet(String s);

    public ArrayList<Sujet> afficherHistoriquePersonnel(String s);

    public int tolalJaime(String s);
    public int tolalSignale(String s);

    public int totalSignal(String s);

    public int totalSujet();

    public void signalerSujet(String s);

    public void updateSignale(String s);

    public int verifSujetSignl(String s);

    public int verifGamerSujet(String s, String g);

    public void ajouterFavoris(String titre,String cont,String prop);
    public ArrayList<Sujet> afficherFavorisPersonnel(String s);

}
