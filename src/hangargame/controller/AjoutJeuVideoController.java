/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.entites.Console;
import hangargame.services.CrudConsole;
import hangargame.services.CrudJeuxVideo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Label image;

    @FXML
    private JFXButton btnJeu = new JFXButton();

    @FXML
    private JFXTextField titreJeu = new JFXTextField();

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
    InputStream input;
    @FXML
    private ListView<String> listNomConsole;

    @FXML
    private JFXDatePicker dateJeu = new JFXDatePicker();

    @FXML
    private JFXButton btnImage = new JFXButton();
   String nc1;
    String nc2;
    String nc3;
    
    private String path = "";

    ObservableList<String> Console = FXCollections.observableArrayList();
    @FXML
    private Hyperlink linkaffich;
    
    
    

    @FXML
    void ajouter(ActionEvent event) {
        String titrej = titreJeu.getText();
        String genrej = genreJeu.getText();
        String datej = dateJeu.
                getValue()
                .format(DateTimeFormatter.
                        ofPattern("YYYY-MM-dd"));
        // String datej="date";
        String descj = descriptionJeu.getText();
        
        
        

        if (titrej.isEmpty()) {
            labeTitre.setText("Champ obligatoire");

        } else if (genrej.isEmpty()) {
            labelgenre.setText("Champ obligatoire");

        } else if (descj.isEmpty()) {
            labelDesc.setText("Champ obligatoire");

        } else {
           // crud.ajouterJeuxVideo(titrej, genrej, datej, descj, path, "");
crud.ajouterJeuxVideo(titrej, genrej, datej, descj, path, nc1);
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
            try {
                input = new FileInputStream(path);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutJeuVideoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("image");
            //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }

        ImageView imageView = new ImageView(new Image(input));
        imageView.setFitHeight(112);
        imageView.setFitWidth(178);

        image.setGraphic(imageView);

    }
    
    
    public void load(){
         CrudConsole crud = new CrudConsole();
        List<Console> list = crud.reccuperer2();
        listNomConsole.setItems(Console);
        listNomConsole.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listNomConsole.setFixedCellSize(50);
        listNomConsole.setPrefSize(200, 160);

        

        for (int i = 0; i < list.size(); i++) {

            String lbl = list.get(i).getNom();
            listNomConsole.getItems().add(lbl);
            //lbl.setPrefSize(300, 100);
            // TODO
        }
//        listNomConsole.setOnMouseClicked(event -> {
//            System.out.println(listNomConsole.getSelectionModel().getSelectedItems());
//
//        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       load();
       listNomConsole.setOnMouseClicked(event2 -> {
            nc1 = listNomConsole.getSelectionModel().getSelectedItem();
            System.err.println("test"+nc1);
        });

    }

    @FXML
    private void navigueraffiche(ActionEvent event) throws IOException {
        
        HangarGame hang = new HangarGame();
        hang.depalcerVersAffichageJeu();
    }
}
