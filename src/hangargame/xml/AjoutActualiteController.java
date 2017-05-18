/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Actualite;
import hangargame.services.CrudActualite;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author yesmine
 */
public class AjoutActualiteController implements Initializable {
    String path ="";
    
    CrudActualite cruda = new CrudActualite();

     @FXML
    private JFXButton btnAct = new JFXButton();

    @FXML
    private JFXTextField titreAct  = new JFXTextField();

    @FXML
    private TextArea descriptionAct = new TextArea();

    @FXML
    private Label labeldesc = new Label();

   // @FXML
  //  private Label labeTitre = new Label();

    @FXML
    private JFXButton btnImage = new JFXButton();

    @FXML
    private Label labelimage = new Label();

    
         @FXML
    void ajout(ActionEvent event) {
             System.out.println("hhhhhh");
             
             String titreA = titreAct.getText();
             String descString = descriptionAct.getText();
             
             Actualite a = new Actualite(titreA, descString, path);
             cruda.ajouterActualite(a);
             System.out.println("kfkfkfkfkfkf");
             
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



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
