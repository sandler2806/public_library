
package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseUser;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void openClientHome(View view) {
//        get data from text views
        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        TextView verifyPasswordText = findViewById(R.id.verify_password);
        TextView nameText = findViewById(R.id.name);
        TextView phoneText = findViewById(R.id.phone);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String verifyPassword = verifyPasswordText.getText().toString();
        String name = nameText.getText().toString();
        String phone = phoneText.getText().toString();

        if (username.equals("") || password.equals("") || verifyPassword.equals("") || name.equals("") || phone.equals("")) {
            Toast.makeText(this, "must fill all the form fields!", Toast.LENGTH_SHORT).show();
        } else {
//        Save the user's data
            GlobalUserInfo.global_user_name = username;
            GlobalUserInfo.global_name = name;
//        add user to the database
            FireBaseUser.addUser(this, username, password, verifyPassword, name, phone);
        }


    }

    public void goBack(View view) {
        startActivity(new Intent(this, SignInCustomer.class));
    }
}

