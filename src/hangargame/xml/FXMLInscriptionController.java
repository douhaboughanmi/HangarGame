/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import hangargame.services.ServicesGamer;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXMLInscriptionController implements Initializable {

    ServicesGamer s = new ServicesGamer();
    @FXML
    private AnchorPane InterInscription;

    @FXML
    private JFXTextField TF_email;

    @FXML
    private JFXTextField TF_IoginIns;

    @FXML
    private JFXPasswordField PF_passwordIns;

    @FXML
    private JFXPasswordField PF_passwordConfIns;

    @FXML
    private JFXTextField TF_adresse;

    @FXML
    private JFXTextField TF_prenom;

    @FXML
    private JFXTextField TF_Nom;

    @FXML
    private JFXTextField TF_tel;
    @FXML
    private Label L_mail;

    @FXML
    private Label L_PasswordConf;

    @FXML
    private Label L_password;

    @FXML
    private Label L_Login;
       @FXML
    private JFXSpinner chargement;

    @FXML
    private Label code;

    @FXML
    private JFXTextField TF_Code;

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            TF_email.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_email.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_email.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            TF_IoginIns.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_IoginIns.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_IoginIns.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            PF_passwordIns.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            PF_passwordIns.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        PF_passwordIns.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            PF_passwordConfIns.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            PF_passwordConfIns.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        PF_passwordConfIns.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            TF_adresse.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_adresse.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_adresse.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            TF_prenom.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_prenom.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_prenom.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            TF_Nom.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_Nom.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_Nom.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            TF_tel.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_tel.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_tel.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            VLogin.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @FXML
    void Inscription(ActionEvent event) throws IOException {
       

       
        if (!PF_passwordIns.getText().equals(PF_passwordConfIns.getText())) {
            L_PasswordConf.setText("Mot de passe non conforme !");
            L_PasswordConf.setTextFill(Color.web("#ea5050"));
            L_password.setText("Mot de passe non conforme !");
            L_password.setTextFill(Color.web("#ea5050"));
        } 
        else if (!s.VerifMail(TF_email.getText())) {
            L_mail.setText("E-mail existant !");
            L_mail.setTextFill(Color.web("#ea5050"));
         } 
        else if (!s.VerifLogin(TF_IoginIns.getText())) {
            L_Login.setText("Login existant !");
            L_Login.setTextFill(Color.web("#ea5050"));
        }
        else if (!s.EmailValidation(TF_email.getText())) {
            L_mail.setText("E-mail invalide !");
            L_mail.setTextFill(Color.web("#ea5050"));
        } else if (s.Inscription(TF_email.getText(), TF_IoginIns.getText(), PF_passwordIns.getText(), PF_passwordConfIns.getText(), TF_adresse.getText(), TF_prenom.getText(), TF_Nom.getText(), TF_tel.getText())) {
           
         // chargement = new JFXSpinner();
            
        }
    }

    @FXML
    void VerificationCode(ActionEvent event) throws IOException {
        
       if(s.ValidationCode(TF_email.getText(),TF_Code.getText()))
       {
           AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            InterInscription.getChildren().addAll(anchorPane);
       }
       else
       {   
           code.setText("Code incorrect! ");
       }
    }
   
}
