/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Console;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author mishka
 */
public interface IConsoleCrud {
    public void ajouterConsole(Console c);
    public void supprimerConsole(Console c);
    public void modifierConsole(Console c);
    public ObservableList<Console>  afficherConsole();
}
