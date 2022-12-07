package com.example.mylib;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseModel  {
    DatabaseReference myRef;

    public FireBaseModel(){
        myRef= FirebaseDatabase.getInstance().getReference();
    }
}
