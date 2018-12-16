package ovh.robot84.advent2018;


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
    dayStar1.parsingProgramArguments(args);
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
    MapUtils mapUtils = new MapUtils();
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
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.println("Line: " + line);
        Pattern p = Pattern.compile("^position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>$");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("m.group(%s): %s %d\n", i, m.group(i), Integer.valueOf(m.group(i)));
            }
            galaxy.addStar(new Star(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)),
                    Integer.valueOf(m.group(3)), Integer.valueOf(m.group(4))));

        } else {
            System.out.println("DUPA. Cos nie maczuje");
        }
    } // end of while
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


    boolean[][] createBooleanMap() {
        boolean[][] map = new boolean[getYdimension() + 1][getXdimension() + 1];
        for (int i = 0; i < getYdimension(); i++) {
            Arrays.fill(map[i], false);
        }
        for (Star star : stars) {
            map[Math.abs(minY) + star.positionY][Math.abs(minX) + star.positionX] = true;
        }
        return map;
    }


    void printGalaxyMap() {
        boolean[][] map = createBooleanMap();
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

class MapUtils {

    boolean[][] shrinkTwice(boolean[][] map, int threshold) {
        final int SHRINK_FACTOR = 2;
        int density = 0;
        int xSize = map[0].length;
        int ySize = map.length;
        boolean[][] shrinkedMap = new boolean[ySize / SHRINK_FACTOR][xSize / SHRINK_FACTOR];
        System.out.printf("Input map xSize %d, ySize %d\n", xSize, ySize);
        System.out.printf("Shrinked map xSize %d, ySize %d\n", xSize / 2, ySize / 2);
        if (threshold < 1) threshold = 1;
        else if (threshold > 4) threshold = 4;

        for (int y = 0; y < ySize / SHRINK_FACTOR; y++) {
            for (int x = 0; x < xSize / SHRINK_FACTOR; x++) {
                density = 0;
                if (map[y * 2][x * 2]) density++;
                if (map[y * 2 + 1][x * 2]) density++;
                if (map[y * 2][x * 2 + 1]) density++;
                if (map[y * 2 + 1][x * 2 + 1]) density++;
                if (density >= threshold) shrinkedMap[y][x] = true;
                else shrinkedMap[y][x] = false;
            }
        }
        return shrinkedMap;
    }


    boolean[][] shrinkTwice(boolean[][] map) {
        return shrinkTwice(map, 2);
    }
}

}
