package application;
import bdd.Bdd;
import todolist.*;
import utilisateur.Utilisateur;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Bdd bdd = new Bdd();
        System.out.println("Entrez votre email : ");
        String email = sc.nextLine();
        System.out.println("Entrez votre mdp : ");
        String mdp = sc.nextLine();

        Utilisateur user = new Utilisateur(email, mdp, bdd);




    }
}
