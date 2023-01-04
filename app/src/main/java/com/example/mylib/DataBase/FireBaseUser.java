package com.example.mylib.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.ClientHomeActivity;
import com.example.mylib.GlobalUserInfo;
import com.example.mylib.Objects.Book;
import com.example.mylib.Objects.BorrowedBook;
import com.example.mylib.Objects.User;
import com.example.mylib.Utils.Base64String;
import com.example.mylib.Utils.Hash;
import com.example.mylib.adapters.BookListProfileAdapter;
import com.example.mylib.adapters.BookTrackAdapter;
import com.example.mylib.adapters.ReturnBookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class FireBaseUser extends FireBaseModel {


    public static void addUser(Activity activity,String username, String password, String verifyPassword,String name, String phone){
        FireBaseUser.getUser(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                check if the username already exist
                if(dataSnapshot.getValue()!=null){
                    Toast.makeText(activity,"username already exist",Toast.LENGTH_SHORT).show();
                }
//                check if the passwords match
                else if(!password.equals(verifyPassword)){
                    Toast.makeText(activity,"verify password does not match to password",Toast.LENGTH_SHORT).show();
                }
                else{
//                    create new user in the database
                    byte[] hash_array = Hash.encrypt(password);
                    String encoded_password = Base64String.encode(hash_array);
                    User user=new User(username,encoded_password,name,phone);
                    myRef.child("users").child(username).setValue(user);
                    Toast.makeText(activity,"sign up successfully",Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity, ClientHomeActivity.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public static DatabaseReference getUser(String userName){
        return myRef.child("users").child(userName);
    }

    //function to sign in customer
    public static void signInCustomer(String username, String password,Activity activity){
        getUser(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //check if the user exists
                if(dataSnapshot.getValue()==null){
                    Toast.makeText(activity,"username does not exist",Toast.LENGTH_SHORT).show();
                }
                //check if the passwords match
                else{
                    User user = dataSnapshot.getValue(User.class);
                    String password_string_hash = user.getPassword();
                    byte[] password_bytes_hash = Base64String.decode(password_string_hash);
                    if(!Hash.verifyPassword(password, password_bytes_hash)){
                        Toast.makeText(activity,"wrong password",Toast.LENGTH_SHORT).show();
                    }
                    //Save the user's data
                    else{
                        GlobalUserInfo.global_name = user.getName();
                        GlobalUserInfo.global_user_name = username;
                        Toast.makeText(activity,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                        activity.startActivity(new Intent(activity, ClientHomeActivity.class));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public static void createBookListForReturn(ListView bookList, Activity activity,String searchKey){
        ArrayList<BorrowedBook> borrowedBooks=new ArrayList<>();
        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                if(user!=null) {
                    borrowedBooks.addAll(user.getBooks());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        DatabaseReference booksRef = FireBaseBook.getAllBook();
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Book> books = new ArrayList<>();
//                add all the books that borrowed by a user
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book=snapshot.getValue(Book.class);
                    for (BorrowedBook borrowedBook :borrowedBooks){
                        if (borrowedBook.getKey().equals(snapshot.getKey()) && book.getName().toLowerCase().startsWith(searchKey.toLowerCase())){
                            books.add(book);
                        }
                    }
                }
                ReturnBookAdapter adapter = new ReturnBookAdapter(activity,borrowedBooks,books);
                bookList.setAdapter(adapter);
                if (books.isEmpty()) {
                    Toast.makeText(activity, "No books to return", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public static void createBookListForProfileClient(ListView bookList, Activity activity){
        ArrayList<BorrowedBook> borrowedBooks=new ArrayList<>();
        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                if(user!=null) {
                    borrowedBooks.addAll(user.getBooks());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        DatabaseReference booksRef = FireBaseBook.getAllBook();
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Book> books = new ArrayList<>();
//                add all the books that borrowed by a user
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book=snapshot.getValue(Book.class);
                    for (BorrowedBook borrowedBook :borrowedBooks){
                        if (borrowedBook.getKey().equals(snapshot.getKey())){
                            books.add(book);
                        }
                    }
                }
                if (!books.isEmpty()) {
                    BookListProfileAdapter adapter = new BookListProfileAdapter(activity, books);
                    bookList.setAdapter(adapter);
                } else {
                    Toast.makeText(activity, "No books burrowed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public static DatabaseReference getAllUsers(){
        return myRef.child("users");
    }



    public static void addToBorrowed(String bookId,int amount, Activity activity){
        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<BorrowedBook> books = user.getBooks();
                BorrowedBook borrowedBook = new BorrowedBook(bookId);
                books.add(borrowedBook);
                FireBaseBook.getBook(bookId).child("amount").setValue(amount - 1);
                Toast.makeText(activity, "Borrowed", Toast.LENGTH_SHORT).show();
                activity.finish();
                activity.overridePendingTransition(0, 0);
                activity.startActivity(activity.getIntent());
                activity.overridePendingTransition(0, 0);
                getUser(GlobalUserInfo.global_user_name).child("books").setValue(books);
                Toast.makeText(activity, "book borrowed successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void removeFromBorrowed(String bookId){

        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<BorrowedBook> books = user.getBooks();
//                remove the book from the list
                if(books!=null) {
                    for(BorrowedBook borrowedBook : books) {
                        if(borrowedBook.getKey().equals(bookId)) {
                            books.remove(borrowedBook);
                            break;
                        }
                    }
                }
//                update the user's books list
                getUser(GlobalUserInfo.global_user_name).child("books").setValue(books);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
}