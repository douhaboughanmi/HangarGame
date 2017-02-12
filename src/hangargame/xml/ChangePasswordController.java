/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXPasswordField;
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
    
    String loginStat=LoginController.LoginStatic;
     ServicesGamer s = new ServicesGamer();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChangePassword(ActionEvent event) {
        if(s.ChangePassword(PF_ChangePassword1.getText(), PF_ChangePassword2.getText(), loginStat))
        {
            LPasswordChange.setText("Votre mot de passe a été modifié");
        }
        else
        {
             LPasswordChange.setText("Mot de passe non conforme");
        }
    }
       @FXML
    void ChangePassword_Login(ActionEvent event) throws IOException {
 AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("Login.fxml"));
           ChangePassword.getChildren().addAll(anchorPane);
    }

}
