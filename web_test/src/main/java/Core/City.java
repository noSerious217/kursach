package Core;

public class City {
    public static enum Code
    {
        MOW, SPB, SAM, ORE
    }

    public static String GetName(City.Code code)
    {
        switch (code)
        {
            case MOW:
                return "Москва";
            case SPB:
                return "Санкт-Петербург";
            case ORE:
                return "Оренбург";
            case SAM:
                return "Самара";
        }
        return null;
    }

    public static Code GetCode(String name)
    {
        if (name.toLowerCase().equals("москва")) return Code.MOW;
        if (name.toLowerCase().equals("санкт-петербург")) return Code.SPB;
        if (name.toLowerCase().equals("оренбург")) return Code.ORE;
        if (name.toLowerCase().equals("самара")) return Code.SAM;
        return null;
    }
}
