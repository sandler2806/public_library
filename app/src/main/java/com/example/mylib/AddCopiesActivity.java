package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseBook;

public class AddCopiesActivity extends AppCompatActivity {

    ListView bookList;

    public void goBack(View view) {
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_copies);
        bookList = findViewById(R.id.bookList);
        //Fill the ListView with the available books
        FireBaseBook.showExistBooks(this, bookList, "");
    }

    public void search(View view) {
        TextView bookNameText = findViewById(R.id.bookName);
        String bookName = bookNameText.getText().toString();
        FireBaseBook.showExistBooks(this, bookList, bookName);
    }

    public void add(View view) {
        View parentView = (View) view.getParent();
        // Access the data associated with the list item, such as the book name and author
        TextView bookIdView = parentView.findViewById(R.id.bookId);
        TextView amountText = parentView.findViewById(R.id.amount);
        TextView numberOfCopiesText = parentView.findViewById(R.id.noOfCopiesTextView);
        int numberOfCopies = Integer.parseInt(numberOfCopiesText.getText().toString().substring(18));

        String bookId = bookIdView.getText().toString();
        try {
            int amount = Integer.parseInt(amountText.getText().toString());
            FireBaseBook.addCopies(bookId, amount, AddCopiesActivity.this);
            numberOfCopiesText.setText("Number of copies: " + (numberOfCopies + amount));

        } catch (Exception e) {
            Toast.makeText(this, "invalid input, numbers only", Toast.LENGTH_SHORT).show();
        }
    }
}