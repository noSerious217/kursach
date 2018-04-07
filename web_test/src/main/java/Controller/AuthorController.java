package Controller;

import Core.Result;
import DAO.AuthorDAO;
import Entity.Author;

import java.sql.SQLException;
import java.util.LinkedList;

public class AuthorController {
   public static Core.Result add(Author author)
    {
        try
        {
            if (author.getId() == 0) return new AuthorDAO().Insert(author.getName());
            else return new AuthorDAO().Insert(author.getId(),author.getName());
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

    public static Core.Result update(Author author)
    {
        try
        {
            return new AuthorDAO().Update(author.getId(),author.getName());
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

    public static Core.Result delete(Author author)
    {
        try
        {
            return new AuthorDAO().Delete(author.getId());
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

    public static Author select(Integer id) throws SQLException {
        return new AuthorDAO().Select(id);
    }

    public static LinkedList<Author> select(String mask) throws SQLException {
        return new AuthorDAO().Select(mask);
    }
}
