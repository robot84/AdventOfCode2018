package ovh.robot84.advent2018;

public class Verbose {
    private static boolean VERBOSE_ENABLE;

    public static void enableVerbose() {
        VERBOSE_ENABLE = true;
    }

    public static void disableVerbose() {
        VERBOSE_ENABLE = false;
    }

    static void print(String... s) {
        if (VERBOSE_ENABLE) {
            for (int i = 0; i < s.length; i++) {
                System.out.println(s[i]);
            }
        }

    }

}
