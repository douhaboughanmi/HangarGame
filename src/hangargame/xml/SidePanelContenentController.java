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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SidePanelContenentController implements Initializable {
HangarGame hangar = new HangarGame();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
       @FXML
    void deplacerMesAnnonces(ActionEvent event) throws IOException {
hangar.depalcerVersAMesAnnonce();
    }
//    
    @FXML
   void depalcerVersMesFavoris(ActionEvent event) throws IOException {
hangar.depalcerVersAMesFavoris();
    }
    
    
       @FXML
    void deplacerVersVideoTest(ActionEvent event) throws IOException {
        HangarGame h = new HangarGame();
        h.depalcerVersVideoTest();

    }

    @FXML
    void deplacerVerslive(ActionEvent event) throws IOException {
          HangarGame h = new HangarGame();
        h.depalcerVersLive();

    }
    
    
     @FXML
    void depalcerVersAffichageSujet(MouseEvent event) throws IOException {
HangarGame h = new HangarGame();
h.depalcerVersAffichageSujetsl();
    }
    
     
    @FXML
    private void AffichageJEUCLIENt(MouseEvent event) throws IOException {
        
          HangarGame hang = new HangarGame();
                     hang.depalcerVersAffichageJeusidePanel();

       
    }

    @FXML
    private void AffichageConsoleClient(MouseEvent event) throws IOException {
        
          HangarGame hang = new HangarGame();
        hang.depalcerVersAffichageConsolesidePanel();
    }

    @FXML
    private void AffichageActualiteClient(ActionEvent event) throws IOException {
          HangarGame hang = new HangarGame();
          hang.depalcerVersAffichageActsidePanel();
        
    }
 
    
    
}
