package Controller;

import Core.Result;
import DAO.Book_AuthorDAO;
import Entity.Book_Author;

import java.sql.SQLException;
import java.util.LinkedList;

public class Book_AuthorController {
    public static Core.Result add(Book_Author book_author)
    {
        try {
            return new Book_AuthorDAO().Insert(book_author.getA_id(), book_author.getB_id());
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

    public static LinkedList<Book_Author> selectByAuthor(int id) throws SQLException {
        return new Book_AuthorDAO().Select(id,true);
    }

    public static LinkedList<Book_Author> selectByBook(int id) throws SQLException {
        return new Book_AuthorDAO().Select(id,false);
    }

    public static Result delete(Book_Author book_author)
    {
        try {
            return new Book_AuthorDAO().Delete(book_author.getA_id(),book_author.getB_id());
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

}
