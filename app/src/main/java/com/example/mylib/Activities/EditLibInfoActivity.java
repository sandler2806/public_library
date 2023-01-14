package com.example.mylib.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseModel;
import com.example.mylib.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditLibInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lib_info);
    }

    public void goBack(View view) {
        startActivity(new Intent(this, LibrarianHomeActivity.class));
    }

    public void update(View view) {
        //get data from text views
        TextView libraryNameText = findViewById(R.id.libraryName);
        TextView phoneNumberText = findViewById(R.id.phoneNumber);
        TextView mailContactText = findViewById(R.id.mailContact);
        TextView sundayText = findViewById(R.id.sundayOpeningHours);
        TextView mondayText = findViewById(R.id.mondayOpeningHours);
        TextView tuesdayText = findViewById(R.id.tuesdayOpeningHours);
        TextView wednesdayText = findViewById(R.id.wednesdayOpeningHours);
        TextView thursdayText = findViewById(R.id.thursdayOpeningHours);
        TextView fridayText = findViewById(R.id.fridayOpeningHours);
        TextView saturdayText = findViewById(R.id.saturdayOpeningHours);
        Activity activity=this;
        FireBaseModel.myRef.child("LibInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String libraryName = libraryNameText.getText().toString();
                String phoneNumber = phoneNumberText.getText().toString();
                String mailContact = mailContactText.getText().toString();
                String sunday = sundayText.getText().toString();
                String monday = mondayText.getText().toString();
                String tuesday = tuesdayText.getText().toString();
                String wednesday = wednesdayText.getText().toString();
                String thursday = thursdayText.getText().toString();
                String friday = fridayText.getText().toString();
                String saturday = saturdayText.getText().toString();


                Object map = dataSnapshot.getValue(Object.class);
                assert map != null;
                String libraryNameDB = (String) ((HashMap<?, ?>) map).get("libraryName");
                String openingHoursDB = (String) ((HashMap<?, ?>) map).get("openingHours");
                String phoneNumberDB = (String) ((HashMap<?, ?>) map).get("phoneNumber");
                String mailContactDB = (String) ((HashMap<?, ?>) map).get("mailContact");
                if(libraryName.equals("")){
                    libraryName=libraryNameDB;
                }
                if(phoneNumber.equals("")){
                    phoneNumber=phoneNumberDB;
                }
                if(mailContact.equals("")){
                    mailContact=mailContactDB;
                }
                if(sunday.equals("")){
                    int sIndex=openingHoursDB.indexOf("Sunday:");
                    int eIndex=openingHoursDB.indexOf("\n",sIndex);
                    sunday=openingHoursDB.substring(sIndex+7,eIndex);
                }
                if(monday.equals("")){
                    int sIndex=openingHoursDB.indexOf("Monday:");
                    int eIndex=openingHoursDB.indexOf("\n",sIndex);
                    monday=openingHoursDB.substring(sIndex+7,eIndex);
                }
                if(tuesday.equals("")){
                    int sIndex=openingHoursDB.indexOf("Tuesday:");
                    int eIndex=openingHoursDB.indexOf("\n",sIndex);
                    tuesday=openingHoursDB.substring(sIndex+8,eIndex);
                }
                if(wednesday.equals("")){
                    int sIndex=openingHoursDB.indexOf("Wednesday:");
                    int eIndex=openingHoursDB.indexOf("\n",sIndex);
                    wednesday=openingHoursDB.substring(sIndex+10,eIndex);
                }
                if(thursday.equals("")){
                    int sIndex=openingHoursDB.indexOf("Thursday:");
                    int eIndex=openingHoursDB.indexOf("\n",sIndex);
                    thursday=openingHoursDB.substring(sIndex+9,eIndex);
                }
                if(friday.equals("")){
                    int sIndex=openingHoursDB.indexOf("Friday:");
                    int eIndex=openingHoursDB.indexOf("\n",sIndex);
                    friday=openingHoursDB.substring(sIndex+7,eIndex);
                }
                if(saturday.equals("")){
                    int sIndex=openingHoursDB.indexOf("Saturday:");
                    saturday=openingHoursDB.substring(sIndex+9);
                }
                String openingHours = "\nSunday:" + sunday +
                        "\n\nMonday:" + monday +
                        "\n\nTuesday:" + tuesday +
                        "\n\nWednesday:" + wednesday +
                        "\n\nThursday:" + thursday +
                        "\n\nFriday:" + friday +
                        "\n\nSaturday:" + saturday;

                System.out.println(openingHours);
                HashMap<String, String> libInfo = new HashMap<>();
                libInfo.put("libraryName", libraryName);
                libInfo.put("phoneNumber", phoneNumber);
                libInfo.put("mailContact", mailContact);
                libInfo.put("openingHours", openingHours);
                FireBaseModel.myRef.child("LibInfo").setValue(libInfo);
                Toast.makeText(activity, "information updated successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity, LibrarianHomeActivity.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }

}