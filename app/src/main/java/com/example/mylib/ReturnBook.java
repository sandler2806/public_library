package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylib.Objects.Book;
import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.Objects.User;
import com.example.mylib.adapters.ReturnBookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReturnBook extends AppCompatActivity {
    ListView bookList;
    ArrayList<String> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
        bookList = (ListView) findViewById(R.id.bookList);
        User user = new User(GlobalUserInfo.global_user_name);
        books=user.getBooks();
        ReturnBookAdapter adapter = new ReturnBookAdapter(ReturnBook.this, books);
        bookList.setAdapter(adapter);


//        getUserFromDB(GlobalUserInfo.global_user_name).setValue(user);
//        FireBaseUser fireBaseUser=new FireBaseUser();
//        fireBaseUser.getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user=dataSnapshot.getValue(User.class);
//                books=user.getBooks();
//                ReturnBookAdapter adapter = new ReturnBookAdapter(ReturnBook.this, books);
//                bookList.setAdapter(adapter);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });

    }

    public void returnBook(View view){

        View parentView = (View)view.getParent();
        TextView bookNameView = parentView.findViewById(R.id.bookNameTextView);
        String bookName = bookNameView.getText().toString();
        // take the reference to the book and set the amount by amount + 1 for return logic
        DatabaseReference booksRef = new FireBaseBook().getBookFromDB(bookName);

        //fix this

//        Book book = new Book(bookName);
//        int amount=book.getAmount();
//        booksRef.child("amount").setValue(amount+1);
//        FireBaseUser fireBaseUser = new FireBaseUser();
//        fireBaseUser.removeFromBorrowed(bookName);
//        finish();
//        overridePendingTransition(0, 0);
//        startActivity(getIntent());
//        overridePendingTransition(0, 0);

        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);

                int amount=book.getAmount();
                booksRef.child("amount").setValue(amount+1);
                FireBaseUser fireBaseUser = new FireBaseUser();
                fireBaseUser.removeFromBorrowed(bookName);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}