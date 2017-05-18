/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXListView;
import hangargame.HangarGame;
import hangargame.entites.Console;
import hangargame.services.CrudConsole;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AffichageClientConsolesController implements Initializable {

    
    @FXML
    private JFXListView<Label> listConsole;
     List<String> liste = new ArrayList<>();
    
    List<String> listNomc;
    List<String> listdesc;
    List<Date> listdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CrudConsole crud = new CrudConsole();
        List<Console> list = crud.afficherConsole();
        listConsole.getItems().clear();
            
       
           




        for (int i = 0; i < list.size(); i++) {
             Date dd=(Date)list.get(i).getDate_sortie();

            Label lbl = new Label(list.get(i).getNom() +"\n" 
                    + list.get(i).getDescription() +"\n"+dd);
            lbl.setPrefSize(500, 200);
            liste.add(list.get(i).getId() + "");
             // listConsole.getItems().add(lbl);
         
        try {
                 InputStream inputStream= new FileInputStream(list.get(i).getImage());
                 ImageView imageView = new ImageView(new Image(inputStream));
                 imageView.setFitWidth(150);
                 imageView.setFitHeight(100);
                  listConsole.getItems().add(lbl);
          lbl.setGraphic(imageView);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(AffichageClientConsolesController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
            
          

        
    }    
    
}

    @FXML
    private void deplacerAccueil(ActionEvent event) throws IOException {
        
          
        HangarGame hang =new HangarGame();
        hang.depalcerlAccueil();
    }
}