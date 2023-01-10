package com.example.mylib;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LibrarianHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_home);
    }

    public void GoToAddBookLibrarian(View view) {
        startActivity(new Intent(this, AddBookLibrarian.class));
    }

    public void GoToRemoveBookLibrarian(View view) {
        startActivity(new Intent(this, RemoveBookActivity.class));
    }
    public void goBack(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to log out ?");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the admin click yes button then app will close
            startActivity(new Intent(this, SignInLibrarian.class));
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

    public void openBookTracking(View view) {
        startActivity(new Intent(this, BookTrackingActivity.class));
    }

    public void openEditLibInfo(View view) {
        startActivity(new Intent(this, EditLibInfo.class));
    }

    public void openSearchCustomer(View view) {
        startActivity(new Intent(this, SearchCustomerActivity.class));
    }

    public void openAddCopies(View view) {
        startActivity(new Intent(this, AddCopiesActivity.class));
    }



}