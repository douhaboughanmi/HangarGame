/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package hangargame.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.entites.Annonces;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author mayss
 */
public class AjoutAnnonceController implements Initializable {
    
    InputStream inputStream;
    
    @FXML
    private JFXButton btnAnnonce = new JFXButton();
    
    @FXML
    private JFXTextField titreAnnonce = new JFXTextField();
    
    @FXML
    private JFXTextField prixAnnonce = new JFXTextField();
    
    @FXML
    private TextArea descriptionAnnonce = new JFXTextArea();
    
    @FXML
    private JFXComboBox<Label> typeAnnonce = new JFXComboBox<>();
    
    private ToggleGroup a = new ToggleGroup();
    
     @FXML
    private JFXRadioButton radioPS3;
    @FXML
    private JFXRadioButton radioPS4;
    
    @FXML
    private JFXRadioButton radioXbox360;
    
    @FXML
    private JFXRadioButton radioXboxOne;
    
    @FXML
    private JFXRadioButton radioWiU;
    
    @FXML
    private JFXRadioButton radioPsVita;
    
    private String path = "";
    
    @FXML
    private Label labelAnnonce;
    
    @FXML
    private Label labelPrix;
    
    @FXML
    private Label labelDesc;
    
    @FXML
    private Label labelImage;
    
    HangarGame hangarGame = new HangarGame();
    
    RadioButton chk = new JFXRadioButton();
    String choixConsole="";
    
        @FXML
    private Label labelChoix;
    
    @FXML
    void login(ActionEvent event) throws FileNotFoundException {

        //  String choixConsole = a.getSelectedToggle().toString().substring(66);
        String typeA = typeAnnonce.getValue().getText();
        String nomA = titreAnnonce.getText();
        String desc = descriptionAnnonce.getText();
        String ab = prixAnnonce.getText();
        
        a.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {
            RadioButton chk1 = (JFXRadioButton)t1.getToggleGroup().getSelectedToggle();
            choixConsole = chk1.getText();
        });
        
        
        int prix = 0;
        if ((ab.isEmpty()) == false) {
            try {
                prix = Integer.parseInt(ab);
            } catch (NumberFormatException ex) {
              //  System.out.println("dddddd");

                //labelPrix.setText("Champ obligatoire");
            }
            
        }
        
        if(choixConsole.isEmpty()){
        labelChoix.setText("Champ manquant");
        
        }
        
        if(choixConsole.isEmpty()==false){
        labelChoix.setText("");
        
        }
        
        if (nomA.isEmpty() == false
                && desc.isEmpty() == false
                && ab.isEmpty() == false
                && path.isEmpty() == false
                && prix != 0 && choixConsole.isEmpty()==false) {
            
            System.out.println("");
            inputStream = new FileInputStream(path);
            Annonces annonces = new Annonces(nomA, typeA, choixConsole, desc, prix, inputStream, path);
            CrudAnnonces crud = new CrudAnnonces();
            crud.ajouterAnnonces(annonces);
            
        }
        
        if (nomA.isEmpty()) {
            labelAnnonce.setText("Champ obligatoire");
            
        }
        
        if (ab.isEmpty()) {
            labelPrix.setText("Champ obligatoire");
            
        }
        
        if (path.isEmpty()) {
            labelImage.setText("Champ obligatoire");
            
        }
        
        if (desc.isEmpty()) {
            labelDesc.setText("Champ obligatoire");
            
        }
        
        if (nomA.isEmpty() == false) {
            labelAnnonce.setText("");
            
        }
        
        if (ab.isEmpty() == false) {
            labelPrix.setText("");
            
        }
        
        if (desc.isEmpty() == false) {
            labelDesc.setText("");
            
        }
        
        if (path.isEmpty() == false) {
            labelImage.setText("");
            
        }
        
    }
    
    @FXML
    void deplacerVersAcceuilAnnonce(ActionEvent event) throws IOException {
        
        hangarGame.depalcerVersAccueilAnnonce();
        
    }
    
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
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                labelImage.setText("");
                labelImage.setGraphic(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("image");
            //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            
            System.out.println("No Data");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeAnnonce.getItems().add(new Label("Echange"));
        typeAnnonce.getItems().add(new Label("Vente"));
        
        typeAnnonce.setValue(new Label("Echange"));
        
        radioPS4.setToggleGroup(a);
        
        radioPsVita.setToggleGroup(a);
        radioWiU.setToggleGroup(a);
        radioXbox360.setToggleGroup(a);
        radioXboxOne.setToggleGroup(a);
        radioPS3.setToggleGroup(a);
        a.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                
              RadioButton chk = (JFXRadioButton)t1.getToggleGroup().getSelectedToggle();
               choixConsole = chk.getText();
                
            }
        });
    }
    
}
