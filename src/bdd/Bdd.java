package bdd;
import java.sql.*;

public class Bdd {
    public static Connection getConnection() throws SQLException {
        Connection maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toutdouxlist","root","");
    }
}
