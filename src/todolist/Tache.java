package todolist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tache {

    private int id_tache;
    private String nom;
    private String description;
    private boolean realise;
    private Connection bdd;
    private Type type;

    public Tache(int id_tache, String nom, String description, boolean est_realise, Connection bdd, Type type) {
        this.id_tache = id_tache;
        this.nom = nom;
        this.description = description;
        this.realise = est_realise;
        this.bdd = bdd;
        this.type = type;
    }

    public Tache (String nom, String description, Connection bdd, Type type) {
        this.nom = nom;
        this.description = description;
        this.bdd = bdd;
        this.type = type;
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

    public void createTask(int ref_liste){
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = null;
            try {
                requetePrepare = this.bdd.prepareStatement("INSERT INTO tache (nom, description, est_realise, ref_liste, ref_type) VALUES (?,?,?,?,?)");
                requetePrepare.setString(1, nom);
                requetePrepare.setString(2, description);
                requetePrepare.setBoolean(3, realise);
                requetePrepare.setInt(4, ref_liste);
                requetePrepare.setInt(5, type.getId_type());
                requetePrepare.executeUpdate();
                requetePrepare = this.bdd.prepareStatement("SELECT LAST_INSERT_ID() FROM tache");
                ResultSet res = requetePrepare.executeQuery();
                res.next();
                this.id_tache = res.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateTask(){
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = null;
            try {
                requetePrepare = this.bdd.prepareStatement("UPDATE tache SET (nom = ?, description = ?, est_realise = ? WHERE id_tache = ?");
            requetePrepare.setString(1, this.nom);
            requetePrepare.setString(2, this.description);
            requetePrepare.setBoolean(3, this.realise);
            requetePrepare.setInt(4, this.id_tache);
            requetePrepare.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteTask(){
        PreparedStatement requetePrepare = null;
        try {
            requetePrepare = this.bdd.prepareStatement("DELETE FROM tache WHERE id_tache = ?");
        requetePrepare.setInt(1, this.id_tache);
        requetePrepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void validTask() throws SQLException {
        PreparedStatement requetePrepare = this.bdd.prepareStatement("UPDATE tache SET est_realise = ? WHERE id_tache = ?");
        requetePrepare.setBoolean(1, this.realise);
        requetePrepare.setInt(2, this.id_tache);
        requetePrepare.executeUpdate();
    }
    public void interract(){
        this.realise = !this.realise;
    }

    public int getId_tache() { return id_tache; }
    public void setId_tache(int id_tache) { this.id_tache = id_tache; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isRealise() { return realise; }
    public void setRealise(boolean est_realise) { this.realise = est_realise; }
    public Connection getBdd() { return bdd; }
    public void setBdd(Connection bdd) { this.bdd = bdd; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
}
