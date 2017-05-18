/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import hangargame.HangarGame;
import hangargame.entites.Evenement;
import hangargame.services.EvenementCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;

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
                drawer.setVisible(false);
                drawer.close();
            } else {
                drawer.setVisible(true);
                drawer.open();
                
            }
        });
       

    }

    @FXML
    private void showAdresse(MouseEvent event) {
        path = "";
        int a = listView.getSelectionModel().getSelectedIndex();
        path = liste.get(a).toString();
        engine = mapView.getEngine();
        engine.load("https://www.google.fr/maps/search/"+path);
    }

   
}
