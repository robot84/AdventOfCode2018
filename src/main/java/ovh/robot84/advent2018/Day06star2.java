package ovh.robot84.advent2018;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06star2 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day06input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
final static int COMMON = Integer.MAX_VALUE - 1;
private final static String MAP_FILE = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\mapDay06star2.txt";
int[][] mapa;
boolean[][] freshMove;
BufferedWriter wri;
Integer maxX = 0;
Integer maxY = 0;
Integer numOfPlayers;

HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
ArrayList<Point> input = new ArrayList<>();
ArrayList<Boolean> playerCountedToSummary = new ArrayList<>();
ArrayList<Integer> playersPossesion = new ArrayList<>();
ArrayList<Integer> playersDistance = new ArrayList<>();
ArrayList<Integer> playersCountedToSummary = new ArrayList<>();
private MyReader myReader = new MyReader();


private Day06star2(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day06star2 dayStar1 = new Day06star2(INPUT_FILE2);
    System.out.println("ARGS:" + args.length);
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    dayStar1.star1start();
}


private void star1start() {
    String line = null;
    int c = 0;

    input.add(new Point(0, 0));
    playerCountedToSummary.add(false);
    playersDistance.add(1000000);
    playersPossesion.add(0);
        /*
        Read input
         */
    while ((line = myReader.get_line()) != null) {
        Verbose.print("Read Line: " + line);


        Pattern p = Pattern.compile("^(\\d+), (\\d+)$");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            input.add(new Point((int) Integer.valueOf(m.group(1)), (int) Integer.valueOf(m.group(2))));
            playerCountedToSummary.add(true);

            Verbose.print("Adding player");
        }
    }//while

    numOfPlayers = input.size() - 1;
    System.out.println("NumOfPlayers: " + numOfPlayers.toString());


    for (int i = 1; i <= numOfPlayers; i++) {
        playersPossesion.add(0);
    }

    for (int i = 1; i <= numOfPlayers; i++) {
        if (input.get(i).x > maxX) maxX = input.get(i).x;
    }

    for (int i = 1; i <= numOfPlayers; i++) {
        if (input.get(i).y > maxY) maxY = input.get(i).y;
    }

    // maxX is the number of last field on map. but field '0' exist
    // and must be counted if we want to store number of fields on map
    maxX++;
    maxY++;

    System.out.println("maxX: " + maxX.toString() + " maxY: " + maxY.toString());

    mapa = new int[maxY][maxX];
    //freshMove = new boolean[maxY][maxX];
    System.out.printf("Allocated %,d B for map", Integer.BYTES * maxX * maxY);

    openMap();
    HelperMethods.printResult(computingArea());
    System.out.println("Writing map. Please be patient...");
    writeMap();
    closeMap();
    System.exit(0);
}


int computingArea() {

    int fieldsInResult = 0;
    for (int y = 0; y < maxY; y++) {
        for (int x = 0; x < maxX; x++) {
            Point actualIteratedFieldOnMap = new Point(x, y);
            playersDistance.clear();
            playersDistance.add(10000);
            for (int userNum = 1; userNum <= numOfPlayers; userNum++) {
                playersDistance.add(manhattanDistance(actualIteratedFieldOnMap, input.get(userNum)));
                Verbose.printf("ManDist to field (%s,%s) for player %s on (%s,%s) is %s\n", x, y, userNum, input.get(userNum).x, input.get(userNum).y, playersDistance.get(userNum));
            }

            int sum = 0;
            for (int i = 1; i <= numOfPlayers; i++) {
                sum += playersDistance.get(i);
            }
            if (sum < 10000) {
                fieldsInResult++;
                mapa[y][x] = -11;
            }
        }
    }
    return fieldsInResult;
}


private int manhattanDistance(Point p1, Point p2) {
    // kto ma najblizej do tego punktu na mapie?
    int x0 = p1.x, x1 = p2.x, y0 = p1.y, y1 = p2.y;
    int distance = Math.abs(x1 - x0) + Math.abs(y1 - y0);
    //Verbose.printf("Welcome in Manhattan project!\n");
    //Verbose.printf("(%s,%s) (%s,%s) -> %s\n",x0,y0,x1,y1,distance);
    return distance;
}


void openMap() {
    wri = null;
    try {
        wri = new BufferedWriter(new FileWriter(MAP_FILE));
    } catch (IOException e) {
        e.printStackTrace();
    }
}


void closeMap() {

    try {
        wri.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


void writeMap() {

    try {
        wri.write("\nMapa\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to map file.");
        e.printStackTrace();
    }

    for (int y = 0; y < maxY; y++) {
        for (int x = 0; x < maxX; x++) {
            try {
                wri.write((mapa[y][x] + '.'));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            wri.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

}
