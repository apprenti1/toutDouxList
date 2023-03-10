/*
package application;
import bdd.Bdd;
import todolist.*;
import utilisateur.Utilisateur;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Bdd bdd = new Bdd();
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


            Utilisateur user = new Utilisateur(nom, prenom, email, mdp, bdd);
            if (user.insert()) {
                System.out.println("Vous êtes maintenant inscrit !");
            } else {
                System.out.println("Problème");
            }


        } else if (rep == 2) {
            System.out.println("Entrez votre email : ");
            String email = sc.nextLine();
            System.out.println("Entrez votre mdp : ");
            String mdp = sc.nextLine();


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

            } else if (choix == 2) {
                System.out.println("Saisir l'id de liste à gérer : ");
                int id_liste = sc.nextInt();
                System.out.println("souhaitez-vous modifier ou supprimer une liste ?");
                String rep_choix =sc.nextLine();
                if (rep_choix=="modifier") {
                    System.out.print("Saisir le nom");
                    String nom = sc.nextLine();
                    System.out.print("Saisir la description");
                    String description = sc.nextLine();

                    Liste liste = new Liste(id_liste,nom, description, bdd);
                    liste.updateList();
                    liste.setNom(nom);
                    liste.setDescription(description);

                    System.out.println(liste.getNom() + liste.getDescription());

                }
                if(rep_choix=="supprimer") {
                    Liste liste = new Liste(id_liste,bdd);
                    liste.deleteList();


                }

            }
        }
    }
}

 */