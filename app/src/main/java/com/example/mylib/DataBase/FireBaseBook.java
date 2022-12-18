package com.example.mylib.DataBase;



import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.BookTrackingActivity;
import com.example.mylib.BorrowBook;
import com.example.mylib.Objects.Book;
import com.example.mylib.Objects.User;
import com.example.mylib.R;
import com.example.mylib.adapters.BookAdapter;
import com.example.mylib.adapters.BookTrackAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FireBaseBook extends FireBaseModel {
//    public static void Await(Task<DataSnapshot> task){
//        try {
//            while (!task.isComplete()){
//                System.out.println("sleeping");
//                Thread.sleep(100);
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            System.out.println("catch");
//            // handle the exception
//        }
//    }
    public static void addBook(String name, String author, String genre, int amount, String publishYear) {
        Book book = new Book(name, author, genre, publishYear, amount);
        myRef.child("books").child(name).setValue(book);
    }
    public static DatabaseReference getBookFromDB(String bookName) {
        return myRef.child("books").child(bookName);
    }
    public static void showBorrowedBooks(Activity activity,ListView bookList){
        HashMap<String,ArrayList<String>> borrowed=new HashMap<>();

        DatabaseReference usersRef = FireBaseUser.getUsersListRef();
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


        DatabaseReference booksRef = FireBaseBook.getBookListRef();
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

        FireBaseBook.getBookListRef().addListenerForSingleValueEvent(new ValueEventListener() {
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
    public static DatabaseReference getBookListRef() {
        return myRef.child("books");
    }

    public void setAmount(String bookID, int amount) {
        myRef.child("books").child(bookID).child("amount").setValue(amount);
    }
}
