/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Actualite;
import hangargame.entites.JeuxVideo;
import hangargame.services.CrudJeuxVideo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
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
    private TableColumn<?, ?> nomConsole;
    
   

    

  
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
    
    
    @FXML
    private Label image;
    String path="";
    
    private ObservableList<JeuxVideo> data ;
    @FXML
    private JFXTextField rech;
    @FXML
    private JFXButton btnCher;
   InputStream inputStream;
    @FXML
    private Label imageImage;
    @FXML
    private Hyperlink linkAjout;
  

    void afficher() {
        
        
      
        nomjeu.setCellValueFactory(new PropertyValueFactory<>("nom"));
        genrejeu.setCellValueFactory(new PropertyValueFactory<>("genre"));
        descj .setCellValueFactory(new PropertyValueFactory<>("description"));
        datejeu.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
     // nomConsole.setCellValueFactory(new PropertyValueFactory<>("nom_console"));
        
        
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
    private void showcliked(MouseEvent event) throws FileNotFoundException {
       
        
        JeuxVideo jeu = tablejeu.getSelectionModel().getSelectedItem();
        jeu.toString();
        id=jeu.getId();
        System.out.println(id);
        System.out.println(jeu.getNom());
        System.out.println(jeu.getGenre());
        titreJeu.setText(jeu.getNom());
       GenreJeu.setText(jeu.getGenre());
        descriptionjeu.setText(jeu.getDescription());
        //affichage image d'apres un url
        path=jeu.getImage();
        inputStream = new FileInputStream(jeu.getImage());
          ImageView imageView = new ImageView(new Image(inputStream));
                 imageView.setFitWidth(150);
                 imageView.setFitHeight(100);
                 image.setGraphic(imageView);
          
          
        
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
        
          JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            try {
                 inputStream = new FileInputStream(path);
                 ImageView imageView= new ImageView (new Image(inputStream));
                 imageView.setFitHeight(100);
                 imageView.setFitWidth(100);
                 image.setGraphic(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AffichageAdminJeuController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            System.out.println("image");
          //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
        
      String datej= dateJEu.
                getValue()
                .format
        (DateTimeFormatter.
                ofPattern("YYYY-MM-dd")); 
        
      
    String tj = titreJeu.getText();
    String genrej =GenreJeu.getText();
    String desj = descriptionjeu.getText();
    
    JeuxVideo jeu=new JeuxVideo(id, tj, genrej,datej , desj,path, "");
    
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

    @FXML
    private void rechercher(InputMethodEvent event) {
    }

    @FXML
    private void NaviguerAjout(ActionEvent event) throws IOException {
        
         HangarGame hang = new HangarGame();
        hang.depalcerVersAjoutJeu();
    }

}

   


    


