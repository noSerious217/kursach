package DAO;

import Core.City;
import Entity.Edition;
import Entity.Publisher;

import java.sql.*;
import java.util.LinkedList;

public class PublisherDAO {

    private Connection connection;
    public static Exception Exception;

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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PUBLISHERS VALUES (?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, code.toString());
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

    public Core.Result Insert(Integer id, String name, Core.City.Code code)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PUBLISHERS VALUES (?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, code.toString());
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

    public Core.Result Update(int id, String name, City.Code code)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PUBLISHERS SET NAME = ?, CITY = ? WHERE ID = ?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,code.toString());
            preparedStatement.setInt(3,id);
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

    public Core.Result Delete(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PUBLISHERS WHERE ID = ?");
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

    public LinkedList<Publisher> Select(String mask)
    {
        try
        {
            mask = '%' + mask + '%';
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PUBLISHERS WHERE NAME LIKE ?");
            preparedStatement.setString(1,mask);
            ResultSet resultSet = preparedStatement.executeQuery();
            LinkedList<Publisher> linkedList = new LinkedList<Publisher>();
            while (resultSet.next())
            {
                Publisher Publisher = new Publisher();
                Publisher.setId(resultSet.getInt(1));
                Publisher.setName(resultSet.getString(2));
                Publisher.setCity(City.GetName(resultSet.getString(3)));
                linkedList.add(Publisher);
            }
            return linkedList;
        }
        catch (SQLException e)
        {
            Exception = e;
            return null;
        }
        catch (Exception e)
        {
            Exception = e;
            return null;
        }
    }

    public LinkedList<Publisher> Select(Core.City.Code code)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PUBLISHERS WHERE CITY = ?");
            preparedStatement.setString(1,code.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            LinkedList<Publisher> linkedList = new LinkedList<Publisher>();
            while (resultSet.next())
            {
                Publisher Publisher = new Publisher();
                Publisher.setId(resultSet.getInt(1));
                Publisher.setName(resultSet.getString(2));
                Publisher.setCity(City.GetName(resultSet.getString(3)));
                linkedList.add(Publisher);
            }
            return linkedList;
        }
        catch (SQLException e)
        {
            Exception = e;
            return null;
        }
        catch (Exception e)
        {
            Exception = e;
            return null;
        }
    }

    public Publisher Select(Integer id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PUBLISHERS WHERE ID = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                Publisher Publisher = new Publisher();
                Publisher.setId(resultSet.getInt(1));
                Publisher.setName(resultSet.getString(2));
                Publisher.setCity(City.GetName(resultSet.getString(3)));
                return Publisher;
            }
            Exception = null;
            return null;
        }
        catch (SQLException e)
        {
            Exception = e;
            return null;
        }
        catch (Exception e)
        {
            Exception = e;
            return null;
        }
    }
}
