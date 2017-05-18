/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Console;
import hangargame.services.CrudConsole;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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

    private TableColumn<?, ?> colimage;

    @FXML
    private JFXButton btnSup;
    @FXML
    private JFXButton btnmodif;
    @FXML
    private JFXButton BtnImage;
    @FXML
    private Label image;
    
    private ObservableList<Console> data ;
    @FXML
    private JFXTextField titreConsole;
    @FXML
    private TextArea descriptionconsole;
    @FXML
    private JFXDatePicker dateConsole;
    int id ; 

    void afficher() {
        
        
         colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
       coldate.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
     //   colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
       tableConsole.setItems(null);
      tableConsole.setItems(crud.afficherConsole());

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    afficher();
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        
        Console c = new Console();
        CrudConsole cc = new CrudConsole();
        cc.supprimerConsole(tableConsole.getSelectionModel().getSelectedItem().getNom());
        afficher();
    }

    @FXML
    private void Modifier(ActionEvent event) {
      
         String tc = titreConsole.getText();
         String des = descriptionconsole.getText();
            Date datej=java.sql.Date.valueOf(dateConsole.getValue());
         
         Console cc = new Console(id, tc, "", des, datej);
         crud.modifierConsole(cc);
         afficher();
    }

    @FXML
    private void selectImage(ActionEvent event) {
    }

    @FXML
    private void showcliked(MouseEvent event) {
        
        Console cc = tableConsole.getSelectionModel().getSelectedItem();
        cc.toString();
        id=cc.getId();
         System.out.println(id);
          System.out.println(cc.getNom());
        titreConsole.setText(cc.getNom());
        descriptionconsole.setText(cc.getDescription());
    //    String datej= dateConsole.
        //       getValue()
       //       .format
     //  (DateTimeFormatter.
         //      ofPattern("YYYY-MM-DD")); 
        
        
        
    }
    
}
