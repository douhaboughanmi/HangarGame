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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AjoutConsoleController implements Initializable {
   @FXML
    private JFXButton btnConsole;

    @FXML
    private JFXTextField titreConsole = new JFXTextField();

    @FXML
    private TextArea descriptionConsole =  new JFXTextArea();

    @FXML
    private Label labeldesc = new Label();

    @FXML
    private Label labelTitre = new Label();

    @FXML
    private JFXDatePicker dateConsole  = new JFXDatePicker();

    @FXML
    private JFXButton btnImage = new JFXButton();

    @FXML
    private Label labelimage = new Label();

    @FXML
    void ajout(ActionEvent event) {
        
        

    }

    @FXML
    void selectImage(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
