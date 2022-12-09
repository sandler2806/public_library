package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
        TextView nameText = findViewById(R.id.name);
        TextView phoneText = findViewById(R.id.phone);
        String username=usernameText.getText().toString();
        String password=passwordText.getText().toString();
        String verifyPassword=verifyPasswordText.getText().toString();
        String name=nameText.getText().toString();
        String phone=phoneText.getText().toString();

        FireBaseUser fu = new FireBaseUser();
        fu.getUserFromDB(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Toast.makeText(SignUpActivity.this,"username already exist",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(verifyPassword)){
                    Toast.makeText(SignUpActivity.this,"verify password does not match to password",Toast.LENGTH_SHORT).show();
                }
                else{
                    fu.addUserToDB(username,password,name,phone);
                    Toast.makeText(SignUpActivity.this,"sign up successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, ClientHomeActivity.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        GlobalUserInfo.global_user_name = username;
    }
    public void goBack(View view){
        startActivity(new Intent(this, SignInCustomer.class));
    }
}