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
import hangargame.xml.LoginController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.controlsfx.control.Notifications;

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
    
    private InputStream inputStream;
    
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
        inputStream=g.getImage();
        ImageView image=new ImageView(new Image(inputStream));
        image.setFitHeight(178);
        image.setFitWidth(207);
        LImage.setGraphic(image);


    }

    @FXML
    void InformationPersonnelle_Login(ActionEvent event) throws IOException {
      AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("Login.fxml"));
          InfoPersonnel.getChildren().addAll(anchorPane);
    }

    @FXML
    void ModifierInfo(ActionEvent event) {
   int telephone = Integer.parseInt(TF_Tel.getText());
  
   //Gamer g = new Gamer(TF_Nom.getText(), TF_Prenom.getText(), TF_Adresse.getText(), telephone,inputStream);
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
   {   try {
       inputStream= new FileInputStream(path);
       if(s.ModifierInfo(TF_Nom.getText(),TF_Prenom.getText(), TF_Adresse.getText(), telephone,loginStat ,inputStream)!=null)
    
        
               {
      LCon.setText("Vos information ont été modifié ");
             //Image img = new Image("C:/Users/lenovo/Documents/NetBeansProjects/HangarGame/src/hangargame/imagesTickConfirmation.png");

            Notifications notificationBuilder = Notifications.create()
                     .title("Modification réussite")
                     .text("Bienvenue sur Hangar Game "+loginStat)
                     .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
                    
               }
       } catch (FileNotFoundException ex) {
           Logger.getLogger(InformationPersonnelleController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   
    }
   
    }
    @FXML
    void MODIFPhotoUser(ActionEvent event) {
       JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
           try {
                inputStream= new FileInputStream(path);
               ImageView image=new ImageView(new Image(inputStream));
        image.setFitHeight(178);
        image.setFitWidth(207);
        LImage.setGraphic(image);
           } catch (FileNotFoundException ex) {
               Logger.getLogger(InformationPersonnelleController.class.getName()).log(Level.SEVERE, null, ex);
           }
          
            System.out.println("image");
          //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }
    }
    }

