package Controller;

import Core.City;
import Core.Result;
import DAO.PublisherDAO;
import Entity.Publisher;

import java.sql.SQLException;
import java.util.LinkedList;

public class PublisherController {
    public static Core.Result add(Publisher Publisher)
    {
        try
        {
            if (Publisher.getId() == 0) return new PublisherDAO().Insert(Publisher.getName(),City.GetCode(Publisher.getCity()));
            else return new PublisherDAO().Insert(Publisher.getId(),Publisher.getName(),City.GetCode(Publisher.getCity()));
        }
        catch (SQLException e)
        {
            return Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            return Result.EXCEPTION;
        }
    }

    public static Core.Result update(Publisher Publisher)
    {
        try
        {
            return new PublisherDAO().Update(Publisher.getId(),Publisher.getName(),City.GetCode(Publisher.getCity()));
        }
        catch (SQLException e)
        {
            return Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            return Result.EXCEPTION;
        }
    }

    public static Core.Result delete(int id)
    {
        try
        {
            return new PublisherDAO().Delete(id);
        }
        catch (SQLException e)
        {
            return Result.SQLEXCEPTION;
        }
        catch (Exception e)
        {
            return Result.EXCEPTION;
        }
    }

    public static Publisher select(Integer id) throws SQLException {
        return new PublisherDAO().Select(id);
    }

    public static LinkedList<Publisher> select(String mask) throws SQLException {
        return new PublisherDAO().Select(mask);
    }

    public static LinkedList<Publisher> select(City.Code city) throws SQLException {
        return new PublisherDAO().Select(city);
    }

    public static LinkedList<Publisher> select(String mask, City.Code city) throws SQLException {
        return new PublisherDAO().Select(mask,city);
    }

    public static int getMaxID() throws SQLException {
        return new PublisherDAO().getMaxID();
    }
}
