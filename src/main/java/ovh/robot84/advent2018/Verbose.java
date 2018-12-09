package ovh.robot84.advent2018;

import java.sql.SQLOutput;

public class Verbose {

    private static final int DEFAULT_ENABLED_ON_LEVEL_NUM = 1;
    private static final int
            DEFAULT_VERBOSE_LEVEL_FOR_UNMODIFIED_BY_setVerboseLevelForNextPrint_METHOD_USER_PRINTS = 1;
    private static final int spaceForPrompt = 1;

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

    private static boolean VERBOSE_ENABLE;
    private static boolean VERBOSE_MUTED;
    private static boolean MULTI_LEVEL_VERBOSE_ENABLED;

    private static String actualColor = ANSI_CYAN;
    private static Object[] promptForVerboseClassEvents = {"*** "};
    private static Object[] promptsForUserPrints = {">", ">>", ">>>"};
    private static int enabledOnLevel = DEFAULT_ENABLED_ON_LEVEL_NUM;
    private static int verboseLevelForNextPrintF = DEFAULT_VERBOSE_LEVEL_FOR_UNMODIFIED_BY_setVerboseLevelForNextPrint_METHOD_USER_PRINTS;



    public static void enableVerbose() {
        VERBOSE_ENABLE = true;
        printInColor("%sVerbose enabled.\n", promptForVerboseClassEvents);
    }


    public static void disableVerbose() {
        VERBOSE_ENABLE = false;
        printInColor("%sVerbose disabled.\n", promptForVerboseClassEvents);
    }


    public static void mute() {
        VERBOSE_MUTED = true;
        printInColor("%sVerbose muted.\n", promptForVerboseClassEvents);
    }


    public static void unmute() {
        VERBOSE_MUTED = false;
        printInColor("%sVerbose unmuted.\n", promptForVerboseClassEvents);
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


    public static void print(String s) {
            if (VERBOSE_ENABLE && !VERBOSE_MUTED) {
                if (Verbose.verboseLevelForNextPrintF <= Verbose.enabledOnLevel) {
                    Verbose.printInColor(s);
                    Verbose.verboseLevelForNextPrintF =
                            DEFAULT_VERBOSE_LEVEL_FOR_UNMODIFIED_BY_setVerboseLevelForNextPrint_METHOD_USER_PRINTS;
                }
                return;
            }
            return;
    }


    public static void println(String s) {
        print(s + "\n");
    }



    public static void printf(String format, Object... args) {
        if (VERBOSE_ENABLE && !VERBOSE_MUTED) {
            if (Verbose.verboseLevelForNextPrintF <= Verbose.enabledOnLevel) {
                // TODO change String to StringBuilder
                String formatWithPrompt = "%s ".concat(format);
                Object[] objectsWithPromptBeforeThey = new Object[args.length + spaceForPrompt];
                objectsWithPromptBeforeThey[0] = promptsForUserPrints[Verbose.verboseLevelForNextPrintF - 1];
                for (int i = 0; i < args.length; i++) {
                    objectsWithPromptBeforeThey[i + 1] = args[i];
                }
                printInColor(formatWithPrompt, objectsWithPromptBeforeThey);
            }
            Verbose.verboseLevelForNextPrintF =
                    DEFAULT_VERBOSE_LEVEL_FOR_UNMODIFIED_BY_setVerboseLevelForNextPrint_METHOD_USER_PRINTS;
            return;
        }
        return;
    }


    private static void printInColor(String format, Object[] args) {
        setColor();
        System.out.format(format, args);
        resetColor();
    }

    private static void printInColor(String text) {
        setColor();
        System.out.print(text);
        resetColor();
    }



    public static void enableMultiLevelVerbose(int level) {
        enableVerbose();
        Verbose.enabledOnLevel = level;
        printInColor("%sMulti level verbose enabled.\n", promptForVerboseClassEvents);
    }


    public static void disableMultiLevelVerbose() {
        MULTI_LEVEL_VERBOSE_ENABLED = false;
    }


    public static void setVerboseLevelForNextPrint(int verboseLevel) {
        Verbose.verboseLevelForNextPrintF = verboseLevel;
    }


    public static int getVerboseLevelForNextPrintF() {
        return Verbose.verboseLevelForNextPrintF;
    }


    public static void enableTwoLevelVerbose() {
        enableMultiLevelVerbose(2);
    }


    public static void enableThreeLevelVerbose() {
        enableMultiLevelVerbose(3);
    }


}
