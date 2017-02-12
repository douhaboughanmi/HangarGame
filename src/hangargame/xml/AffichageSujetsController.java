/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Sujet;
import hangargame.services.CrudSujet;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class AffichageSujetsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadData();
    }
    CrudSujet crs = new CrudSujet();
   
    @FXML
    private TableView<Sujet> table;

    @FXML
    private TableColumn<Sujet, String> A;

    @FXML
    private TableColumn<Sujet, String> B;

    @FXML
    private TableColumn<Sujet, Timestamp> C;

    @FXML
    private TableColumn<Sujet, String> D;

    @FXML
    private TableColumn<Sujet, String> E;

    @FXML
    private TableColumn<Sujet, Integer> F;
    
     @FXML
    private JFXButton search;

    @FXML
    private ImageView img;
      @FXML
    private JFXTextField champsRech;

    @FXML
    void rechercher(ActionEvent event) {
        
       String nomSujet = champsRech.getText();
        A.setCellValueFactory(new PropertyValueFactory<>("nomSjt"));
        B.setCellValueFactory(new PropertyValueFactory<>("datePub"));
        C.setCellValueFactory(new PropertyValueFactory<>("txtSjt"));
        D.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        E.setCellValueFactory(new PropertyValueFactory<>("gamer"));
        F.setCellValueFactory(new PropertyValueFactory<>("etat"));
        table.setItems(null);
        table.setItems(crs.rechercherSujet(nomSujet));

    }
    
     void LoadData(){
        
        A.setCellValueFactory(new PropertyValueFactory<>("nomSjt"));
        B.setCellValueFactory(new PropertyValueFactory<>("datePub"));
        C.setCellValueFactory(new PropertyValueFactory<>("txtSjt"));
        D.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        E.setCellValueFactory(new PropertyValueFactory<>("gamer"));
        F.setCellValueFactory(new PropertyValueFactory<>("etat"));
        table.setItems(null);
        table.setItems(crs.AffichageSuhetSujetCategorie());
    
    }
}
