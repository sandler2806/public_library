package com.example.mylib.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.ClientHomeActivity;
import com.example.mylib.GlobalUserInfo;
import com.example.mylib.Objects.User;
import com.example.mylib.SignUpActivity;
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

    public static void addUser(Activity activity,String username, String password, String verifyPassword,String name, String phone){
        User user=new User(username,password,name,phone);
        FireBaseUser.getUserFromDB(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Toast.makeText(activity,"username already exist",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(verifyPassword)){
                    Toast.makeText(activity,"verify password does not match to password",Toast.LENGTH_SHORT).show();
                }
                else{
//                    FireBaseUser.addUserToDB(username,password,name,phone);
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
    public static DatabaseReference getUserFromDB(String userName){
        return myRef.child("users").child(userName);
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