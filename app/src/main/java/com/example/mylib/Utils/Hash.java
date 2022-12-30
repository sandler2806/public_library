package com.example.mylib.Utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

//This class encrypt and decrypt the passwords
//before storing and retrieving them from firebase
public class Hash {

    public static byte[] encrypt(String password)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    public static boolean verifyPassword(String password, byte[] passwordHash) {
        byte[] hash = encrypt(password);
        return Arrays.equals(hash, passwordHash);
    }
}
