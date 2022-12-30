package com.example.mylib.Utils;
import java.util.Base64;

public class Base64String {

    static String encode(byte[] hash_array)
    {
        return Base64.getEncoder().encodeToString(hash_array);
    }

    static byte[] decode(String hash_string)
    {
        return Base64.getDecoder().decode(hash_string);
    }
}
