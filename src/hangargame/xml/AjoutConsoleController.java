/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Console;
import hangargame.services.CrudConsole;
import java.io.File;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
public class AjoutConsoleController implements Initializable {
    
    CrudConsole crud = new CrudConsole();
   @FXML
    private JFXButton btnConsole;

    @FXML
    private JFXTextField titreConsole = new JFXTextField();

    @FXML
    private TextArea descriptionConsole =  new JFXTextArea();

    @FXML
    private Label labeldesc = new Label();

    @FXML
    private Label labelTitre = new Label();

    @FXML
    private JFXDatePicker dateConsole  = new JFXDatePicker();

    @FXML
    private JFXButton btnImage = new JFXButton();

    @FXML
    private Label labelimage = new Label();
  
    private String path = "";

    @FXML
    void ajout(ActionEvent event) {
        
          
        String titrec = titreConsole.getText();
        String descc = descriptionConsole.getText();
        String datej= dateConsole.
                getValue()
                .format
        (DateTimeFormatter.
                ofPattern("YYYY-MM-DD"));
        Console c = new Console(titrec, path, descc,datej);
        crud.ajouterConsole(c);
        System.out.println("oui");
         
         
        
        
        
        

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
