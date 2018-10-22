package com.company;
public class NumberNames {
    private static final String[] onesNames = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    private static final String[] tensNames = {
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public static String capitalizedNumberToWords (int n) {
        String result = convertNumberToWords(n);
        String s1 = result.substring(0, 1).toUpperCase();
        return s1 + result.substring(1);
    }

    public static String convertNumberToWords (int n) {
        if (n < 20) {
            return onesNames[n];
        }

        String s = tensNames[n / 10 - 2];
        if (n % 10 == 0) {
            return s;
        }

        return s + "-" + onesNames[n % 10];
    }
}
