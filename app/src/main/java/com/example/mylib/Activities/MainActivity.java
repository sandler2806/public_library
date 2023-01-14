package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylib.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSignInLibrarian(View view) {
        startActivity(new Intent(this, SignInLibrarianActivity.class));
    }

    public void openSignInCustomer(View view) {
        startActivity(new Intent(this, SignInCustomerActivity.class));
    }

}