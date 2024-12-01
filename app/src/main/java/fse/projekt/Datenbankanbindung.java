package fse.projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datenbankanbindung {
    private static final String url = "jdbc:mariadb://localhost:3306/inventarverwaltung";
    private static final String nutzer = "PEN";
    private static final String passwort = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,nutzer,passwort);
    }
}
