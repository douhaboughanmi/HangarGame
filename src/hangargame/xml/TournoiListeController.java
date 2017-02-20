/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.entites.Evenement;
import hangargame.entites.Participants;
import hangargame.entites.Tournoi;
import hangargame.services.CrudParticipant;
import hangargame.services.TournoiCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class TournoiListeController implements Initializable {

    @FXML
    private JFXListView<Label> listeTournoi;
    Tournoi e = new Tournoi();
    ObservableList<Label> lab;
    @FXML
    private JFXTextArea txtNomTournoi;
    @FXML
    private JFXButton btnParticiper;
    List<String> liste = new ArrayList<>();
    ArrayList<Integer> l2 = new ArrayList<Integer>();
     HangarGame hg = new HangarGame();
    
String rec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        liste.clear();
        TournoiCrud crud = new TournoiCrud();
        List<Tournoi> list = crud.afficherTournoi();

        for (int i = 0; i < list.size(); i++) {

           
            Label lbl = new Label("Tournoi = " + i + "\n" + "Nom Tournoi : " + list.get(i).getNom() + "\n"
                    + "Nom Jeu : " + list.get(i).getNom_jeu() + "\n" + "Nombre Gamers Maximum : " + list.get(i).getNbr_max() + "\n" + "Date Debut : "
                    + list.get(i).getDatedebut() + "\n" + "Date Fin : " + list.get(i).getDatefin());
            liste.add(list.get(i).getId() + "");
            lbl.setWrapText(true);
            l2.add(list.get(i).getNbr_max());
            listeTournoi.getItems().add(lbl);
            listeTournoi.setExpanded(true);
            
            listeTournoi.depthProperty().set(1);
        }
    }

    @FXML
    private void showNom(MouseEvent event) {

        Label L = listeTournoi.getSelectionModel().getSelectedItem();
        txtNomTournoi.setText(L.getText());

    }

    @FXML
    private void ajout(ActionEvent event) {
        try {
            
         CrudParticipant CP = new CrudParticipant();
         Label L = listeTournoi.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(liste.get(listeTournoi.getSelectionModel().getSelectedIndex()));
        int b = l2.get(listeTournoi.getSelectionModel().getSelectedIndex());
        int c = CP.recupererParticipant(a);
        int f = b-c ;
        String rest = String.valueOf(f);
        if(b>c){
        Participants p = new Participants(a,"");
      rec=  CP.ajouterParticipants(p);
    if(rec.equals("vous avez déja etez inscrit")){
    Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tournoi");
        alert.setHeaderText(L.getText());
        alert.setContentText("Vous avez déja été inscrit");
        alert.showAndWait(); 
    }  
    if(rec.equals("")){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tournoi");
        alert.setHeaderText(L.getText());
        alert.setContentText("Participation Acceptée !! Bonne Chance! Il reste "+rest+" Places");
        alert.showAndWait(); 
        }}
        else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Tournoi");
        alert.setContentText("Nombre Maximum des Gamers Atteint !! Vous ne Pouvez plus Participer ");
        alert.showAndWait(); 
        }
        
        
        } catch (Exception e) {
            
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Tournoi");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez selectioner un Tournoi!");

        alert.showAndWait(); 
        }
       
        

    }

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
        hg.RetourAccueilEvenement();
    }

}
