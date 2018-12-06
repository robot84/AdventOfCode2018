package ovh.robot84.advent2018;

import java.io.PrintStream;

public class Verbose {

    private static boolean VERBOSE_ENABLE;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static String actualColor = ANSI_CYAN;

    public static void enableVerbose() {
        VERBOSE_ENABLE = true;
    }


    public static void disableVerbose() {
        VERBOSE_ENABLE = false;
    }


    private static void setColor() {
        System.out.print(actualColor);
    }

    public static void setColor(String color) {
        actualColor = color;
    }


    private static void resetColor() {
        System.out.print(ANSI_RESET);
    }


    public static void print(String... s) {
        if (VERBOSE_ENABLE) {
            setColor();
            for (int i = 0; i < s.length; i++) {
                System.out.println(s[i]);
            }
            resetColor();
        }
    }

    public static void println(String... s) {
        print(s);
        System.out.println();
    }


    public static void printf(String format, Object... args) {
        if (VERBOSE_ENABLE) {
            setColor();
            System.out.format(format, args);
            resetColor();
        }
    }

}
