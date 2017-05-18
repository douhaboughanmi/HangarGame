/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.entites.VideoTest;
import hangargame.entites.commentaireVideoTest;
import hangargame.services.CommentaireVideoTestCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CommentaireVideoTestBackController implements Initializable {

    @FXML
    private TableView<commentaireVideoTest> listecoment;

    @FXML
    private TableColumn<commentaireVideoTest, String> lcomen;

    @FXML
    private TableColumn<commentaireVideoTest, String> luser;

    @FXML
    private JFXButton btnsupp;
    @FXML
    private JFXTextField rech;
    private ObservableList<commentaireVideoTest> data;
    
      @FXML
    private Hyperlink listeVid;
      
          @FXML
    private JFXButton btnback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         rech.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filtertList((String) oldValue, (String) newValue);
                

            }
        });
        LoadData();
    }

    void LoadData() {
        CommentaireVideoTestCrud crud = new CommentaireVideoTestCrud();
        System.out.println("Out");
        lcomen.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
        luser.setCellValueFactory(new PropertyValueFactory<>("user"));

        listecoment.setItems(null);
        listecoment.setItems(crud.afficherCommentaire());

    }

    @FXML
    void supprimer(ActionEvent event) {
        
        commentaireVideoTest co = new commentaireVideoTest();

        CommentaireVideoTestCrud crud = new CommentaireVideoTestCrud();
        crud.supprimer(listecoment.getSelectionModel().getSelectedItem().getId());
        LoadData();

    }
    
     public void filtertList(String oldValue, String newValue) {
        ObservableList<commentaireVideoTest> filteredList = FXCollections.observableArrayList();
        if (rech == null || (newValue.length() < oldValue.length()) || newValue == null) {

            listecoment.setItems(data);
            LoadData();
        } else {
            newValue = newValue.toUpperCase();
            for (commentaireVideoTest co: listecoment.getItems()) {
                String filterNom = co.getUser();
                
                if (filterNom.toUpperCase().contains(newValue) ) {
                    filteredList.add(co);
                }
            }
            listecoment.setItems(filteredList);
        }
    }
     
         @FXML
    void consulterListeVid(ActionEvent event) throws IOException {
         HangarGame h = new HangarGame();
     h.depalcerVerslisteVideoBack();

    }
    @FXML
    void back(ActionEvent event) {

    }

}
