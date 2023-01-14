package com.example.mylib.DataBase;

import static com.example.mylib.DataBase.FireBaseUser.getUser;

import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.Activities.GlobalUserInfoActivity;
import com.example.mylib.Objects.Book;
import com.example.mylib.Objects.BorrowedBook;
import com.example.mylib.Objects.User;
import com.example.mylib.adapters.AddCopiesAdapter;
import com.example.mylib.adapters.BookAdapter;
import com.example.mylib.adapters.BookTrackAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FireBaseBook extends FireBaseModel {

    public static void addBook(String bookName, String author, String genre, int amount, String publishingYear,
                               Activity activity) {
        getAllBook().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean flag = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    assert book != null;
                    if (book.getName().equals(bookName) && book.getAuthor().equals(author)) {
                        flag = true;
                        //found this book exists increment its amount in the fire base.
                        Toast.makeText(activity, "Book already exist", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if (!flag) {
                    Book book = new Book(bookName, author, genre, publishingYear, amount);
                    DatabaseReference bookRef = myRef.child("books").push();
                    bookRef.setValue(book);
                    Toast.makeText(activity, "Added book successfully", Toast.LENGTH_SHORT).show();
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    activity.startActivity(activity.getIntent());
                    activity.overridePendingTransition(0, 0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public static void addCopies(String bookId, int amount, Activity activity) {
        getBook(bookId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (amount < 0) {
                    Toast.makeText(activity, "invalid input", Toast.LENGTH_SHORT).show();
                } else {
                    Book book = dataSnapshot.getValue(Book.class);
                    book.setAmount(book.getAmount() + amount);
                    FireBaseModel.myRef.child("books").child(bookId).setValue(book);
                    Toast.makeText(activity, "Added copies of books successfully", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static DatabaseReference getBook(String bookName) {
        return myRef.child("books").child(bookName);
    }

    public static void showBorrowedBooks(Activity activity, ListView bookList, String searchKey) {
        //the key is the name of a book and the value is a list of the users who borrowed it
        HashMap<String, ArrayList<String>> borrowed = new HashMap<>();
        ArrayList<String> ids = new ArrayList<>();
        DatabaseReference usersRef = FireBaseUser.getAllUsers();
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //iterate all the users in the database and create hashmap
                //so each book has a list of the users that borrowed it
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    assert user != null;
                    for (BorrowedBook book : user.getBooks()) {
                        if (!borrowed.containsKey(book.getKey())) {
                            borrowed.put(book.getKey(), new ArrayList<>());
                        }
                        borrowed.get(book.getKey()).add(user.getUsername());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference booksRef = FireBaseBook.getAllBook();
        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Book> books = new ArrayList<>();
                //add all the books that borrowed by a user
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    if (borrowed.containsKey(snapshot.getKey())) {
                        assert book != null;
                        if (book.getName().toLowerCase().startsWith(searchKey.toLowerCase())) {
                            books.add(book);
                            ids.add(snapshot.getKey());
                        }
                    }
                }
                BookTrackAdapter adapter = new BookTrackAdapter(activity, books, borrowed, ids);
                bookList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    public static void showExistBooks(Activity activity, ListView bookList, String key) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();
        //Fill ListView in the given activity with the available books
        getAllBook().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    if (!book.getName().toLowerCase().startsWith(key.toLowerCase())) {
                        continue;
                    }
                    books.add(snapshot.getValue(Book.class));
                    ids.add(snapshot.getKey());
                }
                AddCopiesAdapter adapter = new AddCopiesAdapter(activity, books, ids);
                bookList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    public static void showAvailableBooks(Activity activity, ListView bookList, String key) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<String> idOfBorrowed = new ArrayList<>();
        ArrayList<String> borrowedBooksKey = new ArrayList<>();
        getUser(GlobalUserInfoActivity.globalUserName,
                response -> {
                    User user = response.body();
                    assert user != null;
                    for (BorrowedBook book : user.getBooks()) {
                        borrowedBooksKey.add(book.getKey());
                    }

                    FireBaseBook.getAllBook().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Book book = snapshot.getValue(Book.class);
                                if (book.getAmount() == 0 || borrowedBooksKey.contains(snapshot.getKey()) || !book.getName().toLowerCase().startsWith(key.toLowerCase())) {
                                    continue;
                                }
                                books.add(snapshot.getValue(Book.class));
                                idOfBorrowed.add(snapshot.getKey());
                            }
                            BookAdapter adapter = new BookAdapter(activity, books, idOfBorrowed);
                            bookList.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                });
    }

    public static void removeBook(Activity activity, Book bookToDelete) {

        getAllBook().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = new Book();
                String key = "";
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    book = snapshot.getValue(Book.class);
                    if (book.getName().equals(bookToDelete.getName()) && book.getAuthor().equals(bookToDelete.getAuthor())
                            && book.getGenre().equals(bookToDelete.getGenre()) && book.getPublishingYear().equals(bookToDelete.getPublishingYear())) {
                        key = snapshot.getKey();
                        break;
                    }
                }
                if (!key.equals("")) {
                    int amount = bookToDelete.getAmount();
                    // get the amount of the book in order to prevent multiple getAmount calls
                    int bookAmount = book.getAmount();
                    // check for valid input, if the wanted amount is bigger
                    // than the current then remove all.
                    if (bookAmount > 0 && bookAmount < amount) {
                        Toast.makeText(activity, "can't delete " + amount + " books, there are only "
                                + bookAmount + " books", Toast.LENGTH_LONG).show();
                    }
                    //if the amount is smaller than the current so remove the wanted amount
                    else if (bookAmount > 0 && amount > 0) {
                        book.setAmount(bookAmount - amount);
                        getBook(key).setValue(book);
                        String deletedAmountAns = amount + " books deleted";
                        Toast.makeText(activity, deletedAmountAns, Toast.LENGTH_SHORT).show();
                    }
                    // for zero book available
                    else if (bookAmount == 0) {
                        Toast.makeText(activity, "No books to delete", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void returnBook(String bookId, Activity activity) {
        getBook(bookId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);
                //increase the amount of the book by 1
                if (book != null) {
                    int amount = book.getAmount();
                    getBook(bookId).child("amount").setValue(amount + 1);
                    //refresh the activity
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    activity.startActivity(activity.getIntent());
                    activity.overridePendingTransition(0, 0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public static DatabaseReference getAllBook() {
        return myRef.child("books");
    }
}
