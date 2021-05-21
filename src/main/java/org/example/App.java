package org.example;

import java.io.*;

public class App {

    private static final String FILE_NAME = "out.txt";

    public static void main(String[] args) throws IOException {

        writeToFile(FILE_NAME, getConsoleInput()); // Gets one line input from console and writes it to the file
        System.out.println(readFromFile(FILE_NAME)); // Reads a file by lines and prints it in a separate lines
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
     * Appends text to a file
     * */
    private static void appendToFile(String fileName, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) { // true for append
            fileWriter.write(text);
        }
    }
}
