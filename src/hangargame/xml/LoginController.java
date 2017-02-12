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
import com.restfb.Version;
import com.restfb.types.User;
import com.restfb.types.User.Picture;
import hangargame.HangarGame;
import hangargame.services.ServicesGamer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author lenovo
 */
public class LoginController implements Initializable {

    ServicesGamer s = new ServicesGamer();
    public static String LoginStatic;
    public static Picture p;
    @FXML
    private JFXTextField TF_login;
    @FXML
    private Label code;
    @FXML
    private Pane PaneCode;

    public TextField getText() {
        return this.TF_login;
    }
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
    //public static String mailFB;
     @FXML
    private AnchorPane InterInscription; //Inscri

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

        if (s.Authentification(TF_login.getText(), PF_password.getText())) {
            if (s.ActivationCompte(TF_login.getText())) {
                LCon.setText("Bienvenue " + TF_login.getText());
                LCon.setTextFill(Color.web("#dc143c"));
                Guest.disableProperty();
                LoginStatic=TF_login.getText();
                AnchorPane InterInscription1 = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
                root.getChildren().setAll(InterInscription1);
            } else {
                PaneCode.setVisible(true);
                LCon.setText("Vous devez activez votre compte");
                LCon.setTextFill(Color.web("#dc143c"));
            }
        } else {

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
        String accessToken = "EAACEdEose0cBANmrL31vXessmHQF1ejqIAs3c6xnZB4jPSQ2dVh47ZCRkoJIDYLxmxKRBawFlaLZA7ysUx2HRtnAA8qJm6ajf2g7hQZBq9f3PFa94ZAeaofZBHsBYeqGWYhkU0P3v0WLFDkCb4YZA0KiXSd1GYqa2CZBHZC5JQLgLLBHknmr7AsZCrStSOgUEcl2sZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        User me = fbClient.fetchObject("me", User.class);
        String mailFB = me.getEmail();
         
        if (s.AuthentificationWithFb(mailFB)) {
            if (s.ActivationCompteFB(mailFB)) {
                LCon.setText("Bienvenue " + TF_login.getText());
                LCon.setTextFill(Color.web("#dc143c"));
                Guest.disableProperty();
                AnchorPane InterInscription = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
                root.getChildren().setAll(InterInscription);
            } else {
                PaneCode.setVisible(true);
                LCon.setText("Vous devez activez votre compte");
                LCon.setTextFill(Color.web("#dc143c"));
            }
        }

    }

}
