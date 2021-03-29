
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


public class ReclamationProfService implements IReclamation<ReclamationProf>{

    @Override
    public void ajouterReclamation(ReclamationProf r) {
        String requete = "INSERT INTO `reclamation`(`title`, `date` ,`recl` ,`reclmodif` ,`exp` ,`msg` ,`msgA`) VALUES (?,?,?,?,?,?,?)";
        try {
          PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
          pst.setString(1, r.getTitle());
          pst.setString(2, String.valueOf(r.getDate()));
          pst.setString(3, r.getRecl());
          pst.setString(4, r.getReclmodif());
          pst.setString(5, r.getExp());
          pst.setString(6, r.getMsg());
          pst.setString(7, r.getMsgA());
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
    public void updateReclamation(ReclamationProf r, int id) {
                try {
            String req = "UPDATE `reclamation` SET `title`=?,`date`=?, `recl`=? , `reclmodif`=? , `exp`=? , `msg`=? , `msgA`=? WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
          pst.setString(1, r.getTitle());
          pst.setString(2, String.valueOf(r.getDate()));
          pst.setString(3, r.getRecl());
          pst.setString(4, r.getReclmodif());
          pst.setString(5, r.getExp());
          pst.setString(6, r.getMsg());
          pst.setString(7, r.getMsgA());
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reclamation ajouté");
            }
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @Override
    public List<ReclamationProf> displayReclamation() {
                 List<ReclamationProf> reclamationProfList = new ArrayList<>();
        try {
           String req = "SELECT * FROM reclamation WHERE (msg = 'BR') " ;
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
                ct.setNom_user(rs.getString("nom_user"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationProfList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationProfList; }


    public List<ReclamationProf> displayRC() {
        List<ReclamationProf> reclamationProfList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE msg IN ('UCORBEILLE','UCORBEILLE2') " ;
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
                ct.setNom_user(rs.getString("nom_user"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationProfList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationProfList; }


    public List<ReclamationProf> displayRA() {
        List<ReclamationProf> reclamationProfList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE (msg = 'UARCHIVE') " ;
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
                ct.setNom_user(rs.getString("nom_user"));
                ct.setExp(rs.getString("exp"));
                ct.setMsg(rs.getString("msg"));
                ct.setMsgA(rs.getString("msgA"));
                reclamationProfList.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationProfList; }

}
