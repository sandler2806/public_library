package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void openClientHome(View view){


        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        TextView verifyPasswordText = findViewById(R.id.verify_password);
        String username=usernameText.getText().toString();
        String password=passwordText.getText().toString();
        String verifyPassword=verifyPasswordText.getText().toString();

        if(username.equals("exist")){
            Toast.makeText(SignUpActivity.this,"username already exist",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(verifyPassword)){
            Toast.makeText(SignUpActivity.this,"verify password does not match to password",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(SignUpActivity.this,"sign up successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ClientHomeActivity.class));
        }
    }
    public void goBack(View view){
        startActivity(new Intent(this, SignInCustomer.class));
    }
}