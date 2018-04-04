package DAO;

import java.sql.*;

public class BookDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    public ResultSet SelectResult;
    public Exception Exception;

    public BookDAO() throws SQLException
    {
        if (!Core.ConnectionManager.Connected()) throw new SQLException();
        connection = Core.ConnectionManager.getConnection();
    }

    public Core.Result Insert(String name, int year, String genre)
    {
        try
        {
            Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT MAX(ID) FROM BOOKS");
            resultSet.next();
            int id=0;
            if (resultSet.next()) id = resultSet.getInt(1)+1;
            s.close();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO BOOKS VALUES (?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(4,year);
            preparedStatement.setString(3,genre);
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
            Exception=e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Update(int id, String name, int year, String genre)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE BOOKS SET NAME = ?, YEAR = ?, GENRE = ? WHERE ID = ?");
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,year);
            preparedStatement.setString(3,genre);
            preparedStatement.setInt(4,id);
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
            Exception=e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Select(String mask)
    {
        try
        {
            mask='%'+mask+'%';
            preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE NAME LIKE ?");
            preparedStatement.setString(1,mask);
            ResultSet resultSet = preparedStatement.executeQuery();
            SelectResult = resultSet;
            return Core.Result.SUCCESS;
        }
        catch (SQLException e) {
            Exception = e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception = e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Select(int id)
    {
        try
        {
            preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE ID = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            SelectResult = resultSet;
            return Core.Result.SUCCESS;
        }
        catch (SQLException e) {
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
