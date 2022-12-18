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

public class BookAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<Book> books;
    public BookAdapter(Context c, ArrayList<Book> books)
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

        View v = mInflater.inflate(R.layout.book_list_detail,null);
        TextView bookNameTextView = (TextView) v.findViewById(R.id.bookNameTextView);
        TextView authorTextView = (TextView) v.findViewById(R.id.authorTextView);
        TextView noOfCopiesTextView = (TextView) v.findViewById(R.id.noOfCopiesTextView);


        bookNameTextView.setText(books.get(i).getName());
        authorTextView.setText("Author: " + books.get(i).getAuthor());
        noOfCopiesTextView.setText("Number of copies: " + books.get(i).getAmount());

        return v;
    }
}
