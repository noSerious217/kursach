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
    private static Connection connection = null;

    public static void init()
    {
        setAttributes("web","postgres","123456");
    }

    public static void setAttributes(String db, String name, String pass)
    {
        dbname = db;
        login = name;
        password = pass;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,login,password);
            connected=true;
        }
        catch (Exception e)
        {
            connected = false;
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection==null) connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,login,password);
        return connection;
    }

    public static boolean Connected()
    {
        return connected;
    }

    public static void Close() throws SQLException {
        connection.close();
    }
}
