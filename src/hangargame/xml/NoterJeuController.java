/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import hangargame.HangarGame;
import hangargame.entites.Evaluation;
import hangargame.entites.Gamer;
import hangargame.services.CrudEvaluation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import hangargame.xml.AffichageClientJeuController;


/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class NoterJeuController implements Initializable {


    @FXML
    private ImageView etoilevide1;

    @FXML
    private ImageView etoilevide2;

    private ImageView etoilevide11;



    
    private static int note;
    //static Gamer gamer=new Gamer();
    @FXML
    private ImageView etoilevide3;
    @FXML
    private ImageView Er1;
    @FXML
    private ImageView Er2;
    @FXML
    private ImageView Er3;
    @FXML
    private Hyperlink hyperListe;
    @FXML
    private Hyperlink detail;
    
   
    AffichageClientJeuController ac = new AffichageClientJeuController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        



// gamer.setId("52");
//         TODO

        
         Er1.setOnMouseClicked((MouseEvent E1)->{
            
              Er1.setVisible(true);
              Er2.setVisible(false);
                Er3.setVisible(false);
               note=1;
                 System.out.println(note);
            etoilevide1.setAccessibleText("touche pas");
             });
             
             Er1.setOnMouseExited((MouseEvent E2)->{
             if(etoilevide1.getAccessibleText() != "touche pas")
             {
               Er1.setVisible(false); 
             } 
             });
             
             etoilevide1.setOnMouseEntered((MouseEvent )->{
             
                  Er1.setVisible(true);
                  Er2.setVisible(false);
                  Er3.setVisible(false);
            });
             
              Er2.setOnMouseClicked((MouseEvent E11)->{
             Er1.setVisible(true);
             Er2.setVisible(true);
            Er3.setVisible(false);
             etoilevide1.setAccessibleText("touche pas");
             etoilevide2.setAccessibleText("touche pas");
             note=2;
                 System.out.println(note);
             });
             
             Er2.setOnMouseExited((MouseEvent E22)->{
             if(etoilevide2.getAccessibleText() != "touche pas")
             {
              Er1.setVisible(false);
             Er2.setVisible(false);
            Er3.setVisible(false);
             }
             });
             
             etoilevide2.setOnMouseEntered((MouseEvent )->{
             
                  Er1.setVisible(true); 
                  Er2.setVisible(true);
                  Er3.setVisible(false);
            });
             
            Er3.setOnMouseClicked((MouseEvent E1)->{
             Er1.setVisible(true);
             Er2.setVisible(true);
             Er3.setVisible(true);
             etoilevide1.setAccessibleText("touche pas");
             etoilevide2.setAccessibleText("touche pas");
                          etoilevide3.setAccessibleText("touche pas");
                          note=3;
                          System.out.println(note);

             });
             
             Er3.setOnMouseExited((MouseEvent E2)->{
             if(etoilevide3.getAccessibleText() != "touche pas")
             {
               Er1.setVisible(false);
             Er2.setVisible(false);
             Er3.setVisible(false);
               
             }
             });
             
            etoilevide3.setOnMouseEntered((MouseEvent )->{
             
                  Er1.setVisible(true);
                  Er2.setVisible(true);
                   Er3.setVisible(true);
            });
         
    }    
  

    @FXML
    private void Valider(ActionEvent event) throws IOException {
        
        Alert alert=new Alert(Alert.AlertType.ERROR);
        Evaluation a=new Evaluation();
        a.setNom("y");
        a.setEmail_client("zut@zut.tn");
        a.setNom_jeu("Call of dutty");
       // a.setNom_jeu(new AffichageClientJeuController().RecupefrerNom());
        a.setNote(note);
        CrudEvaluation cr = new  CrudEvaluation() ;
                cr.ajouterEvaluation(a);
                
        
       
      //  alert.show();
        
    }

    @FXML
    private void retourAlaliste(ActionEvent event) throws IOException {
        
        HangarGame hang = new HangarGame();
        hang.depalcerVerslisteJeu();
    }

    @FXML
    private void retouretail(ActionEvent event) throws IOException {
        
        HangarGame hang = new HangarGame();
        hang.depalcerVersDetail();
    }
        
       
        
    }
    

    
    
    



    
        
       
            
         
    


