package com.example.demo.utils;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    InputUtils() {
    }

    public static int inputNumber() {
        int count = 1;
        while (count <= 3) {
            try {
                String text = scanner.nextLine();
                return Integer.parseInt(text);
            } catch (Exception exception) {
                System.err.println("Please enter a numeric value.");
            }
            count++;
        }
        return -1;
    }
}
