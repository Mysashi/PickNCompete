package com.pickcomplete.helpers;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {

    public static String getRandomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST0123456789".toCharArray();

        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String randomStr = sb.toString();

        return randomStr;
    }
}
