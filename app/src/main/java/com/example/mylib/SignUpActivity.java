
package com.example.mylib;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylib.DataBase.FireBaseUser;
public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void openClientHome(View view){
//        get data from text views
        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        TextView verifyPasswordText = findViewById(R.id.verify_password);
        TextView nameText = findViewById(R.id.name);
        TextView phoneText = findViewById(R.id.phone);
        String username=usernameText.getText().toString();
        String password=passwordText.getText().toString();
        String verifyPassword=verifyPasswordText.getText().toString();
        String name=nameText.getText().toString();
        String phone=phoneText.getText().toString();
//        Save the user's data
        GlobalUserInfo.global_user_name = username;
        GlobalUserInfo.global_name = name;
//        add user to the database
//        if(!checkPermission(SignUpActivity.this)){
//            getPermission(SignUpActivity.this);
//        }
//        else {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage("+97254-477-7074", null, "Hi", null, null);//SmsManager smsManager = SmsManager.getDefault();
////        smsManager.sendTextMessage("0544777074",null,"Hi", null, null)
//        }
        FireBaseUser.addUser(this,username,password,verifyPassword,name,phone);
//        sendMessage("555-123-4567",this);

    }
//    private boolean checkPermission(Context context){
//        int resultSms = ContextCompat.checkSelfPermission(context,Manifest.permission.RECEIVE_SMS);
//        int readSms = ContextCompat.checkSelfPermission(context,Manifest.permission.SEND_SMS);
//        return resultSms == PackageManager.PERMISSION_GRANTED && readSms == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void getPermission(Activity activity){
//        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_SMS
//                        ,Manifest.permission.SEND_SMS},
//                1);
//    }
//    static String phoneNumber;
//    public static void sendMessage(String phoneNum, Activity activity) {
//        phoneNumber = phoneNum;
//        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_SMS
//                            ,Manifest.permission.SEND_SMS},
//                    100);
//        } else {
//            // Permission has already been granted
//            sendText();
//        }
//    }
//
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        if(requestCode == 100 && grantResults.length > 0
////                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
////            sendText();
////        }
////        else{
////            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
////        }
////    }
//
//    public static void sendText(){
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(phoneNumber,null,"Hi", null, null);
//    }
    public void goBack(View view){
        startActivity(new Intent(this, SignInCustomer.class));
    }
}

