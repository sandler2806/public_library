package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignInLibrarian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_librarian);
    }
    public void openLibrarianHomeActivity(View view){

//        get data from text views
        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        String username=usernameText.getText().toString();
        String password=passwordText.getText().toString();

//        Allows access only to admin
        if(!username.equals("admin")){
            Toast.makeText(SignInLibrarian.this,"wrong username",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals("admin")){
            Toast.makeText(SignInLibrarian.this,"wrong password",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(SignInLibrarian.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LibrarianHomeActivity.class));
        }
    }
    public void goBack(View view){

        startActivity(new Intent(this, MainActivity.class));
    }

}