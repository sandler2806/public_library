package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.Book;
import com.example.mylib.DataBase.FireBaseBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class RemoveBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);
    }

    public void goBack(View view){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }
    public void removeBook(View view){

        TextView bookNameText = findViewById(R.id.BookNameInsert);
        TextView amountText = findViewById(R.id.BookAmountsInsert);
        String bookName=bookNameText.getText().toString();
        int amount=Integer.parseInt(amountText.getText().toString());
        FireBaseBook fireBaseBook = new FireBaseBook();
        fireBaseBook.removeBook(RemoveBookActivity.this,bookName,amount);
    }
}