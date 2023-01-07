package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;
import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;










public class ProfileClient extends AppCompatActivity {
    ListView bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_client);
//        String nameEntry = "Coming soon";
        String nameEntry = "Welcome " + GlobalUserInfo.global_name;
        final TextView textViewToChange = (TextView) findViewById(R.id.titleClientNameInInfoPage);
        textViewToChange.setText(nameEntry);
        //show user name
        String userNameEntry = "Your user name: " + GlobalUserInfo.global_user_name;
        final TextView textViewToChange2 = (TextView) findViewById(R.id.ClientuserNameInInfoPage);
        textViewToChange2.setText(userNameEntry);
        //show user phone number
        String PhoneNumber = "Your phone number: " + GlobalUserInfo.global_phone_number;
        final TextView textViewToChange3 = (TextView) findViewById(R.id.ClientPhoneNumberInInfoPage);
        textViewToChange3.setText(PhoneNumber);
        //        show all the user's books
        bookList = (ListView) findViewById(R.id.bookListProfileClient);
        FireBaseUser.createBookListForProfileClient(bookList,ProfileClient.this,GlobalUserInfo.global_user_name);
    }
//    public void returnBook(View view){
//
////        get data from text views
//        View parentView = (View)view.getParent();
//        TextView bookNameView = parentView.findViewById(R.id.bookNameTextViewProfileClient);
//    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}