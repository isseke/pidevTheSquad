package Services;

import Entities.ReclamationProf;
import Interfaces.IReclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*public class ReclamationServiceProf implements IReclamation<ReclamationProf> {

    @Override
    public void ajouterReclamation(ReclamationProf r) {

        String requete = "INSERT INTO `reclamationprof`(`title`, `description`, `id_professeur`) VALUES (?,?,?)";

        try {

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, r.getTitle());
            pst.setString(2, r.getDescription());
            pst.setString(3,String.valueOf("1"));

            pst.executeUpdate();
            System.out.println("Reclamation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());   }
    }

    @Override
    public void supprimerReclamation(ReclamationProf r) {
        String req = "DELETE FROM reclamationprof WHERE id=? ";
        try {

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();

            System.out.println("Reclamation has been removed");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   /* @Override
    public void updateReclamation(ReclamationProf r,int id) {
        try {
            String req = "UPDATE `reclamationprof` SET `title`=?,`description`=?,`id_professeur`=? WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, r.getTitle());
            pst.setString(2, r.getDescription());

            pst.setString(3,String.valueOf("1"));
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reclamation modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
*/
  /*  @Override
    public List<ReclamationProf> displayReclamation() {
        List<ReclamationProf> reclamationListProf = new ArrayList<>();
        try {

            String req = "SELECT * FROM reclamationprof " ;

            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ReclamationProf ct = new ReclamationProf();

                ct.setId(rs.getInt("id"));
                ct.setTitle(rs.getString("title"));
                ct.setDescription(rs.getString("description"));
                ct.setAnswer(rs.getString("answer"));
                ct.setId_professeur(rs.getInt("id_professeur"));
                reclamationListProf.add(ct);


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationListProf;

    }
}*/

