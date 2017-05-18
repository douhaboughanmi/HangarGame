/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
        ArrayList<String> l3 = new ArrayList<String>();

     HangarGame hg = new HangarGame();
    
    String rec;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anchor;
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
            l3.add(list.get(i).getNom());
            listeTournoi.getItems().add(lbl);
            listeTournoi.setExpanded(true);
            
            listeTournoi.depthProperty().set(1);
        }
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContenent.fxml"));
            drawer.setSidePane(box);
            
            for(Node node : box.getChildren()){
                System.out.println(node.getAccessibleText());
            if(node.getAccessibleText()!=null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
                
               
            if ("annonce".equals(node.getAccessibleText())){
                
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("AccueilAnnonce.fxml")); 
                    anchor.getChildren().addAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            if ("forum".equals(node.getAccessibleText())){
                 
            
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutSujet.fxml")); 
                    anchor.getChildren().addAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    
            }
            
              if ("xxxx".equals(node.getAccessibleText())){
                 
            
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutJeuVideo.fxml")); 
                    anchor.getChildren().addAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    
            }
                
             if ("GoToIUserInformation".equals(node.getAccessibleText())){
                
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("ChangePassword.fxml")); 
                    anchor.getChildren().addAll(pane);
                  
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
              
              
              
              
                });//apartir
         
            }
            
            }
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

      HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {    
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    @FXML
    private void showNom(MouseEvent event) {

        Label L = listeTournoi.getSelectionModel().getSelectedItem();
        txtNomTournoi.setText(L.getText());

    }

    @FXML
    private void ajout(ActionEvent event) {
       
            
         CrudParticipant CP = new CrudParticipant();
         Label L = listeTournoi.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(liste.get(listeTournoi.getSelectionModel().getSelectedIndex()));
        int b = l2.get(listeTournoi.getSelectionModel().getSelectedIndex());
        String NolT = l3.get(listeTournoi.getSelectionModel().getSelectedIndex());
        int c = CP.recupererParticipant(a);
        int f = b-c ;
        String rest = String.valueOf(f);
            
        if(b>c){
            
        Participants p = new Participants(a,"",NolT,"Request");
          
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
         System.out.println("wiiw"+rec);
        alert.setContentText("Nombre Maximum des Gamers Atteint !! Vous ne Pouvez plus Participer ");
        alert.showAndWait(); 
        }
        
        
       
       
        
       
        

    }
    
    
   

}
