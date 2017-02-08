/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXTextField;
import hangargame.services.ServicesGamer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   

   @FXML
    void ForgotPassword(ActionEvent event) {
        if (!s.VerifMail(TF_FPEmail.getText())) {
            LPasswordForgot.setText("E-mail inexistant !");
            LPasswordForgot.setTextFill(Color.web("#ea5050"));
        }
        else if(!s.EmailValidation(TF_FPEmail.getText())) {
              LPasswordForgot.setText("E-mail invalide !");
             LPasswordForgot.setTextFill(Color.web("#ea5050"));
        }
        else
        {  LPasswordForgot.setText("Mot de passe envoyé !");
            LPasswordForgot.setTextFill(Color.web("#ea5050"));
            s.RecupererPassword(TF_FPEmail.getText());
        }
      
    }

    
}