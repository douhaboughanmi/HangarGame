/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.controller;

import com.jfoenix.controls.JFXTextField;
import hangargame.services.ServicesGamer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
public class ForgotPasswordController implements Initializable {
    
    ServicesGamer s = new ServicesGamer();
    @FXML
    private Label LPasswordForgot;

    @FXML
    private JFXTextField TF_FPEmail;
      @FXML
    private AnchorPane AP_Password;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   

   @FXML
    void ForgotPassword(ActionEvent event) {
        if (s.VerifMail(TF_FPEmail.getText())) {
            LPasswordForgot.setText("E-mail inexistant !");
            LPasswordForgot.setTextFill(Color.web("#ea5050"));
             tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("E-mail incorrect ! ");
                tr.setMessage("Veuillez verifier votre E-mail!");
                tr.setNotificationType(NotificationType.ERROR);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
        }
         if(!s.EmailValidation(TF_FPEmail.getText())) {
              LPasswordForgot.setText("E-mail invalide !");
             LPasswordForgot.setTextFill(Color.web("#ea5050"));
              tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("E-mail invalide! :D");
                tr.setMessage("Veuillez verifier votre E-mail Exp: x@x.x !");
                tr.setNotificationType(NotificationType.WARNING);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
        }
        if(!s.VerifMail(TF_FPEmail.getText()))
        {  LPasswordForgot.setText("Un mail vous a été envoyé!");
            LPasswordForgot.setTextFill(Color.web("#ea5050"));
            s.RecupererPassword(TF_FPEmail.getText());
            
             tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("E-mail envoyé ! :D");
                tr.setMessage("Votre mot de passe a été envoyé !");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
        }
      
    }
 @FXML
    void ForgotPassword_Login(ActionEvent event) throws IOException {
 AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("Login.fxml"));
           AP_Password.getChildren().addAll(anchorPane);
            
    }
    
    
}
