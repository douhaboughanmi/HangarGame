/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Evenement;
import hangargame.services.EvenementCrud;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

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
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtAdresse;
    @FXML
    private JFXDatePicker dateDebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private JFXTextArea areaDescription;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXTextField txtRe;
    @FXML
    private JFXButton btnModif;
    @FXML
    private JFXTextField txtId;
    @FXML
    private TableColumn<?, ?> colId;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection connect;
        Statement ste;
        PreparedStatement prepste;
        LoadData();
        txtRe.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterEvenementList((String) oldValue, (String) newValue);

            }
        });

    }

    private @FXML
    void SupprimerEvenement(ActionEvent event) {
        int i;
        Evenement e = new Evenement();
        EvenementCrud EC = new EvenementCrud();
        i = tableEvenement.getSelectionModel().getSelectedItem().getId();
        EC.supprimerEvenement(i);
        LoadData();

    }

    void LoadData() {
        EvenementCrud EC = new EvenementCrud();

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        tableEvenement.setItems(null);
        tableEvenement.setItems(EC.afficherEvenement());

    }

    @FXML
    private void ajout(ActionEvent event) {
        areaDescription.setWrapText(true);
        Evenement e = new Evenement();
        String a = txtNom.getText();
        String b = areaDescription.getText();
        String c = txtAdresse.getText();
        LocalDate dt = dateDebut.getValue();
        LocalDate de = dateFin.getValue();
        e.setNom(a);
        e.setAdresse(b);
        e.setDescription(c);
        e.setDatedebut(dt);
        e.setDatefin(de);
        EvenementCrud crud = new EvenementCrud();
        
        
        if(dt.isBefore(de) && !a.isEmpty() && !b.isEmpty())
        {
         crud.ajouterEvenement(e);
        LoadData();
        txtNom.clear();
        areaDescription.clear();
        txtAdresse.clear();
        dateDebut.setValue(null);
        dateFin.setValue(null);
        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Vérifier vos informations");
        alert.showAndWait(); 
        }
       
    }

    public void filterEvenementList(String oldValue, String newValue) {
        ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
        if (txtRe == null || (newValue.length() < oldValue.length()) || newValue == null) {

            tableEvenement.setItems(data);
            LoadData();
        } else {
            newValue = newValue.toUpperCase();
            for (Evenement t : tableEvenement.getItems()) {
                String filterNom = t.getNom();
                String filterAdresse = t.getAdresse();
                if (filterNom.toUpperCase().contains(newValue) || filterAdresse.toUpperCase().contains(newValue)) {
                    filteredList.add(t);
                }
            }
            tableEvenement.setItems(filteredList);
        }
    }

    @FXML
    private void show(MouseEvent event) {
        Evenement e = tableEvenement.getSelectionModel().getSelectedItem();

        txtId.setText(Integer.toString(e.getId()));
        txtNom.setText(e.getNom());
        txtAdresse.setText(e.getAdresse());
        areaDescription.setText(e.getDescription());
        areaDescription.setWrapText(true);
        dateDebut.setValue(e.getDatedebut());
        dateFin.setValue(e.getDatefin());

    }

    @FXML
    private void Modifier(ActionEvent event) {
        Evenement e = new Evenement();
        e.setId(Integer.parseInt(txtId.getText()));
        e.setNom(txtNom.getText());
        e.setAdresse(areaDescription.getText());
        e.setDescription(txtAdresse.getText());
        e.setDatedebut(dateDebut.getValue());
        e.setDatefin(dateFin.getValue());
        EvenementCrud crud = new EvenementCrud();
        crud.modifierEvenement(e);
        LoadData();
    }

}
