package com.example.mylib.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mylib.R;

public class ClientHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        String username_entry = "Welcome " + GlobalUserInfoActivity.globalName;
        final TextView textViewToChange = (TextView) findViewById(R.id.titleClientName);
        textViewToChange.setText(username_entry);
    }
    public void goBack(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to log out ?");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            startActivity(new Intent(this, SignInCustomerActivity.class));
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();

    }

    public void goToBorrow(View view) {

        startActivity(new Intent(this, BorrowBookActivity.class));
    }

    public void goToReturn(View view) {

        startActivity(new Intent(this, ReturnBookActivity.class));
    }

    public void gotToProfile(View view) {

        startActivity(new Intent(this, ProfileClientActivity.class));
    }

    public void goToLibInfo(View view) {
        startActivity(new Intent(this, LibInfoClientActivity.class));
    }
}