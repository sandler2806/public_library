package com.example.mylib.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylib.Activities.ClientHomeActivity;
import com.example.mylib.Activities.GlobalUserInfoActivity;
import com.example.mylib.Objects.Book;
import com.example.mylib.Objects.BorrowedBook;
import com.example.mylib.Objects.User;
import com.example.mylib.Utils.Base64String;
import com.example.mylib.Utils.Hash;
import com.example.mylib.adapters.BookListProfileAdapter;
import com.example.mylib.adapters.ReturnBookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FireBaseUser extends FireBaseModel {

    private static String baseUrl = "http://10.0.2.2:3000/";

    // Retrofit builder
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private static UserApiCall myAPICall = retrofit.create(UserApiCall.class);

    public static void getUser(String userName, Callback callback) {

        Call<User> call = myAPICall.getUser(userName);
        call.enqueue(callback);

    }

    public static void getUser(String userName, Consumer<Response<User>> onResponse) {

        try {
            Call<User> call = myAPICall.getUser(userName);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() != 200) {
                        System.out.println("Failure in recieve data");
                        return;
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        onResponse.accept(response);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("Error occurred in get user");
                }
            });
        } catch (Exception e) {
            System.out.println("Error in get user");
        }


    }


    public static DatabaseReference getUserRef(String userName) {
        return myRef.child("users").child(userName);
    }

    public static void addUser(Activity activity, String username, String password,
                               String verifyPassword, String name, String phone) {

        getUser(username,
                response -> {
                    User user = response.body();
                    if (user != null) {
                        Toast.makeText(activity, "username already exist", Toast.LENGTH_SHORT).show();
                    }
                    //check if the passwords match
                    else if (!password.equals(verifyPassword)) {
                        Toast.makeText(activity, "verify password does not match to password",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        //create new user in the database
                        byte[] hash_array = Hash.encrypt(password);
                        String encoded_password = Base64String.encode(hash_array);
                        User newUser = new User(username, encoded_password, name, phone);
                        myRef.child("users").child(username).setValue(newUser);
                        Toast.makeText(activity, "sign up successfully", Toast.LENGTH_SHORT).show();
                        activity.startActivity(new Intent(activity, ClientHomeActivity.class));
                    }
                });
    }


    //function to sign in customer
    public static void signInCustomer(String username, String password, Activity activity) {
        getUser(username,
                response -> {
                    User user = response.body();
                    if (user == null) {
                        Toast.makeText(activity, "username does not exist", Toast.LENGTH_SHORT).show();
                    } else {
                        String password_string_hash = user.getPassword();
                        byte[] password_bytes_hash = Base64String.decode(password_string_hash);
                        if (!Hash.verifyPassword(password, password_bytes_hash)) {
                            Toast.makeText(activity, "wrong password", Toast.LENGTH_SHORT).show();
                        }
                        //Save the user's data
                        else {
                            GlobalUserInfoActivity.globalName = user.getName();
                            GlobalUserInfoActivity.globalUserName = username;
                            GlobalUserInfoActivity.globalPhoneNumber = user.getPhone();
                            Toast.makeText(activity, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                            activity.startActivity(new Intent(activity, ClientHomeActivity.class));
                        }
                    }
                });
    }

    public static void createBookListForReturn(ListView bookList, Activity activity, String searchKey) {
        ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();
        getUser(GlobalUserInfoActivity.globalUserName,
                response -> {
                    User user = response.body();
                    if (user != null) {
                        borrowedBooks.addAll(user.getBooks());
                    }
                    DatabaseReference booksRef = FireBaseBook.getAllBook();
                    booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ArrayList<Book> books = new ArrayList<>();
                            System.out.println(borrowedBooks);
                            //add all the books that borrowed by a user
                            for (BorrowedBook borrowedBook : borrowedBooks) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Book book = snapshot.getValue(Book.class);
                                    if (borrowedBook.getKey().equals(snapshot.getKey()) &&
                                            book.getName().toLowerCase().startsWith(searchKey.toLowerCase())) {
                                        books.add(book);
                                        System.out.println("here and added book: " + book.getName());
                                    }
                                }
                            }
                            ReturnBookAdapter adapter = new ReturnBookAdapter(activity, borrowedBooks, books);
                            bookList.setAdapter(adapter);
                            if (books.isEmpty()) {
                                Toast.makeText(activity, "No books to return", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                });
    }

    public static void createBookListForProfileClient(ListView bookList, Activity activity, String userName) {

        ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();
        getUser(userName,
                response -> {
                    User user = response.body();
                    if (user != null) {
                        borrowedBooks.addAll(user.getBooks());
                    }

                    DatabaseReference booksRef = FireBaseBook.getAllBook();
                    booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ArrayList<Book> books = new ArrayList<>();
                            //add all the books that borrowed by a user
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Book book = snapshot.getValue(Book.class);
                                for (BorrowedBook borrowedBook : borrowedBooks) {
                                    if (borrowedBook.getKey().equals(snapshot.getKey())) {
                                        books.add(book);
                                    }
                                }
                            }
                            if (!books.isEmpty()) {
                                BookListProfileAdapter adapter = new BookListProfileAdapter(activity,
                                        books, borrowedBooks);
                                bookList.setAdapter(adapter);
                            } else {
                                Toast.makeText(activity, "No books burrowed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                });
    }

    public static DatabaseReference getAllUsers() {
        return myRef.child("users");
    }


    public static void addToBorrowed(String bookId, int amount, Activity activity) {
        getUser(GlobalUserInfoActivity.globalUserName,
                response -> {
                    User user = response.body();
                    ArrayList<BorrowedBook> books = user.getBooks();
                    BorrowedBook borrowedBook = new BorrowedBook(bookId);
                    books.add(borrowedBook);
                    FireBaseBook.getBook(bookId).child("amount").setValue(amount - 1);
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    activity.startActivity(activity.getIntent());
                    activity.overridePendingTransition(0, 0);
                    getUserRef(GlobalUserInfoActivity.globalUserName).child("books").setValue(books);
                    Toast.makeText(activity, "Book borrowed successfully",
                            Toast.LENGTH_SHORT).show();
                });
    }

    public static void showUser(String username, TextView usernameText, TextView nameText,
                                TextView phoneText, Activity activity, ListView bookList) {
        getUser(username,
                response -> {
                    User user = response.body();
                    if (user == null) {
                        usernameText.setText("");
                        nameText.setText("");
                        phoneText.setText("");
                        Toast.makeText(activity, "user does not exist",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        usernameText.setText("username: " + username);
                        nameText.setText("name: " + user.getName());
                        phoneText.setText("phone: " + user.getPhone());
                        FireBaseUser.createBookListForProfileClient(bookList, activity, username);
                    }
                });

    }

    public static void removeFromBorrowed(String bookId) {
        getUser(GlobalUserInfoActivity.globalUserName,
                response -> {
                    User user = response.body();
                    assert user != null;
                    ArrayList<BorrowedBook> books = user.getBooks();
                    //remove the book from the list
                    if (books != null) {
                        for (BorrowedBook borrowedBook : books) {
                            if (borrowedBook.getKey().equals(bookId)) {
                                books.remove(borrowedBook);
                                break;
                            }
                        }
                    }
                    //update the user's books list
                    getUserRef(GlobalUserInfoActivity.globalUserName).child("books").setValue(books);
                });
    }
}