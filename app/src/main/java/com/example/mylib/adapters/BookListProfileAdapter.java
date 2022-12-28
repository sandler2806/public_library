package com.example.mylib.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylib.Objects.BorrowedBook;
import com.example.mylib.R;

import java.util.ArrayList;

public class BookListProfileAdapter extends BaseAdapter {
    //This adapter is used in BorrowBook, to list
    //a collection of books within a ListView
    LayoutInflater mInflater;
    ArrayList<BorrowedBook> books;
    public BookListProfileAdapter(Context c, ArrayList<BorrowedBook> books)
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
        View v = mInflater.inflate(R.layout.profile_client_book_list,null);
        TextView bookNameTextView = (TextView) v.findViewById(R.id.bookNameTextViewProfileClient);
        bookNameTextView.setText(books.get(i).getName());


        return v;
    }
}