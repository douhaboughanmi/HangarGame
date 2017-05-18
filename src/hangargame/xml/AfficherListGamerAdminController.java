/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import hangargame.entites.Gamer;
import hangargame.services.CrudSignalisation;
import hangargame.services.ServicesGamer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherListGamerAdminController implements Initializable {

    @FXML
    private TableView<Gamer> TV_Gamer;
    @FXML
    private TableColumn<Gamer, String> CLogin;
    @FXML
    private TableColumn<Gamer, String> CNom;
    @FXML
    private TableColumn<Gamer, String> CPrenom;
    @FXML
    private TableColumn<Gamer, String> CEmail;
    @FXML
    private TableColumn<Gamer, String> CAdresse;
    @FXML
    private TableColumn<Gamer, String> CTel;
    @FXML
    private TableColumn<Gamer, String> CDateInscri;

    private ObservableList<Gamer> data;

    private List<Gamer> listIntermdiaire = new ArrayList<Gamer>();
    @FXML
    private Label LAdresse;
    @FXML
    private Label LEmail;
    @FXML
    private Label LLogin;
    @FXML
    private Label LPrenom;
    @FXML
    private Label LNom;
    @FXML
    private Label LTel;
    @FXML
    private Label LDateInscri;
    @FXML
    private Label LImage;
    @FXML
    private JFXTextField TF_Recherch;

    ServicesGamer s = new ServicesGamer();
    InputStream inputStream;
    @FXML
    private Label LCompte;
    @FXML
    private Label LSignalisation;

    CrudSignalisation crud = new CrudSignalisation();
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
             try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelAdmin.fxml"));
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
        LoadData();

        TF_Recherch.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterGamerList((String) oldValue, (String) newValue);

            }
        });
    }

    @FXML
    void DisplayGamers(MouseEvent event) {

        Gamer gamers = TV_Gamer.getSelectionModel().getSelectedItem();
        //annonces.toString();
        LLogin.setText(gamers.getLogin());
        LNom.setText(gamers.getNom());
        LPrenom.setText(gamers.getPrenom());
        LAdresse.setText(gamers.getAdresse());
        LEmail.setText(gamers.getEmail());
        LDateInscri.setText(gamers.getDateInscription().toString());
        String tel = String.valueOf(gamers.getTel());
        LTel.setText(tel);
        crud.signalisationGamer(gamers.getLogin());
        LSignalisation.setText(crud.signalisationGamer(gamers.getLogin()) + " Signalisations");

        if (gamers.getEtat() == 0) {
            String compte = "Actif ";
            LCompte.setText(compte);
        } else if (gamers.getEtat() == 1) {
            String compte = "Bloquer ";
            LCompte.setText(compte);
        }

        inputStream = gamers.getImage();
        ImageView imageView = new ImageView(new Image(inputStream));
        imageView.setFitHeight(95);
        imageView.setFitWidth(130);

        LImage.setGraphic(imageView);
        
    }

    void LoadData() {

        data = FXCollections.observableArrayList();
        data.clear();
        listIntermdiaire.clear();
        listIntermdiaire = s.AfficherListeGamer();

        data.addAll(listIntermdiaire);

        CLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        CNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        CEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        CAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        CTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        CDateInscri.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));

        TV_Gamer.setItems(null);
        TV_Gamer.setItems(data);

    }

    public void filterGamerList(String oldValue, String newValue) {
        ObservableList<Gamer> filteredList = FXCollections.observableArrayList();
        if (TF_Recherch == null || (newValue.length() < oldValue.length()) || newValue == null) {

            TV_Gamer.setItems(data);

        } else {
            newValue = newValue.toUpperCase();
            for (Gamer t : TV_Gamer.getItems()) {
                String filterLogin = t.getLogin();
                String filterLastName = t.getPrenom();
                String filterFirstName = t.getPrenom();

                if (filterLogin.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue) || filterFirstName.toUpperCase().contains(newValue)) {
                    filteredList.add(t);
                }
            }
            TV_Gamer.setItems(filteredList);
        }
    }

    @FXML
    private void BloquerGamer(ActionEvent event) {

        ServicesGamer se = new ServicesGamer();
        String a = LLogin.getText();
        se.BloquerGamer(a);
        LCompte.setText("Bloquer");

        TV_Gamer.setItems(null);
        LoadData();

    }
@FXML
        private void PDF(ActionEvent event) {
        Document document = new Document();
        try {

            TextInputDialog dialog = new TextInputDialog("HangarGame");
            dialog.setTitle("Entrez le nom de votre fichier");
            dialog.setHeaderText("PDF");
            dialog.setContentText("Entrer le nom "+"\n"+"de Votre fichier PDF");


            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
    
                PdfWriter.getInstance(document, new FileOutputStream(""+result.get()+".pdf"));
            document.open();
            document.add(new Paragraph("Les informations personnelle de " 
                    +LLogin.getText()+ "\n"
                   +"Nom: " + LNom.getText()+ "\n"
                   +"Prenom: " + LPrenom.getText()+ "\n"
                   +"Adresse: " + LAdresse.getText() +"\n"
                   +"E-mail: " + LEmail.getText() +"\n"
                   +"Date d'inscription: " + LDateInscri.getText() +"\n"
                   +"Téléphone: " + LTel.getText()+LImage.getGraphic()
                    
                    
            ));
     
             tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Extraction faite avec succèes");
                tr.setMessage("Télechargement sous Document/netbeans/hangargame ");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
            document.close();
            }else 
            {
                
             tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Erreur");
                tr.setMessage("Aucun nom n'a été saisie  ");
                tr.setNotificationType(NotificationType.ERROR);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
            }

            
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(AfficherListGamerAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void GoAccueil(ActionEvent event) throws IOException {
         AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("AccueilAdmin.fxml"));
        anchor.getChildren().addAll(anchorPane);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        anchor.getChildren().addAll(anchorPane);
    }

  @FXML
    void ExtraireExcel(ActionEvent event) throws FileNotFoundException, IOException {
        List<Gamer> l = new ArrayList();
        l= s.AfficherListeGamer();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("Hangar Game");
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("Login");
        cell = row.createCell(2);
        cell.setCellValue("Nom");
        cell = row.createCell(3);
        cell.setCellValue("Prenom");
        cell = row.createCell(4);
        cell.setCellValue("E-mail");
        cell = row.createCell(5);
        cell.setCellValue("Adresse");
        cell = row.createCell(6);
        cell.setCellValue("Téléphone");
        cell = row.createCell(7);
        cell.setCellValue("Data d'inscription");
        cell = row.createCell(8);
        cell.setCellValue("Etat");
        for(int i=1;i<l.size()-1;i++)
        {                row = spreadsheet.createRow(i);
        cell = row.createCell(1);
        cell.setCellValue(l.get(i).getLogin());
        cell = row.createCell(2);
        cell.setCellValue(l.get(i).getNom());
        cell = row.createCell(3);
        cell.setCellValue(l.get(i).getPrenom());
        cell = row.createCell(4);
        cell.setCellValue(l.get(i).getEmail());
        cell = row.createCell(5);
        cell.setCellValue(l.get(i).getAdresse());
        cell = row.createCell(6);
        cell.setCellValue(l.get(i).getTel());
        cell = row.createCell(7);
        cell.setCellValue(l.get(i).getDateInscription());
        cell = row.createCell(8);
        cell.setCellValue(l.get(i).getEtat());
        
        }
        FileOutputStream out = new FileOutputStream(
                new File("exceldatabase.xlsx"));
        workbook.write(out);
        out.close();
          tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Extraction faite avec succèes");
                tr.setMessage("Télechargement sous Document/netbeans/hangargame ");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(5));
    }


}
