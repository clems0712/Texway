package com.example.texway.Class;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dal {

   protected FirebaseDatabase database;
   protected DatabaseReference myRef;



   public Dal(){
       database  = FirebaseDatabase.getInstance();
       myRef = database.getReference("message");
   }

    public void WriteData() {

        // Ecrire dans la base
        myRef.setValue("Hello, World!");
    }


    public  void ReadData(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            Log.d("DB: ", "Value is: " + value);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("DB: ", "Failed to read value.", error.toException());
        }
    });

}


}
