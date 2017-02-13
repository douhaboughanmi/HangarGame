/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import hangargame.entites.Evenement;
import hangargame.services.EvenementCrud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class HangarGame extends Application {
    
     public static Boolean isSplashLoaded = false;
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("xml/Login.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Hangar Game");
        stage.show();
       
       
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
     // Evenement e = new Evenement("Louay","lol","Evenement de la semaine","2017-01-02","2018-12-23") ;
       EvenementCrud EC = new EvenementCrud();
       // EC.ajouterEvenement(e);
        //EC.supprimerEvenement(17);
        EC.afficherEvenement();
       
    }
    
}
