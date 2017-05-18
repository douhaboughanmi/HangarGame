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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.validation.NumberValidator;
import hangargame.entites.Gamer;
import hangargame.services.ServicesGamer;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
public class InformationPersonnelleController implements Initializable {

    ServicesGamer s = new ServicesGamer();
    
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
    String loginStat = LoginController.LoginStatic;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    private String path = "src/hangargame/images/ImageAnonyme.jpg";
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
 InputStream inputStream;
        Gamer g = s.Afficher(loginStat);
        TF_Nom.setText(g.getNom());
        TF_Prenom.setText(g.getPrenom());
        TF_Email.setText(g.getEmail());
        TF_Adresse.setText(g.getAdresse());
        String tel = Integer.toString(g.getTel());
        TF_Tel.setText(tel);
        LLogin.setText(loginStat);
        //AffichageImage
        inputStream = g.getImage();
        ImageView image = new ImageView(new Image(inputStream));
        image.setFitHeight(187);
        image.setFitWidth(218);
        LImage.setGraphic(image);
        

    }

    void InformationPersonnelle_Login(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        InfoPersonnel.getChildren().addAll(anchorPane);
    }

    @FXML
    void ModifierInfo(ActionEvent event) {

      

        String Nom = TF_Nom.getText().trim();
        String PreNom = TF_Prenom.getText().trim();
        int telephone = Integer.parseInt(TF_Tel.getText());

    
     
         if(TF_Nom.getText().isEmpty())
       {
           LNom.setText("Champs manquant!");
            LNom.setTextFill(Color.web("#eeba0f"));
       }
       if(!TF_Nom.getText().isEmpty())
       {  if(!Nom.matches("[a-zA-Z]*"))
       {
              LNom.setText("Doit contenir que des lettres");
              LNom.setTextFill(Color.web("#eeba0f"));
       }
       else {
             LNom.setText("");
              LNom.setTextFill(Color.web("#eeba0f"));}
       }
     
      
       if(TF_Prenom.getText().isEmpty())
       {
           LPrenom.setText("Champs manquant!");
             LPrenom.setTextFill(Color.web("#eeba0f"));
       }
       if(! TF_Prenom.getText().isEmpty())
       {  if(!PreNom.matches("[a-zA-Z]*"))
       {
               LPrenom.setText("Doit contenir que des lettres");
               LPrenom.setTextFill(Color.web("#eeba0f"));
       }
       else {
              LPrenom.setText("");
               LPrenom.setTextFill(Color.web("#eeba0f"));}
       }
        
        
          if(TF_Adresse.getText().isEmpty())
       {
           LAdresse.setText("Champs manquant!");
           LAdresse.setTextFill(Color.web("#eeba0f"));
       }
       if(!TF_Adresse.getText().isEmpty())
       {
              LAdresse.setText("");
              LAdresse.setTextFill(Color.web("#eeba0f"));
       }
            if(TF_Tel.getText().isEmpty())
       {
           LTel.setText("Champs manquant!");
           LTel.setTextFill(Color.web("#eeba0f"));
       }
       if(!TF_Tel.getText().isEmpty())
       {
             LTel.setText("");
             LTel.setTextFill(Color.web("#eeba0f"));
       }
       
       
        if(TF_Tel.getText().length()<8)
                {
                    LTel.setText("Doit contenir au moin 8 chiffres");
                    LTel.setTextFill(Color.web("#eeba0f"));
                }
         if(TF_Tel.getText().length()>=8)
                {
                    LTel.setText("");
                    LTel.setTextFill(Color.web("#eeba0f"));
                }
      

        
        if ((!TF_Prenom.getText().isEmpty()) && (!TF_Adresse.getText().isEmpty()) && (!TF_Nom.getText().isEmpty()) 
                && (!TF_Tel.getText().isEmpty()
                && Nom.matches("[a-zA-Z]*") 
                && PreNom.matches("[a-zA-Z]*")) 
                && TF_Tel.getText().length() >= 8) 
        {
        
            if (s.ModifierInfo(TF_Nom.getText(), TF_Prenom.getText(), TF_Adresse.getText(), telephone, loginStat,path) != null) {
                LCon.setText("Vos information ont été modifié ");

                tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Modification faite avec succès ");
                tr.setMessage("Bienvenue sur Hangar Gamer " + loginStat);
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));

            }

        }

    }

    @FXML
    void MODIFPhotoUser(ActionEvent event) throws FileNotFoundException {
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
             
                image.setFitHeight(187);
                image.setFitWidth(218);
                     
        
         LImage.setGraphic(image);
            System.out.println("image");
          //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }
    }

    @FXML
    private void Accueil(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        InfoPersonnel.getChildren().addAll(anchorPane);
    }

    @FXML
    private void Info_LogOut(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        InfoPersonnel.getChildren().addAll(anchorPane);
    }

}
