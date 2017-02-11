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
import hangargame.entites.Evenement;
import hangargame.services.EvenementCrud;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private JFXButton btnValider = new JFXButton();

    @FXML
    private JFXTextArea areaDescription = new JFXTextArea();

    @FXML
    private JFXTextField txtNom = new JFXTextField();

    @FXML
    private JFXTextField txtAdresse = new JFXTextField();

    @FXML
    private JFXDatePicker dateDebut = new JFXDatePicker();

    @FXML
    private JFXDatePicker dateFin = new JFXDatePicker();

    @FXML
    void ajout(ActionEvent event) {
         Evenement e = new Evenement();
        e.setNom(txtNom.getText());
        e.setAdresse(txtAdresse.getText());
        e.setDescription(areaDescription.getText());
        e.setDatedebut(dateDebut.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        e.setDatefin(dateFin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));        
        EvenementCrud crud = new EvenementCrud();
        crud.ajouterEvenement(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
