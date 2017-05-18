/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import hangargame.HangarGame;
import hangargame.entites.JeuxVideo;
import hangargame.services.CrudJeuxVideo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class DetailJeuController implements Initializable {

    @FXML
    private Label GenreJeu;
    @FXML
    private Label decriptionJeu;
    @FXML
    private Label Datejeu;
    @FXML
    private Label imageJeu;
    
    @FXML
    private Label titrejeu;
    @FXML
    private JFXButton ShareFB;
    @FXML
    private JFXButton noter;
    @FXML
    private Label labelImage;
     InputStream input;
    @FXML
    private Hyperlink hyperlistejeu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CrudJeuxVideo crud = new CrudJeuxVideo();
        JeuxVideo j = crud.reccupererJeu();
        System.out.println("nom jeu :" + j);
         titrejeu.setText(j.getNom());
        GenreJeu.setText(j.getGenre());
        Datejeu.setText(j.getDate_sortie());
        decriptionJeu.setText(j.getDescription());
        
        try {
            //          ImageView imageView = new ImageView(new Image(input));
//        imageView.setFitHeight(112);
//        imageView.setFitWidth(178);
//
//         labelImage.setGraphic(imageView);
//
        
          ImageView imageView = new ImageView(new Image(new FileInputStream(j.getImage())));
                 imageView.setFitWidth(250);
                 imageView.setFitHeight(150);
                labelImage.setGraphic(imageView);
            System.out.println(j.getImage());
        }
                catch (FileNotFoundException ex) {
            Logger.getLogger(DetailJeuController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
//        ImageView imageView = new ImageView(new Image(j.getImage()));
//        imageView.setFitHeight(200);
//        imageView.setFitWidth(250);
//       labelImage.setGraphic(imageView);

       
//        ImageView imageView = new ImageView(new Image(j.getImage()));
//        imageView.setFitHeight(200);
//        imageView.setFitWidth(250);
//       labelImage.setGraphic(imageView);

       
    }    

    @FXML
    private void Noter(ActionEvent event) throws IOException {
        
        HangarGame hang = new HangarGame();
        hang.depalcerVersNoterJeu();
        
        
    }

    @FXML
    private void PartgerFB(ActionEvent event) {
       CrudJeuxVideo crudd = new CrudJeuxVideo();
        JeuxVideo cc = crudd.reccupererJeu();
        
        String token = "EAACEdEose0cBANXj3N9WqSGGBeK0naP5vSRDGJwXwVcIjCH0f3jsxAZCMd3nAehJQtyheASuk6GlLjxZAY0Xd9BrV26z5THkco6UT8FdByjLRMkF1wm6EhOYmgafwKJ5fMCcWlaybA2XUeNQzuwkmGBNl0HGMigcDcSLTfby7EwAo2z7kadVLz1ZAr9Kb93ZCmTBwZBxpeAZDZD";
        FacebookClient fb = new DefaultFacebookClient(token);
        

        FacebookType response = fb.publish("me/feed", FacebookType.class
                , Parameter.with("message", "Magnifique Jeu" + "\n"
                        + cc.getNom() +  "\n"
                        + cc.getGenre()+  "\n"
                        + cc.getDescription()+  "\n"
                        + cc.getDate_sortie()+  "\n"
                        + "Installer notre application HangarGame"));
    }

    @FXML
    private void retouralaliste(ActionEvent event) throws IOException {
        
        HangarGame hang = new HangarGame();
        hang.depalcerVerslisteJeu();
    }

   

    
    
}
