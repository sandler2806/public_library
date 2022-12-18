package com.example.mylib.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.ClientHomeActivity;
import com.example.mylib.GlobalUserInfo;
import com.example.mylib.Objects.User;
import com.example.mylib.ReturnBook;
import com.example.mylib.SignInCustomer;
import com.example.mylib.adapters.ReturnBookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseUser extends FireBaseModel {
    public static boolean borrowed;
//    public static void Await(Task<DataSnapshot> task){
//        try {
//            while (!task.isComplete()){
//                Thread.sleep(10);
//            }
//        } catch (Exception e) {
//            // handle the exception
//        }
//    }
    public static void addUserToDB(String username, String password, String name, String phone){
        writeNewUser(username,password,name,phone);
    }
    public static void writeNewUser(String username, String password, String name, String phone){
        User user=new User(username,password,name,phone);
        myRef.child("users").child(username).setValue(user);

    }
    public static DatabaseReference getUserFromDB(String userName){
        return myRef.child("users").child(userName);
    }

    //function to sign in customer
    public static void signInCustomer(String username, String password,Activity activity){
        getUserFromDB(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //check if the user exists
                if(dataSnapshot.getValue()==null){
                    Toast.makeText(activity,"username does not exist",Toast.LENGTH_SHORT).show();
                }
                else{
                    User user = dataSnapshot.getValue(User.class);
                    if(user!=null && !user.getPassword().equals(password)){
                        Toast.makeText(activity,"wrong password",Toast.LENGTH_SHORT).show();
                    }
                    //case for matching passwords
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
        getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public static DatabaseReference getUsersListRef(){
        return myRef.child("users");
    }
    public static void addToBorrowed(String bookName,int amount, Activity activity){
//        borrowed = false;
//        User user = new User(GlobalUserInfo.global_user_name);
////        Book book = new Book(bookName);
//        if(user.getBooks()==null){
//            user.setBooks(new ArrayList<>());
//        }
//        if(user.getBooks()!=null && !user.getBooks().contains(bookName)){
//            user.getBooks().add(bookName);
//            borrowed = true;
//        }
//        getUserFromDB(GlobalUserInfo.global_user_name).setValue(user);
//        return borrowed;

        getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<String> books = user.getBooks();
                if(!books.contains(bookName)){
                    books.add(bookName);
                    FireBaseBook.getBookFromDB(bookName).child("amount").setValue(amount - 1);
                    Toast.makeText(activity,"Borrowed",Toast.LENGTH_SHORT).show();
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    activity.startActivity(activity.getIntent());
                    activity.overridePendingTransition(0, 0);

                }
                else{
                    Toast.makeText(activity,"Already borrowed",Toast.LENGTH_SHORT).show();
                }
                getUserFromDB(GlobalUserInfo.global_user_name).child("books").setValue(books);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static void removeFromBorrowed(String bookName){
//        User user = new User(GlobalUserInfo.global_user_name);
//        user.getBooks().remove(bookName);
//        getUserFromDB(GlobalUserInfo.global_user_name).setValue(user);

        getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<String> books = user.getBooks();
                if(books!=null) {
                    books.remove(bookName);
                }
                getUserFromDB(GlobalUserInfo.global_user_name).child("books").setValue(books);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
}