package com.example.mylib.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylib.Objects.Book;
import com.example.mylib.R;

import java.util.ArrayList;
import java.util.HashMap;

public class BookTrackAdapter extends BaseAdapter {
    //This is adapter is used in BookTrackingActivity, to list
    //a collection of borrowed books within a ListView
    LayoutInflater mInflater;
    ArrayList<Book> books;
    HashMap<String,ArrayList<String>> borrowed;
    public BookTrackAdapter(Context c, ArrayList<Book> books, HashMap<String,ArrayList<String>> borrowed)
    {
        this.books = books;
        this.borrowed=borrowed;
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
        //Get handle for the text views
        View v = mInflater.inflate(R.layout.book_tracking,null);
        TextView bookNameTextView = (TextView) v.findViewById(R.id.bookNameTextView);
        TextView authorTextView = (TextView) v.findViewById(R.id.authorTextView);
        TextView noOfAvailableCopiesTextView = (TextView) v.findViewById(R.id.noOfAvailableCopiesTextView);
        TextView noOfBorrowedCopiesTextView = (TextView) v.findViewById(R.id.noOfBorrowedCopiesTextView);
        TextView borrowedByTextView = (TextView) v.findViewById(R.id.borrowedByTextView);

        StringBuilder borrowedBy= new StringBuilder("\n");
        for(String name :borrowed.get(books.get(i).getName())){
            borrowedBy.append(name).append("\n");
        }
        //set the text of each field
        bookNameTextView.setText(books.get(i).getName());
        authorTextView.setText("Author: " + books.get(i).getAuthor());
        noOfAvailableCopiesTextView.setText("Number of available copies: " + books.get(i).getAmount());
        noOfBorrowedCopiesTextView.setText("Number of borrowed copies: " + borrowed.get(books.get(i).getName()).size());
        borrowedByTextView.setText("Borrowed by: " + borrowedBy);

        return v;
    }
}
