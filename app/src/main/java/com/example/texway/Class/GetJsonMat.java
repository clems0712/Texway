
package com.example.texway.Class;
import android.content.Context;
import android.content.res.AssetManager;

import com.example.texway.Class.CriteresMateriaux;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.google.gson.Gson;


public class GetJsonMat {

    public static int notation(List<String> compo, Context context){

        try {
            AssetManager mngr = context.getAssets();
            InputStream is = mngr.open("materiaux.json");

            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            Gson gson = new Gson();
            CriteresMateriaux[] mat = gson.fromJson(br, CriteresMateriaux[].class);
            List<Integer> moyenne = new ArrayList<>();

            for (String c:compo)
            {
                String str = c;
                str = str.toLowerCase();

                str = str.replaceAll("([0-9]+%)", "");
                str = str.trim();
                compo.set(compo.indexOf(c),str);
            }

            for(CriteresMateriaux materiau : mat){


                if (compo.contains(materiau.getMateriaux())){

                    Integer score = Integer.valueOf(materiau.getNotation());
                    System.out.println(materiau.getMateriaux() + " : " + score);
                    moyenne.add(score);
                }else{

                }
            }

            Double total =0.0;
            for(int i:moyenne)
                total += i;

            Double m = total/moyenne.size();
            int moy = m.intValue();

            System.out.println("La moyenne : "+moy);

            return moy;

        } catch (Exception e)
        {
            return 0;
        }
    }

}