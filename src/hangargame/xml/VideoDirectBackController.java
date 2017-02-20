/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Evenement;
import hangargame.entites.VideoEnDirect;
import hangargame.entites.VideoTest;
import hangargame.services.VideoEnDirectCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private JFXTextField rechVideoDirect;
    
    @FXML
    private Label labelid;
    
    @FXML
    private JFXButton btnback;
    
    private int ID;


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
        
        rechVideoDirect.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterEvenementList((String) oldValue, (String) newValue);
                

            }
        });

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
    
    public void filterEvenementList(String oldValue, String newValue) {
        ObservableList<VideoEnDirect> filteredList = FXCollections.observableArrayList();
        if (rechVideoDirect == null || (newValue.length() < oldValue.length()) || newValue == null) {

            listeVideoDirect.setItems(data);
            LoadData();
        } else {
            newValue = newValue.toUpperCase();
            for (VideoEnDirect v: listeVideoDirect.getItems()) {
                String filterNom = v.getNom_videoEnDirect();
                
                if (filterNom.toUpperCase().contains(newValue) ) {
                    filteredList.add(v);
                }
            }
            listeVideoDirect.setItems(filteredList);
        }
    }
    
        @FXML
    void selectiooner(MouseEvent event) {
        
        VideoEnDirect v=  listeVideoDirect.getSelectionModel().getSelectedItem();
      nomVideoDirect.setText(  v.getNom_videoEnDirect());
      urlVideoDirect.setText(v.getUrl_videoEnDirect());
      descVideoDirect.setText(v.getDescription_videoEnDirect());
      
       labelid.setText(""+v.getId_videoEnDirect()); 
        ID = v.getId_videoEnDirect();

    }
    
    @FXML
    void modifierVideoDirect(ActionEvent event) {
        
            String nomVideo = nomVideoDirect.getText();
        String urlvideo = urlVideoDirect.getText();
        String descVideo = descVideoDirect.getText();

        if (nomVideo.isEmpty() == false
                && urlvideo.isEmpty() == false
                && descVideo.isEmpty() == false) {
            VideoEnDirect vid = new VideoEnDirect(ID, nomVideo, urlvideo, descVideo);
            crud.modifier(vid);
            LoadData();
        
  }
    }
    
    
    @FXML
    void back(ActionEvent event) throws IOException {
       
    }

}
