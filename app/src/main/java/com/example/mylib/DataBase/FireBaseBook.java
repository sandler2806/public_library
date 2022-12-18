package com.example.mylib.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.AddBookLibrarian;
import com.example.mylib.ClientHomeActivity;
import com.example.mylib.Objects.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FireBaseBook extends FireBaseModel {
    
    public static void addBook(String name, String author, String genre, int amount, String publishYear,
                               Activity activity) {
        getBookFromDB(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Toast.makeText(activity,"Book already exist",Toast.LENGTH_SHORT).show();
                }
                else{
                    Book book = new Book(name, author, genre, publishYear, amount);
                    getBookFromDB(name).setValue(book);
                    Toast.makeText(activity,"Added book successfully",Toast.LENGTH_SHORT).show();
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    activity.startActivity(activity.getIntent());
                    activity. overridePendingTransition(0, 0);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    public static DatabaseReference getBookFromDB(String bookName) {
        return myRef.child("books").child(bookName);
    }
    public static void removeBook(Activity activity,String bookName, int amount){
        System.out.println("before book");
//
//        Book book = new Book(bookName);
//        int bookAmount = 0;
//        // get the amount of the book in order to prevent multiple getAmount calls
//        if(book!=null){
//            bookAmount = book.getAmount();
//        }
//        // check for valid input, if the wanted amount is bigger
//        // than the current then remove all.
//        if(book!=null && bookAmount>0 && book.getAmount()<amount && amount>0 ){
//            book.setAmount(0);
//            getBookFromDB(bookName).setValue(book);
//            String deletedAmountAns = amount +" books deleted";
//            Toast.makeText(activity,deletedAmountAns,Toast.LENGTH_SHORT).show();
//        }
//        //if the amount is smaller than the current so remove the wanted amount
//        else if(book != null && bookAmount>0 && amount > 0) {
//            book.setAmount(bookAmount-amount);
//            getBookFromDB(bookName).setValue(book);
//            String deletedAmountAns = amount +" books deleted";
//            Toast.makeText(activity,deletedAmountAns,Toast.LENGTH_SHORT).show();
//        }
//        // for zero book available
//        else if(book != null && bookAmount==0) {
//            Toast.makeText(activity,"No books to delete", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(activity,"Invalid input",Toast.LENGTH_SHORT).show();
//        }

        getBookFromDB(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public static void returnBook(String bookName, Activity activity){
        getBookFromDB(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);
                if(book!=null) {
                    int amount = book.getAmount();
                    getBookFromDB(bookName).child("amount").setValue(amount + 1);
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    activity.startActivity(activity.getIntent());
                    activity.overridePendingTransition(0, 0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public static DatabaseReference getBookListRef() {
        return myRef.child("books");
    }

    public void setAmount(String bookID, int amount) {
        myRef.child("books").child(bookID).child("amount").setValue(amount);
    }
}
