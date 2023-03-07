package bdd;
import java.sql.*;

public class Bdd {

    private Connection maConnection;

    {
        try {
            maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toutdouxlist","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Connection getMaConnection() {
        return maConnection;
    }


}
