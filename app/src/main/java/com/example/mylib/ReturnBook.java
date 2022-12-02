package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReturnBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}