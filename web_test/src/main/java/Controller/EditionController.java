package Controller;

import Core.Result;
import DAO.EditionDAO;
import Entity.Edition;

import java.sql.SQLException;
import java.util.*;

public class EditionController {
    public static Core.Result add(Edition Edition)
    {
        try
        {
            if (Edition.getId() == 0) return new EditionDAO().Insert(Edition.getB_id(),Edition.getP_id(),Edition.getPages(),Edition.getCopies(),Edition.getISBN(),Edition.getYear());
            else return new EditionDAO().Insert(Edition.getId(),Edition.getB_id(),Edition.getP_id(),Edition.getPages(),Edition.getCopies(),Edition.getISBN(),Edition.getYear());
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

    public static Core.Result update(Edition Edition, int newb_id)
    {
        try
        {
            return new EditionDAO().Update(Edition.getId(),Edition.getB_id(),newb_id,Edition.getP_id(),Edition.getPages(),Edition.getCopies(),Edition.getISBN(),Edition.getYear());
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

    public static Edition select(Integer id, Integer b_id) throws SQLException {
        return new EditionDAO().Select(id,b_id);
    }

    public static LinkedList<Edition> select() throws SQLException {
        return new EditionDAO().Select();
    }

    public static LinkedList<Edition> selectByBook(Integer b_id) throws SQLException {
        return new EditionDAO().SelectByBook(b_id);
    }

    public static int getMaxID() throws SQLException {
        return new EditionDAO().getMaxID();
    }
}
