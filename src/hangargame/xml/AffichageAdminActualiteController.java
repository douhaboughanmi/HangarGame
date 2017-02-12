/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import hangargame.entites.Actualite;
import hangargame.services.CrudActualite;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<?, ?> colImage;

      @FXML
    private Button btnafficher;

    @FXML
    void afficher() {
        
        
        
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("text"));
        colImage .setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
       tableAct.setItems(null);
       tableAct.setItems(crud.afficherActualite());
                    

    }
    
   
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
        // TODO
    }    
    
}
