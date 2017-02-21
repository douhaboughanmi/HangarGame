/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.entites.Actualite;
import hangargame.services.CrudActualite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AffichageAdminActualiteController implements Initializable {

    CrudActualite crud = new CrudActualite();
    @FXML
    private TableView<Actualite> tableAct;

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TableColumn<?, ?> colDesc;
    @FXML
    private JFXTextField titreAct;
    @FXML
    private TextArea descriptionact;
    @FXML
    private JFXButton BtnImage;
    @FXML
    private Label image;
    @FXML
    private JFXButton btnSup;
    @FXML
    private JFXButton btnmodif;
    int id ;
    String path="";
      InputStream inputStream;
    @FXML
    private Hyperlink hypAjou;


    void afficher() {
        
        
        
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("text"));
       // colImage .setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
       tableAct.setItems(null);
       tableAct.setItems(crud.afficherActualite());
                    

    }
    
   
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
        // TODO
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
                Logger.getLogger(AjoutAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            System.out.println("image");
          //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        Actualite a = new Actualite();
        CrudActualite cc = new CrudActualite();
        cc.supprimerActualite(tableAct.getSelectionModel().getSelectedItem().getTitre());
        afficher();
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
         String ta = titreAct.getText();
    String desc =descriptionact.getText();
    
    Actualite act = new Actualite(id, ta, desc, "");
    crud .modifierActualite(act);
    afficher() ; 
    }

    @FXML
    private void showcliked(MouseEvent event) throws FileNotFoundException {
        
          Actualite act= tableAct.getSelectionModel().getSelectedItem();
       act.toString();
        id=act.getId();
        System.out.println(id);
        System.out.println(act.getTitre());
        System.out.println(act.getText());
       titreAct.setText(act.getTitre());
     descriptionact.setText(act.getText());
     
       path=act.getImage();
        inputStream = new FileInputStream(act.getImage());
          ImageView imageView = new ImageView(new Image(inputStream));
                 imageView.setFitWidth(150);
                 imageView.setFitHeight(100);
                 image.setGraphic(imageView);
        
    }

    @FXML
    private void RetourAjoutAct(ActionEvent event) throws IOException {
        
         HangarGame hang = new HangarGame();
         hang.depalcerVersAjoutAct();
    }
    
}
