package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignInLibrarian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_librarian);
    }
    public void openLibrarianHomeActivity(View view){
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }

}