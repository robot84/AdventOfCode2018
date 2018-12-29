package ovh.robot84.advent2018.helpers;

/**
 * This class implements 3-severity-level console message printer
 * for use with -v -vv -vvv arguments with application command line
 *
 * @author Robert Ząbkiewicz
 * @version 0.2.0
 * @since 2018-12-29
 * <p>
 */
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
private static final int MAX_VERBOSITY_LEVEL = 3;
private static final int MIN_VERBOSITY_LEVEL = 1;

private static boolean VERBOSE_ENABLE;
private static boolean VERBOSE_MUTED;
private static boolean MULTI_LEVEL_VERBOSE_ENABLED;
private static boolean PROMPTS_ENABLED = true;

private static String actualColor = ANSI_CYAN;
private static Object[] promptForVerboseClassEvents = {"*** "};
private static Object[] promptsForUserPrints = {">", ">>", ">>>"};
private static int defaultVerbosityLevel = DEFAULT_ENABLED_ON_LEVEL_NUM;
private static int verboseLevelForNextPrintF = DEFAULT_VERBOSE_LEVEL_FOR_UNMODIFIED_BY_setVerboseLevelForNextPrint_METHOD_USER_PRINTS;


public static void enablePrompts() {
    PROMPTS_ENABLED = true;
}


public static void disablePrompts() {
    PROMPTS_ENABLED = false;
}


public static void enableVerbose() {
    VERBOSE_ENABLE = true;
    printInColor("%sVerbose enabled on level ", promptForVerboseClassEvents);
    printInColor(String.valueOf(defaultVerbosityLevel));
    printInColor(".\n");
}


public static void enableVerbose(int enabledOnLevel) {
    setVerboseDefaultLevel(enabledOnLevel);
    Verbose.enableVerbose();
}


private static void setVerboseDefaultLevel(int enabledOnLevel) {
    if (isVerboseLevelValid(enabledOnLevel))
        Verbose.defaultVerbosityLevel = enabledOnLevel;
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


public static void setColor() {
    System.out.print(actualColor);
}


public static void setColor(String color) {
    actualColor = color;
}


public static void resetColor() {
    System.out.print(ANSI_RESET);
}


public static void print(String s) {
    if (VERBOSE_ENABLE && !VERBOSE_MUTED) {
        if (Verbose.verboseLevelForNextPrintF <= Verbose.defaultVerbosityLevel) {
            Verbose.printInColor(s);
            Verbose.verboseLevelForNextPrintF =
                    DEFAULT_VERBOSE_LEVEL_FOR_UNMODIFIED_BY_setVerboseLevelForNextPrint_METHOD_USER_PRINTS;
        }
        return;
    }
    return;
}


/**
 * Print with desired verbosity level
 * @param level verbosity level. Message will be printed if actual verbosity level is
 *              equal or greater that this variable value
 * @param s
 */
public static void print(int level, String s) {
    Verbose.setVerboseLevelForNextPrint(level);
    print(s);
}


/**
 * Print with actual verbosity level
 *
 * @param s
 */
public static void println(String s) {
    print(s + "\n");
}


/**
 * Print in desired verbosity level with new line on end
 *
 * @param level verbosity level. Message will be printed if actual verbosity level is
 *              *              equal or greater that this variable value
 * @param s
 */
public static void println(int level, String s) {
    Verbose.setVerboseLevelForNextPrint(level);
    println(s);
}


/**
 * Print to console with actual verbosity level.
 * Prints exacly in form as when System.printf() invoked
 *
 * @param format as for System.printf()
 * @param args   as for System.printf()
 */
public static void printf(String format, Object... args) {
    if (VERBOSE_ENABLE && !VERBOSE_MUTED) {
        if (Verbose.verboseLevelForNextPrintF <= Verbose.defaultVerbosityLevel) {
            // TODO change String to StringBuilder
            if (PROMPTS_ENABLED) {
                String formatWithPrompt = "%s ".concat(format);
                Object[] objectsWithPromptBeforeThey = new Object[args.length + spaceForPrompt];
                objectsWithPromptBeforeThey[0] = promptsForUserPrints[Verbose.verboseLevelForNextPrintF - 1];
                for (int i = 0; i < args.length; i++) {
                    objectsWithPromptBeforeThey[i + 1] = args[i];
                }
                printInColor(formatWithPrompt, objectsWithPromptBeforeThey);
            } else {
                printInColor(format, args);
            }
        }
        Verbose.verboseLevelForNextPrintF =
                DEFAULT_VERBOSE_LEVEL_FOR_UNMODIFIED_BY_setVerboseLevelForNextPrint_METHOD_USER_PRINTS;
        return;
    }
    return;
}


/**
 * Print to console with desired verbosity level.
 *
 * @param level  verbosity level. Message will be printed if actual verbosity level is
 *               *              equal or greater that this variable value
 * @param format - as for System.printf()
 * @param args   - as for System.printf()
 */
public static void printf(int level, String format, Object... args) {
    Verbose.setVerboseLevelForNextPrint(level);
    printf(format, args);

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


@Deprecated
public static void setVerboseLevelForNextPrint(int verboseLevel) {
    if(isVerboseLevelValid(verboseLevel))
        Verbose.verboseLevelForNextPrintF = verboseLevel;
}


private static boolean isVerboseLevelValid(int verboseLevel) {
    if (verboseLevel >= MIN_VERBOSITY_LEVEL && verboseLevel <= MAX_VERBOSITY_LEVEL) return true;
    else return false;
}


public static int getVerboseLevelForNextPrintF() {
    return Verbose.verboseLevelForNextPrintF;
}


}
