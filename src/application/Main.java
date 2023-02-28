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

        System.out.println("Bienvenue sur la ToutDouxList !");

        System.out.println("S'inscrire (1) / Se connecter (2) :");
        int rep = sc.nextInt();
        sc.nextLine();

        if (rep == 1) {
            System.out.println("Entrez votre nom : ");
            String nom = sc.nextLine();
            System.out.println("Entrez votre prenom : ");
            String prenom = sc.nextLine();
            System.out.println("Entrez votre email : ");
            String email = sc.nextLine();
            System.out.println("Entrez votre mdp : ");
            String mdp = sc.nextLine();

            Bdd bdd = new Bdd();
            Utilisateur user = new Utilisateur(nom, prenom, email, mdp, bdd);
            if (user.insert()) {
                System.out.println("Vous êtes maintenant inscrit !");
            } else {
                System.out.println("Vous etes déjà inscrit.");
            }


        } else if (rep == 2) {
            System.out.println("Entrez votre email : ");
            String email = sc.nextLine();
            System.out.println("Entrez votre mdp : ");
            String mdp = sc.nextLine();

            Bdd bdd = new Bdd();
            Utilisateur user = new Utilisateur(email, mdp, bdd);
            if (user.connect()) {
                System.out.println("Vous etes maintenant connecté");
            } else {
                System.out.println("Vous n'etes pas connecté.");
            }

            System.out.println("Souhaitez vous : Créer une liste (1) ou gérer une liste (2) ?");
            int choix = sc.nextInt();
            sc.nextLine();

            if (choix == 1) {
                System.out.println("Saisir un nom de liste :");
                String nom = sc.nextLine();
                System.out.println("Saisir une description de liste :");
                String description = sc.nextLine();

                Liste liste = new Liste(nom, description, bdd);
                liste.createList();
                System.out.println("Votre liste " + nom + " a été créée.");
            }
        }
    }
}