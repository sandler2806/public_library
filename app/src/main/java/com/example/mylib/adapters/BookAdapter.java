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
    //This adapter is used in BorrowBook, to list
    //a collection of books within a ListView
    LayoutInflater mInflater;
    ArrayList<Book> books;
    ArrayList<String> borrowedBooksKey;

    public BookAdapter(Context c, ArrayList<Book> books, ArrayList<String> borrowedBooksKey) {
        this.books = books;
        this.borrowedBooksKey = borrowedBooksKey;
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
        View v = mInflater.inflate(R.layout.book_list_detail, null);
        TextView idTextView = (TextView) v.findViewById(R.id.bookId);
        idTextView.setText(borrowedBooksKey.get(i));
        TextView amountTextView = (TextView) v.findViewById(R.id.noOfCopiesTextView);
        amountTextView.setText(String.valueOf(books.get(i).getAmount()));

        TextView bookNameTextView = (TextView) v.findViewById(R.id.bookNameTextView);
        TextView authorTextView = (TextView) v.findViewById(R.id.authorTextView);
        TextView genreTextView = (TextView) v.findViewById(R.id.genreTextView);
        TextView yearPublishedTextView = (TextView) v.findViewById(R.id.yearPublishedTextView);

        //set the text of each field
        bookNameTextView.setText("Book name: " + books.get(i).getName());
        authorTextView.setText("Author: " + books.get(i).getAuthor());
        genreTextView.setText("Genre: " + books.get(i).getGenre());
        yearPublishedTextView.setText("Publish year: " + books.get(i).getPublishingYear());

        return v;
    }
}
