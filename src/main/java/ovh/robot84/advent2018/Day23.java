package ovh.robot84.advent2018;

import javafx.geometry.Point3D;

import java.util.*;
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
 * <p>
 * If you don't have or don't want to use Verbose class, you can
 * change all Verbose.printf() to System.out.printf()
 * and delete calls to other Verbose methods.
 */

public class Day23 {
final static String INPUT_FILE3 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day23input3.txt";
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day23input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day23input1.txt";
private static final int NUM_OF_BOTS = 1000;
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
ArrayList<Nanobot> nanobots = new ArrayList<>(NUM_OF_BOTS);
private MyReader myReader = new MyReader();
private Integer inputSize;


private Day23(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day23 dayStar1 = new Day23(INPUT_FILE1);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


private void star1start() {
    int inputIndex = 0;
    String line = null;
    /*        Read nanobots         */
    Pattern p = Pattern.compile("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)");
    while ((line = myReader.get_line()) != null) {
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.print("Line: " + line);
        Matcher m = p.matcher(line);

        if (m.matches()) {
            nanobots.add(new Nanobot(new Point3D(Double.valueOf(m.group(1)), Double.valueOf(m.group(2)), Double.valueOf(m.group(3))), m.group(4)));
            Verbose.setVerboseLevelForNextPrint(3);
            Verbose.printf("%d %d %d %d\n", nanobots.get(inputIndex).getX(),
                    nanobots.get(inputIndex).getY(),
                    nanobots.get(inputIndex).getZ(),
                    nanobots.get(inputIndex).getRange());
            inputIndex++;
        }
    }//while

    inputSize = nanobots.size();
    Verbose.setVerboseLevelForNextPrint(3);
    Verbose.println("InputSize: " + inputSize.toString());
    ArrayList<Integer> integers = new ArrayList<Integer>();

    Nanobot strongestBot = Collections.max(nanobots);
    int botsInRange = 0;
    int strongestBotRange = strongestBot.getRange();
    int distance = 0;
    for (int i = 0; i < inputSize; i++) {
        distance = ManhattanDistance.compute3DDistance(strongestBot.getCoords(), nanobots.get(i).getCoords());
        if (distance <= strongestBotRange) botsInRange++;
    }
    System.out.printf("Star01: The strongest nanobot is bot with range %d. In his range are %d nanobots\n", strongestBotRange, botsInRange);


    for (int j = 0; j < inputSize; j++) {
        for (int i = 0; i < inputSize; i++) {
            distance = ManhattanDistance.compute3DDistance(nanobots.get(j).getCoords(), nanobots.get(i).getCoords());
            Verbose.setVerboseLevelForNextPrint(3);
            Verbose.printf("Distance beetween bots %d and %d is %d\n", j, i, distance);
            nanobots.get(j).getRadar().addObject(distance, nanobots.get(i).getRange());
        }
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.println("Objects on radar: " + nanobots.get(j).getRadar().getSize());
    }
    ArrayList<Integer> mostInRange = new ArrayList<>();
    ArrayList<Integer> mostInNearRange = new ArrayList<>();
    ArrayList<Integer> mostInFarRange = new ArrayList<>();
    botsInRange = 0;
    int botsInNearRange = 0, botsInFarRange = 0;
    for (int i = 0; i < inputSize; i++) {
        Verbose.setVerboseLevelForNextPrint(2);
        botsInRange = nanobots.get(i).getRadar().getNumOfTargetsInRange();
        mostInRange.add(botsInRange);
        botsInNearRange = nanobots.get(i).getRadar().getNumOfTargetsInNearRange();
        mostInNearRange.add(botsInNearRange);
        botsInFarRange = nanobots.get(i).getRadar().getNumOfTargetsInFarRange();
        mostInFarRange.add(botsInFarRange);
        Verbose.printf("Bots in range of bot %d: %d [near: %d\tfar: %d\n", i, botsInRange, botsInNearRange, botsInFarRange);
    }
    int maxBotsInRange = Collections.max(mostInRange);
    Verbose.println("Maximum bots in range on one bot: " + maxBotsInRange);
    ArrayList<Nanobot> botsWithMaxBotsInRange = new ArrayList<>();
    for (int i = 0; i < inputSize; i++) {
        if (nanobots.get(i).getRadar().getNumOfTargetsInRange() == maxBotsInRange)
            botsWithMaxBotsInRange.add(nanobots.get(i));
    }
    Verbose.println("Number of bots with this range: " + botsWithMaxBotsInRange.size());
    for (Nanobot bo : botsWithMaxBotsInRange)
        Verbose.printf("range is: %d\n", bo.getRange());
    Nanobot bot777 = Collections.min(botsWithMaxBotsInRange);
    Verbose.println("Bot in which range will be destination point has range " + bot777.getRange());

    /*
    ManhattanDistance.getPoints3DWithinManhattanDistance(bot777.getCoords(),bot777.getRange());
    for(points) ustawiasz je jako bot 1001 i liczysz dystans od botow dla tego punktu.
    czyli powtarzasz cala procedure. i wyznaczasz z tych punktow punkt, ktory ma najwiecej botow w zasiegu
*/
    System.out.println("Rezult: " + botsInRange);
   /* for (int i = 1; i <= inputSize; i++) {
    if (nanobots.get(i).x > maxX) maxX = nanobots.get(i).x;
}

    for (int i = 1; i <= inputSize; i++) {
    if (nanobots.get(i).y > maxY) maxY = nanobots.get(i).y;
}
   */
            /*while ((c = myReader.read()) != -1) {
                Verbose.print("!" + (char) c);
            */


}


class RadarObject {
    private int distanceToObject;
    private int objectRange;


    RadarObject(int distanceToObject, int objectRange) {
        setDistanceToObject(distanceToObject);
        setObjectRange(objectRange);
    }


    private int getObjectRange() {
        return this.objectRange;
    }


    private void setObjectRange(int objectRange) {
        this.objectRange = objectRange;
    }


    int getDistanceToObject() {
        return distanceToObject;
    }


    void setDistanceToObject(int distanceToObject) {
        this.distanceToObject = distanceToObject;
    }

}

class Radar {
    private int numOfObjects;
    private int ourRange;
    private int numOfObjectsInRange;
    private int numOfObjectsInFarRange;
    private int numOfObjectsInNearRange;
    private LinkedList<RadarObject> vessels = new LinkedList<>();
    private ListIterator<RadarObject> iterator = vessels.listIterator();


    Radar(int ourRange) {
        this.ourRange = ourRange;
    }


    void addObject(int targetDistance, int targetRange) {
        RadarObject target = new RadarObject(targetDistance, targetRange);
        iterator.add(target);
        if (isTargetInNearRange(target)) {
            numOfObjectsInRange++;
            numOfObjectsInNearRange++;
        } else if (isTargetInFarRange(target)) {
            // numOfObjectsInRange++;
            numOfObjectsInFarRange++;
        }
        numOfObjects++;
    }


    int getNumOfObjectsCovered() {
        return 0;
    }


    boolean isObjectCovered(RadarObject radarObject) {
        //WARNING. This function can send crappy return value because of Manhatan distance granurality
        if (radarObject.getObjectRange() < ourRange && ourRange > radarObject.getDistanceToObject() + radarObject.getObjectRange())
            return true;
        else return false;
    }


    int getNumOfObjectsCovering() {
        return 0;
    }


    int getSize() {
        return numOfObjects;
    }


    boolean isTargetInNearRange(int targetNum) {
        if (vessels.get(targetNum).getDistanceToObject() <= ourRange)
            return true;
            //else if(vessels.get(targetNum).getDistanceToObject() <= (vessels.get(targetNum).getObjectRange()))
            //  return  true;
        else
            return false;
    }


    boolean isTargetInNearRange(RadarObject target) {
        if ((target).getDistanceToObject() <= ourRange)
            return true;
            // else if((target).getDistanceToObject() <= ((target).getObjectRange()))
            //return  true;
        else
            return false;
    }


    boolean isTargetInFarRange(int targetNum) {
        if (vessels.get(targetNum).getDistanceToObject() <= (vessels.get(targetNum).getObjectRange() + ourRange))
            return true;
        else
            return false;
    }


    boolean isTargetInFarRange(RadarObject target) {
        if ((target).getDistanceToObject() <= ((target).getObjectRange() + ourRange))
            return true;
        else
            return false;
    }


    public int getNumOfTargetsInRange() {
        return numOfObjectsInRange;
    }


    public int getNumOfTargetsInNearRange() {
        return numOfObjectsInNearRange;
    }


    public int getNumOfTargetsInFarRange() {
        return numOfObjectsInFarRange;
    }

}

class Nanobot implements Comparable<Nanobot> {
    private Point3D coords;
    private Integer range;
    private Radar radar;


    Nanobot(Point3D coords, String range) {
        this.coords = coords;
        this.range = Integer.valueOf(range);
        this.radar = new Radar(this.range);
    }


    Radar getRadar() {
        return radar;
    }


    void addObjectToRadar(int targetDistance, int targetRange) {
        radar.addObject(targetDistance, targetRange);
    }


    int getRange() {
        return range;
    }


    Point3D getCoords() {
        return coords;
    }


    int getX() {
        return (int) coords.getX();
    }


    int getY() {
        return (int) coords.getY();
    }


    int getZ() {
        return (int) coords.getZ();
    }


    @Override
    public int compareTo(Nanobot o) {
        return this.getRange() - o.getRange();
    }
}
}
