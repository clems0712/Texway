package com.example.texway;

public class Util {

    public static String getQualityByScore(int score)
    {
        String quality;
        if (score > 75)
            quality = "Excellent";
        else if (score > 50)
            quality = "Bon";
        else if (score > 25)
            quality = "Médiocre";
        else
            quality = "Mauvais";

        return quality + " (" + score +")";

    }
}
