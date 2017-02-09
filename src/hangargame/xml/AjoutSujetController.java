package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Gamer;
import hangargame.entites.Sujet;
import hangargame.services.CrudSujet;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AjoutSujetController {

        public void initialize() {
        
        comboCatgr.getItems().add(new Label("Jeux Mobiles"));
        comboCatgr.getItems().add(new Label("Jeux Consoles"));
        comboCatgr.getItems().add(new Label("Jeux PC"));
        comboCatgr.getItems().add(new Label("BlaBLaBlaBla"));
}

        
        
        
        LoginController xx = new LoginController();
        CrudSujet crs = new CrudSujet();
   
         @FXML
    private AnchorPane anacrosujet;
    @FXML
    private ImageView btnaddsjt;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextArea sujetArea;

    @FXML
    private JFXTextField titresujet;

    @FXML
    private JFXComboBox<Label> comboCatgr;



    @FXML
    void Addsbjct(ActionEvent event) {
        String txt = sujetArea.getText();
        String titre = titresujet.getText();
        String catgre = (String) comboCatgr.getValue().getText();
     
        
        Sujet sjt = new Sujet(titre,txt,catgre);
        
      crs.ajoutersujet(sjt);
    }
    
    @FXML
    void homeSujet(MouseEvent event) throws IOException {
          
           
          
                    

    }
    @FXML
    void move(MouseEvent event) throws IOException {
// try {
//            
//             AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutJeuVideo.fxml")); 
//                    anacrosujet.getChildren().addAll(pane);
//        } catch (IOException ex) {
//            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//                    
//    }

}}
