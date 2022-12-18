package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_client);
        String nameEntry = "Welcome " + GlobalUserInfo.global_name;
        final TextView textViewToChange = (TextView) findViewById(R.id.titleClientNameInInfoPage);
        textViewToChange.setText(nameEntry);
        //show user name
        String userNameEntry = "Your user name: " + GlobalUserInfo.global_user_name;
//        final TextView textViewToChange2 = (TextView) findViewById(R.id.ClientuserNameInInfoPage);
//        textViewToChange2.setText(userNameEntry);
        //show user phone number
        String PhoneNumber = "Your phone number: " + GlobalUserInfo.global_phone_number;
//        final TextView textViewToChange3 = (TextView) findViewById(R.id.ClientPhoneNumberInInfoPage);
//        textViewToChange3.setText(PhoneNumber);

    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}