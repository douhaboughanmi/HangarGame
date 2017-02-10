/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hangargame.services.CrudJeuxVideo;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
public class AjoutJeuVideoController implements Initializable {

     CrudJeuxVideo crud = new CrudJeuxVideo();
    @FXML
    private JFXButton btnJeu = new JFXButton();

    @FXML
    private JFXTextField titreJeu = new JFXTextField() ;

    @FXML
    private JFXTextField genreJeu = new JFXTextField();

    @FXML
    private TextArea descriptionJeu = new TextArea();

    @FXML
    private Label labelDesc = new Label();
    @FXML
    private Label labeldate = new Label();

     @FXML
    private Label labeTitre = new Label();

    @FXML
    private Label labelgenre = new Label();
    @FXML
    private Label labelimage = new Label();




    @FXML
    private JFXDatePicker  dateJeu = new JFXDatePicker();

    @FXML
    private JFXButton btnImage = new JFXButton();
     
    private String path = "";
            

    @FXML
    void ajouter(ActionEvent event) {
        
        String titrej = titreJeu.getText();
        String genrej = genreJeu.getText();
     //  String datej= dateJeu.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        String datej="date";
        String descj = descriptionJeu.getText();
        
      

  

                    
            
        

        if (titrej.isEmpty()) {
            labeTitre.setText("Champ obligatoire");

        }
       

        else if (genrej.isEmpty()) {
            labelgenre.setText("Champ obligatoire");

        }

    
        
        else if (descj.isEmpty()) {
            labelDesc.setText("Champ obligatoire");

        }
        else 
        { crud.ajouterJeuxVideo(titrej, genrej, datej, descj, path, "", "");
        System.out.println("oui");
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
    }    
    
}
