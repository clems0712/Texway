package com.example.texway;

import com.example.texway.DAO.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static HashMap<String,List<Integer>> sortScoreByQuality(List<Product> toSort)
    {
        HashMap<String,List<Integer>>  sorted = new HashMap<>();
        sorted.put("Excellent", new ArrayList<Integer>());
        sorted.put("Bon", new ArrayList<Integer>());
        sorted.put("Médiocre", new ArrayList<Integer>());
        sorted.put("Mauvais", new ArrayList<Integer>());
        sorted.put("Tous", new ArrayList<Integer>());


        for(Product product : toSort)
        {
            int score = product.getScore();

            if (score <= 25)
                sorted.get("Mauvais").add(score);
            else if (score <= 50)
                sorted.get("Médiocre").add(score);
            else if (score <= 75)
                sorted.get("Bon").add(score);
            else
                sorted.get("Excellent").add(score);

            sorted.get("Tous").add(score);
        }

        return sorted;
    }
}
