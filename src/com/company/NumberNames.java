/*
 * Collin Hurley
 * 10/22/2018
 * CS 203
 * */
package com.company;
public class NumberNames {
    // String of names of numbers to be accessed by the display method
    private static final String[] onesNames = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    // String of names of tens numbers to be accessed by the display method
    private static final String[] tensNames = {
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    /**************************************************************/
    /* Method: capitalizedNumberToWords */
    /* Purpose: Takes in an integer number and outputs a word*/
    /* version of it capitalized in the correct places
    /* Parameters: an integer number to be converted */
    /* int: an integer to be converted to its word representation */
    /* Returns: String capitalizedNumberToWords the string  */
    /* representation of the number inputed from 0 - 99 */
    /**************************************************************/
    public static String capitalizedNumberToWords (int number) {
        String result = convertNumberToWords(number);
        String s1 = result.substring(0, 1).toUpperCase();
        return s1 + result.substring(1);
    }
    /**************************************************************/
    /* Method: convertNumberToWords */
    /* Purpose: Takes in an integer number and outputs a word*/
    /* version of it from, helper function for the capitalized
    /* Function above
    /* Parameters: an integer number to be converted */
    /* int: an integer to be converted to its word representation */
    /* Returns: String capitalizedNumberToWords the string  */
    /* representation of the number inputed from 0 - 99 */
    /**************************************************************/
    public static String convertNumberToWords (int number) {
        if (number < 20) {
            return onesNames[number];
        }
        // tensName stores the string name in the tens place
        String tensName = tensNames[number / 10 - 2];
        if (number % 10 == 0) {
            return tensName;
        }

        return tensName + "-" + onesNames[number % 10];
    }
}
