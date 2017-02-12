/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Gamer;
import hangargame.services.ServicesGamer;
import hangargame.xml.LoginController;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import hangargame.xml.LoginController;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class InformationPersonnelleController implements Initializable {

    ServicesGamer s = new ServicesGamer();
    @FXML
    private JFXTextField TF_Nom;

    @FXML
    private JFXTextField TF_Prenom;

    @FXML
    private JFXTextField TF_Email;

    @FXML
    private JFXTextField TF_Adresse;

    @FXML
    private JFXTextField TF_Tel;

    @FXML
    private Label LCon;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String login=LoginController.LoginStatic;
        Gamer g = s.Afficher(login );
        TF_Nom.setText(g.getNom());
        TF_Prenom.setText(g.getPrenom());
        TF_Email.setText(g.getEmail());
        TF_Adresse.setText(g.getAdresse());
        String tel = Integer.toString(g.getTel());
        TF_Tel.setText(tel);


    }

    @FXML
    void InformationPersonnelle_Login(ActionEvent event) {

    }

    @FXML
    void ModifierInfo(ActionEvent event) {

    }

}
