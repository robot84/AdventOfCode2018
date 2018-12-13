package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Day04 {
final static int ARRAY_MAX_X = 10_000;
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day04input2.txt";
final static int ARRAY_MAX_Y = 10_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day04input1.txt";
private MyReader myReader = new MyReader();
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
String s;
final static int OUTPUT_DATA_LEVEL__ONLY_RESULT = 0;
final static int OUTPUT_DATA_LEVEL__GUARD_SLEEP_TIMES = 1;
final static int OUTPUT_DATA_LEVEL__GUARD_SLEEP_SCHEDULE = 2;
int falls = 0;
int wakesup = 0;
int minutesInSlepp;


private Day04(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day04 dayStar1 = new Day04(INPUT_FILE1);
    dayStar1.star1start(OUTPUT_DATA_LEVEL__GUARD_SLEEP_SCHEDULE | OUTPUT_DATA_LEVEL__GUARD_SLEEP_TIMES);
}


private int parseGuardWakesUp(String line) {
//[1518-02-27 00:53] wakes up
    String[] foundByFindRegexMethod;
    foundByFindRegexMethod = FindRegex.findRegex(":(\\d+)]", line);
    return Integer.valueOf(foundByFindRegexMethod[0].replace(":", "").replace("]", ""));
}


private int parseGuardFallsAsleep(String line) {
    String[] foundByFindRegexMethod;
    foundByFindRegexMethod = FindRegex.findRegex(":(\\d+)]", line);
    return Integer.valueOf(foundByFindRegexMethod[0].replace(":", "").replace("]", ""));
}


private int parseGuardStartShift(String s) {
    int a = 0;
    try {
        a = Integer.valueOf(s.substring(1));
    } catch (NumberFormatException e) {
        System.out.printf("Dales dupy tutaj, bo chciales ten string na liczbe zamienic: %s\n", s);
        //e.printStackTrace();
    }
    return a;
}


private void star1start(int outputDataLevel) {
    String line;
    Fabric guardRecord = new Fabric();

    Integer[] guardTable = new Integer[4000];
    for (int i = 0; i < guardTable.length; i++) {
        guardTable[i] = 0;
    }

    {
        int guardID = 0;
        while ((line = myReader.get_line()) != null) {

            String[] foundByFindRegexMethod;
            foundByFindRegexMethod = FindRegex.findRegex("#(\\d+)", line);
            if (foundByFindRegexMethod.length != 0) {
                guardID = parseGuardStartShift(foundByFindRegexMethod[0]);
                if ((outputDataLevel & 1) != 0) System.out.printf("\n#%d", guardID);
            } else {
                foundByFindRegexMethod = FindRegex.findRegex("falls", line);
                if (foundByFindRegexMethod.length != 0) {
                    falls = parseGuardFallsAsleep(line);
                    if ((outputDataLevel & 1) != 0) System.out.printf(" %d-", falls);
                } else {
                    foundByFindRegexMethod = FindRegex.findRegex("wakes", line);
                    if (foundByFindRegexMethod.length != 0) {
                        wakesup = parseGuardWakesUp(line);
                        minutesInSlepp = wakesup - falls;
                        guardTable[guardID] += minutesInSlepp;
                        guardRecord.addToFabric(falls, guardID, minutesInSlepp, 1);
                        if ((outputDataLevel & 1) != 0)
                            System.out.printf("%d (%d)[%d]", wakesup, minutesInSlepp, guardTable[guardID]);
                        if ((outputDataLevel & 2) != 0) {
                            System.out.printf("\n#%d ", guardID);
                            for (int index = 0, valueNew = 0; index < 60; index++) {
                                System.out.printf("%d", guardRecord.getField(guardID, index));
                            }
                        }

                    }
                }
            }

        }
    }
    int mostSleeperGuard = 0;
    for (int i = 0, sleepTime = 0; i < guardTable.length; i++) {
        if (guardTable[i] != 0) {
            // System.out.printf("Guard #%d slept %d minutes\n",i,guardTable[i]);
            if (guardTable[i] > sleepTime) {
                sleepTime = guardTable[i];
                mostSleeperGuard = i;
            }
        }
    }
    System.out.printf("\nMost sleeper is Guard #%d (slept %d minutes)\n", mostSleeperGuard, guardTable[mostSleeperGuard]);
    int minute = guardRecord.getIndexForMaxValue(mostSleeperGuard, guardRecord.getMaxValue(mostSleeperGuard));
    System.out.println("Minute: " + minute);

    for (int index = 0, valueNew = 0; index < 60; index++) {
        System.out.printf("%3d", guardRecord.getField(mostSleeperGuard, index));
    }
    System.out.println();
    for (int index = 0, valueNew = 0; index < 60; index++) {
        System.out.printf("%3d", index);
    }
    System.out.println();
    HelperMethods.printResult(minute * mostSleeperGuard);

    mostSleeperGuard = 0;
    for (int i = 0, sleepTime = 0; i < guardTable.length; i++) {
        if (guardTable[i] != 0) {
            // System.out.printf("Guard #%d slept %d minutes\n",i,guardTable[i]);
            if (guardRecord.getMaxValue(i) > sleepTime) {
                sleepTime = guardRecord.getMaxValue(i);
                mostSleeperGuard = i;
            }
        }
    }
    System.out.printf("\nMost sleeper is Guard #%d (slept %d times in minute %d)\n",
            mostSleeperGuard,
            guardRecord.getMaxValue(mostSleeperGuard),
            guardRecord.getIndexForMaxValue(mostSleeperGuard, guardRecord.getMaxValue(mostSleeperGuard)));
    System.out.println();
    HelperMethods.printResult(
            guardRecord.getIndexForMaxValue(
                    mostSleeperGuard, guardRecord.getMaxValue(mostSleeperGuard)
            ) * mostSleeperGuard);
}


}
/*
Case 1

[1518-02-28 23:56] Guard #3323 begins shift
[1518-03-01 00:10] falls asleep
[1518-03-01 00:39] wakes up

Case2
[1518-02-28 00:02] Guard #1439 begins shift
[1518-02-28 00:11] falls asleep
[1518-02-28 00:51] wakes up
[1518-02-28 23:56] Guard #3323 begins shift
[1518-03-01 00:10] falls asleep
[1518-03-01 00:39] wakes up
 */