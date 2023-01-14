package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.R;

public class SignInCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_customer);

    }


    public void openSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void openClientHome(View view) {
        //get data from text views
        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        FireBaseUser.signInCustomer(username, password, SignInCustomerActivity.this);
    }
}