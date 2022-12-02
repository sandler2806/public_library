package com.example.mylib;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseBook {

    static Book book;
    static DatabaseReference myRef= FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    public static void addBook(String name, String author, String genre, int amount, String publishYear){
        Book book = new Book(name, author, genre, publishYear, amount);
        myRef.child("books").child(name).setValue(book);
    }
//    public static Book searchBook(String name){
////        myRef.child("books").child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
////            @Override
////            public void onComplete(@NonNull Task<DataSnapshot> task) {
////                if(task.isSuccessful()){
////                    DataSnapshot snapshot = task.getResult();
////                     book = snapshot.getValue(Book.class);
////                }
////            }
////        });
////        return book;
//    }
}
