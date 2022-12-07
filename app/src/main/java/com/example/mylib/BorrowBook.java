package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
//    Client a=new Client();

//    Client.name="sdfsd";
    ListView bookList;
    String[] books;
    String[] authors;
    int[] noOfCopies;
    ListView listView;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().getDatabase();
    DatabaseReference myRef ;

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);




//        Resources res = getResources();
        bookList = (ListView) findViewById(R.id.bookList);
//        books = res.getStringArray(R.array.books);
//        authors = res.getStringArray(R.array.authors);
//        noOfCopies = res.getIntArray(R.array.numberOfCopies);

//        Book(String name, String author, String genre, String publishing_year,int amount)
        Book[] books = new Book[7];
        books[0] = new Book("Harry potter 1", "J.K rolling","", "",1 );
        books[1] = new Book("Harry potter 2", "J.K rolling","", "",1 );
        books[2] = new Book("Harry potter 3", "J.K rolling","", "",1 );
        books[3] = new Book("Harry potter 4", "J.K rolling","", "",1 );
        books[4] = new Book("Harry potter 5", "J.K rolling","", "",1 );
        books[5] = new Book("Harry potter 6", "J.K rolling","", "",1 );
        books[6] = new Book("Harry potter 7", "J.K rolling","", "",1 );
        BookAdapter adapter = new BookAdapter(this, books);
        bookList.setAdapter(adapter);



    }

    public void borrow(View view){
        // implement borrowing
    }

}