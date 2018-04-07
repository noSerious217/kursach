package Entity;

import Core.City;
import Core.Result;

public class Publisher {
    private Integer id = 0;
    private String name;
    private String city;

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
        if (name.length()==0||name.length()>30) return Result.WRONGATTRIBUTES;
        this.name = name;
        return Result.SUCCESS;
    }

    public String getCity() {
        return city;
    }

    public Result setCity(String city) {
        if (City.GetCode(city)==null) return Result.WRONGATTRIBUTES;
        this.city = city;
        return Result.SUCCESS;
    }

}
