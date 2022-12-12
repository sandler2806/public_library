package com.example.mylib;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.mylib.DataBase.FireBaseBook;


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