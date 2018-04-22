package Entity;

import Core.Result;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Edition implements Comparable<Edition>{
    private Integer id = 0;
    private Integer b_id;
    private Integer p_id;
    private Integer pages;
    private Integer copies;
    private String ISBN;
    private Integer year;

    public Integer getId() {
        return id;
    }

    public Result setId(Integer id) {
        if (id<=0) return Result.WRONGATTRIBUTES;
        this.id = id;
        return Result.SUCCESS;
    }

    public Integer getB_id() {
        return b_id;
    }

    public Result setB_id(Integer b_id) {
        if (b_id <= 0) return Result.WRONGATTRIBUTES;
        this.b_id = b_id;
        return Result.SUCCESS;
    }

    public Integer getP_id() {
        return p_id;
    }

    public Result setP_id(Integer p_id) {
        if (p_id<=0) return Result.WRONGATTRIBUTES;
        this.p_id = p_id;
        return Result.SUCCESS;
    }

    public Integer getPages() {
        return pages;
    }

    public Result setPages(Integer pages) {
        if (pages<=0) return Result.WRONGATTRIBUTES;
        this.pages = pages;
        return Result.SUCCESS;
    }

    public Integer getCopies() {
        return copies;
    }

    public Result setCopies(Integer copies) {
        if (copies<0) return Result.WRONGATTRIBUTES;
        this.copies = copies;
        return Result.SUCCESS;
    }

    public String getISBN() {
        return ISBN;
    }

    public Result setISBN(String ISBN) {
        if (ISBN.length()!=13) return Result.WRONGATTRIBUTES;
        this.ISBN = ISBN;
        return Result.SUCCESS;
    }

    public Integer getYear() {
        return year;
    }

    public Result setYear(Integer year) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        if (year> calendar.get(Calendar.YEAR)) return Result.WRONGATTRIBUTES;
        this.year = year;
        return Result.SUCCESS;
    }

    @Override
    public int compareTo(Edition o) {
        if (b_id-o.b_id==0) return id-o.id;
        return b_id-o.b_id;
    }
}
