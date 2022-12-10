package com.example.mylib.DataBase;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.GlobalUserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseUser extends FireBaseModel {
    static boolean borrowed;
    public void addUserToDB(String username, String password, String name, String phone){
        writeNewUser(username,password,name,phone);
    }
//public void addUser(String username, String password, String name, String phone){//, ArrayList<Book> books,ArrayList<Book> favorites
//    User user=new User(username,password,name,phone);
    public void writeNewUser(String username, String password, String name, String phone){
//        User user = new User(u)
        User user=new User(username,password,name,phone);
        myRef.child("users").child(username).setValue(user);

    }
    public DatabaseReference getUserFromDB(String userID){
        return myRef.child("users").child(userID);
    }
    public DatabaseReference getUsersListRef(){
        return myRef.child("users");
    }

    public void addBookToBooks(String bookName){
        myRef.child("users").child("noam").child("phone").child("0000000");
    }
    public boolean addToBorrowed(String bookName){
        borrowed = false;
        getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<String> books = user.getBooks();
                if(!books.contains(bookName)){
                    books.add(bookName);
                    borrowed = true;
                }
                getUserFromDB(GlobalUserInfo.global_user_name).child("books").setValue(books);

//                ArrayList<String> favorites=user.getFavorites();
//                favorites.add(bookID);
//                getUserFromDB(userID).child("favorites").setValue(favorites);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("sdsdf");
            }

        });
        return borrowed;
    }

    public void removeFromFavorites(String bookID){
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getUserFromDB(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
//                ArrayList<String> favorites=user.getFavorites();
//                if(!favorites.contains(bookID)) return;
//                favorites.remove(bookID);
//                getUserFromDB(userID).child("favorites").setValue(favorites);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
//public class FireBaseUser{
//    static User user;
//    static DatabaseReference myRef= FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
//    static public void addUser(String username, String password, String name, String phone){//, ArrayList<Book> books,ArrayList<Book> favorites
//        User user=new User(username,password,name,phone);
//        user.addFavorite(new Book());
//        myRef.child("users").child(username).setValue(user);
//    }
//    static public User getUser(String username) {
//        myRef.child("users").child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DataSnapshot snapshot = task.getResult();
//                    user = snapshot.getValue(User.class);
//                }
//            }
//        });
//        return new User(user);
//    }
//}