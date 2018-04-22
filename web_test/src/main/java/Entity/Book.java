package Entity;

import Core.Result;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Book implements Comparable<Book>{
    private Integer id = 0;
    private String name;
    private String genre;
    private Integer year;

    public Integer getId() {
        return id;
    }

    public Result setId(Integer id) {
        if (id<=0) return Result.WRONGATTRIBUTES;
        this.id = id;
        return Result.SUCCESS;
    }

    public String getName() {
        return name;
    }

    public Result setName(String name) {
        if (name.length()==0||name.length()>80) return Result.WRONGATTRIBUTES;
        this.name = name;
        return Result.SUCCESS;
    }

    public String getGenre() {
        return genre;
    }

    public Result setGenre(String genre) {
        if (genre.length()==0||genre.length()>20) return Result.WRONGATTRIBUTES;
        this.genre = genre;
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
    public int compareTo(Book o) {
        return id-o.id;
    }
}
