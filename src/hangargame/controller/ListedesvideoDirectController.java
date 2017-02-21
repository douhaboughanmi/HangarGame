/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import hangargame.HangarGame;
import hangargame.entites.VideoTest;
import hangargame.entites.commentaireVideoTest;
import hangargame.services.CommentaireVideoTestCrud;
import hangargame.services.VideoTestCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListedesvideoDirectController implements Initializable {

       @FXML
    private JFXDrawer drawer;
  @FXML
    private JFXHamburger hamburger;
    
    ObservableList<Label> lab;
    @FXML
    private JFXListView<Label> listvideo;
    public String path;
    List<String> l = new ArrayList<>();
    List<Integer> lc = new ArrayList<>();

    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;

    @FXML
    private JFXButton btnplay;

    @FXML
    private JFXButton btnfpause;

    @FXML
    private JFXButton btnlast;

    @FXML
    private JFXButton btnstart;

    @FXML
    private JFXButton btnreload;

    @FXML
    private JFXButton btnslow;

    @FXML
    private JFXButton btnfast;
    @FXML
    private JFXButton btnpartage;
    @FXML
    private JFXButton btnback;

    @FXML
    private Hyperlink ajoutVid;
    @FXML
    private JFXListView<Label> listecom;

    @FXML
    private TextArea textcom;
    @FXML
    private Hyperlink mesVid;
    String loginStat = LoginController.LoginStatic;
    @FXML
    private JFXButton btnajoutcom;
    private List<VideoTest> list = new ArrayList<VideoTest>();

    List<commentaireVideoTest> list1 = new ArrayList<>();

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
        
        
        
        
        VideoTestCrud crud = new VideoTestCrud();
        l.clear();
        list.clear();
        lc.clear();
        list = crud.afficher();

        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label("nom Video:  " + list.get(i).getNom_videoTest() + "\n"
                    + //"url video:   "+list.get(i).getUrl_videoTest() + "DT" + "\n" +
                    "description :  " + list.get(i).getDescription_videoTest() + "\n"
                    + "date:  " + list.get(i).getDate_videoTest() + "\n"
                    + "genre Video:  " + list.get(i).getGenre_videoTest() + "\n"
                    + "console:  " + list.get(i).getConsole_videoTest() + "\n"
                    + "utilisateur:  " + list.get(i).getUser_videoTest());

            l.add(list.get(i).getUrl_videoTest());
            lc.add(list.get(i).getId_videoTest());

            listvideo.getItems().add(lbl);
            listvideo.setExpanded(true);
            listvideo.depthProperty().set(1);
        }
        // int a = listvideo.getSelectionModel().getSelectedIndex();
       

    }

    @FXML
    void load(MouseEvent event) {

        //  l.clear();
        path = "";
        int a = listvideo.getSelectionModel().getSelectedIndex();
        path = l.get(a).toString();

        System.out.println(path);

        me = new Media(new File(path).toURI().toString());

        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        
        ajouterCom();
        

    }

    void ajouterCom() {
     

        int a = listvideo.getSelectionModel().getSelectedIndex();
        CommentaireVideoTestCrud cru = new CommentaireVideoTestCrud();
        list1 = cru.afficher(lc.get(a).toString());
        

        for (int i = 0; i < list1.size(); i++) {

            Label lbl = new Label(list1.get(i).getUser() + "\n"
                    + list1.get(i).getCommentaire());

            listecom.getItems().add(lbl);
            list1.clear();
            
             

        }
        
       

    }

    @FXML
    void ajouter(ActionEvent event) {
        loginStat = "jjjj";

        String commentaire = textcom.getText();
        String user = loginStat;
        int a = listvideo.getSelectionModel().getSelectedIndex();
        String idvideo = lc.get(a).toString();
        CommentaireVideoTestCrud crud = new CommentaireVideoTestCrud();
        commentaireVideoTest c = new commentaireVideoTest(commentaire, user, idvideo);
        crud.ajouterCommentaire(c);
        textcom.setText(" ");

    }

    @FXML
    void play(ActionEvent event) {
        mp.play();

    }

    @FXML
    void pause(ActionEvent event) {
        mp.pause();

    }

    @FXML
    void fast(ActionEvent event) {
        mp.setRate(2);

    }

    @FXML
    void slow(ActionEvent event) {
        mp.setRate(.5);

    }

    @FXML
    void reload(ActionEvent event) {
        mp.seek(mp.getStartTime());
        mp.play();

    }

    @FXML
    void start(ActionEvent event) {
        mp.seek(mp.getStartTime());

    }

    @FXML
    void last(ActionEvent event) {
        mp.seek(mp.getTotalDuration());

    }

    @FXML
    void partage(ActionEvent event) {

    }

    @FXML
    void ajoutVideo(ActionEvent event) throws IOException {
        HangarGame h = new HangarGame();
        h.depalcerlistedesvideoTest();

    }

    @FXML
    void consulterVIdeo(ActionEvent event) throws IOException {
        HangarGame h = new HangarGame();
        h.depalcerUserVideoTest();

    }

    @FXML
    void back(ActionEvent event) throws IOException {
          HangarGame h = new HangarGame();
        h.depalcerVersAccueil();

    }

}
