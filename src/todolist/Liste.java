package todolist;
import bdd.Bdd;
import bdd.VerifFormat;
import oracle.jdbc.internal.XSCacheOutput;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Liste extends VerifFormat {

    private ArrayList<Tache> taches = new ArrayList<Tache>();
    private int id_liste;
    private String nom;
    private String description;
    private Bdd bdd;

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


    public Liste(String nom, String description, Bdd bdd) {
        this.nom = nom;
        this.description = description;
        this.bdd = bdd;
        this.taches = new ArrayList<Tache>();

    }

    public void createList() throws SQLException {
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("INSERT INTO liste(nom,description) VALUES (?,?)");
            requetePrepare.setString(1, this.nom);
            requetePrepare.setString(2, this.description);
            requetePrepare.executeUpdate();
        }
    }

    public void addtask(Tache taches) {
        this.taches.add(taches);
    }

    public void updateList() throws SQLException {
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("UPDATE liste SET (nom = ?, description = ? WHERE id_liste = ?");
            requetePrepare.setString(1, this.nom);
            requetePrepare.setString(2, this.description);
            requetePrepare.setInt(3, this.id_liste);
            requetePrepare.executeUpdate();
        }
    }

    public void deleteList() throws SQLException {
        PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("DELETE FROM tache WHERE id_liste=?");
        requetePrepare.setInt(1, this.id_liste);
        requetePrepare.executeUpdate();
    }


    public int getId_liste() {
        return id_liste;
    }

    public void setId_liste(int id_liste) {
        this.id_liste = id_liste;
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

    public Bdd getBdd() {
        return bdd;
    }

    public void setBdd(Bdd bdd) {
        this.bdd = bdd;
    }
}


