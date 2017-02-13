/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Evenement;
import hangargame.entites.VideoTest;
import hangargame.services.VideoTestCrud;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VideoTestBackController implements Initializable {

    @FXML
    private TableView<VideoTest> listeVideo;

    @FXML
    private TableColumn<VideoTest, String> nomVideo;

    @FXML
    private TableColumn<VideoTest, String> urlVideo;

    @FXML
    private TableColumn<VideoTest, String> descVideo;

    @FXML
    private TableColumn<VideoTest, String> dateVideo;

    @FXML
    private TableColumn<VideoTest, String> genreVideo;

    @FXML
    private TableColumn<VideoTest, String> consoleVideo;

    @FXML
    private TableColumn<VideoTest, String> user;

    private ObservableList<VideoTest> data;

    @FXML
    private JFXButton btnsupprimer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Connection connect;
        Statement ste;
        PreparedStatement prepste;
        LoadData();

    }

    @FXML
    void supprimerVideo(ActionEvent event) {

        VideoTest v = new VideoTest();

        VideoTestCrud crud = new VideoTestCrud();
        crud.supprimer(listeVideo.getSelectionModel().getSelectedItem().getId_videoTest());
        LoadData();

    }
    
     void LoadData(){
       VideoTestCrud crud = new VideoTestCrud();
         System.out.println("Out");
        nomVideo.setCellValueFactory(new PropertyValueFactory<>("nom_videoTest"));
       urlVideo.setCellValueFactory(new PropertyValueFactory<>("url_videoTest"));
        descVideo.setCellValueFactory(new PropertyValueFactory<>("description_videoTest"));
        dateVideo.setCellValueFactory(new PropertyValueFactory<>("date_videoTest"));
        genreVideo.setCellValueFactory(new PropertyValueFactory<>("genre_videoTest"));
        consoleVideo.setCellValueFactory(new PropertyValueFactory<>("console_videoTest"));
        listeVideo.setItems(null);
        listeVideo.setItems(crud.afficherVideoTest());
    
    }

}
