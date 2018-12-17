package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    // print input
    for (Clay clay : clays) {
        Verbose.println(clay.toString());
    }

    processing(clays);

}


private void processing(ArrayList<Clay> clays) {
    int springX = 500;
    int springY = 0;
    // two ways of doing the same
    Clay deepestClay1 = Collections.max(clays, clays.get(0).getComparator());
    Clay deepestClay2 = Collections.max(clays, new ClayCoordYComparator());
    int deep = deepestClay2.getLastY();
    Clay clay1, clay2;
    clay1 = clays.get(1);
    clay2 = clays.get(2);
    Collections.sort(clays);

}


private void readInput(ArrayList<Clay> clays) {
    String line = null;
    while ((line = myReader.get_line()) != null) {
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.print("Line: " + line);

        ArrayList<Integer> al = new ArrayList<>();
        Pattern p1 = Pattern.compile("y=(\\d+),\\s*x=(\\d+)..(\\d+)");
        Pattern p2 = Pattern.compile("x=(\\d+),\\s*y=(\\d+)..(\\d+)");
        Matcher m = p1.matcher(line);
        if (m.matches()) {


            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("m.group(%s): %s\n", i, m.group(i));
                al.add(Integer.valueOf(m.group(i)));
            }
            clays.add(new Clay(al.get(0), al.get(1), al.get(2), false));
        } else {
            m = p2.matcher(line);
            if (m.matches()) {
                al.clear();

                for (int i = 1; i <= m.groupCount(); i++) {
                    Verbose.setVerboseLevelForNextPrint(3);
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


class ClayCoordYComparator implements Comparator<Clay> {
    @Override
    public int compare(Clay o1, Clay o2) {
        return o1.compareTo(o2);
    }
}

class Clay implements Comparable<Clay> {
    boolean vertical = false;
    ThreePointsCoordinate coords;
    Comparator<Clay> coordYcomparison = new Comparator<Clay>() {
        @Override
        public int compare(Clay o1, Clay o2) {
            return o1.compareTo(o2);
        }
    };


    Comparator<Clay> getComparator() {
        return getcoordYComparator();
    }


    Comparator<Clay> getcoordYComparator() {
        return coordYcomparison;
    }

    Clay(int co1, int co2start, int co2stop, boolean vertical) {
        this.vertical = vertical;
        this.coords = new ThreePointsCoordinate(co1, co2start, co2stop);
    }


    /*
    Returns x coordinate if clay is vertical,
     or startX, when x coordinate defined as x=startX..stopX,
     if clay is vertical
     */
    int getX() {
        return getFirstX();
    }


    int getFirstX() {
        if (this.vertical) {
            return coords.co1;
        } else {
            return coords.co2start;
        }
    }


    int getLastX() {
        if (this.vertical) {
            return coords.co1;
        } else {
            return coords.co2stop;
        }
    }


    /*
   Returns y coordinate if clay is horizontal,
    or startY, when clay y coordinates defined as y=startY..stopY
    if clay is horizontal
    */
    int getY() {
        return getFirstY();
    }


    int getFirstY() {
        if (!this.vertical) {
            return coords.co1;
        } else {
            return coords.co2start;
        }
    }


    int getLastY() {
        if (!this.vertical) {
            return coords.co1;
        } else {
            return coords.co2stop;
        }
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


    @Override
    public int compareTo(Clay o) {
        if (this.vertical) {
            if (coords.co2stop == o.coords.co2stop) return 0;
            else if (coords.co2stop > o.coords.co2stop) return coords.co2stop - o.coords.co2stop;
            else return o.coords.co2stop - coords.co2stop;
        } else {
            if (coords.co1 == o.coords.co1) return 0;
            else if (coords.co1 > o.coords.co1) return coords.co1 - o.coords.co1;
            else return o.coords.co1 - coords.co1;
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
