package Controller;

import Core.Result;
import DAO.AuthorDAO;

import java.sql.SQLException;

public class Author {
    private Integer id=0;
    private String name;

    public Integer getId() {
        return id;
    }

    public Core.Result setId(Integer id) {
        if (id<=0) return Result.WRONGATTRIBUTES;
        this.id = id;
        return Result.SUCCESS;
    }

    public String getName() {
        return name;
    }

    public Core.Result setName(String name) {
        if (name.length()==0||name.length()>100) return Result.WRONGATTRIBUTES;
        this.name = name;
        return Result.SUCCESS;
    }

    public static Core.Result add(Author author)
    {
        try
        {
            if (author.id == 0) return new AuthorDAO().Insert(author.name);
            else return new AuthorDAO().Insert(author.id,author.name);
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
            return new AuthorDAO().Update(author.id,author.name);
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
            return new AuthorDAO().Delete(author.id);
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
        AuthorDAO dao = new AuthorDAO();
        if (dao.Select(id) == Result.SUCCESS)
        {
            Author author = new Author();
            dao.SelectResult.next();
            author.setId(dao.SelectResult.getInt(1));
            author.setName(dao.SelectResult.getString(2));
            return author;
        }
        return null;
    }

    public static Author select(String mask) throws SQLException {
        AuthorDAO dao = new AuthorDAO();
        if (dao.Select(mask)==Result.SUCCESS)
        {
            Author author = new Author();
            dao.SelectResult.next();
            author.setId(dao.SelectResult.getInt(1));
            author.setName(dao.SelectResult.getString(2));
            return author;
        }
        return null;
    }
}
