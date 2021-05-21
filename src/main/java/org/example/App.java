package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private static final String FILE_NAME = "out.csv";

    public static void main(String[] args) throws IOException {

        int[] arr = {1, 2, 55, 6, 78, 3};

        writeToFile(FILE_NAME, arr, "; ");

        int[] arrRead = readArrFromFile(FILE_NAME, "; ");

        System.out.println(Arrays.toString(arrRead));

    }

    /**
     * Reads file by lines and writes these lines to a StringBuilder object. Returns this object converted to String
     * */
    private static String readFromFile(String fileName) throws IOException {

        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    /**
     * Splits the file, separated by separator, and adds result numbers to the file
     * */
    private static int[] readArrFromFile(String fileName, String separator) throws IOException {

        List<Integer> arrayList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                for (String number : line.split(separator)) {
                    arrayList.add(Integer.parseInt(number));
                }
            }
        }
        return arrayList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * Asks user for one-line console input. Returns this input
     * */
    private static String getConsoleInput() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter some text: ");
            return bufferedReader.readLine();
        }
    }

    /**
     * Writes text to a file
     * */
    private static void writeToFile(String fileName, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(text);
        }
    }

    /**
     * Takes an int array and writes its elements, separated with separator to the file
     * */
    private static void writeToFile(String fileName, int[] arr, String separator) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int el : arr) {
                fileWriter.write(el + separator);
            }
        }
    }

    /**
     * Appends text to a file
     * */
    private static void appendToFile(String fileName, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) { // true for append
            fileWriter.write(text);
        }
    }

    /**
     * Takes an int array and appends its elements, separated with separator to the file
     * */
    private static void appendToFile(String fileName, int[] arr, String separator) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            for (int el : arr) {
                fileWriter.write(el + separator);
            }
        }
    }
}
