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
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import hangargame.HangarGame;
import hangargame.entites.Annonces;
import hangargame.services.CrudAnnonces;
import hangargame.services.CrudFavoris;
import hangargame.services.CrudSignalisation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mayss
 */
public class AnnonceDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label titreAnnonce;

    @FXML
    private Label prixAnnonce;

    @FXML
    private Label imageAnnonce;

    @FXML
    private Label typreAnnonce;

    @FXML
    private Label ConsoleAnnoce;

    @FXML
    private Label gamerAnnonce;

    @FXML
    private Label decriptionAnnonce;

    public String gamer = "";
    String resultatSignalisation = "";
    String nomFichierPDF;
    Annonces a;

    @FXML
    void favoris(ActionEvent event) {
        CrudFavoris cf = new CrudFavoris();
        cf.ajouterAnnoncesFavoris();
        
        System.out.println("jawi béhi");

    }

    @FXML
    void extrairPDF(ActionEvent event) throws DocumentException, FileNotFoundException {

        TextInputDialog dialog = new TextInputDialog("Annonce");
        dialog.setTitle("Extraire en PDF");
        dialog.setHeaderText("Extraire en PDF");
        dialog.setContentText("Veuillez entrer le nom du fichier");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            nomFichierPDF = result.get();
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, new FileOutputStream(nomFichierPDF + ".pdf"));
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph(a.getNomAnnonces() + "\n"
                    + a.getPrix() + "DT" + "\n"
                    + a.getTypeAnnonces() + "\n"
                    + a.getConsoleAnnonces() + "\n"
                    + a.getGamer()));
            // step 5
            document.close();
            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Succés");
            tr.setMessage("Votre fichier a été crée ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.POPUP);
            tr.showAndDismiss(Duration.seconds(8));
        } else {

            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Erreur");
            tr.setMessage("Aucun Nom mentionner");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setAnimationType(AnimationType.POPUP);
            tr.showAndDismiss(Duration.seconds(8));

        }

    }

    @FXML
    void signalisation(ActionEvent event) {
        CrudSignalisation crud = new CrudSignalisation();
        resultatSignalisation = crud.signalerAnnonce(gamer);
        if (resultatSignalisation.equals("vous avez déja signaler cette annonce")) {
            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Erreur");
            tr.setMessage("Vous avez Déja signaler cette annonce");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(Duration.seconds(8));
        } else {
            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Succès");
            tr.setMessage("Signalisation efectué");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.POPUP);
            tr.showAndDismiss(Duration.seconds(8));

        }

    }

    @FXML
    void partagez(ActionEvent event) throws FileNotFoundException {
        System.out.println("FACEBOOK");
        String token = "EAACEdEose0cBABgXTdD6iOFe2SMCNnsq3WS85CSwQpjXFo52KIVpB9opvj7ZBqsijSYr3NGxpMIpDWoAee8igzBBOvOY6ZB4qYZAu2G8MCZAXwft8SXZABvIZC2aNFklCIJ7G8uBJvkJbCKSZC8gOhlctNkxv9zWUKTfeZAZAMhVM7AC9xws4TaZCeeYM0rvh0HIsZD";
        FacebookClient fb = new DefaultFacebookClient(token);
        FileInputStream i = new FileInputStream(a.getPathImage());

        FacebookType response = fb.publish("me/photos", FacebookType.class,
                BinaryAttachment.with("mayssa", i), Parameter.with("message", a.getNomAnnonces() + "\n"
                        + a.getPrix() + "Dt" + "\n"
                        + "Installer notre application HangarGame"));
tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Succès");
            tr.setMessage("Consulter votre Facebook");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.POPUP);
            tr.showAndDismiss(Duration.seconds(8));
    }

    @FXML
    void retourVersAccueilAnnonce(ActionEvent event) throws IOException {
        HangarGame game = new HangarGame();
        game.depalcerVersAccueilAnnonce();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudAnnonces crud = new CrudAnnonces();
        a = crud.reccupererAnnonce2();
        System.out.println();

        titreAnnonce.setText(a.getNomAnnonces());
        prixAnnonce.setText(a.getPrix() + "DT");
        typreAnnonce.setText(a.getTypeAnnonces());
        ConsoleAnnoce.setText(a.getConsoleAnnonces());
        gamerAnnonce.setText(a.getGamer());
        System.out.println("ddddd" + a.getGamer());
        ImageView imageView = new ImageView(new Image(a.getInputStream()));
        imageView.setFitHeight(200);
        imageView.setFitWidth(250);
        imageAnnonce.setGraphic(imageView);

        decriptionAnnonce.setText(a.getDescriptionAnnonces());
        gamer = a.getGamer();

    }

}
