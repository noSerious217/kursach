package DAO;

import Core.Result;

import java.sql.*;

public class AuthorDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    public ResultSet SelectResult;
    public Exception Exception;

    public AuthorDAO() throws SQLException {
        if (!Core.ConnectionManager.Connected()) throw new SQLException();
        connection = Core.ConnectionManager.getConnection();
    }

    public Core.Result Insert(String name)
    {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) FROM AUTHORS");
            resultSet.next();
            int id=0;
            if (resultSet.next()) id = resultSet.getInt(1)+1;
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO AUTHORS VALUES (?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return Core.Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception=e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception=e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Insert(Integer id, String name)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO AUTHORS VALUES (?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception = e;
            return Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception = e;
            return Result.EXCEPTION;
        }
    }

    public Core.Result Update(Integer id, String newname)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE AUTHORS SET NAME = ? WHERE ID = ?");
            preparedStatement.setString(1,newname);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return Core.Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception=e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception=e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Delete(Integer id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM AUTHORS WHERE ID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return Core.Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception=e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception=e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Select(String mask) {
        mask = '%'+mask+'%';
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM AUTHORS WHERE NAME LIKE ?");
            preparedStatement.setString(1, mask);
            ResultSet res = preparedStatement.executeQuery();
            SelectResult=res;
            return Core.Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception=e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception=e;
            return Core.Result.EXCEPTION;
        }
    }

    public Core.Result Select(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM AUTHORS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            SelectResult=res;
            return Core.Result.SUCCESS;
        }
        catch (SQLException e)
        {
            Exception=e;
            return Core.Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            Exception=e;
            return Core.Result.EXCEPTION;
        }
    }
}
