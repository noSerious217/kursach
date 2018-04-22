package Entity;

import Core.Result;
import DAO.AuthorDAO;
import DAO.BookDAO;

import java.sql.SQLException;

public class Book_Author implements Comparable<Book_Author>{
    private Integer a_id;
    private Integer b_id;

    public Integer getA_id() {
        return a_id;
    }

    public Result setA_id(Integer a_id) throws SQLException {
        if (new AuthorDAO().Select(a_id)==null) return Result.WRONGATTRIBUTES;
        this.a_id = a_id;
        return Result.SUCCESS;
    }

    public Integer getB_id() {
        return b_id;
    }

    public Result setB_id(Integer b_id) throws SQLException {
        if (new BookDAO().Select(b_id)==null) return Result.WRONGATTRIBUTES;
        this.b_id = b_id;
        return Result.SUCCESS;
    }

    @Override
    public int compareTo(Book_Author o) {
        return a_id-o.a_id;
    }
}
