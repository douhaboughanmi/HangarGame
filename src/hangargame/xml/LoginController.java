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

package hangargame.xml;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import hangargame.HangarGame;
import hangargame.services.ServicesGamer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author lenovo
 */
public class LoginController implements Initializable {
    

    
    ServicesGamer s = new ServicesGamer();
    @FXML
    private JFXTextField TF_login;

    @FXML
    private JFXPasswordField PF_password;
      
       @FXML
    private Text Guest;

      @FXML
    private Label LCon;
          @FXML
    private AnchorPane root; //Accueil
          
           
    private static AnchorPane rootPane; //Splash
   // @FXML
    //private AnchorPane InterInscription; //Inscri
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if (!HangarGame.isSplashLoaded) {
            loadSplashScreen();
        }

        rootPane = root;
        try {
            RequiredFieldValidator VLogin= new RequiredFieldValidator();
            TF_login.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_login.focusedProperty().addListener(new ChangeListener<Boolean>(){
                
                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if(!newValue)
                    {
                        TF_login.validate();
                    }
                }
            });
            
            Image icn= new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
                    VLogin.setIcon(new ImageView(icn));
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
          try {
            RequiredFieldValidator VLogin= new RequiredFieldValidator();
            PF_password.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
           PF_password.focusedProperty().addListener(new ChangeListener<Boolean>(){
                
                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if(!newValue)
                    {
                        PF_password.validate();
                    }
                }
            });
            
            Image icn= new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
                    VLogin.setIcon(new ImageView(icn));
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
     private void loadSplashScreen() {
        try {
            HangarGame.isSplashLoaded = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource(("SplashFXML.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("Login.fxml")));
                    root.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    void Connexion(ActionEvent event) throws IOException {
     
    if( s.Authentification(TF_login.getText(),PF_password.getText()))
    {   
        
        LCon.setText("Bienvenue "+TF_login.getText());
        LCon.setTextFill(Color.web("#dc143c"));
        Guest.disableProperty();
         AnchorPane  InterInscription1 =FXMLLoader.load(getClass().getResource("Accueil.fxml"));
         root.getChildren().setAll( InterInscription1);
    }
    else
    {
        
       LCon.setText("Coordonnées incorrectes");
        LCon.setTextFill(Color.web("#dc143c"));
        
    // Image image = new Image(getClass().getResourceAsStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGameClient/src/hangargameclient/images/exlam.png"));
     //LCon.setGraphic(new ImageView(image));

    }
    
    }
    @FXML
    void Inscription(ActionEvent event) throws IOException {
       
        AnchorPane  InterInscription =FXMLLoader.load(getClass().getResource("FXMLInscription.fxml"));
         root.getChildren().setAll( InterInscription);
    }
}