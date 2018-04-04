package Core;

import org.postgresql.core.EncodingPredictor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String dbname;
    private static String login;
    private static String password;
    private static boolean connected;

    public static void setAttributes(String db, String name, String pass)
    {
        dbname = db;
        login = name;
        password = pass;
        try
        {
            Connection c = DriverManager.getConnection("jdbc:postgresql:"+dbname,login,password);
            connected=true;
        }
        catch (Exception e)
        {
            connected = false;
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:postgresql:"+dbname,login,password);
        return c;
    }

    public static boolean Connected()
    {
        return connected;
    }
}
