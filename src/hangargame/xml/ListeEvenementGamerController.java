/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXListView;
import hangargame.HangarGame;
import hangargame.entites.Evenement;
import hangargame.services.EvenementCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class ListeEvenementGamerController implements Initializable {

    @FXML
    private JFXListView<Label> listView;
    Evenement e = new Evenement();
    ObservableList<Label> lab;
    List<String> liste = new ArrayList<>();
    @FXML
    private WebView mapView;
    private WebEngine engine ;
    String path ;
    HangarGame hg = new HangarGame();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        EvenementCrud crud = new EvenementCrud();
        List<Evenement> list = crud.afficherEvenement();

        for (int i = 0; i < list.size(); i++) {

            // Label lbl1 = new Label("Evenement = "+i +"\n");
            Label lbl = new Label("Evenement = " + i + "\n" + "Nom Evenement : " + list.get(i).getNom() + "\n"
                    + "Adresse : " + list.get(i).getAdresse() + "\n" + "Date Debut : " + list.get(i).getDatedebut() + "\n" + "Date Fin : " + list.get(i).getDatefin()
                    + "\n" + list.get(i).getDescription());
            liste.add(list.get(i).getAdresse());
            listView.getItems().add(lbl);
            //listView.setExpanded(true);
           // listView.depthProperty().set(1);
        }

    }

    @FXML
    private void showAdresse(MouseEvent event) {
        path = "";
        int a = listView.getSelectionModel().getSelectedIndex();
        path = liste.get(a).toString();
        engine = mapView.getEngine();
        engine.load("https://www.google.fr/maps/search/"+path);
    }

    @FXML
    private void retourAccueil(ActionEvent event) throws IOException {
        hg.RetourAccueilEvenement();
    }
}
