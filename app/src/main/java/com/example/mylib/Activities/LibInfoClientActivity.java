package com.example.mylib.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mylib.DataBase.FireBaseModel;
import com.example.mylib.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class LibInfoClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_info_client);
        FireBaseModel.myRef.child("LibInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object map = dataSnapshot.getValue(Object.class);
                assert map != null;
                String libraryName = (String) ((HashMap<?, ?>) map).get("libraryName");
                String openingHours = (String) ((HashMap<?, ?>) map).get("openingHours");
                String phoneNumber = (String) ((HashMap<?, ?>) map).get("phoneNumber");
                String mailContact = (String) ((HashMap<?, ?>) map).get("mailContact");
                TextView libraryNameTextView = findViewById(R.id.libraryName);
                TextView openingHoursTextView = findViewById(R.id.openingHours);
                TextView phoneNumberTextView = findViewById(R.id.phoneNumber);
                TextView mailContactTextView = findViewById(R.id.mailContact);
                libraryNameTextView.setText("Library Name: " + libraryName);
                openingHoursTextView.setText("Opening Hours: " + openingHours);
                phoneNumberTextView.setText("Phone Number: " + phoneNumber);
                mailContactTextView.setText("Mail For Contact: " + mailContact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void goBack(View view) {
        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}