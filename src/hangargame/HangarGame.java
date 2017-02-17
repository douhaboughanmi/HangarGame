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

import hangargame.services.EvenementCrud;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class HangarGame extends Application {
    
     public static Boolean isSplashLoaded = false;
             public static AnchorPane anchorPane ;
             public Stage primaryStage ;

    // stage.show();
    public HangarGame() {
    }
             

    @Override
    public void start(Stage primaryStage) throws Exception {
  this.primaryStage = primaryStage ;
        this.primaryStage.setTitle("Hangar Game");
        showMainView();
      //  Parent root = FXMLLoader.load(getClass().getResource("xml/MesAnnonces.fxml"));

        
       // Scene scene = new Scene(root);
        
        //stage.setScene(scene);
       // stage.setTitle("Hangar Game");
       // stage.show();
       
       
    }
    private void showMainView() throws IOException {
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/Login.fxml"));
       anchorPane = loader.load();
       Scene scene = new Scene(anchorPane);
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
    
    public static void depalcerVersAjoutAnnonce() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/AjoutAnnonce.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
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
