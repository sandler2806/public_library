package com.example.mylib.DataBase;



import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FireBaseBook extends FireBaseModel {

        public void addBook(String name, String author, String genre, int amount, String publishYear) {
            Book book = new Book(name, author, genre, publishYear, amount);
            myRef.child("books").child(name).setValue(book);
        }
        public DatabaseReference getBookFromDB(String bookID) {
            return myRef.child("books").child(bookID);
        }
        public void removeBook(Activity activity,String bookName, int amount){
            this.getBookFromDB(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Book book = snapshot.getValue(Book.class);
                    int bookAmount = 0;
                    // get the amount of the book in order to prevent multiple getAmount calls
                    if(book!=null){
                        bookAmount = book.getAmount();
                    }
                    // check for valid input, if the wanted amount is bigger
                    // than the current then remove all.
                    if(book!=null && bookAmount>0 && book.getAmount()<amount && amount>0 ){
                        book.setAmount(0);
                        getBookFromDB(bookName).setValue(book);
                        String deletedAmountAns = amount +" books deleted";
                        Toast.makeText(activity,deletedAmountAns,Toast.LENGTH_SHORT).show();
                    }
                    //if the amount is smaller than the current so remove the wanted amount
                    else if(book != null && bookAmount>0 && amount > 0) {
                        book.setAmount(bookAmount-amount);
                        getBookFromDB(bookName).setValue(book);
                        String deletedAmountAns = amount +" books deleted";
                        Toast.makeText(activity,deletedAmountAns,Toast.LENGTH_SHORT).show();
                    }
                    // for zero book available
                    else if(book != null && bookAmount==0) {
                        Toast.makeText(activity,"No books to delete", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(activity,"Invalid input",Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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
