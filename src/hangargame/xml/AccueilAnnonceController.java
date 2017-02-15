/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import hangargame.HangarGame;
import hangargame.entites.Annonces;
import hangargame.services.CrudAnnonces;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mayss
 */


  
public class AccueilAnnonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     ObservableList<Label> lab;
    @FXML
    private JFXListView<Label> listAnnonces;
    

    String path = "";
   // @FXML
   // private JFXButton btn;
    List<String> listNomA;
    List<String> listTypeA;
    List<Integer> listPrix;
HangarGame hangar = new HangarGame();
   
     @FXML
    void reccupererAnnonceEchange(ActionEvent event) {

        System.out.println("djjdjdjdj");
       // listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonEchange();
listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());
            
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);
            
           
            
            
            
            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);

            listAnnonces.setStyle("-fx-background-color: #4FB978  ; -fx-text-fill: black; ");
        }

    }
    
    

    @FXML
    void reccupererAnnonceVente(ActionEvent event) {

        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonVente();
 listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);

           
            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnoncePC(ActionEvent event) {

        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPC();
listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnoncePS4(ActionEvent event) {

        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPS4();
listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }
        
    }
    
    
    @FXML
    void reccupererAnnoncePS3(ActionEvent event) {

        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPS3();
listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }
        
    }
    
    @FXML
    void reccupererAnnonceXbox360(ActionEvent event) {

        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonXbox360();
listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }
        
    }
    
    @FXML
    void reccupererAnnonceXboxOne(ActionEvent event) {

         System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonXboxOne();
listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }
        
    }
    
    
      @FXML
    void reccupererAnnoncePSVita(ActionEvent event) {

        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPSVita();
listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSimple();

        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + 
                   list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(70);

            lbl.setGraphic(imageView);
            

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
    }    
    
}

@FXML
    void CreerVotrePropreAnnonce(ActionEvent event) {
         try {
             hangar.depalcerVersAjoutAnnonce();
         } catch (IOException ex) {
             Logger.getLogger(AccueilAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
