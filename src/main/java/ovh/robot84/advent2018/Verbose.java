package ovh.robot84.advent2018;

public class Verbose {

    private static boolean VERBOSE_ENABLE;
    private static boolean VERBOSE_MUTED;
    private static boolean MULTI_LEVEL_VERBOSE_ENABLED;



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
    private static Object[] prompt = {"*** "};
    private static int enabledOnLevel = 1;
    private static int verboseLevelForNextPrintF;


    public static void enableVerbose() {
        VERBOSE_ENABLE = true;
        printInColor("%sVerbose enabled.\n", prompt);
    }


    public static void disableVerbose() {
        VERBOSE_ENABLE = false;
        printInColor("%sVerbose disabled.\n", prompt);
    }


    public static void mute() {
        VERBOSE_MUTED = true;
        printInColor("%sVerbose muted.\n", prompt);
    }


    public static void unmute() {
        VERBOSE_MUTED = false;
        printInColor("%sVerbose unmuted.\n", prompt);
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
        if (VERBOSE_ENABLE && !VERBOSE_MUTED) {
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
        if (VERBOSE_ENABLE && !VERBOSE_MUTED) {
            if (Verbose.verboseLevelForNextPrintF <= Verbose.enabledOnLevel) {
                Verbose.verboseLevelForNextPrintF = 0;
                printInColor(format, args);
            }
            return;
        }
        return;
    }

    private static void printInColor(String format, Object[] args) {
        setColor();
        System.out.format(format, args);
        resetColor();
    }


    public static void enableMultiLevelVerbose(int level) {
        enableVerbose();
        Verbose.enabledOnLevel = level;
        printInColor("%sMulti level verbose enabled.\n", prompt);
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
