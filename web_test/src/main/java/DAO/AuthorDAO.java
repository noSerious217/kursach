package DAO;

import Core.Result;
import Entity.Author;

import java.sql.*;
import java.util.LinkedList;

public class AuthorDAO {

    private Connection connection;
    public static Exception Exception;

    public AuthorDAO() throws SQLException {
        if (!Core.ConnectionManager.Connected()) throw new SQLException();
        connection = Core.ConnectionManager.getConnection();
    }

    public int getMaxID()
    {
        try
        {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) FROM AUTHORS");
        resultSet.next();
        return resultSet.getInt(1);
        }
        catch (SQLException e)
        {
            Exception=e;
            return 0;
        }
        catch (Exception e)
        {
            Exception=e;
            return 0;
        }
    }

    public Core.Result Insert(String name)
    {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) FROM AUTHORS");
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

    public LinkedList<Author> Select(String mask) {
        mask = '%'+mask+'%';
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM AUTHORS WHERE NAME LIKE ?");
            preparedStatement.setString(1, mask);
            ResultSet res = preparedStatement.executeQuery();
            LinkedList<Author> linkedList = new LinkedList<Author>();
            while (res.next())
            {
                Author author = new Author();
                author.setId(res.getInt(1));
                author.setName(res.getString(2));
                linkedList.add(author);
            }
            preparedStatement.close();
            return linkedList;
        }
        catch (SQLException e)
        {
            Exception=e;
            return null;
        }
        catch (Exception e)
        {
            Exception=e;
            return null;
        }
    }

    public Author Select(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM AUTHORS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                Author author = new Author();
                author.setId(res.getInt(1));
                author.setName(res.getString(2));
                return author;
            }
            Exception = null;
            return null;
        }
        catch (SQLException e)
        {
            Exception=e;
            return null;
        }
        catch (Exception e)
        {
            Exception=e;
            return null;
        }
    }
}
