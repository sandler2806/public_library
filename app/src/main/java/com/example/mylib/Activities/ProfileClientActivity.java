package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;

import com.example.mylib.DataBase.FireBaseUser;
import com.example.mylib.R;


public class ProfileClientActivity extends AppCompatActivity {
    ListView bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_client);
        String nameEntry = "Welcome " + GlobalUserInfoActivity.globalName;
        final TextView textViewToChange = (TextView) findViewById(R.id.titleClientNameInInfoPage);
        textViewToChange.setText(nameEntry);
        //show user name
        String userNameEntry = "Your user name: " + GlobalUserInfoActivity.globalUserName;
        final TextView textViewToChange2 = (TextView) findViewById(R.id.ClientuserNameInInfoPage);
        textViewToChange2.setText(userNameEntry);
        //show user phone number
        String PhoneNumber = "Your phone number: " + GlobalUserInfoActivity.globalPhoneNumber;
        final TextView textViewToChange3 = (TextView) findViewById(R.id.ClientPhoneNumberInInfoPage);
        textViewToChange3.setText(PhoneNumber);
        //show all the user's books
        bookList = (ListView) findViewById(R.id.bookListProfileClient);
        FireBaseUser.createBookListForProfileClient(bookList, ProfileClientActivity.this, GlobalUserInfoActivity.globalUserName);
    }

    public void goBack(View view) {
        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}