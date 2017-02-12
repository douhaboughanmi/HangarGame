/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import hangargame.entites.Console;
import hangargame.services.CrudConsole;
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
public class AffichageAdminConsoleController implements Initializable {
    CrudConsole crud = new CrudConsole();
    
     @FXML
    private TableView<Console> tableConsole;
   @FXML
    private TableColumn<?, ?> colnom;

    @FXML
    private TableColumn<?, ?> coldesc;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colimage;

    @FXML
    private Button btnafficher;

    @FXML
    void afficher() {
        
        
         colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
       coldate.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
       tableConsole.setItems(null);
      tableConsole.setItems(crud.afficherConsole());

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    afficher();
    }    
    
}
