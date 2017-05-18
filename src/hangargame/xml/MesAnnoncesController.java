/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import hangargame.HangarGame;
import hangargame.entites.Annonces;
import hangargame.services.CrudAnnonces;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author mayss
 */
public class MesAnnoncesController implements Initializable  {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Annonces> tableAnnonce;

    @FXML
    private TableColumn<Annonces, String> colNomAnnonce;

    @FXML
    private TableColumn<Annonces, String> colTypeAnnonce;

    @FXML
    private TableColumn<Annonces, String> colConsoleAnnonce;

    @FXML
    private TableColumn<Annonces, String> colPrixAnnonce;
String choixConsole="";
    @FXML
    private TableColumn<Annonces, String> colDescriptionAnnonce;

    private ObservableList<Annonces> data;

    @FXML
    private JFXTextField nomMesAnnonces;

    @FXML
    private JFXTextField PrixMesAnnonces;

    @FXML
    private TextArea descMesAnnonces;

    @FXML
    private JFXComboBox<Label> typeMesAnnonces;

    @FXML
    private Label imageMesAnnonces;
    String path = "";

    InputStream inputStream;
    int ID;

    HangarGame hangar = new HangarGame();
    Annonces annonces = new Annonces();
    
      @FXML
    private Label lblTitre;

    @FXML
    private Label lblPrix;

    @FXML
    private Label lblDesc;

     int prix =0;
     
      @FXML
    private JFXRadioButton radioPC;

    @FXML
    private JFXRadioButton radioPS4;

    @FXML
    private JFXRadioButton radioPS3;

    @FXML
    private JFXRadioButton radioPsVita;

    @FXML
    private JFXRadioButton radioXbox1;

    @FXML
    private JFXRadioButton radioXbox360;
      private ToggleGroup a = new ToggleGroup();
     
      
    @FXML
    private Label lblChoix;
    
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
                inputStream = new FileInputStream(path);
                ImageView imageView = new ImageView(new Image(inputStream));
                imageView.setFitHeight(122);
                imageView.setFitWidth(178);

                imageMesAnnonces.setGraphic(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("image");
            //  labelImage.setIcon(imageIcon);
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No Data");
        }

    }

    @FXML
    void showOnclick(MouseEvent event) throws FileNotFoundException {
        PrixMesAnnonces.setText("");

        annonces = tableAnnonce.getSelectionModel().getSelectedItem();
       
        ID = annonces.getIdAnnonces();
        System.out.println(ID);
        nomMesAnnonces.setText(annonces.getNomAnnonces());
        PrixMesAnnonces.setText("" + annonces.getPrix());
        descMesAnnonces.setText(annonces.getDescriptionAnnonces());
        typeMesAnnonces.setValue(new Label(annonces.getTypeAnnonces()));
         path = annonces.getPathImage();
        
        InputStream inputStream2 = new FileInputStream(path);
        ImageView imageView = new ImageView(new Image(inputStream2));
        imageView.setFitHeight(107);
        imageView.setFitWidth(148);

        imageMesAnnonces.setGraphic(imageView);
    }

    @FXML
    void modifierMesAnnonces(ActionEvent event) {

        String nomA = nomMesAnnonces.getText();
        String prixA = PrixMesAnnonces.getText();
        String desc = descMesAnnonces.getText();
        String typeA = typeMesAnnonces.getValue().getText();
        
        
        a.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {             
            RadioButton chk1 = (JFXRadioButton)t1.getToggleGroup().getSelectedToggle();
            choixConsole = chk1.getText();
        });
        
       if(nomA.isEmpty()){
        lblTitre.setText("Champ manquant");}
         if(prixA.isEmpty()){
         lblPrix.setText("champ manquant");
        }if(prixA.isEmpty()==false){
            try {
            prix =Integer.parseInt(prixA);
        } catch (Exception e) {
                System.out.println("numeric");
                lblPrix.setText("only numeric number supported");
        }
         }
         if(desc.isEmpty()){
         lblDesc.setText("Champ manquant");}
         
         if(!nomA.isEmpty()){
        lblTitre.setText("");}
         if(!prixA.isEmpty()){
         lblPrix.setText("");
        
         }
         if(!desc.isEmpty()){
         lblDesc.setText("");}  
         if(choixConsole.isEmpty()){
        lblChoix.setText("vous devez choisir");
         
         }
         if(!choixConsole.isEmpty()){
        lblChoix.setText("");
         
         }
         
        if ((prix!=0) && (nomA.isEmpty()==false) && (desc.isEmpty()==false)&& choixConsole.isEmpty()==false) {
            try {
                System.out.println("wwwwiwww");
                inputStream = new FileInputStream(path);
                System.out.println(path);
                Annonces annonces = new Annonces(ID, nomA, typeA, choixConsole
                        , desc, prix, inputStream,0,path);

                CrudAnnonces crud = new CrudAnnonces();
                crud.modifierAnnonces(annonces);

              data = FXCollections.observableArrayList();

                data.addAll(crud.reccupererSimple2());

                colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces"));
                colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces"));
                colConsoleAnnonce.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
                colPrixAnnonce.setCellValueFactory(new PropertyValueFactory<>("prix"));
                colDescriptionAnnonce.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
                tableAnnonce.setItems(null);
                tableAnnonce.setItems(data);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 

    }

    @FXML
    void retourAcceuil(ActionEvent event) throws IOException {
        HangarGame hangarGame = new HangarGame();
        hangarGame.depalcerVersAcceuil();
    }

    @FXML
    void supprimerMesAnnonces(ActionEvent event) {

        Annonces annonces = new Annonces(ID, "", "", "", "", 0, null);
        CrudAnnonces crud = new CrudAnnonces();
        crud.supprimerAnnonces(annonces);

        data = FXCollections.observableArrayList();

        data.addAll(crud.reccupererSimple2());

        colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces"));
        colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces"));
        colConsoleAnnonce.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
        colPrixAnnonce.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDescriptionAnnonce.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
        tableAnnonce.setItems(null);
        tableAnnonce.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         radioPS4.setToggleGroup(a);
        
        radioPsVita.setToggleGroup(a);
        radioXbox1.setToggleGroup(a);
        radioXbox360.setToggleGroup(a);
       radioPC.setToggleGroup(a);
        radioPS3.setToggleGroup(a);
         a.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                
              RadioButton chk = (JFXRadioButton)t1.getToggleGroup().getSelectedToggle();
               choixConsole = chk.getText();
                
            }
        });
        
        data = FXCollections.observableArrayList();
        CrudAnnonces crud = new CrudAnnonces();
        data.addAll(crud.reccupererSimple2());
        typeMesAnnonces.getItems().add(new Label("Echange"));
        typeMesAnnonces.getItems().add(new Label("Vente"));
        colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces"));
        colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces"));
        colConsoleAnnonce.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
        colPrixAnnonce.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDescriptionAnnonce.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
        tableAnnonce.setItems(null);
        tableAnnonce.setItems(data);
    }

}
