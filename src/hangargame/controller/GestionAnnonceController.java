/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Annonces;
import hangargame.services.CrudAnnonces;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mayss
 */
public class GestionAnnonceController implements Initializable {

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
    private TableColumn<Annonces, String> colPrix;

    @FXML
    private TableColumn<Annonces, String> colConsole;

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

    @FXML
    private Label lblTitre;

    @FXML
    private Label lblPrix;

    @FXML
    private Label lblDesc;
    int ID;
String path;

 Annonces annonces = new Annonces();
    
        @FXML
    void show(MouseEvent event) throws FileNotFoundException {

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
    void supprimerMesAnnonces(ActionEvent event) {
        Annonces annonces = new Annonces(ID, "", "", "", "", 0, null);
        CrudAnnonces crud = new CrudAnnonces();
        crud.supprimerAnnonces(annonces);

        data = FXCollections.observableArrayList();

        data.addAll(crud.reccupererSimple2());

        colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces"));
        colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces"));
        colConsole.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
        tableAnnonce.setItems(null);
        tableAnnonce.setItems(data);
    }

    @FXML
    private TableColumn<Annonces, String> colDescription;
    private ObservableList<Annonces> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        data = FXCollections.observableArrayList();
        CrudAnnonces crud = new CrudAnnonces();
        data.addAll(crud.reccupererSimple2());

        colNomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonces"));
        colTypeAnnonce.setCellValueFactory(new PropertyValueFactory<>("typeAnnonces"));
        colConsole.setCellValueFactory(new PropertyValueFactory<>("consoleAnnonces"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionAnnonces"));
        tableAnnonce.setItems(null);
        tableAnnonce.setItems(data);

    }

}
