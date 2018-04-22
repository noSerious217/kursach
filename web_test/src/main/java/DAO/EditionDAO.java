package DAO;

import Entity.Edition;

import java.sql.*;
import java.util.LinkedList;

public class EditionDAO {

    private Connection connection;
    public static Exception Exception;

    public EditionDAO() throws SQLException
    {
        if (!Core.ConnectionManager.Connected()) throw new SQLException();
        connection = Core.ConnectionManager.getConnection();
    }

    public Core.Result Insert(int book_id, int publisher_id, int pages, int copies, String isbn, int year)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(ID) FROM EDITIONS WHERE B_ID=?");
            preparedStatement.setInt(1,book_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int id=0;
            if (resultSet.next()) id = resultSet.getInt(1)+1;
            preparedStatement = connection.prepareStatement("INSERT INTO EDITIONS VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,book_id);
            preparedStatement.setInt(3,publisher_id);
            preparedStatement.setInt(4,pages);
            preparedStatement.setInt(5,copies);
            preparedStatement.setString(6,isbn);
            preparedStatement.setInt(7,year);
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

    public Core.Result Insert(int id, int book_id, int publisher_id, int pages, int copies, String isbn, int year)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO EDITIONS VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,book_id);
            preparedStatement.setInt(3,publisher_id);
            preparedStatement.setInt(4,pages);
            preparedStatement.setInt(5,copies);
            preparedStatement.setString(6,isbn);
            preparedStatement.setInt(7,year);
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

    public Core.Result Update(int e_id, int b_id, int newb_id, int p_id, int pages, int copies, String ISBN, int year)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE EDITIONS SET b_id = ?, COPIES = ?, P_ID=?, PAGES=?, ISBN=?, YEAR=? WHERE id = ? and b_id = ?");
            preparedStatement.setInt(1,newb_id);
            preparedStatement.setInt(2,copies);
            preparedStatement.setInt(3,p_id);
            preparedStatement.setInt(4,pages);
            preparedStatement.setString(5,ISBN);
            preparedStatement.setInt(6,year);
            preparedStatement.setInt(7,e_id);
            preparedStatement.setInt(8,b_id);
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

    public Edition Select(Integer id, Integer b_id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EDITIONS WHERE ID = ? AND B_ID=?");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,b_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                Edition Edition = new Edition();
                Edition.setId(resultSet.getInt(1));
                Edition.setB_id(resultSet.getInt(2));
                Edition.setP_id(resultSet.getInt(3));
                Edition.setPages(resultSet.getInt(4));
                Edition.setCopies(resultSet.getInt(5));
                Edition.setISBN(resultSet.getString(6));
                Edition.setYear(resultSet.getInt(7));
                return Edition;
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

    public LinkedList<Edition> SelectByBook(Integer b_id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EDITIONS WHERE B_ID = ?");
            preparedStatement.setInt(1,b_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            LinkedList<Edition> linkedList = new LinkedList<Edition>();
            while (resultSet.next())
            {
                Edition Edition = new Edition();
                Edition.setId(resultSet.getInt(1));
                Edition.setB_id(resultSet.getInt(2));
                Edition.setP_id(resultSet.getInt(3));
                Edition.setPages(resultSet.getInt(4));
                Edition.setCopies(resultSet.getInt(5));
                Edition.setISBN(resultSet.getString(6));
                Edition.setYear(resultSet.getInt(7));
                linkedList.add(Edition);
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

    public LinkedList<Edition> Select()
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM EDITIONS");
            LinkedList<Edition> linkedList = new LinkedList<Edition>();
            while (resultSet.next())
            {
                Edition Edition = new Edition();
                Edition.setId(resultSet.getInt(1));
                Edition.setB_id(resultSet.getInt(2));
                Edition.setP_id(resultSet.getInt(3));
                Edition.setPages(resultSet.getInt(4));
                Edition.setCopies(resultSet.getInt(5));
                Edition.setISBN(resultSet.getString(6));
                Edition.setYear(resultSet.getInt(7));
                linkedList.add(Edition);
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

    public int getMaxID()
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT MAX(ID) FROM EDITIONS");
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (SQLException e)
        {
            Exception = e;
            return 0;
        }
        catch (Exception e)
        {
            Exception = e;
            return 0;
        }
    }
}

