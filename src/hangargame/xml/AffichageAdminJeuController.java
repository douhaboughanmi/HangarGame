/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Actualite;
import hangargame.entites.JeuxVideo;
import hangargame.services.CrudJeuxVideo;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AffichageAdminJeuController implements Initializable {

     CrudJeuxVideo crud = new CrudJeuxVideo();
     
  
    @FXML
    private TableView<JeuxVideo> tablejeu;

    @FXML
    private TableColumn<?, ?> nomjeu;

    @FXML
    private TableColumn<?, ?> genrejeu;

    @FXML
    private TableColumn<?, ?> datejeu;

    @FXML
    private TableColumn<?, ?> descj;

    @FXML
    private TableColumn<?, ?> image;

  
    @FXML
    private JFXButton btnAfficher= new JFXButton();
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private JFXButton btnSup;

    @FXML
    void afficher() {
        
        
      
        nomjeu.setCellValueFactory(new PropertyValueFactory<>("nom"));
        genrejeu.setCellValueFactory(new PropertyValueFactory<>("genre"));
        descj .setCellValueFactory(new PropertyValueFactory<>("description"));
        datejeu.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
        tablejeu.setItems(null);
        tablejeu.setItems(crud.afficherJeuxVideo());
                    
               
                   
                    
                
        
        
        }
    
    

     
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        
       
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        
      JeuxVideo j = new JeuxVideo();
        CrudJeuxVideo cj = new CrudJeuxVideo();
      cj.supprimerJeuxVideo(tablejeu.getSelectionModel().getSelectedItem().getNom());
       //afficher();
        
        
    }
    
}

