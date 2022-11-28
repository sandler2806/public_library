package com.example.mylib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;

    String[] books;
    String[] authors;
    int[] noOfCopies;

    ItemAdapter(Context c, String[] b, String[] a, int[] n){
        books = b;
        authors = a;
        noOfCopies = n;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return books.length;
    }

    @Override
    public Object getItem(int i) {
        return books[i];
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

        String book = books[i];
        String author = authors[i];
        int noOfCopiesOfbook = noOfCopies[i];

        bookNameTextView.setText(book);
        authorTextView.setText(author);
        noOfCopiesTextView.setText("Number of copies: " + noOfCopiesOfbook);

        return v;
    }
}
