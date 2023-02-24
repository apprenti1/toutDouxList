package todolist;
import bdd.Bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tache {

        private String nom;
        private String description;
        private boolean est_realise;

        public Tache (String nom, String description, boolean est_realise){
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
            PreparedStatement requetePrepare = maConnection.prepareStatement("UPDATE tache SET (nom,description,est_realise)");
            requetePrepare.setString(1,this.nom);
            requetePrepare.setString(2,this.description);
            requetePrepare.setBoolean(3, est_realise);
            requetePrepare.executeUpdate();
        }

        public void deleteTask(Connection maConnection,) throws SQLException{
            PreparedStatement requetePrepare = maConnection.prepareStatement("DELETE FROM tache WHERE ");

        }

        public void validTask(){

        }

    }

