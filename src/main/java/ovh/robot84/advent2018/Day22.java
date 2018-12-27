package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.TextStyle.NARROW;

/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-24
 * <p>
 * If you don't have or don't want to use Verbose class, you can
 * change all Verbose.printf() to System.out.printf()
 * and delete calls to other Verbose methods.
 * <p>
 * WARNING:
 * To compile this class:
 * Please change MyClass to any class you want to us.
 */

public class Day22 {

private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input1.txt";
private final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input2.txt";
private final static int ARRAY_MAX_X = 2_0;
private final static int ARRAY_MAX_Y = 7_05;
private static final int ROCKY = 0;
private static final int WET = 1;
private static final int NARROW = 2;
List<MyRegion> points4D = new ArrayList<MyRegion>();
private HashMap<Character, Integer> hm1 = new HashMap();
private ArrayList<String> strings = new ArrayList<>();
private ArrayList<Integer> integers = new ArrayList<Integer>();
private ArrayList<Long> longs = new ArrayList<Long>();
private MyReader myReader = new MyReader();


private Day22(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day22 dayStar1 = new Day22(INPUT_FILE1);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


/*
Test
depth 510
target 10,10
Moje
depth: 4848
target: 15,700
 */
private void star1start() {
    String line = null;
    int targetX = 15, targetY = 700, caveDepth = 4848;


//    MyCave myCave = new MyCave(510,10,10);
    MyCave myCave = new MyCave(caveDepth, targetX, targetY);
    MyRegion regio, regioNorth = null, regioWest = null;
    for (int y = 0; y < ARRAY_MAX_Y - 1; y++) {

        for (int x = 0; x < ARRAY_MAX_X; x++) {
            if (y != 0) regioNorth = myCave.regions.get(ARRAY_MAX_X * (y - 1) + x);
            if (x != 0) regioWest = myCave.regions.get(ARRAY_MAX_X * y + x - 1);
            if (x == 0 && y == 0)
                myCave.addRegion(regio = new MyRegion(x, y, targetX, targetY, 0, 0, caveDepth));
            else if (x == 0)
                myCave.addRegion(regio = new MyRegion(x, y, targetX, targetY, regioNorth.getEroLv(), 0, caveDepth));
            else if (y == 0)
                myCave.addRegion(regio = new MyRegion(x, y, targetX, targetY, 0, regioWest.getEroLv(), caveDepth));
            else
                myCave.addRegion(regio = new MyRegion(x, y, targetX, targetY, regioNorth.getEroLv(), regioWest.getEroLv(), caveDepth));
            Verbose.println(regio.toString());
        }

    }
    System.out.println("Risk " + myCave.getRisk());


}


/*

 */
private class MyCave {
    int eroLvl1, eroLvl2;
    int caveDepth;
    int targetX;
    int targetY;
    List<MyRegion> regions = new ArrayList<>();


    MyCave(int caveDepth, int targetX, int targetY) {
        this.caveDepth = caveDepth;
        this.targetX = targetX;
        this.targetY = targetY;
    }


    int getRisk() {
        IntSummaryStatistics summary = regions
                .stream()
                .filter(
                        reg -> reg.isInRiskTerrain() == true
                )
                .collect(
                        Collectors
                                .summarizingInt(MyRegion::getType)
                );

        return (int) summary.getSum();

    }


    int getCaveDepth() {
        return caveDepth;
    }


    int getEroLvl1() {
        return eroLvl1;
    }


    int getEroLvl2() {
        return eroLvl2;
    }


    void addRegion(MyRegion region) {
        regions.add(region);
    }


}


private class MyRegion {

    final int targetX;
    final int targetY;
    final int x, y;
    final int caveDepth;
    int geoIndex;
    int eroLv;
    int eroLvl1, eroLvl2;
    int type;


    MyRegion(int x, int y, int targetX, int targetY, int eroLv1, int eroLv2, int caveDepth) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.eroLvl1 = eroLv1;
        this.eroLvl2 = eroLv2;

        this.caveDepth = caveDepth;
        computeRegionParams();

    }


    public int getGeoIndex() {
        return geoIndex;
    }


    public int getEroLv() {
        return eroLv;
    }


    private void computeRegionParams() {
        if (x == 0 && y == 0) geoIndex = 0;
        else if (x == targetX && y == targetY) geoIndex = 0;
        else if (y == 0) geoIndex = x * 16807;
        else if (x == 0) geoIndex = y * 48271;
//        Otherwise, the region's geologic index is the result of multiplying
//        the erosion levels of the regions at X-1,Y and X,Y-1.
        else geoIndex = eroLvl1 * eroLvl2;


        eroLv = (geoIndex + caveDepth) % 20183;
        if (eroLv % 3 == 0) type = ROCKY;
        else if (eroLv % 3 == 1) type = WET;
        else type = NARROW;


    }


    int getType() {
        return type;
    }

    String typeToString(int type) {
        if (type == ROCKY) return "ROCKY";
        else if (type == WET) return "WET";
        else if (type == NARROW) return "NARROW";
        else return "(unknown)";
    }


    @Override
    public String toString() {

        String string = String.format("[(%d,%d) %d %d %s]", x, y, geoIndex, eroLv, this.typeToString(type));
        return string;
    }


    public boolean isInRiskTerrain() {
        if (x <= targetX && y <= targetY) return true;
        return false;
    }
}
}
