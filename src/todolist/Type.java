package todolist;

public class Type {
    private int idType;
    private String libelle;
    private String codeCouleur;

    public Type(int idType, String libelle, String codeCouleur) {
        this.idType = idType;
        this.libelle = libelle;
        this.codeCouleur = codeCouleur;
    }

    public int getIdType() {
        return idType;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setIdType(int idType) {
        this.idType = idType;
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
}



