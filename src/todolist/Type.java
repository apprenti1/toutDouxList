package todolist;
import java.sql.*;
import customData.CustomData;

public class Type {

    private int id_type;
    private String libelle;
    private String code_couleur;
    private Connection bdd;

    public Type(int id_type, Connection bdd) {
        this.id_type = id_type;
        this.bdd = bdd;
        try {
            PreparedStatement req = bdd.prepareStatement("SELECT libelle,code_couleur from type where id_type = ?");
            req.setInt(1,id_type);
            ResultSet res = req.executeQuery();
            res.next();
            this.libelle = res.getString("libelle");
            this.code_couleur = res.getString("code_couleur");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Type(String libelle, String code_couleur, Connection bdd) {
        this.libelle = libelle;
        this.code_couleur = code_couleur;
        this.bdd = bdd;
    }

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

    public void createType() throws SQLException {
        if (this.verifStringFormat(this.libelle) && this.verifStringFormat(this.code_couleur)){
            PreparedStatement requetePrepare = this.bdd.prepareStatement("INSERT INTO type (libelle, code_couleur) VALUES (?,?)");
            requetePrepare.setString(1, this.libelle);
            requetePrepare.setString(2, this.code_couleur);
            requetePrepare.executeUpdate();
        }
    }

    public void updateType() throws SQLException {
        if (this.verifStringFormat(this.libelle) && this.verifStringFormat(this.code_couleur)){
            PreparedStatement requetePrepare = this.bdd.prepareStatement("UPDATE type SET libelle = ?, code_couleur = ? WHERE id_type = ?");
            requetePrepare.setString(1, this.libelle);
            requetePrepare.setString(2, this.code_couleur);
            requetePrepare.setInt(3, this.id_type);
            requetePrepare.executeUpdate();
        }
    }

    public void deleteType() throws SQLException {
        PreparedStatement requetePrepare = this.bdd.prepareStatement("DELETE FROM type WHERE id_type = ?");
        requetePrepare.setInt(1, this.id_type);
        requetePrepare.executeUpdate();
    }

    public int getId_type() { return id_type; }
    public void setId_type(int id_type) { this.id_type = id_type; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public String getCode_couleur() { return code_couleur; }
    public void setCode_couleur(String code_couleur) { this.code_couleur = code_couleur; }
    public Connection getBdd() { return bdd; }
    public void setBdd(Connection bdd) { this.bdd = bdd; }
}
