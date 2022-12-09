package com.example.mylib.DataBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseModel  {
    DatabaseReference myRef;

    public FireBaseModel(){
        myRef= FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    }
}
