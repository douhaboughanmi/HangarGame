/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import hangargame.HangarGame;
import hangargame.entites.VideoTest;
import hangargame.services.VideoTestCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutVideoTestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton btnAjoutVideo;

    @FXML
    private JFXTextField nomVideoTest;

    @FXML
    private JFXTextField genreVideoTest;

    @FXML
    private Label urlVideoTest;

    @FXML
    private JFXButton btn_urlVideoTest;

    @FXML
    private JFXTextArea descVideoTest;

    @FXML
    private JFXCheckBox cv1;
    @FXML
    private JFXCheckBox cv2;

    @FXML
    private JFXCheckBox cv3;

    @FXML
    private JFXCheckBox cv4;

    @FXML
    private JFXCheckBox cv5;

    @FXML
    private JFXCheckBox cv6;
    @FXML
    private Label labelnom;

    @FXML
    private Label labelgenre;
    @FXML
    private Label labeldesc;
    @FXML
    private Hyperlink listvid;
  

    VideoTestCrud crud = new VideoTestCrud();
    String loginStat=LoginController.LoginStatic;
    @FXML
    void ajoutVideo(ActionEvent event) {
        
          loginStat="jjjj";
      

        String nomVideo = nomVideoTest.getText();
        String genrevideo = genreVideoTest.getText();
        String descVideo = descVideoTest.getText();
        String combocv1=" ";
        String combocv2=" ";
        String combocv3=" ";
        String combocv4=" ";
        String combocv5=" ";
        String combocv6=" ";

        if (cv1.isSelected()) {
             combocv1 = cv1.getText();
        }
        if (cv2.isSelected()) {
             combocv2 = cv2.getText();
        }
        if (cv3.isSelected()) {
             combocv3 = cv3.getText();
        }
        if (cv4.isSelected()) {
             combocv4 = cv4.getText();
        }
        if (cv5.isSelected()) {
             combocv5 = cv5.getText();
        }
        if (cv6.isSelected()) {
             combocv6 = cv6.getText();
        }

        String combo = combocv1 + "  " + combocv2 + "  " + combocv3 + "  " + combocv4 + "  "
                + combocv5 + "   " + combocv6;
        if (nomVideo.isEmpty() == false
                && genrevideo.isEmpty() == false
                && descVideo.isEmpty() == false
                && path.isEmpty() == false) {
            VideoTest v = new VideoTest(nomVideo, path, descVideo, null, genrevideo, combo,loginStat);
            crud.ajouter(v);

        }
        if (nomVideo.isEmpty()) {
            labelnom.setText("Champ obligatoire");

        }

        if (genrevideo.isEmpty()) {
            labelgenre.setText("Champ obligatoire");

        }

        if (path.isEmpty()) {
            urlVideoTest.setText("Champ obligatoire");

        }

        if (descVideo.isEmpty()) {
            labeldesc.setText("Champ obligatoire");

        }

        if (nomVideo.isEmpty() == false) {
            labelnom.setText("");

        }

        if (genrevideo.isEmpty() == false) {
            labelgenre.setText("");

        }

        if (descVideo.isEmpty() == false) {
            labeldesc.setText("");

        }

        if (path.isEmpty() == false) {
            urlVideoTest.setText("");

        }
        
        nomVideoTest.setText("");
        genreVideoTest.setText("");
        descVideoTest.setText("");
        

    }

    String path = "";

    @FXML
    void selectVideo(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("mp4", "mp4");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();

            System.out.println("video");
            //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }
        System.out.println(path);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContenent.fxml"));
            drawer.setSidePane(box);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }
    
      @FXML
    void deplacerverslistevid(ActionEvent event) throws IOException {
        HangarGame h = new HangarGame();
        h.depalcerVideotest();

    }

    
     @FXML
    void back(ActionEvent event) throws IOException {
        HangarGame h = new HangarGame();
        h.depalcerVersAccueil();
         

    }

}
