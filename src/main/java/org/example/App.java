package org.example;

import java.io.*;

public class App {

    public static void main( String[] args ) throws IOException {

        String fileName = "out.txt";

//        writeToFile(fileName, "Hello"); // Hello
//        appendToFile(fileName, "Woo"); // HelloWoo
//        appendToFile(fileName, "Woo"); // HelloWooWoo
//        appendToFile(fileName, "Woo"); // HelloWooWooWoo

        appendToFile(fileName, "What"); // HelloWooWooWooWhat
    }

    private static void writeToFile(String fileName, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(text);
        fileWriter.close();
    }

    private static void appendToFile(String fileName, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true); // true for append
        fileWriter.write(text);
        fileWriter.close();
    }
}
