package utilisateur;
import bdd.Bdd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {
    private int id_user;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String oldnom;
    private String oldprenom;
    private String oldemail;
    private String oldmdp;
    private Bdd bdd;


    private boolean connected = false;

    public Utilisateur (String email, String mdp, Bdd bdd){
        this.email = email;
        this.mdp = mdp;
        this.bdd = bdd;
    }
    public Utilisateur (String nom, String prenom, String email, String mdp, Bdd bdd){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.bdd = bdd;
    }

    public boolean connect() throws SQLException {
        if (this.verifStringFormat(this.email)&&this.verifStringFormat(this.mdp)){
            PreparedStatement req = this.bdd.getMaConnection().prepareStatement("SELECT id_utilisateur, nom, prenom from utilisateur where login = ? and mdp = ? ;");
            req.setString(1, this.email);
            req.setString(2, this.mdp);

            ResultSet rs = req.executeQuery();
            if (rs.next()){
                this.id_user = rs.getInt("id_utilisateur");
                this.nom = rs.getString("nom");
                this.prenom = rs.getString("prenom");
                this.oldnom = this.nom;
                this.oldprenom = this.prenom;
                this.oldemail = this.email;
                this.oldmdp = this.mdp;
                this.connected = true;
                return true;
            }else{return false;}
        }else{return false;}
    }

    public boolean update() throws SQLException {
        if (this.connected && this.verifStringFormat(this.nom)&&this.verifStringFormat(this.prenom)&&this.verifStringFormat(this.email)&&this.verifStringFormat(this.mdp)){
            try{
                if (this.email != this.oldemail && this.verifExistEmail()){
                    return false;
                }
                PreparedStatement req = this.bdd.getMaConnection().prepareStatement("UPDATE utilisateur SET nom = ?, prenom = ?, login = ?, mdp = ? WHERE id_utilisateur = ?");
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
            PreparedStatement req = this.bdd.getMaConnection().prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur = ?");
            req.setInt(1, this.id_user);
            req.executeUpdate();
            this.connected = false;
        }
    }

    public boolean insert() throws SQLException {
        if (!this.connected && !this.verifExistEmail() && this.verifStringFormat(this.nom)&&this.verifStringFormat(this.prenom)&&this.verifStringFormat(this.email)&&this.verifStringFormat(this.mdp)){
            try {
            PreparedStatement req = this.bdd.getMaConnection().prepareStatement("INSERT INTO utilisateur (nom, prenom, login, mdp) VALUES (?,?,?,?) ;");
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
    private boolean verifExistEmail() throws SQLException {
        PreparedStatement req = this.bdd.getMaConnection().prepareStatement("SELECT count(id_utilisateur) from utilisateur where login = ?;");
        req.setString(1, this.email);
        ResultSet rs = req.executeQuery();
        rs.next();
        if (rs.getInt(1)!=0){return true;}else{return false;}
    }
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getMdp() {return mdp;}
    public void setMdp(String mdp) {this.mdp = mdp;}
    public boolean isConnected() {return connected;}
}

