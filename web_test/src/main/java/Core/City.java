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

    public static String GetName(String name)
    {

        if (name.toLowerCase().equals("москва")) return "MOW";
        if (name.toLowerCase().equals("санкт-петербург")) return "SPB";
        if (name.toLowerCase().equals("оренбург")) return "ORE";
        if (name.toLowerCase().equals("самара")) return "SAM";
        if (name.toLowerCase().equals("mow")) return "Москва";
        if (name.toLowerCase().equals("spb")) return "Санкт-Петербург";
        if (name.toLowerCase().equals("ore")) return "Оренбург";
        if (name.toLowerCase().equals("sam")) return "Самара";
        return null;
    }


    public static Code GetCode(String name)
    {
        if (name.toLowerCase().equals("москва")||name.toLowerCase().equals("mow")) return Code.MOW;
        if (name.toLowerCase().equals("санкт-петербург")||name.toLowerCase().equals("spb")) return Code.SPB;
        if (name.toLowerCase().equals("оренбург")||name.toLowerCase().equals("ore")) return Code.ORE;
        if (name.toLowerCase().equals("самара")||name.toLowerCase().equals("sam")) return Code.SAM;
        return null;
    }
}
