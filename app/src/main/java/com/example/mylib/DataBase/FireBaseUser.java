package com.example.mylib.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.ClientHomeActivity;
import com.example.mylib.GlobalUserInfo;
import com.example.mylib.Objects.User;
import com.example.mylib.adapters.BookListProfileAdapter;
import com.example.mylib.adapters.ReturnBookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
                    User user=new User(username,password,name,phone);
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
                    if(user!=null && !user.getPassword().equals(password)){
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

    public static void createBookListForReturn(ListView bookList, Activity activity){
        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                if(user!=null) {
                    ArrayList<String> books = user.getBooks();
                    if (!books.isEmpty()) {
                        ReturnBookAdapter adapter = new ReturnBookAdapter(activity, books);
                        bookList.setAdapter(adapter);
                    } else {
                        Toast.makeText(activity, "No books to return", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public static void createBookListForProfileClient(ListView bookList, Activity activity){
        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                if(user!=null) {
                    ArrayList<String> books = user.getBooks();
                    if (!books.isEmpty()) {
                        BookListProfileAdapter adapter = new BookListProfileAdapter(activity, books);
                        bookList.setAdapter(adapter);
                    } else {
                        Toast.makeText(activity, "No books burrowed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public static DatabaseReference getAllUsers(){
        return myRef.child("users");
    }

    public static void addToBorrowed(String bookName,int amount, Activity activity){
        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<String> books = user.getBooks();
                if (!books.contains(bookName)) {
                    books.add(bookName);
                    FireBaseBook.getBook(bookName).child("amount").setValue(amount - 1);
                    Toast.makeText(activity, "Borrowed", Toast.LENGTH_SHORT).show();
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    activity.startActivity(activity.getIntent());
                    activity.overridePendingTransition(0, 0);

                } else {
                    Toast.makeText(activity, "Already borrowed", Toast.LENGTH_SHORT).show();
                }
                getUser(GlobalUserInfo.global_user_name).child("books").setValue(books);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void removeFromBorrowed(String bookName){

        getUser(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<String> books = user.getBooks();
//                remove the book from the list
                if(books!=null) {
                    books.remove(bookName);
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