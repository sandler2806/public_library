package com.example.mylib.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.mylib.Objects.BorrowedBook;
import com.example.mylib.R;

import java.util.ArrayList;

public class ReturnBookAdapter extends BaseAdapter {
    //This adapter is used in BorrowBook, to list
    //a collection of books within a ListView
    LayoutInflater mInflater;
    ArrayList<BorrowedBook> books;
    public ReturnBookAdapter(Context c, ArrayList<BorrowedBook> books)
    {
        this.books = books;
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
        View v = mInflater.inflate(R.layout.return_book_list,null);
        TextView bookNameTextView = (TextView) v.findViewById(R.id.bookNameTextView);
        bookNameTextView.setText(books.get(i).getName());
        TextView returnDate = (TextView) v.findViewById(R.id.ReturnDateTview);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(books.get(i).getBorrow_date(), formatter);

        // Add 14 days to the date
        LocalDate newDate = date.plusDays(14);

        // Format the new date and print it
        returnDate.setText("Return date: " + formatter.format(newDate));

        return v;
    }
}
