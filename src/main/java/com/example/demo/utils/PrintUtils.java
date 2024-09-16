package com.example.demo.utils;

public class PrintUtils {
    PrintUtils(){}

    public static void infoMessage(String message) {
        System.out.println(">>> INFO: " + message);
    }

    public static void errorMessage(String message) {
        System.err.println(">>> ERROR: " + message);
    }
}
