/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import hangargame.HangarGame;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SidePanelContenentController implements Initializable {
    HangarGame hg = new HangarGame();

    @FXML
    private VBox drawer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deplacementEvenementAdmin(ActionEvent event) throws IOException {
        hg.depalcerVersEvenementGamer();  }

    @FXML
    private void DeplacementVersTournoi(ActionEvent event) throws IOException {
        hg.DeplacerVersTournoi();
    }
 
   
}
