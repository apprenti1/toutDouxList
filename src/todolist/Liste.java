package todolist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Liste {

    private ArrayList<Tache> taches = new ArrayList<Tache>();
    private int id_liste;
    private int ref_utilisateur;
    private String nom;
    private String description;
    private Connection bdd;

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


    public Liste(String nom, String description, int ref_utilisateur, Connection bdd) {
        this.nom = nom;
        this.description = description;
        this.bdd = bdd;
        this.taches = new ArrayList<Tache>();
        this.ref_utilisateur = ref_utilisateur;
    }
    public Liste(String nom, String description, int ref_utilisateur, int id_liste, Connection bdd) {
        this.nom = nom;
        this.description = description;
        this.bdd = bdd;
        this.taches = new ArrayList<Tache>();
        this.id_liste = id_liste;
        this.ref_utilisateur = ref_utilisateur;

    }


    public void createList() {
        try {
            if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
                PreparedStatement requetePrepare = this.bdd.prepareStatement("INSERT INTO liste(nom,description,ref_utilisateur) VALUES (?,?,?)");
                requetePrepare.setString(1, this.nom);
                requetePrepare.setString(2, this.description);
                requetePrepare.setInt(3, this.ref_utilisateur);
                requetePrepare.executeUpdate();
                requetePrepare = this.bdd.prepareStatement("SELECT LAST_INSERT_ID() FROM liste");
                ResultSet res = requetePrepare.executeQuery();
                res.next();
                    this.id_liste = res.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addtask(Tache tache) throws SQLException {
        tache.createTask(this.id_liste);
        this.taches.add(tache);
    }


    public void updateList() throws SQLException {
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = this.bdd.prepareStatement("UPDATE liste SET (nom = ?, description = ? WHERE id_liste = ?");
            requetePrepare.setString(1, this.nom);
            requetePrepare.setString(2, this.description);
            requetePrepare.setInt(3, this.id_liste);
            requetePrepare.executeUpdate();
        }
    }

    public void deleteList() throws SQLException {
        PreparedStatement requetePrepare = this.bdd.prepareStatement("DELETE FROM liste WHERE id_liste=?");
        requetePrepare.setInt(1, this.id_liste);
        requetePrepare.executeUpdate();
    }


    public int getId_liste() {
        return id_liste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


