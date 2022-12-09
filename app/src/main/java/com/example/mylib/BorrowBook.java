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
import android.widget.TextView;
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
                    Book book = snapshot.getValue(Book.class);
                    if(book.getAmount()==0){
                        continue;
                    }
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
        View parentView = (View)view.getParent();

        // Access the data associated with the list item, such as the book name and author
        TextView bookNameView = parentView.findViewById(R.id.bookNameTextView);
        TextView amountText = parentView.findViewById(R.id.noOfCopiesTextView);
        String bookName = bookNameView.getText().toString();
        int amount=Integer.parseInt(amountText.getText().toString().substring(18));
        DatabaseReference booksRef = new FireBaseBook().getBookFromDB(bookName);
        booksRef.child("amount").setValue(amount-1);
        FireBaseUser fu = new FireBaseUser();
        fu.addToBorrowed(bookName);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }

}