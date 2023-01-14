package com.example.mylib.Objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowedBook {

    private String key;
    private String borrowDate;

    public BorrowedBook() {
        // Default constructor for DataSnapshot.getValue
    }

    public BorrowedBook(String key) {
        this.key = key;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // Get the current date
        Date currentDate = new Date();
        // Format the date using the SimpleDateFormat object
        this.borrowDate = sdf.format(currentDate);
    }

    public String getKey() {
        return key;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
}
