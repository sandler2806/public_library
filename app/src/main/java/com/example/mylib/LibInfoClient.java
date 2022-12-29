package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseModel;
import com.example.mylib.Objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;


public class LibInfoClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_info_client);

        FireBaseModel.myRef.child("LibInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Object map = dataSnapshot.getValue(Object.class);
                assert map != null;

                String libraryName= (String) ((HashMap<?, ?>)map).get("libraryName");
                String openingHours=(String) ((HashMap<?, ?>)map).get("openingHours");
                String phoneNumber=(String) ((HashMap<?, ?>)map).get("phoneNumber");
                String mailContact=(String) ((HashMap<?, ?>)map).get("mailContact");
                TextView libraryNameTextView =  findViewById(R.id.libraryName);
                TextView openingHoursTextView =  findViewById(R.id.openingHours);
                TextView phoneNumberTextView =  findViewById(R.id.phoneNumber);
                TextView mailContactTextView =  findViewById(R.id.mailContact);
                libraryNameTextView.setText("library name: "+libraryName);
                openingHoursTextView.setText("opening hours: "+openingHours);
                phoneNumberTextView.setText("phone number: "+phoneNumber);
                mailContactTextView.setText("mail for contact: "+mailContact);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }
    public void goBack(View view){

        startActivity(new Intent(this, ClientHomeActivity.class));
    }
}