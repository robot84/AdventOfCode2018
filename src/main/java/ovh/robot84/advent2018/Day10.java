package ovh.robot84.advent2018;


import ovh.robot84.advent2018.helpers.HelperMethods;
import ovh.robot84.advent2018.helpers.MapWriter;
import ovh.robot84.advent2018.helpers.MyReader;
import ovh.robot84.advent2018.helpers.Verbose;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;
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
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day10input1.txt";
private final static String MAP_FILE = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\mapDay10star1.txt";
BufferedWriter wri;
private MyReader myReader = new MyReader();

private Day10(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day10 dayStar1 = new Day10(INPUT_FILE1);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


private void star1start() {
    Galaxy galaxy = new Galaxy();

    createGalaxy(galaxy);
    long galaxyArea = galaxy.getArea();
    long previousGalaxyArea;
    int iteration = 0;
    MapWriter mapWriter = new MapWriter(MAP_FILE);
    mapWriter.openMap();

    System.out.printf("Starting with galaxy with %d stars, dimensions %dx%d and area of %d\n",
            galaxy.countStars(), galaxy.getYdimension(), galaxy.getXdimension(), galaxy.getArea());
    for (int counter = 0; iteration < Integer.MAX_VALUE - 1; iteration++) {
        previousGalaxyArea = galaxyArea;
        galaxy.moveStarsByTheirVelocity();
        galaxyArea = galaxy.getArea();
        if (previousGalaxyArea < galaxyArea) counter++;
        if (counter > 3) break;
    }

    System.out.printf("After %d iteration galaxy started to raise. Galaxy area: %d\n", iteration, galaxyArea);
    System.out.printf("Ending with galaxy with %d stars, dimensions %dx%d and area of %d\n",
            galaxy.countStars(), galaxy.getYdimension(), galaxy.getXdimension(), galaxy.getArea());

    //boolean[][]compressedMap=mapUtils.shrinkTwice(galaxy.createBooleanMap(), 1);

    //mapWriter.writeMap(compressedMap,"MY GALAXY (and not a Samsung ;)");
    mapWriter.writeMap(galaxy.createBooleanMap(), "MY GALAXY (and not a Samsung ;)");
    //galaxy.printGalaxyMap();
    mapWriter.closeMap();
}


void createGalaxy(Galaxy galaxy) {
    String line = null;
    /*        Read input         */
    while ((line = myReader.get_line()) != null) {
        Verbose.println(3, "Line: " + line);
        Pattern p = Pattern.compile("^position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>$");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.printf(3, "m.group(%s): %s %d\n", i, m.group(i), Integer.valueOf(m.group(i)));
            }
            galaxy.addStar(new Star(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)),
                    Integer.valueOf(m.group(3)), Integer.valueOf(m.group(4))));

        } else {
            System.out.println("DUPA. Cos nie maczuje");
        }
    } // end of while
}


class Star {
    int positionX, positionY, velocityX, velocityY;


    Star(int positionX, int positionY, int velocityX, int velocityY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }


}

class Galaxy {
    ArrayList<Star> stars = new ArrayList<>();
    ArrayList<Integer> starsDimX = new ArrayList<>();
    ArrayList<Integer> starsDimY = new ArrayList<>();
    int maxX;
    int maxY;
    int minX;
    int minY;


    public void addStar(Star star) {
        stars.add(star);
        this.recalculateDimensionsAfterAddingStar(star);
    }


    private void recalculateDimensionsAfterAddingStar(Star star) {
        starsDimX.add(star.positionX);
        starsDimY.add(star.positionY);
        maxX = Collections.max(starsDimX);
        maxY = Collections.max(starsDimY);
        minX = Collections.min(starsDimX);
        minY = Collections.min(starsDimY);
    }


    private void recalculateDimenstionsAfterMovingStars() {
        starsDimX.clear();
        starsDimY.clear();
        for (Star star : stars) {
            starsDimX.add(star.positionX);
            starsDimY.add(star.positionY);
        }
        maxX = Collections.max(starsDimX);
        maxY = Collections.max(starsDimY);
        minX = Collections.min(starsDimX);
        minY = Collections.min(starsDimY);
    }


    public void moveStarsByTheirVelocity() {
        ListIterator<Star> iterator = stars.listIterator();
        while (iterator.hasNext()) {
            Star star = iterator.next();
            star.positionX += star.velocityX;
            star.positionY += star.velocityY;
        }

        recalculateDimenstionsAfterMovingStars();
    }


    public long getArea() {
        return (getXdimension()) * (getYdimension());
    }


    public int getXdimension() {
        return Math.abs(minX) + Math.abs(maxX);
    }


    public int getYdimension() {
        return Math.abs(minY) + Math.abs(maxY);
    }


    Boolean[][] createBooleanMap() {
        Boolean[][] map = new Boolean[getYdimension() + 1][getXdimension() + 1];
        for (int i = 0; i < getYdimension(); i++) {
            Arrays.fill(map[i], false);
        }
        for (Star star : stars) {
            map[Math.abs(minY) + star.positionY][Math.abs(minX) + star.positionX] = true;
        }
        return map;
    }


    void printGalaxyMap() {
        Boolean[][] map = createBooleanMap();
        for (int y = 0; y < getYdimension(); y++) {
            for (int x = 0; x < getXdimension(); x++) {
                if (map[y][x]) System.out.print("X");
                else System.out.print(".");
            }
            System.out.println();
        }
    }


    public int countStars() {
        return stars.size();
    }
}


}
