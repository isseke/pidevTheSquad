
package Services;

import Modele.ReclamationProf;
import Interface.IReclamation;
import ConnectDB.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReclamationProfService implements IReclamation<ReclamationProf> {

    @Override
    public void ajouterReclamation(ReclamationProf r) {
        String requete = "INSERT INTO `reclamation`(`id_prof` ,`title`, `date` ,`recl` ,`reclmodif` ,`exp` ,`msg` ,`msgA`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getId_prof());
            pst.setString(2, r.getTitle());
            pst.setString(3, String.valueOf(r.getDate()));
            pst.setString(4, r.getRecl());
            pst.setString(5, r.getReclmodif());
            pst.setString(6, r.getExp());
            pst.setString(7, r.getMsg());
            pst.setString(8, r.getMsgA());
            pst.executeUpdate();
            System.out.println("Reclamation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimerReclamation(ReclamationProf r) {
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
    public void updateReclamation(ReclamationProf r,int id) {
        try {
            String req = "UPDATE `reclamation` SET `id_prof`=?, `title`=?, `date`=?, `recl`=?, `reclmodif`=?, `exp`=? , `msg`=? , `msgA`=?WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, r.getId_prof());
            pst.setString(2, r.getTitle());
            pst.setString(3, String.valueOf(r.getDate()));
            pst.setString(4, r.getRecl());
            pst.setString(5, r.getReclmodif());
            pst.setString(6, r.getExp());
            pst.setString(7, r.getMsg());
            pst.setString(8, r.getMsgA());
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) { System.out.println("Reponse ajouté"); } } catch (SQLException ex) {
            System.out.println(ex.getMessage()); } }


    @Override
    public List<ReclamationProf> displayReclamation(int idu) {
        List<ReclamationProf> reclamationList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE (msg = 'BR') AND ( id_prof = '"+idu+"')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ReclamationProf ct = new ReclamationProf();
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
                reclamationList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList; }


    public List<ReclamationProf> displayRC(int idu) {
        List<ReclamationProf> reclamationList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE msg IN ('PCORBEILLE','PCORBEILLE2') AND ( id_prof = '"+idu+"')" ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ReclamationProf ct = new ReclamationProf();
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
                reclamationList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList; }


    public List<ReclamationProf> displayRA(int idu) {
        List<ReclamationProf> reclamationList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE (msgA = 'PARCHIVE')  AND ( id_prof = '"+idu+"') " ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ReclamationProf ct = new ReclamationProf();
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
                reclamationList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList; }

}
