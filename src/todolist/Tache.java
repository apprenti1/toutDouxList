package todolist;
import bdd.Bdd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tache {

        private int id_tache;
        private String nom;
        private String description;
        private String type_tache;
        private boolean est_realise;


        public Tache (int id_tache, String nom, String description, String type_tache, boolean est_realise){
            this.id_tache = id_tache;
            this.nom = nom;
            this.description = description;
            this.type_tache = type_tache;
            this.est_realise = est_realise;
        }
        public void createTask(Connection maConnection) throws SQLException {
            PreparedStatement requetePrepare = maConnection.prepareStatement("INSERT INTO tache VALUES (?,?,?,?)");
            requetePrepare.setString(1, this.nom);
            requetePrepare.setString(2, this.description);
            requetePrepare.setString(3, this.type_tache);
            requetePrepare.setBoolean(4, this.est_realise);
            requetePrepare.executeUpdate();
        }

        public void updateTask(Connection maConnection) throws SQLException {
            PreparedStatement requetePrepare = maConnection.prepareStatement("UPDATE tache SET (?,?,?,?)");
            requetePrepare.setString(1,this.nom);
            requetePrepare.setString(2,this.description);
            requetePrepare.setString(3, this.type_tache);
            requetePrepare.setBoolean(4, est_realise);
            requetePrepare.executeUpdate();
        }

        public void deleteTask(Connection maConnection) throws SQLException{
            PreparedStatement requetePrepare = maConnection.prepareStatement("DELETE FROM tache WHERE ?");
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
