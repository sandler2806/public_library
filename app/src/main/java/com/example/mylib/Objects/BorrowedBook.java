package com.example.mylib.Objects;

import com.example.mylib.GlobalUserInfo;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowedBook {

    private String name;

    private String borrow_date;

    public BorrowedBook(){
        // Default constructor for DataSnapshot.getValue
    }
    public BorrowedBook(String name){
        this.name = name;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // Get the current date
        Date currentDate = new Date();
        // Format the date using the SimpleDateFormat object
        this.borrow_date = sdf.format(currentDate);
    }

    public String getName() {return name;}

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }
}
