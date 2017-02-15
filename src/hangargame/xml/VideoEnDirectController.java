/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VideoEnDirectController implements Initializable {

    @FXML
    private WebView web;
    private WebEngine engine;
    @FXML
    private MediaView med;
    private MediaPlayer mediaP;
    private static final String url1 = "";
        @FXML
    private JFXButton btnweb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        System.out.println("hhhhh");
        engine = web.getEngine();
        engine.load("https://player.twitch.tv/?volume=0.5&channel=eclypsiatvlol");

//     engine.executeScript("window.location = \"https://www.youtube.com/\";");
//        
//        System.out.println(url.toString());
//        System.out.println(this.getClass().getResource(url1).toExternalForm());
// 
//     mediaP = new MediaPlayer(new Media(this.getClass().getResource(url1).toExternalForm()));
//    mediaP.setAutoPlay(true);
//    med.setMediaPlayer(mediaP);
//          } catch (URISyntaxException ex) {
//              Logger.getLogger(VideoEnDirectController.class.getName()).log(Level.SEVERE, null, ex);
//          }
    }


    
  

    @FXML
    private void openWeb(ActionEvent event) {
                 Desktop d = Desktop.getDesktop();
              
        try {
                  try {
                      d.browse(new URI("https://player.twitch.tv/?volume=0.5&channel=momanus"));
                  } catch (IOException ex) {
                      Logger.getLogger(VideoEnDirectController.class.getName()).log(Level.SEVERE, null, ex);
                  }
        } catch (URISyntaxException ex) {
            Logger.getLogger(VideoEnDirectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
