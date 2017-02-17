/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Actualite;
import hangargame.entites.JeuxVideo;
import hangargame.services.CrudJeuxVideo;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
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
    private TableColumn<?, ?> colid;
    @FXML
    private JFXButton btnSup;
    @FXML
    private JFXTextField titreJeu;
    @FXML
    private JFXTextField GenreJeu;
    @FXML
    private TextArea descriptionjeu;
    @FXML
    private JFXButton BtnImage;
    @FXML
    private JFXButton btnmodif;
    int id ; 
    @FXML
    private JFXDatePicker dateJEu;
    
    
    
    
    private ObservableList<JeuxVideo> data ;
    @FXML
    private JFXTextField rech;
    @FXML
    private JFXButton btnCher;
   

    void afficher() {
        
        
      
        nomjeu.setCellValueFactory(new PropertyValueFactory<>("nom"));
        genrejeu.setCellValueFactory(new PropertyValueFactory<>("genre"));
        descj .setCellValueFactory(new PropertyValueFactory<>("description"));
        datejeu.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
     //  image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
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
       afficher();
        
        
    }

    @FXML
    private void showcliked(MouseEvent event) {
       
        
        JeuxVideo jeu = tablejeu.getSelectionModel().getSelectedItem();
        jeu.toString();
        id=jeu.getId();
        System.out.println(id);
        System.out.println(jeu.getNom());
        System.out.println(jeu.getGenre());
        titreJeu.setText(jeu.getNom());
       GenreJeu.setText(jeu.getGenre());
        descriptionjeu.setText(jeu.getDescription());
          String datej= dateJEu.
                getValue()
                .format
        (DateTimeFormatter.
                ofPattern("YYYY-MM-DD"));    
          
        
      //  image.setText(jeu.getImage());

             
        
        // date a faire 
        
       // input=jeu.getInputStream();
     //   ImageView imageView = new ImageView(new Image(annonces.getInputStream()));
     //       imageView.setFitHeight(112);
        //    imageView.setFitWidth(178);

         //   imageMesAnnonces.setGraphic(imageView);
    }

    @FXML
    private void selectImage(ActionEvent event) {
        
        
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
        
      String datej= dateJEu.
                getValue()
                .format
        (DateTimeFormatter.
                ofPattern("YYYY-MM-DD")); 
      
    String tj = titreJeu.getText();
    String genrej =GenreJeu.getText();
    String desj = descriptionjeu.getText();
    JeuxVideo jeu=new JeuxVideo(id, tj, genrej,datej , desj,"", "");
    
    crud.modifierJeuxVideo(jeu);
    afficher();
    
//    if(crud.modifierJeuxVideo(jeu))
//    {
//        System.out.println("OOKK");
//        data=FXCollections.observableArrayList();
//     data.addAll(crud.reccuperer());
//     afficher();
//    }
//    else
//    {
//        System.out.println("Non");
//    }
     System.out.println(genrej);
    }
   
   
   
   // data=FXCollections.observableArrayList();

    @FXML
    private void rechercher(ActionEvent event) {
         String nomJeu = rech.getText();
         
          nomjeu.setCellValueFactory(new PropertyValueFactory<>("nom"));
        genrejeu.setCellValueFactory(new PropertyValueFactory<>("genre"));
        descj .setCellValueFactory(new PropertyValueFactory<>("description"));
        datejeu.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
     // image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
        tablejeu.setItems(null);
        tablejeu.setItems(crud.rechercher(nomJeu));
       
        
        
    }

}

   


    


