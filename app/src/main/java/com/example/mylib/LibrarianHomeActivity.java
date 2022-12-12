package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LibrarianHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_home);
    }
    public void GoToAddBookLibrarian(View view){
        startActivity(new Intent(this, AddBookLibrarian.class));
    }
    public void GoToRemoveBookLibrarian(View view){
        startActivity(new Intent(this, RemoveBookActivity.class));
    }
    public void goBack(View view){
        startActivity(new Intent(this, SignInLibrarian.class));
    }
    public void openBookTracking(View view){
        startActivity(new Intent(this, BookTrackingActivity.class));
    }
}