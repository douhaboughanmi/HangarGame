/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import hangargame.services.ServicesGamer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private Label LPasswordChange;
    @FXML
    private JFXPasswordField PF_ChangePassword1;
    @FXML
    private Label L_password;
    @FXML
    private JFXPasswordField PF_ChangePassword2;
    @FXML
    private Label L_PasswordConf;
    @FXML
    private AnchorPane ChangePassword;

    String loginStat = LoginController.LoginStatic;
    ServicesGamer s = new ServicesGamer();
    @FXML
    private JFXPasswordField PF_AncienPassword;
    @FXML
    private Label L_AncienPassword;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContenent.fxml"));
            drawer.setSidePane(box);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    @FXML
    private void ChangePassword(ActionEvent event) {
        if(PF_ChangePassword1.getText().length() <5)
        {
           L_password.setText("mot de passe trop court !");
        } if(PF_ChangePassword2.getText().length() <5)
        {
            L_PasswordConf.setText("mot de passe trop court !");
        } if(PF_ChangePassword1.getText().length() >5)
        {
            LPasswordChange.setText("");
        } if(PF_ChangePassword2.getText().length() >5)
        {
            L_PasswordConf.setText("");
        }
        
        if (PF_AncienPassword.getText().equals(s.RecupererPasswordLogin(loginStat))
                &&PF_ChangePassword1.getText().length() >5 && PF_ChangePassword2.getText().length() >5) {
            if (s.ChangePassword(PF_ChangePassword1.getText(), PF_ChangePassword2.getText(), loginStat)) {
               
                   tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Modification réussite "+loginStat);
                tr.setMessage("Votre mot de passe a été modifié avec succès :) ");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
            } else {
                LPasswordChange.setText("*Mot de passe non conforme !");
                 LPasswordChange.setTextFill(Color.web("#DF0101"));
                L_PasswordConf.setText("*Mot de passe non conforme !");
                 L_PasswordConf.setTextFill(Color.web("#DF0101"));
            }
        } else {
            L_AncienPassword.setText("*Mot de passe incorrect !");
            L_AncienPassword.setTextFill(Color.web("#DF0101"));
             tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Mot de passe incorrecte "+loginStat);
                tr.setMessage("Veuillez vérifier votre ancien mot de passe ! ");
                tr.setNotificationType(NotificationType.ERROR);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
        }
    }

    @FXML
    void ChangePassword_Login(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        ChangePassword.getChildren().addAll(anchorPane);
    }

    @FXML
    private void Accueil(ActionEvent event) throws IOException {
         AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        ChangePassword.getChildren().addAll(anchorPane);
    }

}
