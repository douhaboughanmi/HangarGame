/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXListView;
import hangargame.entites.Console;
import hangargame.services.CrudConsole;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AffichageClientConsolesController implements Initializable {

    
    @FXML
    private JFXListView<Label> listConsole;
    
    List<String> listNomc;
    List<String> listdesc;
    List<String> listdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CrudConsole crud = new CrudConsole();
        List<Console> list = crud.afficherConsole();
        listConsole.getItems().clear();

        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNom() +"\n" 
                    + list.get(i).getDescription() +"\n"+list.get(i).getDate_sortie());
            lbl.setPrefSize(300, 100);
         listConsole.getItems().add(lbl);

        
    }    
    
}
}