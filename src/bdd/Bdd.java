package bdd;
import java.sql.*;

public class Bdd {

    private Connection maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toutdouxlist","root","");

    public Bdd() throws SQLException {
    }

    public Connection getMaConnection() {
        return maConnection;
    }

    /* public Connection getConnection(){
        return this.maConnection;
    } */
}
