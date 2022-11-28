package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignInCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_customer);
    }




    public void openSignUp(View view){

        startActivity(new Intent(this, SignUpActivity.class));
    }
    public void goBack(View view){

        startActivity(new Intent(this, MainActivity.class));
    }
    public void openClientHome(View view){

        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        String username=usernameText.getText().toString();
        String password=passwordText.getText().toString();

        if(username.equals("notExist")){
            Toast.makeText(SignInCustomer.this,"username does not exist",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals("correct")){
            Toast.makeText(SignInCustomer.this,"wrong password",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(SignInCustomer.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ClientHomeActivity.class));
        }
    }

}