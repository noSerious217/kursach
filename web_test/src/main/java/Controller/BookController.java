package Controller;

import Core.Result;
import DAO.BookDAO;
import Entity.Book;

import java.sql.SQLException;
import java.util.*;

public class BookController {
    public static Core.Result add(Book book)
    {
        try
        {
            if (book.getId() == 0) return new BookDAO().Insert(book.getName(),book.getYear(),book.getGenre());
            else return new BookDAO().Insert(book.getId(),book.getName(),book.getYear(),book.getGenre());
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

    public static Core.Result update(Book book)
    {
        try
        {
            return new BookDAO().Update(book.getId(),book.getName(),book.getYear(),book.getGenre());
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

    public static Core.Result delete(Book book)
    {
        try
        {
            return new BookDAO().Delete(book.getId());
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

    public static Book select(Integer id) throws SQLException {
        return new BookDAO().Select(id);
    }

    public static LinkedList<Book> select(String mask) throws SQLException {
        return new BookDAO().Select(mask);
    }
}
