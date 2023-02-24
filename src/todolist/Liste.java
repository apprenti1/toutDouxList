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

        public Liste (int id_liste ,String nom , String description){
            this.id_liste = id_liste;
            this.nom = nom ;
            this.description = description;
        }
public void createList(Bdd maConnection) throws SQLException {
    PreparedStatement requetePrepare = maConnection.getConnection().prepareStatement("INSERT INTO liste VALUES (?,?)");
    requetePrepare.setString(1, this.nom);
    requetePrepare.setString(2, this.description);
    requetePrepare.executeUpdate();


}

public void addtask(Tache taches){
            this.taches.add(taches);
}

public void updateList(Bdd maConnection) throws SQLException{
    PreparedStatement requetePrepare = maConnection.getConnection().prepareStatement("UPDATE liste SET (nom,description)");
    requetePrepare.setString(1,this.nom);
    requetePrepare.setString(2,this.description);
    requetePrepare.executeUpdate();
}
public void deleteList (Bdd maConnection)throws SQLException{
    PreparedStatement requetePrepare = maConnection.getConnection().prepareStatement("DELETE FROM tache WHERE id_tache=?");
    requetePrepare.setInt(1, this.id_liste);
    requetePrepare.executeUpdate();
}
public void afficher(){

}
}

