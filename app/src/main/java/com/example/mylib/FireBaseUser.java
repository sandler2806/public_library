package com.example.mylib;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FireBaseUser{
    static User user;
    static DatabaseReference myRef= FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    static public void addUser(String username, String password, String name, String phone){//, ArrayList<Book> books,ArrayList<Book> favorites
        User user=new User(username,password,name,phone);
        user.addFavorite(new Book());
        myRef.child("users").child(username).setValue(user);
    }
    static public User getUser(String username) {
        myRef.child("users").child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    user = snapshot.getValue(User.class);
                }
            }
        });
        return new User(user);
    }
}