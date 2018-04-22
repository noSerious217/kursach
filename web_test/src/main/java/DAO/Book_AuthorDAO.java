package DAO;

import Entity.Book_Author;

import java.sql.*;
import java.util.LinkedList;

public class Book_AuthorDAO {

    private Connection connection;
    public static Exception Exception;

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

    public LinkedList<Book_Author> Select(int id, boolean a)
    {
        try {
            String prepare;
            if (a) prepare="SELECT * FROM BOOKS_AUTHORS WHERE A_ID=?";
                    else prepare="SELECT * FROM BOOKS_AUTHORS WHERE B_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(prepare);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            LinkedList<Book_Author> linkedList = new LinkedList<Book_Author>();
            while (resultSet.next())
            {
                Book_Author book_author = new Book_Author();
                book_author.setA_id(resultSet.getInt(1));
                book_author.setB_id(resultSet.getInt(2));
                linkedList.add(book_author);
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

    public LinkedList<Book_Author> Select()
    {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM BOOKS_AUTHORS");
            LinkedList<Book_Author> linkedList = new LinkedList<Book_Author>();
            while (resultSet.next())
            {
                Book_Author book_author = new Book_Author();
                book_author.setA_id(resultSet.getInt(1));
                book_author.setB_id(resultSet.getInt(2));
                linkedList.add(book_author);
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

    public Core.Result Delete(int a_id, int b_id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM BOOKS_AUTHORS WHERE A_ID = ? AND B_ID = ?");
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
}
