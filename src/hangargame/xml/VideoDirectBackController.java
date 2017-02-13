/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.VideoEnDirect;
import hangargame.entites.VideoTest;
import hangargame.services.VideoEnDirectCrud;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VideoDirectBackController implements Initializable {

    @FXML
    private JFXButton btnmodifierVideoDirect;

    @FXML
    private TableColumn<VideoEnDirect, String> nomlive;

    @FXML
    private TableColumn<VideoEnDirect, String> urllive;

    @FXML
    private TableColumn<VideoEnDirect, String> desclive;

    @FXML
    private TableView<VideoEnDirect> listeVideoDirect;

    @FXML
    private JFXButton btnsupprimerVideoDirect;

    @FXML
    private JFXButton btnVideoDirect;

    @FXML
    private JFXTextField nomVideoDirect;

    @FXML
    private JFXTextField urlVideoDirect;

    @FXML
    private JFXTextArea descVideoDirect;

    @FXML
    private Label labelnom;

    @FXML
    private Label labelurl;

    @FXML
    private Label labeldesc;

    VideoEnDirectCrud crud = new VideoEnDirectCrud();
    private ObservableList<VideoEnDirect> data;

    @FXML
    void ajoutVideoDirect(ActionEvent event) {

        String nomVideo = nomVideoDirect.getText();
        String urlvideo = urlVideoDirect.getText();
        String descVideo = descVideoDirect.getText();

        if (nomVideo.isEmpty() == false
                && urlvideo.isEmpty() == false
                && descVideo.isEmpty() == false) {
            VideoEnDirect vid = new VideoEnDirect(nomVideo, urlvideo, descVideo);
            crud.ajouter(vid);
            LoadData();
        }

        if (nomVideo.isEmpty()) {
            labelnom.setText("Champ obligatoire");

        }

        if (urlvideo.isEmpty()) {
            labelurl.setText("Champ obligatoire");

        }

        if (descVideo.isEmpty()) {
            labeldesc.setText("Champ obligatoire");

        }
         if (nomVideo.isEmpty() == false) {
            labelnom.setText("");

        }

        if (urlvideo.isEmpty() == false) {
            labelurl.setText("");

        }

        if (descVideo.isEmpty() == false) {
            labeldesc.setText("");

        }
        
        nomVideoDirect.setText("");
        urlVideoDirect.setText("");
        descVideoDirect.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connect;
        Statement ste;
        PreparedStatement prepste;
        LoadData();

    }

    @FXML
    void modifierVideoDirect(ActionEvent event) {

    }

    @FXML
    void supprimerVideoDirect(ActionEvent event) {

        VideoEnDirect v = new VideoEnDirect();
        VideoEnDirectCrud crud = new VideoEnDirectCrud();
        crud.supprimer(listeVideoDirect.getSelectionModel().getSelectedItem().getId_videoEnDirect());
        LoadData();

    }

    void LoadData() {
        VideoEnDirectCrud crud = new VideoEnDirectCrud();

        System.out.println("Out");
        nomlive.setCellValueFactory(new PropertyValueFactory<>("nom_videoEnDirect"));
        urllive.setCellValueFactory(new PropertyValueFactory<>("url_videoEnDirect"));
        desclive.setCellValueFactory(new PropertyValueFactory<>("description_videoEnDirect"));

        listeVideoDirect.setItems(null);
        listeVideoDirect.setItems(crud.afficherVideoEnDirect());
    }

}
