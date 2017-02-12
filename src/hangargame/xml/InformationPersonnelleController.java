/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Gamer;
import hangargame.services.ServicesGamer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import hangargame.xml.LoginController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class InformationPersonnelleController implements Initializable {

    ServicesGamer s = new ServicesGamer();
     private String path = "";
     @FXML
    private Label LImage;

    @FXML
    private Label LLogin;
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
     @FXML
    private Label LNom;

    @FXML
    private Label LAdresse;

    @FXML
    private Label LPrenom;

    @FXML
    private Label LTel;
     @FXML
    private AnchorPane InfoPersonnel;
    String loginStat=LoginController.LoginStatic;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Gamer g = s.Afficher(loginStat );
        TF_Nom.setText(g.getNom());
        TF_Prenom.setText(g.getPrenom());
        TF_Email.setText(g.getEmail());
        TF_Adresse.setText(g.getAdresse());
        String tel = Integer.toString(g.getTel());
        TF_Tel.setText(tel);
        LLogin.setText(loginStat);


    }

    @FXML
    void InformationPersonnelle_Login(ActionEvent event) throws IOException {
      AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("Login.fxml"));
          InfoPersonnel.getChildren().addAll(anchorPane);
    }

    @FXML
    void ModifierInfo(ActionEvent event) {
   int telephone = Integer.parseInt(TF_Tel.getText());
   // Gamer g = new Gamer();
   Gamer g = new Gamer(TF_Nom.getText(), TF_Prenom.getText(), TF_Adresse.getText(), telephone);
     if (TF_Adresse.getText().isEmpty()) {
           LAdresse.setText("Champ obligatoire");

        }
       

        if (TF_Tel.getText().isEmpty()) {
            LTel.setText("Champ obligatoire");

        }

        if (TF_Nom.getText().isEmpty()) {
            LNom.setText("Champ obligatoire");

        }
        
        if (TF_Prenom.getText().isEmpty()) {
           LPrenom.setText("Champ obligatoire");

        }
        
   if (!TF_Adresse.getText().isEmpty()) {
            LAdresse.setText("");

        }
       

        if (!TF_Tel.getText().isEmpty()) {
           LTel.setText("");

        }

       
        
        if (!TF_Nom.getText().isEmpty()) {
            LNom.setText("");

        }
        
        if (!TF_Prenom.getText().isEmpty()) {
            LPrenom.setText("");

        }
   if((!TF_Prenom.getText().isEmpty())&&(!TF_Adresse.getText().isEmpty())&&(!TF_Nom.getText().isEmpty())&&(!TF_Tel.getText().isEmpty()))
   {
       if( g!=s.ModifierInfo(TF_Nom.getText(),TF_Prenom.getText(), TF_Adresse.getText(), telephone,loginStat))
    {
      s.Afficher(loginStat);
      LCon.setText("Vos information ont été modifié ");
    }
   
    }
   
    }
}
