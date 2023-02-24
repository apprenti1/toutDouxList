package todolist;
import bdd.Bdd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tache {

        private int id_tache;
        private String nom;
        private String description;
        private boolean est_realise;

        public Tache (int id_tache, String nom, String description, boolean est_realise){
            this.id_tache = id_tache;
            this.nom = nom;
            this.description = description;
            this.est_realise = est_realise;
        }

        public void createTask(Connection maConnection) throws SQLException {
            PreparedStatement requetePrepare = maConnection.prepareStatement("INSERT INTO tache VALUES (?,?,?)");
            requetePrepare.setString(1, this.nom);
            requetePrepare.setString(2, this.description);
            requetePrepare.setBoolean(3, this.est_realise);
            requetePrepare.executeUpdate();
        }

        public void updateTask(Connection maConnection) throws SQLException {
            PreparedStatement requetePrepare = maConnection.prepareStatement("UPDATE tache SET (?,?,?) WHERE id_tache = ?");
            requetePrepare.setString(1,this.nom);
            requetePrepare.setString(2,this.description);
            requetePrepare.setBoolean(3, est_realise);
            requetePrepare.setInt(4, id_tache);
            requetePrepare.executeUpdate();
        }

        public void deleteTask(Connection maConnection) throws SQLException{
            PreparedStatement requetePrepare = maConnection.prepareStatement("DELETE FROM tache WHERE id_tache = ?");
            requetePrepare.setInt(1, this.id_tache);
            requetePrepare.executeUpdate();
        }

        public void validTask(Connection maConnection) throws SQLException{
            PreparedStatement requetePrepare = maConnection.prepareStatement("UPDATE tache SET est_realise = ? WHERE id_tache = ?");
            requetePrepare.setBoolean(1, this.est_realise);
            requetePrepare.setInt(2, this.id_tache);
            requetePrepare.executeUpdate();
        }
    }
