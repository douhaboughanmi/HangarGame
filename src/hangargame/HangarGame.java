/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import hangargame.entites.Evenement;
import hangargame.serviceinterface.IEvaluationCrud;
import hangargame.services.CrudEvaluation;
import hangargame.services.EvenementCrud;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class HangarGame extends Application {
    
     public static Boolean isSplashLoaded = false;
             public static AnchorPane anchorPane ;
             public Stage primaryStage ;

    // stage.show();
    public HangarGame() {
    }
             

    @Override
    public void start(Stage primaryStage) throws Exception {
  this.primaryStage = primaryStage ;
        this.primaryStage.setTitle("Hangar Game");
        showMainView();
       //Parent root = FXMLLoader.load(getClass().getResource("xml/TournoiListe.fxml"));

        
       // Scene scene = new Scene(root);
        
        //stage.setScene(scene);
       // stage.setTitle("Hangar Game");
       // stage.show();
       
       
    }
    private void showMainView() throws IOException {
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/Accueil.fxml"));
       anchorPane = loader.load();
       Scene scene = new Scene(anchorPane);
       primaryStage.setScene(scene);
       primaryStage.show();
    }
 //louay//   
     public static void depalcerVersEvenementGamer() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/ListeEvenementGamer.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
      public static void RetourAccueilEvenement() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/Accueil.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
       public static void DeplacerVersTournoi() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/TournoiListe.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    
    
    
    //marwen//
      public static void depalcerVersAccueil() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/Accueil.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    
    public static void depalcerlistedesvideoTest() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/AjoutVideoTest.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    public static void depalcerVideotest() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/listedesvideoDirect.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    public static void depalcerUserVideoTest() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/UserVideoTest.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    public static void depalcerVersLive() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/VideoEnDirect.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
     public static void depalcerVersVideoTest() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/listedesvideoDirect.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
     public static void depalcerVersCommentaireBack() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/CommentaireVideoTestBack.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
      public static void depalcerVerslisteVideoBack() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/VideoTestBack.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
      //halim//
      
      public static void depalcerVersAjoutAnnonce() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/AjoutAnnonce.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }

    
    public static void depalcerVersAccueilAnnonce() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/AccueilAnnonce.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    public static void depalcerVersAnnonceDetail() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/AnnonceDetail.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    
    public static void depalcerVersAMesAnnonce() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/MesAnnonces.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    
    public static void depalcerVersAMesFavoris() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/MesFavoris.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    
    
    
    public static void depalcerVersAcceuil() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/Accueil.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
//  HAMZA
    public static void depalcerVersAffichageSujetsl() throws IOException{
    FXMLLoader loader = new FXMLLoader() ;
       loader.setLocation(HangarGame.class.getResource("xml/AffichageSujets.fxml"));
       AnchorPane mainItem = loader.load();
       anchorPane.getChildren().addAll(mainItem);
    }
    //Yesmine
    public static void showStat()  {
         final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc
            = new StackedBarChart<String, Number>(xAxis, yAxis);
    final XYChart.Series<String, Number> series1
            = new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series2
            = new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series3
            = new XYChart.Series<String, Number>();
        Stage primaryStage = new Stage();
        IEvaluationCrud evals = new CrudEvaluation();

        primaryStage.setTitle("Hangar Game");
        sbc.setTitle("Top 10 Best Games");
        xAxis.setLabel("Games");
        yAxis.setLabel("Rate");
        series1.setName("2017");
       // series2.setName("2018");
       // series1.setName("2018");
        evals.getTopEvaluation().forEach((tab) -> {

            series1.getData().add(new XYChart.Data<String, Number>(tab.getNom_jeu(), tab.getNote())); 
        });

        Scene scene = new Scene(sbc, 800, 600);
        sbc.getData().addAll(series1);
       
       
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     public static void depalcerVersDetailJeu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/DetailJeu.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
    
    
    public static void depalcerVersAffichageJeu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AffichageAdminJeu.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
    
     public static void depalcerVersAjoutJeu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AjoutJeuVideo.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
     
     public static void depalcerVersNoterJeu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/NoterJeu.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
         
    }
    
      public static void depalcerVerslisteJeu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AffichageClientJeu.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
      
       public static void depalcerVersDetail() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/DetailJeu.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
        public static void depalcerVersListeAct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AffichageAdminActualite.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
        public static void depalcerVersAjoutAct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AjoutActualite.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
         public static void depalcerVersAffichageActsidePanel() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AffichageClientActualite.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
          public static void depalcerVersAffichageConsolesidePanel() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AffichageClientConsoles.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
          public static void depalcerVersAffichageJeusidePanel() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HangarGame.class.getResource("xml/AffichageClientJeu.fxml"));
        AnchorPane mainItem = loader.load();
        anchorPane.getChildren().addAll(mainItem);
    }
    
    
    //fin yesmine
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
     // Evenement e = new Evenement("Louay","lol","Evenement de la semaine","2017-01-02","2018-12-23") ;
       //EvenementCrud EC = new EvenementCrud();
       // EC.ajouterEvenement(e);
        //EC.supprimerEvenement(17);
        //EC.afficherEvenement();
       
    }
    
}