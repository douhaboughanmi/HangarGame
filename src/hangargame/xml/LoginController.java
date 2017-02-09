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
    @FXML
    private JFXTextField TF_login;
     @FXML
    private Label code;
      @FXML
    private Pane PaneCode;

    public TextField getText(){
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
   // @FXML
    //private AnchorPane InterInscription; //Inscri
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if (!HangarGame.isSplashLoaded) {
            loadSplashScreen();
        }

        rootPane = root;
        PaneCode.setVisible(false);
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
        if(s.ActivationCompte(TF_login.getText()))
        {LCon.setText("Bienvenue "+TF_login.getText());
        LCon.setTextFill(Color.web("#dc143c"));
        Guest.disableProperty();
         AnchorPane  InterInscription1 =FXMLLoader.load(getClass().getResource("Accueil.fxml"));
         root.getChildren().setAll( InterInscription1);
        }
        else 
        {
            PaneCode.setVisible(true);
           LCon.setText("Vous devez activez votre compte");
        LCon.setTextFill(Color.web("#dc143c"));
        }
    }
    else
    {
        
       LCon.setText("Coordonnées incorrectes");
        LCon.setTextFill(Color.web("#dc143c"));
        
    

    }
    
    }
    @FXML
    void Inscription(ActionEvent event) throws IOException {
       
        AnchorPane  InterInscription =FXMLLoader.load(getClass().getResource("FXMLInscription.fxml"));
         root.getChildren().setAll( InterInscription);
    }
       @FXML
    void LinkForgotPassword(ActionEvent event) throws IOException {
            AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
            root.getChildren().clear();
            //anchorPane.setPrefSize(300, 300);
           root.getChildren().addAll(anchorPane);
    }
       @FXML
    void VerificationCode(ActionEvent event) throws IOException {
        
       if(s.ValidationCode(TF_login.getText(),TF_Code.getText()))
       {
           AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            root.getChildren().addAll(anchorPane);
       }
       else
       {   
           code.setText("Code incorrect! ");
       }
    }
     @FXML
    void LoginFB(ActionEvent event) {
  //String accessToken ="EAACEdEose0cBAI1JtC814SJG4RThDS3gG0XQ2Fynur0EdRmTY6Ncy3gAIv7M8tPYrvtuFpo1udsJ6ghldejGZCsmk3UBvpV49Su5ZA1rE28CZCvfPQMNfk7ae8c85F37sC2RbNZBXZCzfWdp8ZBH03l2n7RZADeFdBWSeDgnheIXlxyu2EqPn4itnFv2yJmuhsZD";
      
        //User me= fbClient.fetchObject("me", User.class);
     //  System.out.println(me.getName());
      
        String domain="http://esprit.tn/";
        String appId="1204005309707869";
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
         WebDriver driver = new ChromeDriver();
         driver.get(authUrl);
         String accessToken ;
         while(true){
             if(!driver.getCurrentUrl().contains("facebook.com"))
             {
                 String url=driver.getCurrentUrl();
                 accessToken= url.replaceAll(".*#access_token=(.+)&.*","$1");
                 driver.quit();
            FacebookClient fbClient= new DefaultFacebookClient(accessToken);
             User user= fbClient.fetchObject("me", User.class);
             LCon.setText("Bonjour "+user.getName());
             }
         }
    
    }
}