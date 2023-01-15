package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.Objects.Book;
import com.example.mylib.R;


public class RemoveBookActivity extends AppCompatActivity {

    ListView bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);
        bookList = findViewById(R.id.bookList);
        FireBaseBook.showExistBooksForRemove(this, bookList, "");
    }

    public void goBack(View view) {
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }

    public void search(View view) {
        TextView bookNameText = findViewById(R.id.bookName);
        String bookName = bookNameText.getText().toString();
        FireBaseBook.showExistBooksForRemove(this, bookList, bookName);
    }

    public void Remove(View view) {
        View parentView = (View) view.getParent();
        // Access the data associated with the list item, such as the book name and author
        TextView bookIdView = parentView.findViewById(R.id.bookId);
        TextView amountText = parentView.findViewById(R.id.amount);
        TextView numberOfCopiesText = parentView.findViewById(R.id.noOfCopiesTextView);
        int numberOfCopies = Integer.parseInt(numberOfCopiesText.getText().toString().substring(18));
        String bookId = bookIdView.getText().toString();
        try {
            int amount = Integer.parseInt(amountText.getText().toString());
            FireBaseBook.removeCopies(bookId, amount, RemoveBookActivity.this);
            if(numberOfCopies>= amount && amount>0) {
                numberOfCopiesText.setText("Number of copies: " + (numberOfCopies - amount));
            }
        } catch (Exception e) {
            Toast.makeText(this, "invalid input, numbers only", Toast.LENGTH_SHORT).show();
        }
    }
}