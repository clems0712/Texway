package com.example.texway.Class;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.texway.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dal {



    public void ReadCollection(String P_Magasin){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
       CollectionReference Collect = db.collection(P_Magasin);


    }





    public Product ReadProduct(String P_Magasin, String P_reference){

        //cree un produit a retourner
        final Product L_Product = new Product();

        //initialise
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //recupere document

        DocumentReference docRef = db.collection(P_Magasin).document(P_reference);



        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Database :", "DocumentSnapshot data: " + document.getData());


                        ///CAST STRING EN LIST DE STRING COMPOSITION
                        List<String> L_Listcomposition = new ArrayList<>();
                        String L_Composition = document.getString("Composition");
                        String[] parts_Compistion = L_Composition.split(",");
                        for (int index_part=0;index_part<parts_Compistion.length;index_part++){
                            L_Listcomposition.add(parts_Compistion[index_part]);
                        }

                        ///CAST STRING EN LIST DE STRING TYPE
                        List<String> L_Listtype = new ArrayList<>();
                        String L_type = document.getString("type");
                        String[] parts_type = L_type.split(",");
                        for (int index_part=0;index_part<parts_type.length;index_part++){
                            L_Listtype.add(parts_type[index_part]);
                        }

                        ///CAST STRING EN LIST DE STRING
                        L_Product.setComposition(L_Listcomposition);
                        L_Product.setType(L_Listtype);
                        L_Product.setName(document.getString("nom"));


                    } else {
                        Log.d("Database :", "No such document");
                    }
                } else {
                    Log.d("Database :", "get failed with ", task.getException());
                }
            }
        });



        return L_Product;

}


}
