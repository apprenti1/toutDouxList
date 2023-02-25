package application;
import bdd.Bdd;
import todolist.*;
import utilisateur.Utilisateur;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

            Bdd bdd = new Bdd();
            Connection maConnection = bdd.getMaConnection();

            /* Avant de générer des objets Tache comme ici, il faut déjà générer un objet
            Liste et un objet Type, sinon il manque les ref_liste et ref_type aux tâches */
            Tache nouvTache = new Tache(1, "courses","Faire les courses",false);
            nouvTache.createTask(maConnection);
            maConnection.close();

    }
}
