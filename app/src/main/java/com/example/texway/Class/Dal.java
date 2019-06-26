package com.example.texway.Class;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dal {

    Bitmap DB_Picture;
    Product DB_Product;
    private StorageReference mStorageRef;

    public Dal(){

        DB_Picture= null;
        DB_Product = new Product();

    }


    public void ReadCollection(String P_Magasin){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference Collect = db.collection(P_Magasin);


    }


    public Bitmap ReadPicture(String P_Store,String P_reference){


        mStorageRef = FirebaseStorage.getInstance("gs://texwaydb.appspot.com").getReference();

        StorageReference storageRef = mStorageRef.getStorage().getReference().child(P_Store+"/"+P_reference);


        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                DB_Picture= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


        return DB_Picture;
    }



    public Product ReadProduct(String P_Store, String P_reference){

        //initialise
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //recupere document
        DocumentReference docRef = db.collection(P_Store).document(P_reference);

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
                            L_Listcomposition.add(parts_Compistion[index_part].replace(",",""));
                        }


                        ///CAST STRING EN LIST DE STRING TYPE
                        List<String> L_Listtype = new ArrayList<>();
                        String L_type = document.getString("type");
                        String[] parts_type = L_type.split(",");
                        for (int index_part=0;index_part<parts_type.length;index_part++){
                            L_Listtype.add(parts_type[index_part].replace(",",""));
                        }

                        ///CAST STRING EN LIST DE STRING
                        DB_Product.setComposition(L_Listcomposition);
                        DB_Product.setType(L_Listtype);
                        DB_Product.setName(document.getString("nom"));


                    } else {
                        Log.d("Database :", "No such document");
                    }
                } else {
                    Log.d("Database :", "get failed with ", task.getException());
                }
            }
        });


        return DB_Product;

    }


}
