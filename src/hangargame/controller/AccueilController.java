/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AccueilController implements Initializable {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
      @FXML
    private AnchorPane anchor;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
      
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
    
}
