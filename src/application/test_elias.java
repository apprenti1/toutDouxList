package application;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;

import betterConsoleScanner.ConsoleScanner;
import utilisateur.Utilisateur;
import customData.CustomData;
import todolist.*;

public class test_elias {
    public static void main(String[] args) {


        ConsoleScanner sc = new ConsoleScanner();
        CustomData cdt = new CustomData();
        Connection bdd = cdt.getMaConnection();


        boolean quit = false;
        System.out.println("\n\n");
        while (!quit) {
            try {
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new InternalError("\t" + cdt.color(0) + "\u001B[1m$-------------------UTF-8 not availiable-------------------$\u001B[0m");
            }
            System.out.print("\t" + cdt.color(0) + "\u001B[1m$--------------Bienvenue dans ToutDouxList !!!-------------$\u001B[0m" + cdt.color(1) + "\n\t\tMerci de bien vouloir :\n\t\t- (1) \u001B[4mse connecter\u001B[0m" + cdt.color(1) + "\n\t\t- (2) s'inscrire\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
            if (sc.choixInt(1, 2, 1) == 1) {
                System.out.println("\t" + cdt.color(0) + "\u001B[1m$------------------------Connection------------------------$\u001B[0m");
                String[] form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"E-mail", "Mot de passe"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                Utilisateur user = new Utilisateur(form[0], form[1], bdd);
                if (!user.connect()) {
                    System.out.println("\t" + cdt.color(2) + "\u001B[1m$-----------E-mail ou Mot de passe incorrect !!------------$\u001B[0m");
                } else {
                    while (user != null && user.getId_user() != 0 && user.isConnected()) {
                        System.out.println("\t" + cdt.color(0) + "\u001B[1m$---------------------------Home---------------------------$\u001B[0m");

                        if (user.getListes().size() == 0) {
                            System.out.println(cdt.color(0) + "\t\tAucune liste n'est actuellement créé !");
                            createList(user.getId_user());
                            user.connect();
                        } else {
                            showLists(user);
                            System.out.print(cdt.color(1) + "\n\t\tQue souhaitez vous faire :\n\t\t- (1) \u001B[4mcréer une liste\u001B[0m" + cdt.color(1) + "\n\t\t- (2) gérer une liste\n\t\t- (3) voir mon profil\n\t\t- (4) se déconnecter\n\t\t- (5) quitter" + cdt.color(3) + "\n\t\t\t\u001B[1m>>\u001B[0m\t");
                            switch (sc.choixInt(1, 6, 1)) {
                                case 1:
                                    createList(user.getId_user());
                                    user.connect();
                                    break;
                                case 2:
                                    manageList(user);
                                    break;
                                case 3:
                                    manageProfil(user);
                                    break;
                                case 4:
                                    user = null;
                                    break;
                                case 5:
                                    user = null;
                                    quit = true;
                                    break;
                            }
                        }
                    }
                }
            } else {createProfil();}
        }
    }



    private static Boolean manageProfil(Utilisateur user) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        boolean quit = false;
        while (!quit) {
            System.out.println(cdt.color(0) + "\t$---------------------------Profil--------------------------$");
            showProfil(user);
            System.out.print(cdt.color(1) + "\n\t\tQue souhaitez vous faire :\n\t\t- (1) \u001B[4mrevenir à l'accoueil\u001B[0m" + cdt.color(1) + "\n\t\t- (2) modifier le nom\n\t\t- (3) modifier le prénom\n\t\t- (4) modifier l'E-mail\n\t\t- (5) modifier le Mot de passe\n\t\t- (6) modifier tout\n\t\t- (7) supprimer le compte" + cdt.color(3) + "\n\t\t\t\u001B[1m>>\u001B[0m\t");
            switch (sc.choixInt(1, 7, 1)) {
                case 1:
                    quit = true;
                    break;
                case 2:
                    System.out.print(cdt.color(1) + "\n\t\tNom :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    user.setNom(sc.nextLine());
                    break;
                case 3:
                    System.out.print(cdt.color(1) + "\n\t\tPrénom :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    user.setPrenom(sc.nextLine());
                    break;
                case 4:
                    System.out.print(cdt.color(1) + "\n\t\tE-mail :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    user.setEmail(sc.nextLine());
                    break;
                case 5:
                    System.out.print(cdt.color(1) + "\n\t\tMot de passe :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    user.setMdp(sc.nextLine());
                    break;
                case 6:
                    String[] form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Nom", "Prénom", "E-mail", "Mot de passe"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    user.setNom(form[0]);
                    user.setPrenom(form[1]);
                    user.setEmail(form[2]);
                    user.setMdp(form[3]);
                    break;
                case 7:
                    user.delete();
                    return true;
            }
            user.update();
        }
        return false;
    }
    private static void createProfil(){
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        Connection bdd = cdt.getMaConnection();
        System.out.println("\t" + cdt.color(0) + "\u001B[1m$-----------------------Inscription------------------------$\u001B[0m");
        String confirm = "n";
        String[] form = new String[0];
        while (confirm.toLowerCase().equals("n") || confirm.toLowerCase().equals("non")) {
            form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Nom", "Prénom", "E-mail", "Mot de passe"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
            System.out.print("" + cdt.color(1) + "\n\t\tConfirmez vous la création de l'utilisateur :\n\t\tNom: " + form[0] + " | Prénom: " + form[1] + " | E-mail: " + form[2] + " | Mot de passe: " + form[3] + "\n\t\t- (\u001B[4mOUI/O\u001B[0m" + cdt.color(1) + ")\n\t\t- (NON/N)\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
            confirm = sc.nextLine();
        }
        if (new Utilisateur(form[0], form[1], form[2], form[3], bdd).insert()){
            System.out.println("\t" + cdt.color(1) + "\u001B[1m$---------------------Utilisateur créé---------------------$\u001B[0m");
        }
        else {
            System.out.println("\t" + cdt.color(2) + "\u001B[1m$--------------Erreur E-mail déjà existant !!--------------$\u001B[0m");
        }
    }
    private static void showProfil(Utilisateur user) {
        CustomData cdt = new CustomData();
        System.out.println(cdt.color(1) + "\t\tNom: " + cdt.color(4) + user.getNom() + cdt.color(1) + "\n\t\tPrénom: " + cdt.color(4) + user.getPrenom() + cdt.color(1) + "\n\t\tE-mail: " + cdt.color(4) + user.getEmail() + cdt.color(1) + "\n\t\tMot de passe: " + cdt.color(4) + user.getMdp());
    }


    private static void manageList(Utilisateur user) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        System.out.println("\t" + cdt.color(0) + "\u001B[1m$---------------------Gestion de listes--------------------$\u001B[0m");
        showLists(user);
        System.out.print(cdt.color(1) + "\n\t\tQuelle liste souhaitez vous gérer :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
        int choix = sc.choixInt(1, user.getListes().size(), "\t" + cdt.color(2) + "\u001B[1m$------------------Liste innexistante !!!------------------$\u001B[0m") - 1;
        Liste liste = user.getListes().get(choix);
        boolean quit = false;
        while (!quit) {
            showList(liste);
            System.out.print(cdt.color(1) + "\n\t\tQue souhaitez vous faire :\n\t\t- (1) \u001B[4mrevenir à l'accueil\u001B[0m" + cdt.color(1) + "\n\t\t- (2) cocher/décocher une tâche\n\t\t- (3) créer une tâche\n\t\t- (4) gérer une tache\n\t\t- (5) modifier le titre\n\t\t- (6) modifier la description\n\t\t- (7) modifier tout\n\t\t- (8) supprimer la liste" + cdt.color(3) + "\n\t\t\t\u001B[1m>>\u001B[0m\t");
            switch (sc.choixInt(1, 8, 1)) {
                case 1:
                    quit = true;
                    break;
                case 2:
                    selectTache(liste).interract();
                    break;
                case 3:
                    createTache(liste, user);
                    break;
                case 4:
                    manageTache(liste, user);
                    break;
                case 5:
                    liste.setNom(sc.form(cdt.color(1) + "\n\t\t", new String[]{"Titre"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t")[0]);
                    liste.updateList();
                    break;
                case 6:
                    liste.setNom(sc.form(cdt.color(1) + "\n\t\t", new String[]{"Description"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t")[0]);
                    liste.updateList();
                    break;
                case 7:
                    String[] form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Titre", "Description"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    liste.setNom(form[0]);
                    liste.setDescription(form[1]);
                    liste.updateList();
                    break;
                case 8:
                    liste.deleteList();
                    user.delListe(choix);
                    quit=true;
                    break;
            }
        }
    }
    private static void createList(int userID) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        Connection bdd = cdt.getMaConnection();
        System.out.println("\t" + cdt.color(0) + "\u001B[1m$--------------------Crée ta liste !!!---------------------$\u001B[0m");
        String[] form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Nom", "Description"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
        new Liste(form[0], form[1], userID, bdd).createList();
    }
    private static void showList(Liste liste) {
        CustomData cdt = new CustomData();
        System.out.println("\n\t\t\u001B[1m" + cdt.color(4) + "$------" + liste.getNom() + "------$" + cdt.color(3) + "\n\t\t\t" + liste.getDescription());
        int increment = 1;
        for (Tache tache : liste.getTaches()) {
            System.out.println(cdt.color(1) + "\u001B[1m\t\t[" + (tache.isRealise() ? "X" : " ") + "]|" + increment++ + "|\u001B[38;2;" + tache.getType().getCode_couleur() + "m██" + cdt.color(1) + "|  \t" + tache.getNom() + "\t  |  \t" + tache.getDescription() + "\t  |");
        }
    }
    private static void showLists(Utilisateur user) {
        CustomData cdt = new CustomData();
        System.out.println(cdt.color(1) + "\t\tVos listes :");
        int increment = 1;
        for (Liste liste : user.getListes()) {
            System.out.println(cdt.color(3) + "\t\t|" + increment + "|\t\t    " + liste.getNom() + "    \t|\t    " + liste.getDescription() + "    \t|");
            increment++;
        }
    }


    public static void manageTache(Liste liste, Utilisateur user) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        System.out.println("\t" + cdt.color(0) + "\u001B[1m$--------------------Gerer une tache---------------------$\u001B[0m");
        showList(liste);
        System.out.print(cdt.color(1) + "\n\t\tQuelle tache souhaitez vous gérer :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
        Tache tache = liste.getTaches().get(sc.choixInt(1, liste.getTaches().size(), "\t" + cdt.color(2) + "\u001B[1m$-------------------Tâche innexistante !!!-------------------$\u001B[0m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t") - 1);
        boolean quit = false;
        while (!quit) {
            System.out.println(cdt.color(1) + "\u001B[1m\t\t[" + (tache.isRealise() ? "X" : " ") + "]|\u001B[38;2;" + tache.getType().getCode_couleur() + "m██" + cdt.color(1) + "|  \t" + tache.getNom() + "\t  |  \t" + tache.getDescription() + "\t  |  \t" + tache.getType().getLibelle() + "\t  |");
            System.out.print(cdt.color(1) + "\n\t\tQue souhaitez vous faire :\n\t\t- (1) \u001B[4mretourner à la gestion de la liste\u001B[0m" + cdt.color(1) + "\n\t\t- (2) changer le titre\n\t\t- (3) changer la description\n\t\t- (4) changer le type\n\t\t- (5) supprimer la tâche" + cdt.color(3) + "\n\t\t\t\u001B[1m>>\u001B[0m\t");
            switch (sc.choixInt(1, 8, 1)) {
                case 1:
                    quit=true;
                    break;
                case 2:
                    tache.setNom(sc.form(cdt.color(1) + "\n\t\t", new String[]{"Nom"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t")[0]);
                    tache.updateTask();
                    break;
                case 3:
                    tache.setNom(sc.form(cdt.color(1) + "\n\t\t", new String[]{"Description"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t")[0]);
                    tache.updateTask();
                    break;
                case 4:
                    System.out.print(cdt.color(1) + "\n\t\tQue souhaitez vous faire :\n\t\t- (1) \u001B[4msélectionner un type\u001B[0m" + cdt.color(1) + "\n\t\t- (2) créer un type" + cdt.color(3) + "\n\t\t\t\u001B[1m>>\u001B[0m\t");
                    if (sc.choixInt(1, 2, 1) == 1) {
                        tache.setType(selectType(user, "\t" + cdt.color(2) + "\u001B[1m$-------------------Tâche innexistante !!!-------------------$\u001B[0m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t"));
                    } else {
                        tache.setType(createType(user));
                    }
                    break;
                case 5:
                    tache.deleteTask();
                    quit=true;
                    break;
            }
        }
    }
    private static void createTache(Liste liste, Utilisateur user) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        Connection bdd = cdt.getMaConnection();
        System.out.println("\t" + cdt.color(0) + "\u001B[1m$--------------------Crée ta tache !!!---------------------$\u001B[0m");
        String[] form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Titre", "Description"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
        Tache tache;
        if (user.getTypes().size()>0) {
            System.out.print(cdt.color(1) + "\n\t\tQue souhaitez vous faire :\n\t\t- (1) \u001B[4msélectionner un type\u001B[0m" + cdt.color(1) + "\n\t\t- (2) créer un type" + cdt.color(3) + "\n\t\t\t\u001B[1m>>\u001B[0m\t");
            if (sc.choixInt(1, 2, 1) == 1) {
                tache = new Tache(form[0], form[1], bdd, selectType(user, "\t" + cdt.color(2) + "\u001B[1m$-------------------Tâche innexistante !!!-------------------$\u001B[0m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t"));
            } else {
                tache = new Tache(form[0], form[1], bdd, createType(user));
            }
        }
        else {
            tache = new Tache(form[0], form[1], bdd, createType(user));
        }
        tache.createTask(liste.getId_liste());
        liste.addtask(tache);
    }
    private static Tache selectTache(Liste liste) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        showList(liste);
        System.out.print(cdt.color(1)+"\n\t\tVeuillez sélectionner une tâche :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
        return liste.getTaches().get(sc.choixInt(1, liste.getTaches().size(), "\t" + cdt.color(2) + "\u001B[1m$-------------------Tâche innexistante !!!-------------------$\u001B[0m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t") - 1);
    }



    private static void manageType(Utilisateur user){
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        System.out.println("\t" + cdt.color(0) + "\u001B[1m$---------------------Gestion des types--------------------$\u001B[0m");
        showTypes(user);
        System.out.print(cdt.color(1) + "\n\t\tQuelle type souhaitez vous gérer :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
        int choix = sc.choixInt(1, user.getListes().size(), "\t" + cdt.color(2) + "\u001B[1m$-------------------Type innexistante !!!------------------$\u001B[0m") - 1;
        Type type = user.getTypes().get(choix);
        boolean quit = false;
        while (!quit) {
            System.out.println(cdt.color(1)+type.getLibelle()+" | "+"\u001B[38;2;"+type.getCode_couleur()+"m██");
            System.out.print(cdt.color(1) + "\n\t\tQue souhaitez vous faire :\n\t\t- (1) \u001B[4mrevenir à l'accueil\u001B[0m" + cdt.color(1) + "\n\t\t- (2) modifier la description\n\t\t- (3) modifier la couleur\n\t\t- (4) supprimer le type" + cdt.color(3) + "\n\t\t\t\u001B[1m>>\u001B[0m\t");
            switch (sc.choixInt(1, 4, 1)) {
                case 1:
                    quit = true;
                    break;
                case 2:
                    type.setLibelle(sc.form(cdt.color(1) + "\n\t\t", new String[]{"Description"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t")[0]);
                    type.updateType();
                    break;
                case 3:
                    String[] form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Couleur R", "Couleur G", "Couleur B"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    type.setCode_couleur(form[0] + ";" + form[1] + ";" + form[2]);
                    break;
                case 4:
                    form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Libelle", "Couleur R", "Couleur G", "Couleur B"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
                    type.setLibelle(form[0]);
                    type.setCode_couleur(form[1] + ";" + form[2] + ";" + form[3]);
                    break;
                case 5:
                    type.deleteType();
                    user.delType(choix);
                    quit=true;
                    break;
            }
            type.updateType();
        }
    }
    private static Type createType(Utilisateur user) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        Connection bdd = cdt.getMaConnection();
        System.out.println("\t" + cdt.color(0) + "\u001B[1m$--------------------Crée ta tâche !!!---------------------$\u001B[0m");
        String[] form = sc.form(cdt.color(1) + "\n\t\t", new String[]{"Libelle", "Couleur R", "Couleur G", "Couleur B"}, " :\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t", "\t" + cdt.color(2) + "\u001B[1m$-----------------/!\\ format incorrect /!\\-----------------$\n\t\tLes charactères suivants sont à ne pas utiliser :\n\t\t\t\t('\"',''',' ','(',')')\n\t$----------------------------------------------------------$\u001B[1m\n\t\t\t" + cdt.color(3) + "\u001B[1m>>\u001B[0m\t");
        Type type = new Type(form[0], form[1] + ";" + form[2] + ";" + form[3], user.getId_user(), bdd);
        type.createType();
        return type;
    }
    private static void showTypes(Utilisateur user) {
        CustomData cdt = new CustomData();
        System.out.println(cdt.color(3) + "\n\t\tVos types :");
        ArrayList<Type> types = user.getTypes();
        int increment = 1;
        for (Type type : types) {
            System.out.println(cdt.color(1) + "\n\t\t|" + increment + "|\u001B[38;2;" + type.getCode_couleur() + "m██" + cdt.color(1) + "|  \t" + type.getLibelle() + "\t  |");
        }
    }
    private static Type selectType(Utilisateur user, String text) {
        CustomData cdt = new CustomData();
        ConsoleScanner sc = new ConsoleScanner();
        showTypes(user);
        return user.getTypes().get(sc.choixInt(1, user.getTypes().size(), text) - 1);
    }


}


