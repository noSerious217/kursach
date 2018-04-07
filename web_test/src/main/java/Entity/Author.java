package Entity;

import Core.Result;

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
}
