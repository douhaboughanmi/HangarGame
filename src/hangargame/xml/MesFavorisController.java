/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXListView;
import hangargame.HangarGame;
import hangargame.entites.Annonces;
import hangargame.services.CrudAnnonces;
import hangargame.services.CrudFavoris;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mayss
 */
public class MesFavorisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    HangarGame hangar = new HangarGame();
      @FXML
    private JFXListView<Label> listView;

      List<String> liste=new ArrayList<>();
      List<Annonces> list = new ArrayList<>();
    
     @FXML
    void annonce(MouseEvent event) throws IOException {
        int a = listView.getSelectionModel().getSelectedIndex();
         AccueilAnnonceController.indexAnnonce2= Integer.parseInt( liste.get(a));
         hangar.depalcerVersAnnonceDetail();
    }
      
      
            @FXML
    void retourAcceuil(ActionEvent event) throws IOException {
HangarGame hg = new HangarGame();
hg.depalcerVersAcceuil();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        CrudFavoris cf = new CrudFavoris();
        list =cf.afficherFavoris();

        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n"
                    + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces()
            );
            liste.add(list.get(i).getIdAnnonces() + "");

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listView.getItems().add(lbl);

            listView.setExpanded(true);
            listView.depthProperty().set(1);
        }
        
    }    
    
}
