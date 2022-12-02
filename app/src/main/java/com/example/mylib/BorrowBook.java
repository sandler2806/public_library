package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    String[] books;
    String[] authors;
    int[] noOfCopies;
    ListView listView;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().getDatabase();
    DatabaseReference myRef ;

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);




//        Resources res = getResources();
//        bookList = (ListView) findViewById(R.id.bookList);
//        books = res.getStringArray(R.array.books);
//        authors = res.getStringArray(R.array.authors);
//        noOfCopies = res.getIntArray(R.array.numberOfCopies);

        //ItemAdapter adapter = new ItemAdapter(this,books, authors, noOfCopies);
        //bookList.setAdapter(adapter);

        listView = findViewById(R.id.bookList);
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.book_list_detail, list);

        setMyRef(database.getReference("books"));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot ss: snapshot.getChildren())
                {
                    list.add(ss.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}