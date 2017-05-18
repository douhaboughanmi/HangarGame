/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import hangargame.services.ServicesGamer;
import hangargame.utils.SendCodeValidation;
import hangargame.utils.SendMessage;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXMLInscriptionController implements Initializable {

    String loginStat = LoginController.LoginStatic;
    ServicesGamer s = new ServicesGamer();
    private String path = "src/hangargame/images/ImageAnonyme.jpg";
    @FXML
    private AnchorPane InterInscription;

    @FXML
    private JFXTextField TF_email;

    @FXML
    private JFXTextField TF_IoginIns;

    @FXML
    private JFXPasswordField PF_passwordIns;
    @FXML
    private Label LImage;
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
    private Label L_Inscri1;
    @FXML
    private Label L_Prenom;
    @FXML
    private Label L_Nom;
    @FXML
    private Label L_Tel;
    @FXML
    private Label L_adresse;
    @FXML
    private Separator v1;
    @FXML
    private Separator v4;
    @FXML
    private Separator v3;
    @FXML
    private Separator v2;
    @FXML
    private Label LCour1;
    @FXML
    private Label L_court2;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        v1.setVisible(false);
        v2.setVisible(false);
        v3.setVisible(false);
        v4.setVisible(false);

    }

    @FXML
    void Inscription(ActionEvent event) throws IOException {
        String Nom = TF_Nom.getText().trim();
        String PreNom = TF_prenom.getText().trim();

        try {

            NumberValidator numValidator = new NumberValidator();
            TF_tel.getValidators().add(numValidator);
            L_Tel.setText("Doit contenir que des numéros !");
            L_Tel.setTextFill(Color.web("#eeba0f"));

            TF_tel.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        TF_tel.validate();
                    }
                }
            });
            Image icn;

            icn = new Image(new FileInputStream("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/images/exlam.png"));
            numValidator.setIcon(new ImageView(icn));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (PF_passwordIns.getText().isEmpty()) {
            L_password.setText("Champs manquant!");
            L_password.setTextFill(Color.web("#eeba0f"));

        }
        if (!PF_passwordIns.getText().isEmpty()) {
            L_password.setText("");
            L_password.setTextFill(Color.web("#eeba0f"));
        }
        if (PF_passwordConfIns.getText().isEmpty()) {
            L_PasswordConf.setText("Champs manquant!");
            L_PasswordConf.setTextFill(Color.web("#eeba0f"));

        }
        if (!PF_passwordIns.getText().isEmpty()) {
            L_PasswordConf.setText("");
            L_PasswordConf.setTextFill(Color.web("#eeba0f"));
        }
        if (TF_email.getText().isEmpty()) {
            L_mail.setText("Champs manquant!");
            L_mail.setTextFill(Color.web("#eeba0f"));

        }
        if (!TF_email.getText().isEmpty()) {
            L_mail.setText("");
            L_mail.setTextFill(Color.web("#eeba0f"));
        }
        if (TF_IoginIns.getText().isEmpty()) {
            L_Login.setText("Champs manquant!");
            L_Login.setTextFill(Color.web("#eeba0f"));
        }
        if (!TF_IoginIns.getText().isEmpty()) {
            L_Login.setText("");
            L_Login.setTextFill(Color.web("#eeba0f"));
        }

        if (TF_Nom.getText().isEmpty()) {
            L_Nom.setText("Champs manquant!");
            L_Nom.setTextFill(Color.web("#eeba0f"));
        }
        if (!TF_Nom.getText().isEmpty()) {
            if (!Nom.matches("[a-zA-Z]*")) {
                L_Nom.setText("Doit contenir que des lettres");
                L_Nom.setTextFill(Color.web("#eeba0f"));
            } else {
                L_Nom.setText("");
                L_Nom.setTextFill(Color.web("#eeba0f"));
            }
        }

        if (TF_prenom.getText().isEmpty()) {
            L_Prenom.setText("Champs manquant!");
            L_Prenom.setTextFill(Color.web("#eeba0f"));
        }
        if (!TF_prenom.getText().isEmpty()) {
            if (!PreNom.matches("[a-zA-Z]*")) {
                L_Prenom.setText("Doit contenir que des lettres");
                L_Prenom.setTextFill(Color.web("#eeba0f"));
            } else {
                L_Prenom.setText("");
                L_Prenom.setTextFill(Color.web("#eeba0f"));
            }
        }

        if (TF_adresse.getText().isEmpty()) {
            L_adresse.setText("Champs manquant!");
            L_adresse.setTextFill(Color.web("#eeba0f"));
        }
        if (!TF_adresse.getText().isEmpty()) {
            L_adresse.setText("");
            L_adresse.setTextFill(Color.web("#eeba0f"));
        }
        if (TF_tel.getText().isEmpty()) {
            L_Tel.setText("Champs manquant!");
            L_Tel.setTextFill(Color.web("#eeba0f"));
        }
        if (!TF_tel.getText().isEmpty()) {
            L_Tel.setText("");
            L_Tel.setTextFill(Color.web("#eeba0f"));
        }

        if (!PF_passwordIns.getText().equals(PF_passwordConfIns.getText())) {
            L_PasswordConf.setText("Mot de passe non conforme !");

            L_PasswordConf.setTextFill(Color.web("#eeba0f"));
            L_password.setText("Mot de passe non conforme !");
            L_password.setTextFill(Color.web("#eeba0f"));
        }
        if (!s.VerifMail(TF_email.getText())) {
            L_mail.setText("E-mail existe deja !");
            L_mail.setTextFill(Color.web("#eeba0f"));
        }
        if (!s.VerifLogin(TF_IoginIns.getText())) {
            L_Login.setText("Login existe deja !");
            L_Login.setTextFill(Color.web("#9a1414"));
        }
        if (!s.EmailValidation(TF_email.getText())) {
            L_mail.setText("E-mail invalide: Exp x@x.x !");
            L_mail.setTextFill(Color.web("#9a1414"));
        }
        if (TF_tel.getText().length() < 8) {
            L_Tel.setText("Doit contenir au moin 8 caractère");
            L_Tel.setTextFill(Color.web("#9a1414"));
        }
        if (TF_tel.getText().length() > 8) {
            L_Tel.setText("");
            L_Tel.setTextFill(Color.web("#9a1414"));
        }
        if (PF_passwordIns.getText().length() > 5) {
            LCour1.setText(" ");
            LCour1.setTextFill(Color.web("#9a1414"));
        }
        if (PF_passwordIns.getText().length() < 5) {
            LCour1.setText("Mot de passe très court!");
            LCour1.setTextFill(Color.web("#9a1414"));
        }
        if (PF_passwordConfIns.getText().length() < 5) {
            L_court2.setText("Mot de passe très court!");
            L_court2.setTextFill(Color.web("#9a1414"));
        }
        if (PF_passwordConfIns.getText().length() > 5) {
            L_court2.setText("");
            L_court2.setTextFill(Color.web("#9a1414"));
        }
        if (!PF_passwordIns.getText().isEmpty() && !PF_passwordConfIns.getText().isEmpty()
                && !TF_email.getText().isEmpty() && !TF_IoginIns.getText().isEmpty() && !TF_Nom.getText().isEmpty()
                && !TF_prenom.getText().isEmpty() && !TF_adresse.getText().isEmpty() && !TF_tel.getText().isEmpty()
                && PF_passwordIns.getText().equals(PF_passwordConfIns.getText()) && s.VerifMail(TF_email.getText())
                && s.VerifLogin(TF_IoginIns.getText()) && TF_tel.getText().length() >= 8 && PF_passwordIns.getText().length() >= 5
                && PF_passwordConfIns.getText().length() >= 5 && Nom.matches("[a-zA-Z]*") && PreNom.matches("[a-zA-Z]*")
                && s.EmailValidation(TF_email.getText())) {

            s.Inscription(TF_email.getText(), TF_IoginIns.getText(), PF_passwordIns.getText(), PF_passwordConfIns.getText(), TF_Nom.getText(), TF_prenom.getText(), TF_adresse.getText(), TF_tel.getText(), path);

            loginStat = TF_IoginIns.getText();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
            InterInscription.getChildren().addAll(anchorPane);

            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Inscription réussite " + TF_IoginIns.getText());
            tr.setMessage("Vous allez recevoir un code de validation sur votre E-mail et sur votre téléphone");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(Duration.seconds(5));

        }

    }

    @FXML
    void Inscription_Login(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        InterInscription.getChildren().addAll(anchorPane);
    }

    @FXML
    void PhotoUser(ActionEvent event) throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            InputStream in = new FileInputStream(path);
            ImageView image = new ImageView(new Image(in));
            v1.setVisible(true);
            v2.setVisible(true);
            v3.setVisible(true);
            v4.setVisible(true);
            image.setFitHeight(147);
            image.setFitWidth(164);

            LImage.setGraphic(image);
            System.out.println("image");
            //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }
    }

    @FXML
    private void GoAccueil(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        InterInscription.getChildren().addAll(anchorPane);
    }

}
