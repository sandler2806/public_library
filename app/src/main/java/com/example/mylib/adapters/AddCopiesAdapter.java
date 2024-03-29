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

public class AddCopiesAdapter extends BaseAdapter {
    //This adapter is used in BorrowBook, to list
    //a collection of books within a ListView
    LayoutInflater mInflater;
    ArrayList<Book> books;
    ArrayList<String> booksKey;

    public AddCopiesAdapter(Context c, ArrayList<Book> books, ArrayList<String> booksKey) {
        this.books = books;
        this.booksKey = booksKey;
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
    public View getView(int i, View view, ViewGroup viewGroup) {//called for each items
        //Get handle for the text views
        View v = mInflater.inflate(R.layout.add_copies_detail, null);
        TextView idTextView = (TextView) v.findViewById(R.id.bookId);
        idTextView.setText(booksKey.get(i));
        TextView bookNameTextView = (TextView) v.findViewById(R.id.bookNameTextView);
        TextView authorTextView = (TextView) v.findViewById(R.id.authorTextView);
        TextView noOfCopiesTextView = (TextView) v.findViewById(R.id.noOfCopiesTextView);

        //set the text of each field
        bookNameTextView.setText("Book name: " + books.get(i).getName());
        authorTextView.setText("Author: " + books.get(i).getAuthor());
        noOfCopiesTextView.setText("Number of copies: " + books.get(i).getAmount());

        return v;
    }
}
