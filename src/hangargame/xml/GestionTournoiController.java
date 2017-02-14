/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Tournoi;
import hangargame.services.TournoiCrud;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class GestionTournoiController implements Initializable {
    
    @FXML
    private JFXTextField txtNomTournoi;
    @FXML
    private JFXTextField txtNomJeu;
    @FXML
    private JFXTextField nbrMax;
    @FXML
    private JFXDatePicker dateDebut;
    @FXML
    private JFXDatePicker dateFin;
//    @FXML
//    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<Tournoi, String> colNom;
    @FXML
    private TableColumn<Tournoi, String> colNJeu;
    @FXML
    private TableColumn<?, ?> colNbr;
    @FXML
    private JFXButton btnValider;
    @FXML
    private TableColumn<?, ?> ColdateDebut;
    @FXML
    private TableColumn<?, ?> ColdateFin;
    @FXML
    private TableView<Tournoi> tableTournoi;
    private ObservableList<Tournoi> data;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private JFXTextField txtRe;
    @FXML
    private JFXButton btnSupprimer;
    @FXML
    private Label attLabel;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
        Connection connect;
        Statement ste;
        PreparedStatement prepste;
        ResultSet rs;
        txtRe.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterTournoiList((String) oldValue, (String) newValue);

            }
        });
        
        
    }
    
    void LoadData() {
        TournoiCrud EC = new TournoiCrud();
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNJeu.setCellValueFactory(new PropertyValueFactory<>("nom_jeu"));
        colNbr.setCellValueFactory(new PropertyValueFactory<>("nbr_max"));
        ColdateDebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        ColdateFin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        tableTournoi.setItems(null);
        tableTournoi.setItems(EC.afficherTournoi());
        
    }
    
    @FXML
    private void ajout(ActionEvent event) {
        Tournoi e = new Tournoi();
        e.setNom(txtNomTournoi.getText());
        e.setNom_jeu(txtNomJeu.getText());
        int num = Integer.parseInt(nbrMax.getText());
        e.setNbr_max(num);
        e.setDatedebut(dateDebut.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        e.setDatefin(dateFin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        TournoiCrud crud = new TournoiCrud();
        crud.ajouterTournoi(e);
        LoadData();
        txtNomTournoi.clear();
        txtNomJeu.clear();
        nbrMax.clear();
        dateDebut.setValue(null);
        dateFin.setValue(null);
        
    }
    
    @FXML
    private void SupprimerTournoi(ActionEvent event) {
        int i;
        Tournoi e = new Tournoi();
        TournoiCrud EC = new TournoiCrud();
        i = tableTournoi.getSelectionModel().getSelectedItem().getId();
        EC.supprimerTournoi(i);
        LoadData();
        
    }
    
    
    public void filterTournoiList(String oldValue, String newValue) {
        ObservableList<Tournoi> filteredList = FXCollections.observableArrayList();
        if(txtRe == null || (newValue.length() < oldValue.length()) || newValue == null) {
            
            tableTournoi.setItems(data);
            LoadData();
        }
        else {
            newValue = newValue.toUpperCase();
            for(Tournoi t : tableTournoi.getItems()) {
                String filterFirstName = t.getNom();
                String filterLastName = t.getNom_jeu();
                if(filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(t);
                }
            }
            tableTournoi.setItems(filteredList);
        }
    }
     
    
}
