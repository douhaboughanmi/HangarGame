/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import hangargame.HangarGame;
import hangargame.entites.Sujet;
import hangargame.services.CrudSujet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class AffichageSujetsController implements Initializable {

    HangarGame h = new HangarGame();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadData();
        notif();

    }
    CrudSujet crs = new CrudSujet();
    MouseEvent evnt;

    CrudSujet cs = new CrudSujet();
    ArrayList<String> LS = new ArrayList<>();

    String nom;
    Font arial;
    @FXML
    private AnchorPane recherche;

    @FXML
    private Label titre;

    @FXML
    private JFXTextArea contenue;

    @FXML
    private Label auteur;

    @FXML
    private Label date;

    @FXML
    private Label categorie;

    @FXML
    private JFXButton search;

    @FXML
    private ImageView img;
    @FXML
    private JFXTextField champsRech;

    @FXML
    private JFXListView<Label> listSujet;

    @FXML
    private Label note;

    @FXML
    private Label nbrComm;
    static Sujet s;
    static String y;
    
     @FXML
    Sujet affichagesujet(MouseEvent event) {
        afficherSujet();
        String a = titre.getText();
        String b = contenue.getText();
         s = new Sujet(a, b);
        return s;
    }
    MouseEvent evt;
  
    

    @FXML
    void facebookShare(ActionEvent event) {

        String token = "EAACEdEose0cBALnIvBcBV32LZBvIt52GcF5uwJwI7pAIECg6YgZBVXMBqNjmqsg6aGWr3577oZAUjOlYHQXwBEBH9ZAALXsh3hnQe4G1uOuBwVYAEh1BQnDnQwAAlXgl4tyZCuExoqnwr1ZCAzbQwH9qMUbRzKWoDp2XIHXNedjGkHwclMBrXqYdsEHcLjqf8ZD";
        FacebookClient fb = new DefaultFacebookClient(token);

        FacebookType response = fb.publish("me/feed", FacebookType.class, Parameter.with("message", "Titre de Sujet  "+affichagesujet(evt).getNomSujet()+"\n"
       +"Contenue de sujet"+affichagesujet(evt).gettextSujet() ));
        System.out.println(affichagesujet(evt));
        notiffacebook(affichagesujet(evt).getNomSujet());
    }

    @FXML
    void clear(MouseEvent event) {
        listSujet.getItems().clear();
    }

    @FXML
    void moveToSujet(ActionEvent event) throws IOException {
        HangarGame ha = new HangarGame();
        HangarGame.depalcerVersAjouterUnSujet();
    }

    @FXML
    void depvrsConsulterSujet(ActionEvent event) throws IOException {
        h.depalcerVersConsulterSujet();
    }

    @FXML
    void rechercher(ActionEvent event) {

        ArrayList<Sujet> LR = cs.rechercherSujet(nom);
        nom = champsRech.getText();
        for (int i = 0; i < LR.size(); i++) {

            Label lb = new Label(LR.get(i).getNomSujet() + "\n" + LR.get(i).getdateSjt() + "\n" + LR.get(i).getNote());
            LS.add(LR.get(i).getNomSujet());
            listSujet.getItems().add(lb);
            listSujet.setExpanded(true);
            listSujet.depthProperty().set(4);

        }
    }

    void LoadData() {

        LS.clear();

        ArrayList<Sujet> L = cs.AffichageSuhetSujetCategorie();

        for (int i = 0; i < L.size(); i++) {

            Label lb = new Label("Titre de Sujet :" + L.get(i).getNomSujet() + "\n" + "Date de Publication Sujet   :"
                    + "  " + L.get(i).getdateSjt() + "\n" + "Note Obtenue   :" + L.get(i).getNote());
            LS.add(L.get(i).getNomSujet());
            listSujet.getItems().add(lb);
            listSujet.setExpanded(true);
            listSujet.depthProperty().set(4);
            lb.setMinHeight(50);
            lb.setMinWidth(150);

        }

    }

    @FXML
    String recuperernomsujet(MouseEvent event) {

        int x = listSujet.
                getSelectionModel().
                getSelectedIndex();
         y = LS.get(x);

        System.out.println("lenum de sujet"+y);

        return y;

    }



    public void afficherSujet() {
        String noms = recuperernomsujet(evnt);
        Sujet s = cs.consulterSujet(noms);
        titre.setText(s.getNomSujet());
        contenue.setDisable(true);
        contenue.setText(s.gettextSujet());
        contenue.setWrapText(true);
        date.setText(s.getdateSjt() + "");
        auteur.setText(s.getGamer());
        nbrComm.setText(s.getetat() + "");
        note.setText(s.getNote() + "");
        categorie.setText(s.getCategorie());

    }

    public void notif() {
        int x = cs.totalSujet();
        TrayNotification tr = new TrayNotification();
        tr.setTitle("Nobre des sujets récupérer ");
        tr.setMessage(x + "");
        tr.setNotificationType(NotificationType.INFORMATION);
        tr.setAnimationType(AnimationType.POPUP);
        tr.showAndDismiss(Duration.seconds(3));
    }
    
      public void notiffacebook(String x ) {
        
        TrayNotification tr = new TrayNotification();
        tr.setTitle("Facebook");
        tr.setMessage("Le sujet "+ x+ " a été bien partagé");
        tr.setNotificationType(NotificationType.INFORMATION);
        tr.setAnimationType(AnimationType.POPUP);
        tr.showAndDismiss(Duration.seconds(3));
    }
}
