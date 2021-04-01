
package Services;

import Modele.AdminReclamation;
import Interface.IReclamationAdmin;
import ConnectDB.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminReclamationService implements IReclamationAdmin<AdminReclamation> {

    @Override
    public void answerReclamation(AdminReclamation r ,int id) {
        String requete = "UPDATE `reclamation` SET `etat`=?, `recl`=? ,`date`=? ,`reclmodif`=? ,`exp`=? , `msg`=? , `msgA`=? WHERE id='"+id+"'";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, r.getEtat());
            pst.setString(2, r.getRecl());
            pst.setString(3, String.valueOf(r.getDate()));
            pst.setString(4, r.getReclmodif());
            pst.setString(5, r.getExp());
            pst.setString(6, r.getMsg());
            pst.setString(7, r.getMsgA());
            pst.executeUpdate();
            System.out.println("Reponse ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimerReclamation(AdminReclamation r) {
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
    public void updateReclamation(AdminReclamation r,int id) {
                try {
            String req = "UPDATE `reclamation` SET `etat`=?, `date`=?, `recl`=?, `reclmodif`=?, `exp`=? , `msg`=? , `msgA`=?WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
          pst.setString(1, r.getEtat());
          pst.setString(2, String.valueOf(r.getDate()));
          pst.setString(3, r.getRecl());
          pst.setString(4, r.getReclmodif());
          pst.setString(5, r.getExp());
          pst.setString(6, r.getMsg());
          pst.setString(7, r.getMsgA());
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) { System.out.println("Reponse ajouté"); } } catch (SQLException ex) {
            System.out.println(ex.getMessage()); } }

    @Override
    public List<AdminReclamation> displayReclamation() {
        List<AdminReclamation> reclamationList = new ArrayList<>();
        try { String req = "SELECT * FROM reclamation WHERE (msgA = 'ABR') AND (id_prof IS NULL)" ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                AdminReclamation ct = new AdminReclamation();
                ct.setId(rs.getInt("id"));
                ct.setTitle(rs.getString("title"));
                ct.setDate(rs.getString("date"));
                ct.setEtat(rs.getString("etat"));
                ct.setRecl(rs.getString("recl"));
                ct.setReclmodif(rs.getString("reclmodif"));
                ct.setId_prof(rs.getInt("id_user"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationList.add(ct); }
        } catch (SQLException ex) { System.out.println(ex.getMessage()); }

        try { String req = "SELECT * FROM reclamation WHERE (msgA = 'ABR') AND (id_user IS NULL)" ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                AdminReclamation ct = new AdminReclamation();
                ct.setId(rs.getInt("id"));
                ct.setTitle(rs.getString("title"));
                ct.setDate(rs.getString("date"));
                ct.setEtat(rs.getString("etat"));
                ct.setRecl(rs.getString("recl"));
                ct.setReclmodif(rs.getString("reclmodif"));
                ct.setId_prof(rs.getInt("id_prof"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationList.add(ct); }
        } catch (SQLException ex) { System.out.println(ex.getMessage()); }
        return reclamationList; }

  /*  @Override
    public List<AdminReclamation> displayReclamation() {
                 List<AdminReclamation> reclamationList = new ArrayList<>();
        try {
           String req = "SELECT * FROM reclamation WHERE (msgA = 'ABR') " ;
           Statement st = MyConnection.getInstance().getCnx().createStatement();
           ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                AdminReclamation ct = new AdminReclamation();
                ct.setId(rs.getInt("id"));
                ct.setTitle(rs.getString("title"));
                ct.setDate(rs.getString("date"));
                ct.setEtat(rs.getString("etat"));
                ct.setRecl(rs.getString("recl"));
                ct.setReclmodif(rs.getString("reclmodif"));
                ct.setId_user(rs.getInt("id_user"));
                ct.setId_prof(rs.getInt("id_prof"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList; } */


    public List<AdminReclamation> displayRC() {
        List<AdminReclamation> reclamationList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE msgA IN ('ACORBEILLE','ACORBEILLE2') " ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                AdminReclamation ct = new AdminReclamation();
                ct.setId(rs.getInt("id"));
                ct.setTitle(rs.getString("title"));
                ct.setDate(rs.getString("date"));
                ct.setEtat(rs.getString("etat"));
                ct.setRecl(rs.getString("recl"));
                ct.setReclmodif(rs.getString("reclmodif"));
                ct.setId_user(rs.getInt("id_user"));
                ct.setId_prof(rs.getInt("id_prof"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList; }


    public List<AdminReclamation> displayRA() {
        List<AdminReclamation> reclamationList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE (msgA = 'AARCHIVE') " ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                AdminReclamation ct = new AdminReclamation();
                ct.setId(rs.getInt("id"));
                ct.setTitle(rs.getString("title"));
                ct.setDate(rs.getString("date"));
                ct.setEtat(rs.getString("etat"));
                ct.setRecl(rs.getString("recl"));
                ct.setReclmodif(rs.getString("reclmodif"));
                ct.setId_user(rs.getInt("id_user"));
                ct.setId_prof(rs.getInt("id_prof"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList; }
}
