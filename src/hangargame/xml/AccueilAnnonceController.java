/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.xml;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import hangargame.HangarGame;
import hangargame.entites.Annonces;
import hangargame.services.CrudAnnonces;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author mayss
 */
public class AccueilAnnonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static String indexAnnonce;
    public static int indexAnnonce2;
    List<String> liste = new ArrayList<>();
    ObservableList<Label> lab;
    @FXML
    private JFXListView<Label> listAnnonces;

    String path = "";
    // @FXML
    // private JFXButton btn;
    List<String> listNomA;
    List<String> listTypeA;
    List<Integer> listPrix;
    HangarGame hangar = new HangarGame();

     @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

     @FXML
    private AnchorPane anchor;
    
    @FXML
    void reccupererAnnonceEchange(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        // listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonEchange();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");
            // System.out.println(list.get(i).getIdAnnonces());
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
           imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);

           
        }

    }

    @FXML
    void Annoncedetail(MouseEvent event) throws IOException {
        int a = listAnnonces.getSelectionModel().getSelectedIndex();

        indexAnnonce2 = Integer.parseInt(liste.get(a));
        System.out.println("indeeeeeeeex"+indexAnnonce2);
        hangar.depalcerVersAnnonceDetail();

        System.out.println("" + a);
    }

    @FXML
    void reccupererAnnonceVente(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonVente();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
           imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnoncePC(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPC();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
           imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnoncePS4(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPS4();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnoncePS3(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPS3();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnonceXbox360(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonXbox360();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n"
                    + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
           imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnonceXboxOne(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonXboxOne();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");
            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
           imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    @FXML
    void reccupererAnnoncePSVita(ActionEvent event) {
        liste.clear();
        System.out.println("djjdjdjdj");
        listAnnonces.getItems();
        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSelonPSVita();
        listAnnonces.getItems().clear();
        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n" + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces());

            liste.add(list.get(i).getIdAnnonces() + "");

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
           imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

    }

    
    @FXML
    void retourAccueil(ActionEvent event) throws IOException {
        HangarGame hanarGame = new HangarGame();
        hangar.depalcerVersAcceuil();
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        CrudAnnonces crud = new CrudAnnonces();
        List<Annonces> list = crud.reccupererSimple();

        for (int i = 0; i < list.size(); i++) {

            Label lbl = new Label(list.get(i).getNomAnnonces() + "\n"
                    + list.get(i).getPrix() + "DT" + "\n" + list.get(i).getTypeAnnonces()
            );
            liste.add(list.get(i).getIdAnnonces() + "");

            ImageView imageView = new ImageView(new Image(list.get(i).getInputStream()));
            imageView.setFitHeight(150);
            imageView.setFitWidth(180);

            lbl.setGraphic(imageView);

            listAnnonces.getItems().add(lbl);

            listAnnonces.setExpanded(true);
            listAnnonces.depthProperty().set(1);
        }

   
    
    try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContenent.fxml"));
            drawer.setSidePane(box);
            
            for(Node node : box.getChildren()){
                System.out.println(node.getAccessibleText());
            if(node.getAccessibleText()!=null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
                
               
            if ("annonce".equals(node.getAccessibleText())){
                
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("AccueilAnnonce.fxml")); 
                    anchor.getChildren().addAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            if ("forum".equals(node.getAccessibleText())){
                 
            
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutSujet.fxml")); 
                    anchor.getChildren().addAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    
            }
            
              if ("xxxx".equals(node.getAccessibleText())){
                 
            
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutJeuVideo.fxml")); 
                    anchor.getChildren().addAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    
            }
                
             if ("GoToIUserInformation".equals(node.getAccessibleText())){
                
       try {
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("ChangePassword.fxml")); 
                    anchor.getChildren().addAll(pane);
                  
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
              
              
              
              
                });//apartir
         
            }
            
            }
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

      HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {    
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.setVisible(false);
                drawer.close();
            } else {
                 drawer.setVisible(true);
                drawer.open();
                System.out.println("c'est fait");
            }
        });
    
    
    }

    @FXML
    void CreerVotrePropreAnnonce(ActionEvent event) {
        try {
            System.out.println(LoginController.LoginStatic);
            hangar.depalcerVersAjoutAnnonce();
        } catch (IOException ex) {
            Logger.getLogger(AccueilAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
