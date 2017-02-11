/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Evenement;
import hangargame.services.EvenementCrud;
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
 * @author Louay
 */
public class AffichageEvenementController implements Initializable {

    @FXML
    private TableView<Evenement> tableEvenement;
    @FXML
    private TableColumn<Evenement, String> colNom;
    @FXML
    private TableColumn<Evenement, String> colDesc;
    @FXML
    private TableColumn<Evenement, String> colAdresse;
    @FXML
    private TableColumn<Evenement, String> colDateDebut;
    @FXML
    private TableColumn<Evenement, String> colDateFin;

    private ObservableList<Evenement> data;
    @FXML
    private JFXButton btnSupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection connect;
        Statement ste;
        PreparedStatement prepste;

    }

    
    private @FXML
     void SupprimerEvenement(ActionEvent event) {
        Evenement e = new Evenement();
        EvenementCrud EC = new EvenementCrud();
        EC.supprimerEvenement(tableEvenement.getSelectionModel().getSelectedItem().getId());
        LoadData();
        
    }
     void LoadData(){
        try {
            Connection connect;
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from evenement");
            while (rs.next()) {
                data.add(new Evenement(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        
        tableEvenement.setItems(null);
        tableEvenement.setItems(data);
    
    }

}
