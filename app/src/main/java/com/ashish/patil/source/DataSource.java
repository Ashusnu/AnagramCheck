package com.ashish.patil.source;

import java.util.Arrays;
import java.util.Locale;

public class DataSource {

    public static boolean isValidInput(String input) {
        return input.trim().length() > 0;
    }

    public static boolean isAnagram(String firstString, String secondString) {
        char[] string1 = firstString.toLowerCase(Locale.ENGLISH).toCharArray();
        char[] string2 = secondString.toLowerCase(Locale.ENGLISH).toCharArray();

        if (string1.length != string2.length) {
            return false;
        }

        Arrays.sort(string1);
        Arrays.sort(string2);

        for (int i = 0; i < string1.length; i++) {
            if (string1[i] != string2[i]) {
                return false;
            }
        }
        return true;
    }
}
