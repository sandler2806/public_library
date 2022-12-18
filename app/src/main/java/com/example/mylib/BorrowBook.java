package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.Objects.Book;
import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.adapters.BookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BorrowBook extends AppCompatActivity {

    ListView bookList;
    BookAdapter adapter;
    ArrayList<Book> books = new ArrayList<>();
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);
        DatabaseReference booksRef = new FireBaseBook().getBookListRef();
        bookList = (ListView) findViewById(R.id.bookList);
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Book book = snapshot.getValue(Book.class);
                    if(book.getAmount()==0){
                        continue;
                    }
                    books.add(snapshot.getValue(Book.class));
                }
                adapter = new BookAdapter(BorrowBook.this, books);
                bookList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    public void borrow(View view){
        View parentView = (View)view.getParent();
        // Access the data associated with the list item, such as the book name and author
        TextView bookNameView = parentView.findViewById(R.id.bookNameTextView);
        TextView amountText = parentView.findViewById(R.id.noOfCopiesTextView);
        String bookName = bookNameView.getText().toString();
        int amount=Integer.parseInt(amountText.getText().toString().substring(18));
//        DatabaseReference booksRef = new FireBaseBook().getBookFromDB(bookName);
//        if(FireBaseUser.addToBorrowed(bookName,amount,BorrowBook.this)) {
//            booksRef.child("amount").setValue(amount - 1);
//            Toast.makeText(BorrowBook.this,"Borrowed",Toast.LENGTH_SHORT).show();
//            finish();
//            overridePendingTransition(0, 0);
//            startActivity(getIntent());
//            overridePendingTransition(0, 0);
//        }
//        else{
//            Toast.makeText(BorrowBook.this,"Already borrowed",Toast.LENGTH_SHORT).show();
//        }
        FireBaseUser.addToBorrowed(bookName,amount,BorrowBook.this);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}