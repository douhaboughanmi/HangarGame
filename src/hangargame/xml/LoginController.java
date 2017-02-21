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
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import hangargame.HangarGame;
import hangargame.entites.Gamer;
import hangargame.services.ServicesGamer;
import hangargame.utils.AuthentificationFB;
import hangargame.utils.SendMessage;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author lenovo
 */
public class LoginController implements Initializable {

    HangarGame hangar = new HangarGame();
    ServicesGamer s = new ServicesGamer();
    public static String LoginStatic;
   
    @FXML
    private JFXTextField TF_login;
    @FXML
    private Label code;
    @FXML
    private Pane PaneCode;
    @FXML
    private ImageView TFCon;
    public String path="https://hangargame.com";
   
    @FXML
    private JFXTextField TF_Code;
    @FXML
    private JFXPasswordField PF_password;

    @FXML
    private Text Guest;

    @FXML
    private Label LCon;
    @FXML
    private AnchorPane root; //Accueil

    private static AnchorPane rootPane; //Splash

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!HangarGame.isSplashLoaded) {
            loadSplashScreen();
        }

          rootPane = root;
        PaneCode.setVisible(false);
        try {
            RequiredFieldValidator VLogin = new RequiredFieldValidator();
            TF_login.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            TF_login.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_login.validate();
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
            PF_password.getValidators().add(VLogin);
            VLogin.setMessage("Champs manquant");
            PF_password.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        PF_password.validate();
                    }
                }
            });

            Image icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
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
      
        if (1==s.Authentification(TF_login.getText(), PF_password.getText())) {
          
            LoginStatic=s.RecupererLoginFromEmail(TF_login.getText());
            
             
            if (s.ActivationCompte(TF_login.getText())) {
               
                LCon.setText("Bienvenue " + TF_login.getText());
                LCon.setTextFill(Color.web("#dc143c"));
                Guest.disableProperty();
                LoginStatic=s.RecupererLoginFromEmail(TF_login.getText());
                AnchorPane InterInscription1 = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
                root.getChildren().setAll(InterInscription1);
            } else {
                PaneCode.setVisible(true);
                LCon.setText("Vous devez activez votre compte");
                LCon.setTextFill(Color.web("#dc143c"));
                
            }
        } 
        else if(2==s.Authentification(TF_login.getText(), PF_password.getText()))
        
        {
           AnchorPane InterInscription1 = FXMLLoader.load(getClass().getResource("AccueilAdmin.fxml"));
                root.getChildren().setAll(InterInscription1);
        }
        else   { 
        
            PaneCode.setVisible(false);
            LCon.setText("Coordonnées incorrectes");
            LCon.setTextFill(Color.web("#dc143c"));

        }
       
      

    }

    @FXML
    void Inscription(ActionEvent event) throws IOException {

        AnchorPane InterInscription = FXMLLoader.load(getClass().getResource("FXMLInscription.fxml"));
        root.getChildren().setAll(InterInscription);
    }

    @FXML
    void LinkForgotPassword(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        root.getChildren().clear();
        //anchorPane.setPrefSize(300, 300);
        root.getChildren().addAll(anchorPane);
    }

    @FXML
    void VerificationCode(ActionEvent event) throws IOException {

        if (s.ValidationCode(TF_login.getText(), TF_Code.getText())) {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            root.getChildren().addAll(anchorPane);
        } else {
            code.setText("Code incorrect! ");
        }
    }

    @FXML
    void LoginFB(ActionEvent event) throws IOException {
      AuthentificationFB fb = new AuthentificationFB();
      Gamer g= fb.AuthentificationFB();
      
      
        
      if (s.InscriptionFB(g)) {
            LoginStatic = g.getLogin();

            AnchorPane InterInscription1 = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            root.getChildren().setAll(InterInscription1);

            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Inscription effectuée avec succès ");
            tr.setMessage("Bienvenue sur Hangar Gamer " + g.getLogin());
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(Duration.seconds(5));

        }

    }

    @FXML
    private void LoginGooglePlus(ActionEvent event) throws IOException, URISyntaxException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI(path));
        
    }

   

}
