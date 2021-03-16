/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
import Interfaces.IReclamation;
import Utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ReclamationService implements IReclamation<Reclamation>{

    @Override
    public void ajouterReclamation(Reclamation r) {
       
        String requete = "INSERT INTO `reclamation`(`title`, `date`, `id_apprenant`) VALUES (?,?,?)";
        try {
          PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
          pst.setString(1, r.getTitle());
          pst.setString(2, r.getDate());
          pst.setString(3,String.valueOf("1"));
          pst.executeUpdate();
           System.out.println("Reclamation ajouté");
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());   }
    }

    @Override
    public void supprimerReclamation(Reclamation r) {
                String req = "DELETE FROM reclamation WHERE id=? ";
         try {
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            
                System.out.println("Reclamation has been removed");
            
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
    }

    @Override
    public void updateReclamation(Reclamation r,int id) {
                try {
            String req = "UPDATE `reclamation` SET `title`=?,`date`=?,`id_apprenant`=? WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
          pst.setString(1, r.getTitle());
          pst.setString(2, r.getDate());
          pst.setString(3,String.valueOf("1"));
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reclamation modifié");
            }
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @Override
    public List<Reclamation> displayReclamation() {
        List<Reclamation> reclamationList = new ArrayList<>();
        try {
           String req = "SELECT * FROM reclamation" ;
           Statement st = MyConnection.getInstance().getCnx().createStatement();
           ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reclamation ct = new Reclamation();
                ct.setId(rs.getInt("id"));
                ct.setTitle(rs.getString("title"));
                ct.setDate(rs.getString("date"));
                ct.setEtat(rs.getString("etat"));
                ct.setId_apprenant(rs.getInt("id_apprenant"));
                reclamationList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList;
}
}
