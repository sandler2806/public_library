package com.example.mylib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookLibrarian extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://public-library-8027f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().getDatabase();
    DatabaseReference myRef ;

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_librarian);
        ReadBook("1");
    }

    private void ReadBook(String Id){
        setMyRef(database.getReference("books"));
        this.myRef.child(Id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot snapshot = task.getResult();
                    String bookName = String.valueOf(snapshot.child("name").getValue());
                    Toast.makeText(AddBookLibrarian.this, bookName, Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(AddBookLibrarian.this,"No data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}