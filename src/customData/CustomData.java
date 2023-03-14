package customData;
import java.sql.*;
import java.util.ArrayList;

public class CustomData {

    private Connection maConnection;


    private String[] colors;

    public CustomData() {

        this.colors = new String[]{"232;255;109","43;177;161","225;104;82","66;216;217","25;186;85"};

        try {
            this.maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toutdouxlist","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Connection getMaConnection() {return maConnection;}
    public String color(int colorID) {return "\u001B[38;2;"+this.colors[colorID]+"m";}




}
