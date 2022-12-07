package com.example.mylib;

//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class FireBaseBook extends FireBaseModel {
//
//    static Book book;
//
//    //    DatabaseReference myRef;
//


//    package com.example.thelibrary.fireBase.model;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

    public class FireBaseBook extends FireBaseModel {
        public void addBookToDB(String name, String author, String genre, int amount, String publishYear) {

            writeNewBook(name, author, genre, amount, publishYear);
        }
        public void addBook(String name, String author, String genre, int amount, String publishYear) {
            Book book = new Book(name, author, genre, publishYear, amount);
            myRef.child("books").child(name).setValue(book);
        }
        public void writeNewBook(String name, String author, String genre, int amount, String publishYear) {
            Book book = new Book(name, author, genre, publishYear, amount);
            myRef = myRef.child("books");
//            String keyId = myRef.push().getKey();
//            book.setId(keyId);
            myRef.child("name").setValue(book, new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
//                        Toast.makeText(activity, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
//                        Toast.makeText(activity, "Saved!!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        public DatabaseReference getBookFromDB(String bookID) {
            return myRef.child("books").child(bookID);
        }

        public DatabaseReference getBookListRef() {
            return myRef.child("books");
        }

        public void setAmount(String bookID, int amount) {
            myRef.child("books").child(bookID).child("amount").setValue(amount);
        }

        public void setbookRating(String bookID, double bookRating) {
            myRef.child("books").child(bookID).child("bookRating").setValue(bookRating);
        }

        public void setdiv(String bookID, double div) {
            myRef.child("books").child(bookID).child("div").setValue(div);
        }
    }
