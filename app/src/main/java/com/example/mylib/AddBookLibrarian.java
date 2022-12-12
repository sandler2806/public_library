package com.example.mylib;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.mylib.DataBase.FireBaseBook;
import android.view.View;
import android.widget.TextView;

public class AddBookLibrarian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_librarian);
    }
    public void goBack(View view){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }
    public void addBook(View view){
        FireBaseBook fu=new FireBaseBook();
        TextView bookNameText = findViewById(R.id.BookNameInsert);
        TextView authorText = findViewById(R.id.BookAuthorInsert);
        TextView genreText = findViewById(R.id.BookGenreInsert);
        TextView amountText = findViewById(R.id.BookAmountsInsert);
        TextView publishingYearText = findViewById(R.id.BookYearPublishedInsert);
        String bookName=bookNameText.getText().toString();
        String author=authorText.getText().toString();
        String genre=genreText.getText().toString();
        int amount=Integer.parseInt(amountText.getText().toString());
        String publishingYear=publishingYearText.getText().toString();
        fu.addBook(bookName,author,genre,amount,publishingYear);
        Toast.makeText(AddBookLibrarian.this,"book added successfully", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);


    }
}