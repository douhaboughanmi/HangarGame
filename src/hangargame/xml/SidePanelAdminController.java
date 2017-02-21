/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import hangargame.HangarGame;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SidePanelAdminController implements Initializable {

    @FXML
    private VBox drawer;

HangarGame hangar = new HangarGame();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherListGamer(ActionEvent event) throws IOException {
        hangar.depalcerVersAfficherListGamers();
    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
        hangar.depalcerVersStatistique();
    }
    
}
