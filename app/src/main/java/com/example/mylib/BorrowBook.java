package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mylib.adapters.BookAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
                    books.add(snapshot.getValue(Book.class));
                }
                adapter = new BookAdapter(BorrowBook.this, books);
                bookList.setAdapter(adapter);

//                book = dataSnapshot.getValue(Book.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

//        Book[] books = new Book[7];
//        books[0] = new Book("Harry potter 1", "J.K rolling","", "",1 );
//        books[1] = new Book("Harry potter 2", "J.K rolling","", "",1 );
//        books[2] = new Book("Harry potter 3", "J.K rolling","", "",1 );
//        books[3] = new Book("Harry potter 4", "J.K rolling","", "",1 );
//        books[4] = new Book("Harry potter 5", "J.K rolling","", "",1 );
//        books[5] = new Book("Harry potter 6", "J.K rolling","", "",1 );
//        books[6] = new Book("Harry potter 7", "J.K rolling","", "",1 );
//        adapter = new BookAdapter(this, books);
//        bookList.setAdapter(adapter);
//        String bookSize =

    }

    public void borrow(View view){

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }

}