package com.example.mylib.DataBase;

import androidx.annotation.NonNull;

import com.example.mylib.GlobalUserInfo;
import com.example.mylib.Objects.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseUser extends FireBaseModel {
    public static boolean borrowed;
    static public void Await(Task<DataSnapshot> task){
        try {
            while (!task.isComplete()){
                Thread.sleep(10);
            }
        } catch (Exception e) {
            // handle the exception
        }
    }
    public void addUserToDB(String username, String password, String name, String phone){
        writeNewUser(username,password,name,phone);
    }
    public void writeNewUser(String username, String password, String name, String phone){
        User user=new User(username,password,name,phone);
        myRef.child("users").child(username).setValue(user);

    }
    public DatabaseReference getUserFromDB(String userID){
        return myRef.child("users").child(userID);
    }
    public DatabaseReference getUsersListRef(){
        return myRef.child("users");
    }
    public boolean addToBorrowed(String bookName){
        borrowed = false;
        User user = new User(GlobalUserInfo.global_user_name);
        if(!user.getBooks().contains(bookName)){
            user.getBooks().add(bookName);
            borrowed = true;
        }
        getUserFromDB(GlobalUserInfo.global_user_name).setValue(user);
        return borrowed;

//        getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                ArrayList<String> books = user.getBooks();
//                if(!books.contains(bookName)){
//                    books.add(bookName);
//                    borrowed = true;
//                }
//                getUserFromDB(GlobalUserInfo.global_user_name).child("books").setValue(books);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return borrowed;
    }
    public void removeFromBorrowed(String bookName){
        User user = new User(GlobalUserInfo.global_user_name);
        user.getBooks().remove(bookName);
        getUserFromDB(GlobalUserInfo.global_user_name).setValue(user);

//        getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                ArrayList<String> books = user.getBooks();
//                books.remove(bookName);
//                getUserFromDB(GlobalUserInfo.global_user_name).child("books").setValue(books);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });

    }
    public void removeFromFavorites(String bookName){
        User user = new User(GlobalUserInfo.global_user_name);
        user.getFavorites().remove(bookName);
        getUserFromDB(GlobalUserInfo.global_user_name).setValue(user);

//        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        getUserFromDB(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
////                ArrayList<String> favorites=user.getFavorites();
////                if(!favorites.contains(bookID)) return;
////                favorites.remove(bookID);
////                getUserFromDB(userID).child("favorites").setValue(favorites);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
    }
}