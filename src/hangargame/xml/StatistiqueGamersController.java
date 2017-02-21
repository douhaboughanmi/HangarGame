/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.xml;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import hangargame.entites.StatGamers;
import hangargame.services.ServicesStatGamers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StatistiqueGamersController implements Initializable {

        @FXML
    private AnchorPane anchor;
    
        @FXML
    private BarChart<?, ?> statestique;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelAdmin.fxml"));
            drawer.setSidePane(box);

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
        ServicesStatGamers s= new ServicesStatGamers();
        
       
         ArrayList<StatGamers> a= new ArrayList();
         a = s.chart();
         
         XYChart.Series series=new XYChart.Series();
         series.setName("Nombre de Gamer");
         for ( int i =0; i< a.stream().count(); i++)
         {
              series.getData().add(new XYChart.Data(String.valueOf(a.get(i).getMois()),a.get(i).getNbrGamers()));
         }
        
         
         
         statestique.getData().add(series);
    }    

    @FXML
    private void GoAccueil(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("AccueilAdmin.fxml"));
        anchor.getChildren().addAll(anchorPane);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        anchor.getChildren().addAll(anchorPane);
    }
    
}
