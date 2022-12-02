package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.View;
import android.widget.TextView;

public class AddBookLibrarian extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().getDatabase();
    DatabaseReference myRef ;

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_librarian);
        ReadBook("1");
    }

    private void ReadBook(String Id){
        setMyRef(database.getReference("books"));
        this.myRef.child(Id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot snapshot = task.getResult();
                    String bookName = String.valueOf(snapshot.child("name").getValue());
                    Toast.makeText(AddBookLibrarian.this, bookName, Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(AddBookLibrarian.this,"No data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void goBack(){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }
    public void addBook(){
        TextView bookNameText = findViewById(R.id.BookNameInsert);
        TextView authorText = findViewById(R.id.BookAuthorInsert);
        TextView genreText = findViewById(R.id.BookGenreInsert);
        TextView amountText = findViewById(R.id.BookAmountsInsert);
        TextView publishingYearText = findViewById(R.id.BookYearPublishedInsert);
        String bookName=bookNameText.getText().toString();
        String author=authorText.getText().toString();
        String genre=genreText.getText().toString();
        String amount=amountText.getText().toString();
        String publishingYear=publishingYearText.getText().toString();
        //add book
    }
}