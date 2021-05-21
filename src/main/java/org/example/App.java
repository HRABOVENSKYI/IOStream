package org.example;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String FILE_NAME = "out.csv";
    private static final String URL = "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/59cb6066-1fac-41ed-a571-811db551c75b/download/zp-lupen-2019.csv";
    private static final String ENCODING = "windows-1251";

    public static void main(String[] args) throws Exception {

        System.out.println(readArrFromURL(URL, ";")); // prints all URL info

    }

    /**
     * Reads file by lines and writes these lines to a StringBuilder object. Returns this object converted to String
     */
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
     */
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

    private static List<User> readArrFromURL(String url, String separator) throws IOException {

        List<User> users = new ArrayList<>();

        URL website = new URL(url);
        URLConnection connection = website.openConnection();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), ENCODING))) {
            String line;
            in.readLine(); // skip first header line
            while ((line = in.readLine()) != null) {
                String[] attributes = line.split(separator);
                String name = attributes[0];
                String position = attributes[1];
                Double totalSalary = Double.parseDouble(attributes[11].replace(",", "."));
                users.add(new User(name, position, totalSalary));
            }
        }
        return users;
    }

    /**
     * Asks user for one-line console input. Returns this input
     */
    private static String getConsoleInput() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter some text: ");
            return bufferedReader.readLine();
        }
    }

    /**
     * Writes text to a file
     */
    private static void writeToFile(String fileName, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(text);
        }
    }

    /**
     * Takes an int array and writes its elements, separated with separator to the file
     */
    private static void writeToFile(String fileName, int[] arr, String separator) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int el : arr) {
                fileWriter.write(el + separator);
            }
        }
    }

    /**
     * Appends text to a file
     */
    private static void appendToFile(String fileName, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) { // true for append
            fileWriter.write(text);
        }
    }

    /**
     * Takes an int array and appends its elements, separated with separator to the file
     */
    private static void appendToFile(String fileName, int[] arr, String separator) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            for (int el : arr) {
                fileWriter.write(el + separator);
            }
        }
    }

    /**
     * Read text from URL with a specific encoding (which can be omitted if all works fine)
     */
    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), ENCODING));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }
}
