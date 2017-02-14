/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Annonces;
import hangargame.entites.Gamer;
import hangargame.services.CrudAnnonces;
import hangargame.services.ServicesGamer;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherListGamerAdminController implements Initializable {
    @FXML
    private TableView<Gamer> TV_Gamer;
    @FXML
    private TableColumn<Gamer, String> CLogin;
    @FXML
    private TableColumn<Gamer, String> CNom;
    @FXML
    private TableColumn<Gamer, String> CPrenom;
    @FXML
    private TableColumn<Gamer, String> CEmail;
    @FXML
    private TableColumn<Gamer, String> CAdresse;
    @FXML
    private TableColumn<Gamer, String> CTel;
    @FXML
    private TableColumn<Gamer, String> CDateInscri;
    
     private ObservableList<Gamer> data;
    @FXML
    private Label LAdresse;
    @FXML
    private Label LEmail;
    @FXML
    private Label LLogin;
    @FXML
    private Label LPrenom;
    @FXML
    private Label LNom;
    @FXML
    private Label LTel;
    @FXML
    private Label LDateInscri;
    @FXML
    private Label LImage;
    @FXML
    private JFXTextField TF_Recherch;

    ServicesGamer s = new ServicesGamer();
   InputStream inputStream ;
    @FXML
    private Label LCompte;
    @FXML
    private Label LSignalisation;
    @FXML
    private Label PDF;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LoadData();
       
        TF_Recherch.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterGamerList((String) oldValue, (String) newValue);
                        
            }
        });
    }   
 @FXML
    void DisplayGamers(MouseEvent event) {

        Gamer gamers =  TV_Gamer.getSelectionModel().getSelectedItem();
        //annonces.toString();
        LLogin.setText(gamers.getLogin());
        LNom.setText(gamers.getNom());
        LPrenom.setText(gamers.getPrenom());
        LAdresse.setText(gamers.getAdresse());
        LEmail.setText(gamers.getEmail());
        LDateInscri.setText(gamers.getDateInscription().toString());
        String tel = String.valueOf(gamers.getTel());
        LTel.setText(tel);
        if(gamers.getEtat()==0)
        {
            String compte ="Actif ";
            LCompte.setText(compte);
        }
        else if (gamers.getEtat()==1)
        {
             String compte ="Bloquer ";
             LCompte.setText(compte);
        }
        
         
        
        
        inputStream=gamers.getImage();
        ImageView imageView = new ImageView(new Image(inputStream));
            imageView.setFitHeight(102);
            imageView.setFitWidth(130);

            LImage.setGraphic(imageView);
    }
    void LoadData() {
        
        
         data=FXCollections.observableArrayList();
       
      data.addAll(s.AfficherListeGamer());
      
      CLogin.setCellValueFactory(new PropertyValueFactory<>("login" ));
      CNom.setCellValueFactory(new PropertyValueFactory<>("nom" ));
      CPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      CEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        CAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse" ));
      CTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
      CDateInscri.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
      
       TV_Gamer.setItems(null);
        TV_Gamer.setItems(data);

    }
    
    public void filterGamerList(String oldValue, String newValue) {
        ObservableList<Gamer> filteredList = FXCollections.observableArrayList();
        if(TF_Recherch == null || (newValue.length() < oldValue.length()) || newValue == null) {
            
            TV_Gamer.setItems(data);
          
        }
        else {
            newValue = newValue.toUpperCase();
            for(Gamer t : TV_Gamer.getItems()) {
                String filterLogin = t.getLogin();
                String filterLastName = t.getPrenom();
                String filterFirstName = t.getPrenom();
                
                if(filterLogin.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)|| filterFirstName.toUpperCase().contains(newValue)) {
                    filteredList.add(t);
                }
            }
            TV_Gamer.setItems(filteredList);
        }
    }
    
    @FXML
    private void BloquerGamer(ActionEvent event) {
    }

    @FXML
    private void ExtrairePDF(ActionEvent event) {
    }
     
    
}
