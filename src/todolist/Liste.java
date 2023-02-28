package todolist;
import bdd.Bdd;
import todolist.Tache;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Liste {

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


    public Liste(int id_liste, String nom, String description) {
        this.id_liste = id_liste;
        this.nom = nom;
        this.description = description;
        this.bdd = bdd;
    }

    public void createList() throws SQLException {
        if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
            PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("INSERT INTO liste VALUES (?,?,?)");
            requetePrepare.setString(1, nom);
            requetePrepare.setString(2, description);
            requetePrepare.executeUpdate();
        }
    }

        public void addtask (Tache taches){
            this.taches.add(taches);
        }

        public void updateList () throws SQLException {
            if (this.verifStringFormat(this.nom) && this.verifStringFormat(this.description)) {
                PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("UPDATE liste SET (nom = ?, description = ? WHERE id_liste = ?");
                requetePrepare.setString(1, this.nom);
                requetePrepare.setString(2, this.description);
                requetePrepare.setInt(3, this.id_liste);
                requetePrepare.executeUpdate();
            }
        }

            public void deleteList () throws SQLException {
                PreparedStatement requetePrepare = this.bdd.getMaConnection().prepareStatement("DELETE FROM tache WHERE id_tache=?");
                requetePrepare.setInt(1, this.id_liste);
                requetePrepare.executeUpdate();
            }




    }


