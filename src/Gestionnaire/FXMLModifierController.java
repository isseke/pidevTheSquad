/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.Evenement;
import Services.ServiceEvenement;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLModifierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label il_label;
    
    @FXML
    private Label m_id_even;
    
   @FXML
    private TextField m_even_id;
    
    @FXML
    private TextField mtheme;

    @FXML
    private TextField mlien;

    @FXML
    private TextField mpresentateur;

    @FXML
    private DatePicker mdate;

    @FXML
    private Button mbtnModifier;

    @FXML
    private Button mbtnAnnuler;

    public void setM_even_id(String m_even_id) {
        this.m_even_id.setText(m_even_id);
    }

    public void setIl_label(String il_label) {
        this.il_label.setText(il_label);
    }
    
    
    
    public void setM_id_even(String m_id_even) {
        this.m_id_even.setText(m_id_even);
    }

    public void setMtheme(String mtheme) {
        this.mtheme.setText(mtheme); ;
    }

    public void setMlien(String mlien) {
        this.mlien.setText(mlien);
    }

    public void setMpresentateur(String mpresentateur) {
        this.mpresentateur.setText(mpresentateur);
    }

    public void setMdate(String mdate) {
        this.mdate.setValue(LocalDate.parse(mdate));
    }

    public void setMbtnModifier(Button mbtnModifier) {
        this.mbtnModifier = mbtnModifier;
    }

    public void setMbtnAnnuler(Button mbtnAnnuler) {
        this.mbtnAnnuler = mbtnAnnuler;
    }
    
    
    

    @FXML
    void mAnnuler(MouseEvent event) {
        Stage stage = (Stage) mbtnAnnuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void mModifier(MouseEvent event) {
        
        String lien = mlien.getText();
        String theme = mtheme.getText();
        String presentateur = mpresentateur.getText();
        String date = mdate.getValue().toString();
        int id = Integer.parseInt(il_label.getText());
        
        Evenement evt = new Evenement(id,lien, theme, presentateur, date);
        
        ServiceEvenement service = new ServiceEvenement();
        
        service.modifier(evt);
        
        FXMLPrincipalEventController ep = new FXMLPrincipalEventController();
        
        ep.miseAjour();

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
