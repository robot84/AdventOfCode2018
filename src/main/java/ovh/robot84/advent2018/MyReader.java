package ovh.robot84.advent2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MyReader {

File file_path = null;
FileReader file = null;
Scanner sc = null;
BufferedReader bufferedReader = null;
String currentLine = null;


MyReader() {

}


MyReader(String path) {
    this.open_file(path);
}


void open_file(String path) {
    try {
        file_path = new File(path);
        file = new FileReader(file_path);
        bufferedReader = new BufferedReader(file);
    } catch (Exception zd) {
        System.out.println("Input file not found. Exiting...");
        System.exit(1);
        zd.printStackTrace();
    }
}


void close_file() {
    try {
        bufferedReader.close();
    } catch (IOException e) {
        System.out.println("Cannot close a file. Exiting...");
        System.exit(1);
        e.printStackTrace();
    }
}


String get_line() {
    try {
        currentLine = bufferedReader.readLine();
    } catch (IOException e) {
        System.out.println("Exception: Cannot read line. Exiting.");
        System.exit(1);
        e.printStackTrace();
    }
    sc = null;
    return currentLine;
}


String readLine() {
    return get_line();
}


int read() {
    try {
        return bufferedReader.read();
    } catch (IOException e) {
        System.out.println("Exception occured: Cannot read char. Exiting.");
        e.printStackTrace();
    }
    return Integer.parseInt(null);
}


String read_char() {
    int i = 0;
    try {
        i = bufferedReader.read();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (i != -1)
            return String.valueOf((char) i);
        else
            return null;
    }

}


String read_string(String delimeter) {
    if (sc == null) {

        if (currentLine == null) {
            System.out.println("Exception: Use get_line() method before reading from file.");
            System.exit(1);
        }
        try {

            sc = new Scanner(currentLine).useDelimiter(delimeter);
        } catch (Exception zd) {
            zd.printStackTrace();
        }
    }

    if (sc.hasNext()) {
        return sc.next();

    } else {
        return null;
    }
}


Integer read_int(String delimeter) {
    if (sc == null) {
        if (currentLine == null) {
            System.out.println("Exception: Use get_line() method before reading from file.");
            System.exit(1);
        }

        try {
            sc = new Scanner(currentLine);
        } catch (Exception zd) {
            zd.printStackTrace();
        }
    }

    if (sc.hasNextInt()) {
        return sc.nextInt();

    } else {
        return null;
    }
}


Double read_double(String delimeter) {
    if (sc == null) {
        if (currentLine == null) {
            System.out.println("Exception: Use get_line() method before reading from file.");
            System.exit(1);
        }

        try {
            sc = new Scanner(currentLine);
        } catch (Exception zd) {
            zd.printStackTrace();
        }
    }

    if (sc.hasNextDouble()) {
        return sc.nextDouble();

    } else {
        return null;
    }
}

}