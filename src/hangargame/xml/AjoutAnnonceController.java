/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import hangargame.services.CrudAnnonces;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author mayss
 */
public class AjoutAnnonceController implements Initializable {

    @FXML
    private JFXButton btnAnnonce = new JFXButton();

    @FXML
    private JFXTextField titreAnnonce = new JFXTextField();

    @FXML
    private JFXTextField prixAnnonce = new JFXTextField();

    @FXML
    private TextArea descriptionAnnonce = new JFXTextArea();

    @FXML
    private JFXComboBox<Label> typeAnnonce = new JFXComboBox<>();

    @FXML
    private ToggleGroup a = new ToggleGroup();

    @FXML
    void login(ActionEvent event) {
        String typeA = typeAnnonce.getValue().getText();
        String nomA = titreAnnonce.getText();
        String desc = descriptionAnnonce.getText();
        String ab = prixAnnonce.getText();
        int prix = Integer.parseInt(ab);
        CrudAnnonces crud = new CrudAnnonces();
         crud.ajouterAnnonces(nomA,typeA,"",desc,prix);

       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeAnnonce.getItems().add(new Label("Echange"));
        typeAnnonce.getItems().add(new Label("Vente"));

        typeAnnonce.setPromptText("Choisisez le type d'annonce");
        
       

    }

}
