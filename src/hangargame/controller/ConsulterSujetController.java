/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import hangargame.entites.CommentaireSujet;
import hangargame.entites.Sujet;
import hangargame.services.CrudCommentaireForum;
import hangargame.services.CrudEvaluationSujet;
import hangargame.services.CrudSujet;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class ConsulterSujetController implements Initializable {

    /**
     * Initializes the controller class.
     */
    CrudCommentaireForum cn = new CrudCommentaireForum();
    CrudEvaluationSujet ces = new CrudEvaluationSujet();
    CrudSujet cs = new CrudSujet();
    public String s = "c";
    public String g = "hamza";

    @FXML
    private AnchorPane consulterSujet;

    @FXML
    private Label sujet;

    @FXML
    private Text titreSujet;

    @FXML
    private Text datesujet;

    @FXML
    private Text note;

    @FXML
    private JFXListView<Label> champscomm;

    @FXML
    private JFXButton afficherlescomm;

    @FXML
    private TextArea ajoutcomm;

    @FXML
    private JFXButton ajoutcomnt;
    @FXML
    private Pane paneajout;

    @FXML
    private JFXTextArea sujetContenue;
    @FXML
    private Text titredeSujet;

    @FXML
    private Text Datepublication;
    @FXML
    private JFXButton jaime;

    @FXML
    private JFXButton jaimePas;
    @FXML
    private JFXButton signalersjt;
     @FXML
    private JFXButton favoris;

    @FXML
    void addFavorite(ActionEvent event) {
cs.ajouterFavoris(s);
    }
   @FXML
    void executeSignal(ActionEvent event) {
        int y = cs.verifGamerSujet(s, g);
        int x = cs.verifSujetSignl(s);
        System.out.println(x);
        if (y==0){
        if (x == 0){
            cs.signalerSujet(s);
        }
       else if ( x>= 5){
            cs.supprimerSujet(s);
            System.out.println("ghgjkhhl");
        }
        else {
          cs.updateSignale(s);
       }
        }else {
            impossibleSignaler();
            signalersjt.setVisible(false);
        }
            

    }

    @FXML
    void jaimePasfn(ActionEvent event) {
        JInternalFrame frame = null;
        int note = -1;
        int verif = ces.verifierGamer(g);
        if (verif == 0) {
            ces.ajouterEvaluation(g, s, note);
        } else {
            notifErrAddSbjct();
        }

    }

    @FXML
    void jaimefn(ActionEvent event) {
       JInternalFrame frame = null;
        int note = 1;
        int verif = ces.verifierGamer(g);
        if (verif == 0) {
            ces.ajouterEvaluation(g,s , note);
            notifOkAddSbjct();
        } else {
            notifErrAddSbjct();
        }
    }

    @FXML
    void afficherAjout(ActionEvent event) {
        ajoutcomm.setVisible(true);
        ajoutcomnt.setVisible(true);
        paneajout.setVisible(true);
    }

    @FXML
    void ajoutcomntfn(ActionEvent event) {
        JInternalFrame frame = null;
        String cmnt = ajoutcomm.getText();

        CommentaireSujet c = new CommentaireSujet(cmnt, s);

        cn.ajouterComentaire(c);
//        JOptionPane.showMessageDialog(frame,
//                "commentaire ajouter dans le sujet : " + s + "");
        affficherCommentaires(s);
    }

    @FXML
    private JFXButton signalerSujet;

    public void affficherCommentaires(String s) {

        CrudCommentaireForum crc = new CrudCommentaireForum();
        ArrayList<CommentaireSujet> L = crc.afficherCommentaire(s);

        for (int i = 0; i < L.size(); i++) {

            Label lb = new Label(L.get(i).gettextCommnt() + "   " + L.get(i).getdatecoment());

            champscomm.getItems().add(lb);
            champscomm.setExpanded(true);

        }
    }

    public void afficherSujet(String s) {
        CrudSujet cs = new CrudSujet();
        Sujet st = cs.consulterSujet(s);
        System.out.println(st.gettextSujet());
        sujetContenue.setText(st.gettextSujet());
        titredeSujet.setText(st.getNomSujet());
        Datepublication.setText(st.getdateSjt() + "");

    }

    @FXML
    void signal(ActionEvent event) {

        cn.updateEtat(s);
        System.out.println("hjdghjdghjgdhgj");
        signalerSujet.setVisible(false);

    }
    @FXML
    private JFXComboBox<Label> comboCat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affficherCommentaires(s);
        afficherSujet(s);
        ajoutcomm.setVisible(false);
        ajoutcomnt.setVisible(false);
        paneajout.setVisible(false);
//////////////////////////////////

    }

    @FXML
    void noterSjt(ActionEvent event) {

    }

    @FXML
    void signaler(MouseEvent event) {
        JInternalFrame frame = null;
        int n = JOptionPane.showConfirmDialog(
                frame,
                "voudriez vous bien signaler ce commentaire",
                "",
                JOptionPane.YES_NO_OPTION);
        System.out.println("hhhhhhhhhhhhh");
    }
      public void notifErrAddSbjct(){
        
        TrayNotification tr = new TrayNotification();
                tr.setTitle("Erreure");
                tr.setMessage("vous avez deja évaluer ce sujet");
                tr.setNotificationType(NotificationType.ERROR);
                tr.setAnimationType(AnimationType.POPUP);
                tr.showAndDismiss(Duration.seconds(3));
    }
        public void notifOkAddSbjct(){
        
        TrayNotification tr = new TrayNotification();
                tr.setTitle("Succes");
                tr.setMessage("vous avez deja évaluer ce sujet");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.POPUP);
                tr.showAndDismiss(Duration.seconds(3));
    }
          public void impossibleSignaler(){
        
        TrayNotification tr = new TrayNotification();
                tr.setTitle("Erreure ");
                tr.setMessage("Vous avez deja signalé ce sujet");
                tr.setNotificationType(NotificationType.ERROR);
                tr.setAnimationType(AnimationType.POPUP);
                tr.showAndDismiss(Duration.seconds(3));
    }

}
