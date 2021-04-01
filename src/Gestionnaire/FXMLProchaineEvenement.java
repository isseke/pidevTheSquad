/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.Evenement;
import Services.ServiceEvenement;
import Services.ServicePromotion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLProchaineEvenement implements Initializable {
   @FXML
   private TableView<Evenement> prochainEvnt;
   
    @FXML
    private TilePane tilepane;
    @FXML
    private Button btnevenement;

    @FXML
    private Button btnPromotion;

    @FXML
    private TableColumn<Evenement, String> ProchainThemeEvent;

    @FXML
    private TableColumn<Evenement, String> ProchainPresentateurEvent;

    @FXML
    private TableColumn<Evenement, String> ProchainDateEvent;

    @FXML
    private TableColumn<Evenement, String> ProchainLienEvent;
     @FXML
    private Button prEvenement;

    @FXML
    private DatePicker dateRechercheEvenement;

    @FXML
    private Button seEvenement;

    @FXML
    private Button btnRecherche;
        @FXML
    private Text labelevt;
            @FXML
    private Label aTheme;

    @FXML
    private Label aDate;

    @FXML
    private Label aPresentateur;

    @FXML
    private Label aLen;

    @FXML
    private ImageView aImage;

    @FXML
    void labelActualiserEvent(MouseEvent event) {
        evenementprincipal();
    }

    @FXML
    void prochainEvenements(MouseEvent event) {
        evenementprochain();

    }
    @FXML
    private Button testpro;

    @FXML
    void homeClick(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/vue/Professeurhome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window=(Stage) testpro.getScene().getWindow();

        window.setScene(new Scene(root,1370,700));

    }


    @FXML
    void rechercheEvenement(MouseEvent event) {
        evenemencherche();
    }

    @FXML
    void semaineEvenement(MouseEvent event) {
        
    }
    public void evenementprincipal(){

        srvEvt = new ServiceEvenement();
        ArrayList<Evenement> liste = srvEvt.prochainEvenementp();
        tilepane.getChildren().clear();

        for(int i = 0; i< liste.size();i++){
            InputStream is = liste.get(i).getInputStream();
            String theme = liste.get(i).getTheme();
            String lien = liste.get(i).getLien();
            String date = liste.get(i).getDate();
            String presentateur = liste.get(i).getPresentateur();
            System.out.println(liste.size() +" "+is+" i="+i);

            try {
                OutputStream os = new FileOutputStream( new File("photo.jpg"));
                System.out.println(os);

                byte[] content = new byte[1024];

                int size = 0;
                while((size = is.read(content)) != -1){

                    os.write(content, 0, size);

                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            Image image = new Image("file:photo.jpg", 200, 150, false, false);


            tilepane.setPadding(new Insets(10,10,10,10));
            tilepane.setHgap(10);
            tilepane.setVgap(10);

            //paneTile.getChildren().add(imageV1);


            Label lLien = new Label(lien);
            lLien.setFont(new Font("Arial", 25));


            Label lDate = new Label(date);
            lDate.setFont(new Font("Arial", 20));

            Label lTheme = new Label(theme);

            lTheme.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: #fff;");

            Label lPresnetateur = new Label(presentateur);


            ImageView imageV1 = new ImageView();

            imageV1.setImage(image);

            VBox vbox = new VBox();
            vbox.setPrefHeight(150);
            vbox.setPrefWidth(200);


            vbox.setStyle("-fx-border-radius : 16; -fx-border-color : #15fe20;-fx-padding: 10");

            vbox.getChildren().addAll(imageV1,lTheme,lDate);

            vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    try {
                        System.out.println("c'est qliqué");

                        Stage stage = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/FXMLAfficheEvenement.fxml"));
                        Parent root = loader.load();
                        FXMLAfficheEvenementController afevt = loader.getController();

                        afevt.setaDate("Date : "+date);
                        afevt.setaPresentateur("Presentation : "+presentateur);
                        afevt.setaTheme("Theme :"+theme);
                        //afevt.setaLen("Lien : "+lien);
                        afevt.setaImage(image);
                        afevt.setHyperLien(lien);




                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLProchaineEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            tilepane.getChildren().add(vbox);
            // paneTile.getChildren().add(imageV1);

        }

    }
    public void evenementprochain(){

        srvEvt = new ServiceEvenement();
        ArrayList<Evenement> liste = srvEvt.prochainEvenementpp();
        tilepane.getChildren().clear();

        for(int i = 0; i< liste.size();i++){
            InputStream is = liste.get(i).getInputStream();
            String theme = liste.get(i).getTheme();
            String lien = liste.get(i).getLien();
            String date = liste.get(i).getDate();
            String presentateur = liste.get(i).getPresentateur();
            System.out.println(liste.size() +" "+is+" i="+i);

            try {
                OutputStream os = new FileOutputStream( new File("photo.jpg"));
                System.out.println(os);

                byte[] content = new byte[1024];

                int size = 0;
                while((size = is.read(content)) != -1){

                    os.write(content, 0, size);

                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            Image image = new Image("file:photo.jpg", 200, 150, false, false);


            tilepane.setPadding(new Insets(10,10,10,10));
            tilepane.setHgap(10);
            tilepane.setVgap(10);

            //paneTile.getChildren().add(imageV1);


            Label lLien = new Label(lien);
            lLien.setFont(new Font("Arial", 25));


            Label lDate = new Label(date);
            lDate.setFont(new Font("Arial", 20));

            Label lTheme = new Label(theme);

            lTheme.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: #fff;");

            Label lPresnetateur = new Label(presentateur);


            ImageView imageV1 = new ImageView();

            imageV1.setImage(image);

            VBox vbox = new VBox();
            vbox.setPrefHeight(150);
            vbox.setPrefWidth(200);


            vbox.setStyle("-fx-border-radius : 16; -fx-border-color : #15fe20;-fx-padding: 10");

            vbox.getChildren().addAll(imageV1,lTheme,lDate);

            vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    try {
                        System.out.println("c'est qliqué");

                        Stage stage = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/FXMLAfficheEvenement.fxml"));
                        Parent root = loader.load();
                        FXMLAfficheEvenementController afevt = loader.getController();

                        afevt.setaDate("Date : "+date);
                        afevt.setaPresentateur("Presentation : "+presentateur);
                        afevt.setaTheme("Theme :"+theme);
                        //afevt.setaLen("Lien : "+lien);
                        afevt.setaImage(image);
                        afevt.setHyperLien(lien);




                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLProchaineEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            tilepane.getChildren().add(vbox);
            // paneTile.getChildren().add(imageV1);

        }

    }
    public void evenemencherche(){

        srvEvt = new ServiceEvenement();
        String date_recherche = String.valueOf(dateRechercheEvenement.getValue());
        ArrayList<Evenement> liste = srvEvt.prochainEvenementR(date_recherche);

        tilepane.getChildren().clear();

        for(int i = 0; i< liste.size();i++){
            InputStream is = liste.get(i).getInputStream();
            String theme = liste.get(i).getTheme();
            String lien = liste.get(i).getLien();
            String date = liste.get(i).getDate();
            String presentateur = liste.get(i).getPresentateur();
            System.out.println(liste.size() +" "+is+" i="+i);

            try {
                OutputStream os = new FileOutputStream( new File("photo.jpg"));
                System.out.println(os);

                byte[] content = new byte[1024];

                int size = 0;
                while((size = is.read(content)) != -1){

                    os.write(content, 0, size);

                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            Image image = new Image("file:photo.jpg", 200, 150, false, false);


            tilepane.setPadding(new Insets(10,10,10,10));
            tilepane.setHgap(10);
            tilepane.setVgap(10);

            //paneTile.getChildren().add(imageV1);


            Label lLien = new Label(lien);
            lLien.setFont(new Font("Arial", 25));


            Label lDate = new Label(date);
            lDate.setFont(new Font("Arial", 20));

            Label lTheme = new Label(theme);

            lTheme.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: #fff;");

            Label lPresnetateur = new Label(presentateur);


            ImageView imageV1 = new ImageView();

            imageV1.setImage(image);

            VBox vbox = new VBox();
            vbox.setPrefHeight(150);
            vbox.setPrefWidth(200);


            vbox.setStyle("-fx-border-radius : 16; -fx-border-color : #15fe20;-fx-padding: 10");

            vbox.getChildren().addAll(imageV1,lTheme,lDate);

            vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    try {
                        System.out.println("c'est qliqué");

                        Stage stage = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/FXMLAfficheEvenement.fxml"));
                        Parent root = loader.load();
                        FXMLAfficheEvenementController afevt = loader.getController();

                        afevt.setaDate("Date : "+date);
                        afevt.setaPresentateur("Presentation : "+presentateur);
                        afevt.setaTheme("Theme :"+theme);
                        //afevt.setaLen("Lien : "+lien);
                        afevt.setaImage(image);
                        afevt.setHyperLien(lien);




                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLProchaineEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            tilepane.getChildren().add(vbox);
            // paneTile.getChildren().add(imageV1);

        }

    }


    @FXML
    ServiceEvenement srvEvt;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evenementprincipal();
        
        // miseAjourProchainEvent();
        
    }    
    
    
    public void images(){
        
        srvEvt = new ServiceEvenement();
            ArrayList<Evenement> liste = srvEvt.prochainEvenementp();
            
            for(int i = 0; i< liste.size();i++){
                InputStream is = liste.get(i).getInputStream();
                String theme = liste.get(i).getTheme();
                String lien = liste.get(i).getLien();
                String date = liste.get(i).getDate();
                String presentateur = liste.get(i).getPresentateur();
            System.out.println(liste.size() +" "+is+" i="+i);
            
        try {
            OutputStream os = new FileOutputStream( new File("photo.jpg"));
            System.out.println(os);
            
                    byte[] content = new byte[1024];

                    int size = 0;
                     while((size = is.read(content)) != -1){
                
                os.write(content, 0, size);
                
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Image image = new Image("file:photo.jpg", 200, 150, false, false);
        
        
        tilepane.setPadding(new Insets(10,10,10,10));        
        tilepane.setHgap(10);
        tilepane.setVgap(10);
        
        //paneTile.getChildren().add(imageV1);
        
        
            Label lLien = new Label(lien);
            lLien.setFont(new Font("Arial", 25));
            
            
            Label lDate = new Label(date);
            lDate.setFont(new Font("Arial", 20));
            
            Label lTheme = new Label(theme);
            
            lTheme.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: #fff;");
            
            Label lPresnetateur = new Label(presentateur);
          

            ImageView imageV1 = new ImageView();
            
            imageV1.setImage(image);
            
            VBox vbox = new VBox();
            vbox.setPrefHeight(150);
            vbox.setPrefWidth(200);
            
            
            vbox.setStyle("-fx-border-radius : 16; -fx-border-color : #15fe20;-fx-padding: 10");

            vbox.getChildren().addAll(imageV1,lTheme,lDate);
            
            vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        try {
                            System.out.println("c'est qliqué");
                            
                            Stage stage = new Stage();
                     
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/FXMLAfficheEvenement.fxml"));
                            Parent root = loader.load();
                            FXMLAfficheEvenementController afevt = loader.getController();
                            
                            afevt.setaDate("Date : "+date);
                            afevt.setaPresentateur("Presentation : "+presentateur);
                            afevt.setaTheme("Theme :"+theme);
                            //afevt.setaLen("Lien : "+lien);
                            afevt.setaImage(image);
                            afevt.setHyperLien(lien);
                            
                            
                            
                            
                            Scene scene = new Scene(root);
                            
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLProchaineEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
            });
            tilepane.getChildren().add(vbox);
           // paneTile.getChildren().add(imageV1);
                
            }
            
        }
        
    
    
   /* public void initialiserProchainEvenement(ServiceEvenement srvEvt){
        this.srvEvt = srvEvt;
       prochainEvnt.setItems(srvEvt.prochainEvenement());
  
    }
    
    public void initialiserProchainEvenements(ServiceEvenement srvEvt){
        this.srvEvt = srvEvt;
       prochainEvnt.setItems(srvEvt.prochainEvenements());
  
    }
    
    public void initialiserRechercheEvenement(ServiceEvenement srvEvt){
        this.srvEvt = srvEvt;
        String date_recherche = String.valueOf(dateRechercheEvenement.getValue());
       prochainEvnt.setItems(srvEvt.rechercheEvenements(date_recherche));
  
    }
    
    public void miseAjourProchainEvent(){
        ProchainThemeEvent.setCellValueFactory(cellData -> cellData.getValue().ThemeProperty());
        ProchainDateEvent.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        ProchainPresentateurEvent.setCellValueFactory(cellData -> cellData.getValue().PresentateurProperty());
        
        ProchainLienEvent.setCellValueFactory(cellData -> cellData.getValue().LienProperty());
        initialiserProchainEvenement(new ServiceEvenement());
    }
    public void miseAjourProchainEvents(){
        ProchainThemeEvent.setCellValueFactory(cellData -> cellData.getValue().ThemeProperty());
        ProchainDateEvent.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        ProchainPresentateurEvent.setCellValueFactory(cellData -> cellData.getValue().PresentateurProperty());
        
        ProchainLienEvent.setCellValueFactory(cellData -> cellData.getValue().LienProperty());
        initialiserProchainEvenements(new ServiceEvenement());
    }
        public void miseAjourRechercheEvents(){
        ProchainThemeEvent.setCellValueFactory(cellData -> cellData.getValue().ThemeProperty());
        ProchainDateEvent.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        ProchainPresentateurEvent.setCellValueFactory(cellData -> cellData.getValue().PresentateurProperty());
        
        ProchainLienEvent.setCellValueFactory(cellData -> cellData.getValue().LienProperty());
        initialiserRechercheEvenement(new ServiceEvenement());
    }*/
    
    
    
}
