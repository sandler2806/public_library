package com.example.mylib.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylib.Objects.Book;
import com.example.mylib.Objects.BorrowedBook;
import com.example.mylib.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BookListProfileAdapter extends BaseAdapter {
    //This adapter is used in BorrowBook, to list
    //a collection of books within a ListView
    LayoutInflater mInflater;
    ArrayList<Book> books;
    ArrayList<BorrowedBook> borrowedBooks;

    public BookListProfileAdapter(Context c, ArrayList<Book> books, ArrayList<BorrowedBook> borrowedBooks) {
        this.books = books;
        this.borrowedBooks = borrowedBooks;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Set the views
        View v = mInflater.inflate(R.layout.profile_client_book_list, null);
        TextView bookNameTextView = (TextView) v.findViewById(R.id.bookNameTextViewProfileClient);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(borrowedBooks.get(i).getBorrowDate(), formatter);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the difference between the two dates
        long days = ChronoUnit.DAYS.between(date, currentDate);

        String show = "Book name: " + books.get(i).getName();
        if (days > 14) {
            show += "\nThe book is " + (days - 14) + " days late";
            bookNameTextView.setTextColor(Color.RED);
        }
        bookNameTextView.setText(show);


        return v;
    }
}