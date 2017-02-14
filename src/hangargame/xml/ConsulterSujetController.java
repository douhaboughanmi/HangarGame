/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import hangargame.entites.CommentaireSujet;
import hangargame.entites.Sujet;
import hangargame.services.CrudCommentaireForum;
import hangargame.services.CrudSujet;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

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
    public String s = "b";
    @FXML
    private AnchorPane consulterSujet;

    @FXML
    private JFXListView<Label> commentaires;

    @FXML
    private Label sujet;

    @FXML
    private Text titreSujet;

    @FXML
    private Text datesujet;

    @FXML
    private Text note;

    @FXML
    private JFXButton ajoutCmntBtn;

    @FXML
    private JFXTextArea textAreaAjout;
    @FXML
    private JFXButton afficherCmnt;
      @FXML
    private JFXButton signalerSujet;

    @FXML
    void afficher(ActionEvent event) {
        textAreaAjout.setVisible(true);
        ajoutCmntBtn.setVisible(true);
        
    }

    public void affficherCommentaires(String s) {

        CrudCommentaireForum crc = new CrudCommentaireForum();
        ArrayList<CommentaireSujet> L = crc.afficherCommentaire(s);

        for (int i = 0; i < L.size(); i++) {

            Label lb = new Label(L.get(i).gettextCommnt() + "   " + L.get(i).getdatecoment());

            commentaires.getItems().add(lb);
            commentaires.setExpanded(true);
            commentaires.depthProperty().set(1);
        }
    }

    public void afficherSujet(String s) {
        CrudSujet cs = new CrudSujet();
        Sujet st = cs.consulterSujet(s);
        titreSujet.setText(st.getNomSujet());
        datesujet.setText("" + st.getdateSjt());
        note.setText(st.getetat() + "");
        sujet.setText(st.gettextSujet());

    }

    @FXML
    void ajoutCmnt(ActionEvent event) {
JInternalFrame frame = null;
        String cmnt = textAreaAjout.getText();

        CommentaireSujet c = new CommentaireSujet(cmnt, s);

        cn.ajouterComentaire(c);
        JOptionPane.showMessageDialog(frame,
    "commentaire ajouter dans le sujet : "+s+"");

    }

    @FXML
    void signal(ActionEvent event) {

        cn.updateEtat(s);
        System.out.println("hjdghjdghjgdhgj");
        signalerSujet.setVisible(false);
        

    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        affficherCommentaires(s);
        afficherSujet(s);
        textAreaAjout.setVisible(false);
        ajoutCmntBtn.setVisible(false);
 
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

}
