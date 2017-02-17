/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Console;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author mishka
 */
public interface IConsoleCrud {
    public void ajouterConsole(Console c);
   public void supprimerConsole(String nom );
    public void modifierConsole(Console c);
    public List<Console> reccuperer();
    public ObservableList<Console>  afficherConsole();
}
