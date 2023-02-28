package todolist;
import bdd.Bdd;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tache {

    private int id_tache;
    private String nom;
    private String description;
    private boolean est_realise;
    private Bdd bdd;

    public Tache(int id_tache, String nom, String description, boolean est_realise, Bdd bdd) {
        this.id_tache = id_tache;
        this.nom = nom;
        this.description = description;
        this.est_realise = est_realise;
        this.bdd = bdd;
    }

    private boolean verifStringFormat(String text) {
        if ((text.indexOf('"') +
                text.indexOf("'") +
                text.indexOf('\n') +
                text.indexOf('\r') +
                text.indexOf(' ') +
                text.indexOf('\t') +
                text.indexOf('(') +
                text.indexOf(')')) == -8) {
            return true;
        } else {
            return false;
        }
    }

    public void createTask() throws SQLException {
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("INSERT INTO tache VALUES (?,?,?)");
            requetePrepare.setString(1, nom);
            requetePrepare.setString(2, description);
            requetePrepare.setBoolean(3, est_realise);
            requetePrepare.executeUpdate();
        }
    }

    public void updateTask() throws SQLException {
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("UPDATE tache SET (nom = ?, description = ?, est_realise = ? WHERE id_tache = ?");
            requetePrepare.setString(1, this.nom);
            requetePrepare.setString(2, this.description);
            requetePrepare.setBoolean(3, this.est_realise);
            requetePrepare.setInt(4, this.id_tache);
            requetePrepare.executeUpdate();
        }
    }

    public void deleteTask() throws SQLException {
        PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("DELETE FROM tache WHERE id_tache = ?");
        requetePrepare.setInt(1, this.id_tache);
        requetePrepare.executeUpdate();
    }

    public void validTask() throws SQLException {
        PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("UPDATE tache SET est_realise = ? WHERE id_tache = ?");
        requetePrepare.setBoolean(1, this.est_realise);
        requetePrepare.setInt(2, this.id_tache);
        requetePrepare.executeUpdate();
    }
}
