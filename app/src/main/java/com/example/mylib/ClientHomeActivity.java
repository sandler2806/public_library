package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
    }
    public void goBack(View view){

        startActivity(new Intent(this, SignInCustomer.class));
    }
    public void goToBorrow(View view) {

        startActivity(new Intent(this, BorrowBook.class));
    }

    public void goToReturn(View view) {

        startActivity(new Intent(this, ReturnBook.class));
    }

    public void gotToProfile(View view) {

        startActivity(new Intent(this, ProfileClient.class));
    }

    public void goToLibInfo(View view) {
        startActivity(new Intent(this, LibInfoClient.class));
    }
}