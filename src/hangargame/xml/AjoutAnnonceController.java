/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import hangargame.services.CrudAnnonces;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mayss
 */
public class AjoutAnnonceController implements Initializable {

    @FXML
    private JFXButton btnAnnonce = new JFXButton();

    @FXML
    private JFXTextField titreAnnonce = new JFXTextField();

    @FXML
    private JFXTextField prixAnnonce = new JFXTextField();

    @FXML
    private TextArea descriptionAnnonce = new JFXTextArea();

    @FXML
    private JFXComboBox<Label> typeAnnonce = new JFXComboBox<>();

    @FXML
    private ToggleGroup a = new ToggleGroup();
    
    private String path = "";
    
     @FXML
    private Label labelAnnonce;

    @FXML
    private Label labelPrix;

    @FXML
    private Label labelDesc;

    @FXML
    private Label labelImage;
    

    @FXML
    void login(ActionEvent event) {
       String typeA = typeAnnonce.getValue().getText();
        String nomA = titreAnnonce.getText();
        String desc = descriptionAnnonce.getText();
        String ab = prixAnnonce.getText();
        
        int prix = 0;
        if ((ab.isEmpty()) == false) {
            prix = Integer.parseInt(ab);
        }

        if (nomA.isEmpty()==false
                && desc.isEmpty()==false
                && ab.isEmpty()==false
                && path.isEmpty()==false)
                 {

            System.out.println("");

            CrudAnnonces crud = new CrudAnnonces();
            crud.ajouterAnnonces(nomA, typeA, "", desc, prix, path);
        }

        if (nomA.isEmpty()) {
            labelAnnonce.setText("Champ obligatoire");

        }
       

        if (ab.isEmpty()) {
            labelPrix.setText("Champ obligatoire");

        }

        if (path.isEmpty()) {
            labelImage.setText("Champ obligatoire");

        }
        
        if (desc.isEmpty()) {
            labelDesc.setText("Champ obligatoire");

        }
        
   if (nomA.isEmpty()==false) {
            labelAnnonce.setText("");

        }
       

        if (ab.isEmpty()==false) {
            labelPrix.setText("");

        }

       
        
        if (desc.isEmpty()==false) {
            labelDesc.setText("");

        }
        
        if (path.isEmpty()==false) {
            labelImage.setText("");

        }
        

       
    }
    
    
        @FXML
    void selectImage(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
          
          
            System.out.println("image");
          //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeAnnonce.getItems().add(new Label("Echange"));
        typeAnnonce.getItems().add(new Label("Vente"));

         typeAnnonce.setValue(new Label("Echange"));
        
       

    }

}
