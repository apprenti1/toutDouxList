package utilisateur;
import customData.CustomData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import todolist.Liste;

public class Utilisateur {

    private int id_user = 0;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String oldemail;
    private Connection bdd;


    private ArrayList<Liste> listes;


    private boolean connected = false;

    public Utilisateur (String email, String mdp, Connection bdd){
        this.email = email;
        this.mdp = mdp;
        this.bdd = bdd;
    }
    public Utilisateur (String nom, String prenom, String email, String mdp, Connection bdd){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.bdd = bdd;
    }

    public boolean connect(){
            try {
                if (this.verifStringFormat(this.email)&&this.verifStringFormat(this.mdp)){
                    PreparedStatement req = null;
                    req = this.bdd.prepareStatement("SELECT id_utilisateur, nom, prenom from utilisateur where login = ? and mdp = ? ;");
                    req.setString(1, this.email);
                    req.setString(2, this.mdp);

                    ResultSet rs = req.executeQuery();
                    if (rs.next()){
                        this.id_user = rs.getInt("id_utilisateur");
                        this.nom = rs.getString("nom");
                        this.prenom = rs.getString("prenom");
                        this.oldemail = this.email;
                        this.connected = true;
                        req = this.bdd.prepareStatement("SELECT nom,description,id_liste from liste where ref_utilisateur = ? ;");
                        req.setInt(1, this.id_user);
                        rs = req.executeQuery();
                        this.listes = new ArrayList<Liste>();
                        while (rs.next()){
                            this.listes.add(new Liste(rs.getString("nom"), rs.getString("description"), rs.getInt("id_liste"), this.id_user, this.bdd));
                        }
                        return true;
                    }else{return false;}
                }else{return false;}
            } catch (SQLException e) {
                System.out.println("SQL Error : "+e.getMessage()+"\nCode : "+e.getErrorCode()+"State : "+e.getSQLState());
                return false;
            }
    }

    public boolean update() throws SQLException {
        if (this.connected && this.verifStringFormat(this.nom)&&this.verifStringFormat(this.prenom)&&this.verifStringFormat(this.email)&&this.verifStringFormat(this.mdp)){
            try{
                if (this.email != this.oldemail && this.verifExistEmail()){
                    return false;
                }
                PreparedStatement req = this.bdd.prepareStatement("UPDATE utilisateur SET nom = ?, prenom = ?, login = ?, mdp = ? WHERE id_utilisateur = ?");
                req.setString(1, this.nom);
                req.setString(2, this.prenom);
                req.setString(3, this.email);
                req.setString(4, this.mdp);
                req.setInt(5, this.id_user);
                req.executeUpdate();
                this.connect();
                return true;
            }
            catch(Exception e){return false;}
        }
        else{return false;}
    }

    public void delete() throws SQLException {
        if (this.connected){
            PreparedStatement req = this.bdd.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur = ?");
            req.setInt(1, this.id_user);
            req.executeUpdate();
            this.connected = false;
        }
    }

    public boolean insert(){
        if (!this.connected && !this.verifExistEmail() && this.verifStringFormat(this.nom)&&this.verifStringFormat(this.prenom)&&this.verifStringFormat(this.email)&&this.verifStringFormat(this.mdp)){
            try {
            PreparedStatement req = this.bdd.prepareStatement("INSERT INTO utilisateur (nom, prenom, login, mdp) VALUES (?,?,?,?) ;");
            req.setString(1, this.nom);
            req.setString(2, this.prenom);
            req.setString(3, this.email);
            req.setString(4, this.mdp);
            req.executeUpdate();
            return true;
            }catch(Exception e){return false;}
        }
        else{return false;}
    }

    private boolean verifStringFormat(String text){
        if ((   text.indexOf('"')+
                text.indexOf("'")+
                text.indexOf('\n')+
                text.indexOf('\r')+
                text.indexOf(' ')+
                text.indexOf('\t')+
                text.indexOf('(')+
                text.indexOf(')'))==-8){
            return true;
        } else{ return false;}}
    private boolean verifExistEmail(){
        PreparedStatement req = null;
        try {
            req = this.bdd.prepareStatement("SELECT count(id_utilisateur) from utilisateur where login = ?;");
            req.setString(1, this.email);
            ResultSet rs = req.executeQuery();
            rs.next();
            if (rs.getInt(1)!=0){return true;}else{return false;}
        } catch (SQLException e) {
            return false;
        }
    }
    public int getId_user(){return id_user;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getMdp() {return mdp;}
    public void setMdp(String mdp) {this.mdp = mdp;}
    public boolean isConnected() {return connected;}
    public ArrayList<Liste> getListes() {return listes;}

}

