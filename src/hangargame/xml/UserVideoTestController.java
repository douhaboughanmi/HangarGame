/*
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
import hangargame.HangarGame;
import hangargame.entites.VideoTest;
import hangargame.services.VideoTestCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UserVideoTestController implements Initializable {

    @FXML
    private Hyperlink toutVideo;

     @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    
    @FXML
    private TableView<VideoTest> listevideo;

    @FXML
    private TableColumn<VideoTest, String> nomvid;

    @FXML
    private TableColumn<VideoTest, String> urlvid;

    @FXML
    private TableColumn<VideoTest, String> descvid;

    @FXML
    private TableColumn<VideoTest, String> datevid;

    @FXML
    private TableColumn<VideoTest, String> genrevid;

    @FXML
    private TableColumn<VideoTest, String> consolevid;

    @FXML
    private JFXButton btnmodif;

    @FXML
    private JFXButton btnsup;

    @FXML
    private JFXTextField nom;

    @FXML
    private Label labelnom;

    @FXML
    private JFXTextField url;

    @FXML
    private Label labelurl;

    @FXML
    private JFXTextField desc;

    @FXML
    private Label labeldesc;

    @FXML
    private JFXTextField date;

    @FXML
    private Label labeldate;

    @FXML
    private JFXTextField genre;

    @FXML
    private Label labelgenre;

    @FXML
    private JFXTextField console;

    @FXML
    private Label labelconsole;

    @FXML
    private JFXButton btnback;
    @FXML
    private JFXTextField rech;
    @FXML
    private Label labid;

    private int ID;
    private ObservableList<VideoTest> data;

    String loginStat = LoginController.LoginStatic;

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
        
        
        LoadData();
        rech.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filtertList((String) oldValue, (String) newValue);

            }
        });
    }

    void LoadData() {

        VideoTestCrud crud = new VideoTestCrud();
        System.out.println("Out");
        nomvid.setCellValueFactory(new PropertyValueFactory<>("nom_videoTest"));
        //urlvid.setCellValueFactory(new PropertyValueFactory<>("url_videoTest"));
        descvid.setCellValueFactory(new PropertyValueFactory<>("description_videoTest"));
        //datevid.setCellValueFactory(new PropertyValueFactory<>("date_videoTest"));
        genrevid.setCellValueFactory(new PropertyValueFactory<>("genre_videoTest"));
        consolevid.setCellValueFactory(new PropertyValueFactory<>("console_videoTest"));

        listevideo.setItems(null);
        listevideo.setItems(crud.afficherVideoTestUser(loginStat));

    }

    @FXML
    void selectionner(MouseEvent event) {

        VideoTest v = listevideo.getSelectionModel().getSelectedItem();
        nom.setText(v.getNom_videoTest());

        desc.setText(v.getDescription_videoTest());
        genre.setText(v.getGenre_videoTest());
        console.setText(v.getConsole_videoTest());

        labid.setText("" + v.getId_videoTest());
        ID = v.getId_videoTest();
    }

    public void filtertList(String oldValue, String newValue) {
        ObservableList<VideoTest> filteredList = FXCollections.observableArrayList();
        if (rech == null || (newValue.length() < oldValue.length()) || newValue == null) {

            listevideo.setItems(data);
            LoadData();
        } else {
            newValue = newValue.toUpperCase();
            for (VideoTest v : listevideo.getItems()) {
                String filterNom = v.getNom_videoTest();

                if (filterNom.toUpperCase().contains(newValue)) {
                    filteredList.add(v);
                }
            }
            listevideo.setItems(filteredList);
        }
    }

    @FXML
    void supprimer(ActionEvent event) {

        VideoTest v = new VideoTest();

        VideoTestCrud crud = new VideoTestCrud();
        crud.supprimer(listevideo.getSelectionModel().getSelectedItem().getId_videoTest());
        LoadData();

    }

    @FXML
    void modifier(ActionEvent even) {
        VideoTestCrud crud = new VideoTestCrud();

        String nomVideo = nom.getText();

        String descVideo = desc.getText();
        String genreVideo = genre.getText();
        String consoleVideo = console.getText();

        if (nomVideo.isEmpty() == false
                && descVideo.isEmpty() == false
                && genreVideo.isEmpty() == false
                && consoleVideo.isEmpty() == false) {
            VideoTest vid = new VideoTest(ID, nomVideo, descVideo, genreVideo, consoleVideo);
            crud.modifier(vid);
            LoadData();
        }

        if (nomVideo.isEmpty()) {
            labelnom.setText("Champ obligatoire");

        }

        if (genreVideo.isEmpty()) {
            labelgenre.setText("Champ obligatoire");

        }

        if (descVideo.isEmpty()) {
            labeldesc.setText("Champ obligatoire");
        }

        if (consoleVideo.isEmpty()) {
            labelconsole.setText("Champ obligatoire");

        }
        if (nomVideo.isEmpty() == false) {
            labelnom.setText("");

        }

        if (genreVideo.isEmpty() == false) {
            labelgenre.setText("");

        }

        if (descVideo.isEmpty() == false) {
            labeldesc.setText("");

        }
        if (consoleVideo.isEmpty() == false) {
            labelconsole.setText("");

        }

        nom.setText("");

        desc.setText("");
        genre.setText("");
        console.setText("");

    }

    @FXML
    void afficherVideo(ActionEvent event) throws IOException {
        HangarGame h = new HangarGame();
        h.depalcerVideotest();

    }

    @FXML
    void back(ActionEvent event) throws IOException {
         HangarGame h = new HangarGame();
        h.depalcerVersAccueil();

    }

}
