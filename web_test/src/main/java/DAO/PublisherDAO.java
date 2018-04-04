package DAO;

import java.sql.*;

public class PublisherDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    public ResultSet SelectResult;
    public Exception Exception;

    public PublisherDAO() throws SQLException {
        if (!Core.ConnectionManager.Connected()) throw new SQLException();
        connection = Core.ConnectionManager.getConnection();
    }

    public Core.Result Insert(String name, Core.City.Code code)
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) FROM PUBLISHERS");
            int id=0;
            if (resultSet.next()) id = resultSet.getInt(1)+1;
            statement.close();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PUBLISHER VALUES (?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, Core.City.GetName(code));
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

    public Core.Result Delete(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PUBLISHER WHERE ID = ?");
            preparedStatement.setInt(1,id);
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

    public Core.Result Select(String mask)
    {
        try
        {
            mask = '%' + mask + '%';
            preparedStatement = connection.prepareStatement("SELECT * FROM PUBLISHER WHERE NAME LIKE ?");
            preparedStatement.setString(1,mask);
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

    public Core.Result Select(Core.City.Code code)
    {
        try
        {
            preparedStatement = connection.prepareStatement("SELECT * FROM PUBLISHER WHERE CITY = ?");
            preparedStatement.setString(1,code.toString());
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
