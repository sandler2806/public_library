package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.R;

public class SearchCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);
    }

    public void goBack(View view) {

        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }

    public void search(View view) {
        TextView usernameText = findViewById(R.id.username);
        TextView usernameSearchText = findViewById(R.id.usernameSearch);
        TextView nameText = findViewById(R.id.name);
        TextView phoneText = findViewById(R.id.phone);
        String username = usernameSearchText.getText().toString();
        ListView bookList = (ListView) findViewById(R.id.bookListProfileClient);

        FireBaseUser.showUser(username, usernameText, nameText, phoneText, this, bookList);
    }
}