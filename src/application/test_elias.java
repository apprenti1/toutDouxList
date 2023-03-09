package application;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import betterConsoleScanner.ConsoleScanner;
import utilisateur.Utilisateur;
import bdd.Bdd;

public class test_elias {
    public static void main (String[]args) {
        boolean quit = false;
        ConsoleScanner sc = new ConsoleScanner();
        Connection bdd = new Bdd().getMaConnection();
        System.out.print("\t\u001B[38;2;232;255;109m\u001B[1m$--------------Bienvenue dans ToutDouxList !!!-------------$\u001B[0m");
        while (!quit) {
            try {
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
            }catch(UnsupportedEncodingException e){
                throw new InternalError("\t\u001B[38;2;232;255;109m\u001B[1m$-------------------UTF-8 not availiable-------------------$\u001B[0m");
            }

                System.out.print("\u001B[38;2;43;177;161m\n\t\tMerci de bien vouloir :\n\t\t- (1) \u001B[4mse connecter\u001B[0m\u001B[38;2;43;177;161m\n\t\t- (2) s'inscrire\n\t\t\t\u001B[38;2;66;216;217m\u001B[1m>>\u001B[0m\t");
                if (sc.choixInt(1, 2, 1, "\t\u001B[38;2;225;104;82m\u001B[1m$-------------Ce choix n'est pas disponible !!-------------$\u001B[1m\n\t\t\t\u001B[38;2;66;216;217m\u001B[1m>>\u001B[0m\t") == 1) {
                    System.out.println("\t\u001B[38;2;232;255;109m\u001B[1m$------------------------Connection------------------------$\u001B[0m");
                    String[] form = sc.form("\u001B[38;2;43;177;161m\n\t\t", new String[]{"E-mail", "Mot de passe"}, " :\n\t\t\t\u001B[38;2;66;216;217m\u001B[1m>>\u001B[0m\t", "\t\u001B[38;2;225;104;82m\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t\u001B[38;2;66;216;217m\u001B[1m>>\u001B[0m\t");
                    Utilisateur user = new Utilisateur(form[0], form[1], bdd);
                    if (!user.connect()) {
                        System.out.println("\t\u001B[38;2;225;104;82m\u001B[1m$-----------E-mail ou Mot de passe incorrect !!------------$\u001B[0m");
                    } else {
                        while (user.getId_user() != 0) {
                            System.out.println("\t\u001B[38;2;225;104;82m\u001B[1m$---------------------------Home---------------------------$\u001B[0m");

                        }
                    }
                }
                else {
                    System.out.println("\t\u001B[38;2;232;255;109m\u001B[1m$-----------------------Inscription------------------------$\u001B[0m");
                    String confirm = "n";
                    String[] form = new String[0];
                    while (confirm.toLowerCase().equals("n")||confirm.toLowerCase().equals("non")) {
                        form = sc.form("\u001B[38;2;43;177;161m\n\t\t", new String[]{"Nom", "Prénom", "E-mail", "Mot de passe"}, " :\n\t\t\t\u001B[38;2;66;216;217m\u001B[1m>>\u001B[0m\t", "\t\u001B[38;2;225;104;82m\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t\u001B[38;2;66;216;217m\u001B[1m>>\u001B[0m\t");
                        System.out.print("\u001B[38;2;43;177;161m\n\t\tConfirmez vous la création de l'utilisateur :\n\t\tNom: "+form[0]+" | Prénom: "+form[1]+" | E-mail: "+form[2]+" | Mot de passe: "+form[3]+"\n\t\t- (\u001B[4mOUI/O\u001B[0m\u001B[38;2;43;177;161m)\n\t\t- (NON/N)\n\t\t\t\u001B[38;2;66;216;217m\u001B[1m>>\u001B[0m\t");
                        confirm = sc.nextLine();
                    }
                    new Utilisateur(form[0], form[1], form[2], form[3], bdd).insert();
                }
        }
    }
}
