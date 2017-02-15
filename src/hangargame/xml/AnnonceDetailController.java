/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import hangargame.entites.Annonces;
import hangargame.services.CrudAnnonces;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mayss
 */
public class AnnonceDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label titreAnnonce;

    @FXML
    private Label prixAnnonce;

    @FXML
    private Label imageAnnonce;

    @FXML
    private Label typreAnnonce;

    @FXML
    private Label ConsoleAnnoce;

    @FXML
    private Label gamerAnnonce;

    @FXML
    private Label decriptionAnnonce;

    @FXML
    void favoris(ActionEvent event) {

        CrudAnnonces crud = new CrudAnnonces();
        crud.ajouterAnnoncesFavoris();
        System.out.println("jawi béhi");

    }
    
    
     @FXML
    void signalisation(ActionEvent event) {
        CrudAnnonces crud = new  CrudAnnonces();
        crud.signalerAnnonce();
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudAnnonces crud = new CrudAnnonces();
        Annonces a = crud.reccupererAnnonce();
        System.out.println("wwwwwwwwww" + a);
        titreAnnonce.setText(a.getNomAnnonces());
        prixAnnonce.setText(a.getPrix() + "DT");
        typreAnnonce.setText(a.getTypeAnnonces());
        ConsoleAnnoce.setText(a.getConsoleAnnonces());
        gamerAnnonce.setText(a.getGamer());
        System.out.println("ddddd" + a.getGamer());
        ImageView imageView = new ImageView(new Image(a.getInputStream()));
        imageView.setFitHeight(200);
        imageView.setFitWidth(250);
        imageAnnonce.setGraphic(imageView);

        decriptionAnnonce.setText(a.getDescriptionAnnonces());

    }

}
