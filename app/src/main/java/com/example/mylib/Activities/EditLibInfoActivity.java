package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseModel;
import com.example.mylib.R;

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
        String openingHours = "\nSunday:" + sunday +
                "\n\nMonday:" + monday +
                "\n\nTuesday:" + tuesday +
                "\n\nWednesday:" + wednesday +
                "\n\nThursday:" + thursday +
                "\n\nFriday:" + friday +
                "\n\nSaturday:" + saturday;
        HashMap<String, String> map = new HashMap<>();
        map.put("libraryName", libraryName);
        map.put("phoneNumber", phoneNumber);
        map.put("mailContact", mailContact);
        map.put("openingHours", openingHours);
        FireBaseModel.myRef.child("LibInfo").setValue(map);
        Toast.makeText(this, "information updated successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LibrarianHomeActivity.class));


    }

}