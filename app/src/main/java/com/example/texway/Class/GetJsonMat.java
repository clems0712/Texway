
package com.example.texway.Class;
import com.example.texway.Class.CriteresMateriaux;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.google.gson.Gson;


public class GetJsonMat {

    public static void notation(List<String> compo){

        String path = "app/src/materiaux.json";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));


            Gson gson = new Gson();
            CriteresMateriaux[] mat = gson.fromJson(bufferedReader, CriteresMateriaux[].class);
            List<Integer> moyenne = new ArrayList<>();

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
            System.out.println("La moyenne :"+moy);
            //System.out.println("moyenne :"+Math.round(moy));
        } catch (Exception e)
        {

        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        List<String> ListCompo = new ArrayList<String>();
        ListCompo.add("Lin");
        ListCompo.add("latex");
        ListCompo.add("Coton");
        notation(ListCompo);

    }

}