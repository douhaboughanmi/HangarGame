package hangargame.xml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import hangargame.entites.Sujet;
import hangargame.services.CrudSujet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class AjoutSujetController {

    public String s = "hamza";
    CrudSujet crs = new CrudSujet();

    private static final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();

    public void initialize() {
        comboCatgr.setValue(new Label("Jeux Mobiles"));
        comboCatgr.getItems().add(new Label("Jeux Mobiles"));
        comboCatgr.getItems().add(new Label("Jeux Consoles"));
        comboCatgr.getItems().add(new Label("Jeux PC"));
        comboCatgr.getItems().add(new Label("BlaBLaBlaBla"));
        affficherHistorique(s);
        afficherTotalJaime(s);

    }

    @FXML
    private AnchorPane anacrosujet;
    @FXML
    private ImageView btnaddsjt;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextArea sujetArea;

    @FXML
    private JFXTextField titresujet;

    @FXML
    private JFXComboBox<Label> comboCatgr;
    @FXML
    private JFXListView<Label> listehistorique;

    @FXML
    private Label Totaljaime;

    @FXML
    private Label totalsignale;

    @FXML
    void Addsbjct(ActionEvent event) {
        JInternalFrame frame = null;

        String txt = sujetArea.getText();
        String titre = titresujet.getText();
        String catgre = (String) comboCatgr.getValue().getText();

        if (catgre.isEmpty()) {

            JOptionPane.showMessageDialog(frame,
                    "le contenue de message est vide  ");
        } else {
            Sujet sjt = new Sujet(titre, txt, catgre);
            crs.ajoutersujet(sjt);
            JOptionPane.showMessageDialog(frame,
                    "votre sujet :" + titre + " a été ajouté  ");
            affficherHistorique(s);
        }

    }

    @FXML
    void affichStat(ActionEvent event) {
        afficherStat();
    }

    public void affficherHistorique(String s) {

        ArrayList<Sujet> L = crs.afficherHistoriquePersonnel(s);

        for (int i = 0; i < L.size(); i++) {

            Label l = new Label("Titre de Sujet  :  " + L.get(i).getNomSujet() + "\n" + "Date de publication  :  " + L.get(i).getdateSjt());

            listehistorique.
                    getItems().add(l);
            listehistorique.setExpanded(true);
            listehistorique.depthProperty().set(1);
            l.setContentDisplay(ContentDisplay.CENTER);

        }
    }

    public void afficherTotalJaime(String s) {

        int x = crs.tolalJaime(s);

        Totaljaime.setText(x + "");

    }

    public void afficherStat() {
        int a = crs.tolalJaime(s);
        int x = crs.tolalSignale(s);
        BorderPane root;
        PieChart pieChart;
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Mes Statistiques ");
        details.addAll(new PieChart.Data("Total des j'aime", a),
                new PieChart.Data("Total des signales", x)
        );
        root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);
        pieChart = new PieChart();
        pieChart.setData(details);
        pieChart.setTitle("Le rapport entre \n siganles et notes obtenues");
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(true);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(50);
        root.setCenter(pieChart);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
