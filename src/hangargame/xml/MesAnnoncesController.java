/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.entites.Annonces;
import hangargame.entites.Evenement;
import hangargame.services.CrudAnnonces;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author mayss
 */
public class MesAnnoncesController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TableView<Annonces> tableAnnonce;

    @FXML
    private TableColumn<Annonces,String> colNomAnnonce;

    @FXML
    private TableColumn<Annonces, String> colTypeAnnonce;

    @FXML
    private TableColumn<Annonces, String> colConsoleAnnonce;

    @FXML
    private TableColumn<Annonces, String> colPrixAnnonce;

    @FXML
    private TableColumn<Annonces, String> colDescriptionAnnonce;
    
    private ObservableList<Annonces> data;
    
     @FXML
    private JFXTextField nomMesAnnonces;

    @FXML
    private JFXTextField PrixMesAnnonces;

    @FXML
    private TextArea descMesAnnonces;

    @FXML
    private JFXComboBox<Label> typeMesAnnonces;

    @FXML
    private Label imageMesAnnonces;
    String path="" ;
    
    InputStream inputStream ;
    int ID ;
    
    HangarGame hangar = new HangarGame();
    
          @FXML
    void selectImage(ActionEvent event) {

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
                 ImageView imageView = new ImageView(new Image(inputStream));
                 imageView.setFitHeight(122);
                 imageView.setFitWidth(178);
                 
                 imageMesAnnonces.setGraphic(imageView);
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
      void showOnclick(MouseEvent event) {
        Annonces annonces = tableAnnonce.getSelectionModel().getSelectedItem();
        annonces.toString();
        ID=annonces.getIdAnnonces();
        nomMesAnnonces.setText(annonces.getNomAnnonces());
        PrixMesAnnonces.setText(""+annonces.getPrix());
        descMesAnnonces.setText(annonces.getDescriptionAnnonces());
        typeMesAnnonces.setValue(new Label(annonces.getTypeAnnonces()));
        inputStream=annonces.getInputStream();
        ImageView imageView = new ImageView(new Image(annonces.getInputStream()));
            imageView.setFitHeight(112);
            imageView.setFitWidth(178);

            imageMesAnnonces.setGraphic(imageView);
    }
    
    @FXML
    void modifierMesAnnonces(ActionEvent event) {
        
     String nomA=   nomMesAnnonces.getText();
       String prixA= PrixMesAnnonces.getText();
      String desc=  descMesAnnonces.getText();
       String typeA= typeMesAnnonces.getValue().getText();
     InputStream  inputStrea;
     
            try {
             
               inputStream = new FileInputStream(path);
                System.out.println(path);
                 Annonces annonces = new Annonces(ID,nomA, typeA, "", desc,Integer.parseInt(prixA), inputStream );
         
       CrudAnnonces crud = new CrudAnnonces();
       crud.modifierAnnonces(annonces);
       
        data=FXCollections.observableArrayList();
        
      data.addAll(crud.reccupererSimple2());
      
      colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces" ));
      colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces" ));
      colConsoleAnnonce.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
      colPrixAnnonce.setCellValueFactory(new PropertyValueFactory<>("prix"));
      colDescriptionAnnonce.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
       tableAnnonce.setItems(null);
        tableAnnonce.setItems(data);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    
     
    

    @FXML
    void supprimerMesAnnonces(ActionEvent event) {
 
         Annonces annonces = new Annonces(ID,"", "", "", "",0, null );
         CrudAnnonces crud = new CrudAnnonces();
         crud.supprimerAnnonces(annonces);
         
          data=FXCollections.observableArrayList();
        
      data.addAll(crud.reccupererSimple2());
      
      colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces" ));
      colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces" ));
      colConsoleAnnonce.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
      colPrixAnnonce.setCellValueFactory(new PropertyValueFactory<>("prix"));
      colDescriptionAnnonce.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
       tableAnnonce.setItems(null);
        tableAnnonce.setItems(data);
         
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data=FXCollections.observableArrayList();
        CrudAnnonces crud = new CrudAnnonces();
      data.addAll(crud.reccupererSimple2());
      
      colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces" ));
      colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces" ));
      colConsoleAnnonce.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
      colPrixAnnonce.setCellValueFactory(new PropertyValueFactory<>("prix"));
      colDescriptionAnnonce.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
       tableAnnonce.setItems(null);
        tableAnnonce.setItems(data);
    }    
    
}
