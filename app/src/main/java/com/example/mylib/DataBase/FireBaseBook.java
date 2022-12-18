package com.example.mylib.DataBase;

import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.Objects.Book;
import com.example.mylib.Objects.User;
import com.example.mylib.adapters.BookAdapter;
import com.example.mylib.adapters.BookTrackAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FireBaseBook extends FireBaseModel {

    public static void addBook(String name, String author, String genre, int amount, String publishYear,
                               Activity activity) {
        getBook(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Toast.makeText(activity,"Book already exist",Toast.LENGTH_SHORT).show();
                }
                else{
                    Book book = new Book(name, author, genre, publishYear, amount);
                    getBook(name).setValue(book);
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
    public static DatabaseReference getBook(String bookName) {
        return myRef.child("books").child(bookName);
    }
    public static void showBorrowedBooks(Activity activity,ListView bookList){
        HashMap<String,ArrayList<String>> borrowed=new HashMap<>();

        DatabaseReference usersRef = FireBaseUser.getAllUsers();
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user= snapshot.getValue(User.class);
                    assert user != null;
                    for(String book: user.getBooks()){
                        if(!borrowed.containsKey(book)){
                            borrowed.put(book,new ArrayList<>());
                        }
                        borrowed.get(book).add(user.getUsername());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference booksRef = FireBaseBook.getAllBook();
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Book> books = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book=snapshot.getValue(Book.class);
                    if(borrowed.containsKey(book.getName())){
                        books.add(book);
                    }
                }
                BookTrackAdapter adapter = new BookTrackAdapter(activity, books,borrowed);
                bookList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public static void showAvailableBooks(Activity activity,ListView bookList){
        ArrayList<Book> books = new ArrayList<>();

        FireBaseBook.getAllBook().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Book book = snapshot.getValue(Book.class);
                    if(book.getAmount()==0){
                        continue;
                    }
                    books.add(snapshot.getValue(Book.class));
                }
                BookAdapter adapter = new BookAdapter(activity, books);
                bookList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public static void removeBook(Activity activity,String bookName, int amount){

        getBook(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
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
                    getBook(bookName).setValue(book);
                    String deletedAmountAns = amount +" books deleted";
                    Toast.makeText(activity,deletedAmountAns,Toast.LENGTH_SHORT).show();
                }
                //if the amount is smaller than the current so remove the wanted amount
                else if(book != null && bookAmount>0 && amount > 0) {
                    book.setAmount(bookAmount-amount);
                    getBook(bookName).setValue(book);
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
        getBook(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);
                if(book!=null) {
                    int amount = book.getAmount();
                    getBook(bookName).child("amount").setValue(amount + 1);
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
    public static DatabaseReference getAllBook() {
        return myRef.child("books");
    }
}
