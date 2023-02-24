package todolist;
import java.sql.*;
import java.util.ArrayList;
import bdd.Bdd;
import java.lang.Object;

public class Type {

    private String libelle;
    private String codeCouleur;

    public Type(String libelle, String codeCouleur) {

        this.libelle = libelle;
        this.codeCouleur = codeCouleur;
    }


    public String getLibelle() {
        return libelle;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public void typeDeTache() {
        System.out.println("Type de tâche pour de futur filtre: " + libelle);
        System.out.println("Code couleur : " + codeCouleur);
    }

    public void modifierType(String nouveauLibelle, String nouveauCodeCouleur) {
        this.libelle = nouveauLibelle;
        this.codeCouleur = nouveauCodeCouleur;

    }

}


