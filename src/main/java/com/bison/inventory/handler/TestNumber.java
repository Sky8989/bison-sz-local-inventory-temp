package com.bison.inventory.handler;

import java.util.regex.Pattern;

public class TestNumber {

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {

        System.out.println( " " + isNumeric("2.0"));
    }

}
