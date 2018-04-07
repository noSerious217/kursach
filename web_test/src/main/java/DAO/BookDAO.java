package DAO;

import Core.Result;
import Entity.Book;

import java.sql.*;
import java.util.LinkedList;

public class BookDAO {

    private Connection connection;
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

    public Core.Result Insert(int id, String name, int year, String genre)
    {
        try
        {
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

    public Core.Result Delete(Integer id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM BOOKS WHERE ID = ?");
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

    public LinkedList<Book> Select(String mask)
    {
        try
        {
            mask='%'+mask+'%';
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE NAME LIKE ?");
            preparedStatement.setString(1,mask);
            ResultSet resultSet = preparedStatement.executeQuery();
            LinkedList<Book> res = new LinkedList<Book>();
            while (resultSet.next())
                {
                    Book book = new Book();
                    book.setId(resultSet.getInt(1));
                    book.setName(resultSet.getString(2));
                    book.setGenre(resultSet.getString(3));
                    book.setYear(resultSet.getInt(4));
                    res.add(book);
                }
                return res;
        }
        catch (SQLException e) {
            Exception = e;
            return null;
        }
        catch (Exception e)
        {
            Exception = e;
            return null;
        }
    }

    public Book Select(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE ID = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book Book = new Book();
            if (resultSet.next()) {
                Book.setId(resultSet.getInt(1));
                Book.setName(resultSet.getString(2));
                Book.setGenre(resultSet.getString(3));
                Book.setYear(resultSet.getInt(4));
                return Book;
            }
            Exception = null;
            return null;
        }
        catch (SQLException e) {
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
