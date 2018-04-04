package DAO;

import java.sql.*;

public class EditionDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    public Exception Exception;
    public ResultSet SelectResult;

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
            resultSet.next();
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

    public Core.Result Update(int e_id, int b_id, int copies)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE EDITIONS SET COPIES = ? WHERE id = ? and b_id = ?");
            preparedStatement.setInt(1,copies);
            preparedStatement.setInt(2,e_id);
            preparedStatement.setInt(3,b_id);
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

