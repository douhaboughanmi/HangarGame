package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Sujet;
import hangargame.services.CrudSujet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class AjoutSujetController {

    CrudSujet crs = new CrudSujet();
   
    @FXML
    private ImageView btnaddsjt;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextArea sujetArea;

    @FXML
    private JFXTextField titresujet;

    @FXML
    private JFXComboBox<?> comboCatgr;



    @FXML
    void Addsbjct(ActionEvent event) {
        String txt = sujetArea.getText();
        String titre = titresujet.getText();
        String catgre = (String) comboCatgr.getValue();
        Sujet sjt = new Sujet(txt,titre,catgre);
        
        crs.ajoutersujet(sjt);
    }

}
