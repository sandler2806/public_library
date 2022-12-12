package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.Book;
import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.DataBase.User;
import com.example.mylib.adapters.BookAdapter;
import com.example.mylib.adapters.ReturnBookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReturnBook extends AppCompatActivity {
    ListView bookList;
    ArrayList<String> books;
    String[] authors;
    int[] noOfCopies;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);

        bookList = (ListView) findViewById(R.id.bookList);
        FireBaseUser fu=new FireBaseUser();
        fu.getUserFromDB(GlobalUserInfo.global_user_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                books=user.getBooks();
                ReturnBookAdapter adapter = new ReturnBookAdapter(ReturnBook.this, books);
                bookList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void returnBook(View view){

        View parentView = (View)view.getParent();
        TextView bookNameView = parentView.findViewById(R.id.bookNameTextView);
        String bookName = bookNameView.getText().toString();
        DatabaseReference booksRef = new FireBaseBook().getBookFromDB(bookName);
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);

                int amount=book.getAmount();
                booksRef.child("amount").setValue(amount+1);
                FireBaseUser fu = new FireBaseUser();
                fu.removeFromBorrowed(bookName);
//                finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0); dosent

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
//        View parentView = (View)view.getParent();
//
//        // Access the data associated with the list item, such as the book name and author
//        TextView bookNameView = parentView.findViewById(R.id.bookNameTextView);
//        TextView amountText = parentView.findViewById(R.id.noOfCopiesTextView);
//        String bookName = bookNameView.getText().toString();

//        int amount=Integer.parseInt(amountText.getText().toString().substring(18));
//        DatabaseReference booksRef = new FireBaseBook().getBookFromDB(bookName);
//        booksRef.child("amount").setValue(amount+1);
//        FireBaseUser fu = new FireBaseUser();
//        fu.removeFromBorrowed(bookName);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}