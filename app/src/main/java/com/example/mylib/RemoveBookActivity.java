package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        String amount=amountText.getText().toString();
        //remove book
    }
}