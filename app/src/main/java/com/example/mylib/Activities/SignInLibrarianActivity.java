package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.R;

public class SignInLibrarianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_librarian);
    }

    public void openLibrarianHomeActivity(View view) {
        //get data from text views
        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        //Allows access only to admin
        if (!username.equals("admin")) {
            Toast.makeText(SignInLibrarianActivity.this, "wrong username", Toast.LENGTH_SHORT).show();
        } else if (!password.equals("admin")) {
            Toast.makeText(SignInLibrarianActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SignInLibrarianActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LibrarianHomeActivity.class));
        }
    }

    public void goBack(View view) {

        startActivity(new Intent(this, MainActivity.class));
    }

}