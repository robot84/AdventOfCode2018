package ovh.robot84.advent2018;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-01
 */
public class Day17 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day17input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day17input1.txt";
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
private MyReader myReader = new MyReader();


private Day17(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day17 dayStar1 = new Day17(INPUT_FILE1);
    dayStar1.parsingProgramArguments(args);
    //Verbose.mute();
    Verbose.disablePrompts();
    dayStar1.star1start();
}


private void star1start() {
    ArrayList<Clay> clays = new ArrayList<Clay>();

    readInput(clays);

    for (Clay clay : clays) {
        Verbose.println(clay.toString());
    }

}


private void readInput(ArrayList<Clay> clays) {
    String line = null;
    while ((line = myReader.get_line()) != null) {
        Verbose.print("Line: " + line);

        ArrayList<Integer> al = new ArrayList<>();
        Pattern p1 = Pattern.compile("y=(\\d+),\\s*x=(\\d+)..(\\d+)");
        Pattern p2 = Pattern.compile("x=(\\d+),\\s*y=(\\d+)..(\\d+)");
        Matcher m = p1.matcher(line);
        if (m.matches()) {
            al.clear();
            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.printf("m.group(%s): %s\n", i, m.group(i));
                al.add(Integer.valueOf(m.group(i)));
            }
            clays.add(new Clay(al.get(0), al.get(1), al.get(2), false));
        } else {
            m = p2.matcher(line);
            if (m.matches()) {
                al.clear();
                for (int i = 1; i <= m.groupCount(); i++) {
                    Verbose.printf("m.group(%s): %s\n", i, m.group(i));
                    al.add(Integer.valueOf(m.group(i)));
                }
                clays.add(new Clay(al.get(0), al.get(1), al.get(2), true));
            }
        }
    }
}


private void parsingProgramArguments(String[] args) {
    System.out.println("Program ARGS num:" + args.length + " ARGS:" + args);
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
    if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
    if (args.length > 0 && args[0].equals("--create-input-file"))
        HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), INPUT_FILE1);
}


class Clay {
    boolean vertical = false;
    ThreePointsCoordinate coords;


    Clay(int co1, int co2start, int co2stop, boolean vertical) {
        this.vertical = vertical;
        this.coords = new ThreePointsCoordinate(co1, co2start, co2stop);
    }


    public String toString() {
        String vertical;
        String horizontal;
        if (this.vertical) {
            vertical = String.format("clay: x=%d y=%d..%d vertical\n", coords.co1, coords.co2start, coords.co2stop);
            return vertical;
        } else {
            horizontal = String.format("clay: y=%d x=%d..%d horizontal\n", coords.co1, coords.co2start, coords.co2stop);
            return horizontal;
        }
    }
}

class ThreePointsCoordinate {
    Integer co1 = new Integer(0);
    Integer co2start = new Integer(0);
    Integer co2stop = new Integer(0);


    ThreePointsCoordinate(int co1, int co2start, int co2stop) {
        this.co1 = Integer.valueOf(co1);
        this.co2start = Integer.valueOf(co2start);
        this.co2stop = Integer.valueOf(co2stop);
    }
}

class ThreePointsCoordinates {
    ArrayList<ThreePointsCoordinate> co1 = new ArrayList<>();


    void add(ThreePointsCoordinate co) {
        this.co1.add(co);
    }
}

}
