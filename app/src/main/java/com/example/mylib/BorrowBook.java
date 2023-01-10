package com.example.mylib;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylib.DataBase.FireBaseBook;
import com.example.mylib.DataBase.FireBaseUser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

//This class represents the Borrowing act
//The class using 2 layouts file to represent the data to the use
//1) activity_borrow_book: contain a single ListView each represent a book
//2) book_list_detail: contain the design of each item on the list
public class BorrowBook extends AppCompatActivity {


    ListView bookList;

    public void goBack(View view) {
        startActivity(new Intent(this, ClientHomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);
        bookList = findViewById(R.id.bookList);
        //Fill the ListView with the available books
        FireBaseBook.showAvailableBooks(this, bookList, "");
    }

    public void search(View view) {
        TextView bookNameText = findViewById(R.id.bookName);
        String bookName = bookNameText.getText().toString();
        FireBaseBook.showAvailableBooks(this, bookList, bookName);
    }

    public void borrow(View view) {
        View parentView = (View) view.getParent();
        // Access the data associated with the list item, such as the book name and author
        TextView bookIdView = parentView.findViewById(R.id.bookId);
        TextView amountText = parentView.findViewById(R.id.noOfCopiesTextView);
        String bookId = bookIdView.getText().toString();
        int amount = Integer.parseInt(amountText.getText().toString());

        verifyPermission();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to add notification for return the book?");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

            addBorrowEvent();

            FireBaseUser.addToBorrowed(bookId, amount, BorrowBook.this);
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            FireBaseUser.addToBorrowed(bookId, amount, BorrowBook.this);

            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }


    public void verifyPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CALENDAR)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 1);
            }
        } else {
            // Permission has already been granted
        }
    }

    public void addBorrowEvent() {

        // When the user click yes button then app will close
        LocalDateTime now = null;
        ContentValues values = new ContentValues();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now().plusWeeks(2).minusHours(1L);


            // Set the start time of the event to the current date and time
            long startTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            values.put(CalendarContract.Events.DTSTART, startTime);

            // Set the end time of the event to 2 weeks from now
            LocalDateTime endDateTime = LocalDateTime.now().plusWeeks(2);
            long endTime = endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            values.put(CalendarContract.Events.DTEND, endTime);
            values.put(CalendarContract.Events.TITLE, "Book borrow");
            values.put(CalendarContract.Events.DESCRIPTION, "Has to return the book to the library" + " at the end date");
            values.put(CalendarContract.Events.CALENDAR_ID, 3);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

            getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
                // You can now perform the action that requires this permission
            } else {
                // Permission denied
                // You can disable the functionality that depends on this permission
            }
        }
    }
}