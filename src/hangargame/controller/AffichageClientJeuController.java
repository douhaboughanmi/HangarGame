/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import hangargame.HangarGame;
import hangargame.entites.JeuxVideo;
import hangargame.services.CrudJeuxVideo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AffichageClientJeuController implements Initializable {

      ObservableList<Label> lab;
    @FXML
    private JFXListView<Label> listJeu;
     InputStream input;

    String path = "";
   // @FXML
   // private JFXButton btn;
    List<String> listNomj;
    List<String> listGenreJ;
    List<String> listDescJ;
    List<String> listDateJ;
    HangarGame hangar = new HangarGame();
    List<String> liste = new ArrayList<>();
     static public String indexjeu ="";
    @FXML
    private JFXButton btnstat;
    /**
     * Initializes the controller class.
     */
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CrudJeuxVideo crud = new CrudJeuxVideo();
        List<JeuxVideo> list = crud.afficherJeuxVideo();
        
        listJeu.getItems().clear();
        for (int i = 0; i < list.size(); i++) {
            

            Label lbl = new Label(list.get(i).getNom() + "\n"+ list.get(i).getGenre() +  "\n" + list.get(i).getDescription()+  "\n" + list.get(i).getDate_sortie());
    lbl.setPrefSize(300, 100);
    liste.add(list.get(i).getId() + "");
            System.out.println(liste);
             try {
                 InputStream inputStream= new FileInputStream(list.get(i).getImage());
                 ImageView imageView = new ImageView(new Image(inputStream));
                 imageView.setFitWidth(150);
                 imageView.setFitHeight(100);
                  listJeu.getItems().add(lbl);

          

          lbl.setGraphic(imageView);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(AffichageClientJeuController.class.getName()).log(Level.SEVERE, null, ex);
             }
           
            
           
            

          
    }    
    }    

    @FXML
    private void Recuperer(MouseEvent event) 
        
        throws IOException {
        int a = listJeu.getSelectionModel().getSelectedIndex();

        indexjeu = liste.get(a);
        System.out.println("indeeeeeeeex"+indexjeu);
       hangar.depalcerVersDetailJeu();

       
    }

    @FXML
    private void stat(ActionEvent event) {
        
        HangarGame.showStat();
    }
        
        
    }
    

