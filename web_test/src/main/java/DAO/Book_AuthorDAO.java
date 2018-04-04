package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book_AuthorDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    public ResultSet SelectResult;
    public Exception Exception;

    public Book_AuthorDAO() throws SQLException {
        if (!Core.ConnectionManager.Connected()) throw new SQLException();
        connection = Core.ConnectionManager.getConnection();
    }

    public Core.Result Insert(int a_id, int b_id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO BOOKS_AUTHORS VALUES(?,?)");
            preparedStatement.setInt(1,a_id);
            preparedStatement.setInt(2,b_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return Core.Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception = e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception = e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Select(int id, boolean a)
    {
        try {
            String prepare;
            if (a) prepare="SELECT * FROM BOOKS_AUTHORS WHERE A_ID=?";
                    else prepare="SELECT * FROM BOOKS_AUTHORS WHERE B_ID=?";
            preparedStatement = connection.prepareStatement(prepare);
            ResultSet resultSet = preparedStatement.executeQuery();
            SelectResult = resultSet;
            return Core.Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception = e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception = e;
            return Core.Result.EXCEPTION;
        }
    }
}
