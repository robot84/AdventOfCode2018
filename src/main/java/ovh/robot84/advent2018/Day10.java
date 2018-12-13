package ovh.robot84.advent2018;


import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
public class Day10 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day10input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day10input1.txt";
private final static String MAP_FILE = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\mapDay10star1.txt";
Boolean[][] mapa = new Boolean[1000][1000];
int mapXsize = 2000;
int mapYsize = 2000;
BufferedWriter wri;
Integer maxX, minX, maxY, minY;
Integer vmaxX, vminX, vmaxY, vminY;
private MyReader myReader = new MyReader();
private int inputSize;


private Day10(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day10 dayStar1 = new Day10(INPUT_FILE1);
    dayStar1.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


private void star1start() {
    String line = null;
    ArrayList<Pair<Integer, Integer>> position = new ArrayList<>();
    ArrayList<Pair<Integer, Integer>> velocity = new ArrayList<>();

    /*        Read input         */
    while ((line = myReader.get_line()) != null) {
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.println("Line: " + line);
        Pattern p = Pattern.compile("^position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>$");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("m.group(%s): %s %d\n", i, m.group(i), Integer.valueOf(m.group(i)));
            }
            position.add(new Pair(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2))));
            velocity.add(new Pair(Integer.valueOf(m.group(3)), Integer.valueOf(m.group(4))));
        } else {
            System.out.println("DUPA. Cos nie maczuje");
        }
    } // end of while

    inputSize = position.size() - 1;
    maxX = 0;
    maxY = 0;// because Integer maxX,maxY; NOT initialize var with 0!!
    Verbose.printf("Number of lines in input file: %d\n", inputSize);
    for (int i = 0; i < inputSize; i++) {
        if (position.get(0).getKey() > maxX) maxX = position.get(0).getKey();
    }
    for (int i = 0; i < inputSize; i++) {
        if (position.get(i).getValue() > maxY) maxY = position.get(i).getValue();
    }
    Verbose.println("position maxX: " + maxX.toString() + " maxY: " + maxY.toString());
    minX = 9999;
    minY = 9999;
    for (int i = 0; i < inputSize; i++) {
        if (position.get(i).getKey() < minX) minX = position.get(i).getKey();
    }
    for (int i = 0; i < inputSize; i++) {
        if (position.get(i).getValue() < minY) minY = position.get(i).getValue();
    }
    Verbose.println("position minX: " + minX.toString() + " minY: " + minY.toString());
    minY = Math.abs(minY);
    minX = Math.abs(minX);

    vmaxX = 0;
    vmaxY = 0;// because Integer maxX,maxY; NOT initialize var with 0!!
    for (int i = 0; i < inputSize; i++) {
        if (velocity.get(0).getKey() > vmaxX) vmaxX = velocity.get(0).getKey();
    }
    for (int i = 0; i < inputSize; i++) {
        if (velocity.get(i).getValue() > vmaxY) vmaxY = velocity.get(i).getValue();
    }
    Verbose.println("velocity vmaxX: " + vmaxX.toString() + " vmaxY: " + vmaxY.toString());
    vminX = 9999;
    vminY = 9999;
    for (int i = 0; i < inputSize; i++) {
        if (velocity.get(i).getKey() < vminX) vminX = velocity.get(i).getKey();
    }
    for (int i = 0; i < inputSize; i++) {
        if (velocity.get(i).getValue() < vminY) vminY = velocity.get(i).getValue();
    }
    Verbose.println("velocity: vminX: " + vminX.toString() + " vminY: " + vminY.toString());
    vminY = Math.abs(vminY);
    vminX = Math.abs(vminX);
    mapXsize = 70000;
    mapYsize = 70000;
    Verbose.printf("Allocating map[%d][%d]...\n", mapYsize, mapXsize);
    mapa = new Boolean[mapYsize][mapXsize];
    for (int y = 0; y < mapYsize; y++) {
        for (int x = 0; x < mapXsize; x++) {
            mapa[y][x] = false;
        }
    }
    Verbose.printf("Map allocated.\n");
    openMap();
    int x, y;
    // main loop
    int someLittleValueChoosedByMe = 300;
    int timestamp = someLittleValueChoosedByMe;
    for (int j = 0; j < timestamp; j++) {
        for (int y1 = 0; y1 < mapYsize; y1++) {
            for (int x1 = 0; x1 < mapXsize; x1++) {
                mapa[y1][x1] = false;
            }
        }
        for (int i = 0; i < inputSize; i++) {
            x = mapXsize / 2 + position.get(i).getKey();
            y = mapYsize / 2 + position.get(i).getValue();
            //if(i==0)
            // Verbose.printf("Timestamp: %d. Write to mapa[%d][%d] %d %d\n", i, y, x,position.get(i).getKey(),position.get(i).getValue());
            mapa[y][x] = true;

            position.set(i, new Pair(position.get(i).getKey() + velocity.get(i).getKey(),
                    position.get(i).getValue() + velocity.get(i).getValue()));
        }
        writeMap(mapXsize, mapYsize, j);
    }
    closeMap();
}


private void parsingProgramArguments(String[] args) {
    System.out.println("Program ARGS num:" + args.length + " ARGS:" + args);
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
    if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
    if (args.length > 0 && args[0].equals("--create-input-file")) {
        HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), INPUT_FILE1);
        System.exit(0);
    }
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


void writeMap(int mapXsize, int mapYsize, int iteration) {

    DotCounter dc = new DotCounter(300);
    dc.start();

    try {
        wri.write("\nMapa number " + iteration + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to map file.");
        e.printStackTrace();
    }
    Verbose.setVerboseLevelForNextPrint(3);
    Verbose.printf("Writing map to file.\n");
    for (int y = 0; y < mapXsize; y++) {
        for (int x = 0; x < mapYsize; x++) {
            try {
                if (!mapa[y][x]) wri.write(('.'));
                else {
                    //wri.write((mapa[y][x] + 'X' ));
                    wri.write(('X'));
                    //Verbose.printf("%d %d\n",y,x);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            wri.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dc.check();
    }
}


class IntPair extends Pair {
    IntPair(Integer x, Integer y) {
        super(x, y);
    }
}

}
