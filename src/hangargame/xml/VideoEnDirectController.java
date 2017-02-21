/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.deploy.util.NativeLibraryBundle;
//import com.teamdev.jxbrowser.chromium.Browser;
//import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import hangargame.HangarGame;
import hangargame.entites.VideoEnDirect;
import hangargame.services.VideoEnDirectCrud;
import java.awt.Canvas;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import static javafx.scene.paint.Color.web;
import static javafx.scene.paint.Color.web;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VideoEnDirectController implements Initializable {

    
        @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private WebView web;
    private WebEngine engine;

    @FXML
    private JFXButton btnweb;
    @FXML
    private JFXButton btnback;
    @FXML
    private Hyperlink listVideo;

    ObservableList<Label> lab;
    @FXML
    private JFXListView<Label> listvideo;
    public String path;
    List<String> l = new ArrayList<>();
    
       @FXML
    private Label labelvid;

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
        
        

        VideoEnDirectCrud crud = new VideoEnDirectCrud();
        List<VideoEnDirect> list = crud.afficher();

        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label("nom live:  " + list.get(i).getNom_videoEnDirect() + "\n"
                    + //"url video:   "+list.get(i).getUrl_videoTest() + "DT" + "\n" +
                    "description :  " + list.get(i).getDescription_videoEnDirect());

            l.add(list.get(i).getUrl_videoEnDirect());

            listvideo.getItems().add(lbl);
            listvideo.setExpanded(true);
            listvideo.depthProperty().set(1);
        }

    }

    @FXML
    void load(MouseEvent event)  {

        path = "";
        int a = listvideo.getSelectionModel().getSelectedIndex();
        path = l.get(a).toString();

        System.out.println(path);
        engine = web.getEngine();
       engine.load(path);
        //Browser browser = new Browser();
        //BrowserView view = new BrowserView(browser);
        
      
        

      //  Scene scene = new Scene(new BorderPane(view), 700, 500);

        //browser.loadURL(path);
        //labelvid
        
        

    }

    @FXML
    private void openWeb(ActionEvent event) {

        path = "";
        int a = listvideo.getSelectionModel().getSelectedIndex();
        path = l.get(a).toString();

        System.out.println(path);
        Desktop d = Desktop.getDesktop();

        try {
            try {
                d.browse(new URI(path));
            } catch (IOException ex) {
                Logger.getLogger(VideoEnDirectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(VideoEnDirectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    void back(ActionEvent event) throws IOException {
         HangarGame h = new HangarGame();
        h.depalcerVersAccueil();

    }

}
