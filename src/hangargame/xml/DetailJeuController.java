/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
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
        System.out.println("wwwwwwwwww" + j);
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
    }

    @FXML
    private void retouralaliste(ActionEvent event) throws IOException {
        
        HangarGame hang = new HangarGame();
        hang.depalcerVerslisteJeu();
    }

   

    
    
}
