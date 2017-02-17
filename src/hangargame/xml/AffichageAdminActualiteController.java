/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Actualite;
import hangargame.services.CrudActualite;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AffichageAdminActualiteController implements Initializable {

    CrudActualite crud = new CrudActualite();
    @FXML
    private TableView<Actualite> tableAct;

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TableColumn<?, ?> colDesc;
    @FXML
    private JFXTextField titreAct;
    @FXML
    private TextArea descriptionact;
    @FXML
    private JFXButton BtnImage;
    @FXML
    private Label image;
    @FXML
    private JFXButton btnSup;
    @FXML
    private JFXButton btnmodif;
    int id ;


    void afficher() {
        
        
        
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("text"));
       // colImage .setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
       tableAct.setItems(null);
       tableAct.setItems(crud.afficherActualite());
                    

    }
    
   
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
        // TODO
    }    

    @FXML
    private void selectImage(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        Actualite a = new Actualite();
        CrudActualite cc = new CrudActualite();
        cc.supprimerActualite(tableAct.getSelectionModel().getSelectedItem().getTitre());
        afficher();
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
         String ta = titreAct.getText();
    String desc =descriptionact.getText();
    
    Actualite act = new Actualite(id, ta, desc, "");
    crud .modifierActualite(act);
    afficher() ; 
    }

    @FXML
    private void showcliked(MouseEvent event) {
        
          Actualite act= tableAct.getSelectionModel().getSelectedItem();
       act.toString();
        id=act.getId();
        System.out.println(id);
        System.out.println(act.getTitre());
        System.out.println(act.getText());
       titreAct.setText(act.getTitre());
     descriptionact.setText(act.getText());
        
    }
    
}
