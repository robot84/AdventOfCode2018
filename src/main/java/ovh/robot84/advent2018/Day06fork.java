package ovh.robot84.advent2018;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06fork {
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day06input2.txt";
    final static int ARRAY_MAX_X = 2_000;
    final static int ARRAY_MAX_Y = 2_000;
    final static int COMMON = Integer.MAX_VALUE - 1;
    private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day06input1.txt";
    private final static String MAP_FILE = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\map.txt";
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
    private MyReader myReader = new MyReader();

    private Day06fork(String input_file) {
        myReader.open_file(input_file);
    }


    public static void main(String[] args) {
        Day06fork dayStar1 = new Day06fork(INPUT_FILE1);
        System.out.println("ARGS:" + args.length);
        if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
        dayStar1.star1start();
    }


    private void star1start() {
        String line = null;
        int c = 0;

        input.add(new Point(0, 0));
        playerCountedToSummary.add(false);
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

        initPositions(numOfPlayers);

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
        freshMove = new boolean[maxY][maxX];
        System.out.printf("Allocated %,d B for map", Integer.BYTES * maxX * maxY);

        openMap();

        for (int distance = 1; distance < Math.min(maxX, maxY); distance++) {
            clearFreshMove();
            Verbose.print("Move nr " + distance);
            for (int i = 1; i <= numOfPlayers; i++) {
                Verbose.print("Player nr " + i);
                goPossesing((Integer) (i), input.get(i), distance);
                Verbose.print("End of turn for Player nr " + i);
            }
            writeMap();
        }

        HelperMethods.printResult(sumValidPossesions());
        System.out.println("Writing map. Please be patient...");
        writeMap();
        closeMap();
        System.exit(0);
    }


    void initPositions(Integer numOfPlayers) {
        for (int i = 1; i <= numOfPlayers; i++) {
            goPossesing(i, input.get(i), 0);
        }
    }


    private int sumValidPossesions() {
        int sum = 0;
        for (int i = 1; i >= numOfPlayers; i++) {
            if (playerCountedToSummary.get(i)) sum += playersPossesion.get(i);
        }
        return sum;
    }

    private void generateAllPlayersPossesion() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (mapa[y][x] != COMMON) playersPossesion.set(mapa[y][x], playersPossesion.get(mapa[y][x]) + 1);
            }
        }
    }


    private void clearFreshMove() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                freshMove[y][x] = false;
            }
        }
    }


    boolean goPossesing(Integer player, Point po, int distance) {
        Verbose.printf("Player %s going possesing", player.toString());

        if ((po.x < 0 || po.x >= maxX || po.y < 0 || po.y >= maxY)) {
            if (playerCountedToSummary.get(player))
                playerCountedToSummary.set(player, false);
            Verbose.printf("Player %s will not be counted to summary", player.toString());
            return false;
        }

        distance--;
        if (distance >= 0) {
            Point reference = new Point(po);
            po.setLocation(new Point(reference.x, reference.y - 1));
            goPossesing(player, po, distance);
            po.setLocation(new Point(reference.x + 1, reference.y));
            goPossesing(player, po, distance);
            po.setLocation(new Point(reference.x, reference.y + 1));
            goPossesing(player, po, distance);
            po.setLocation(new Point(reference.x - 1, reference.y));
            goPossesing(player, po, distance);
        } else
            possesField(player, po, po.x, po.y, distance);

        return false;
    }

    boolean possesField(Integer playerID, Point po, Integer x, Integer y, int distance) {
        if (mapa[y][x] == 0) {
            mapa[y][x] = playerID;
            freshMove[y][x] = true;
            Verbose.printf("Player %s possesed field (%s,%s)\n", playerID.toString(), x.toString(), y.toString());
            return true;
        } else if (freshMove[y][x] & mapa[y][x] != playerID) {
            mapa[y][x] = COMMON;
            return false;
        } else {
            return false;
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


    void writeMap() {

        DotCounter dc = new DotCounter(300);
        dc.start();

        try {
            wri.write("\nMapa\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write to map file.");
            e.printStackTrace();
        }


        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                try {
                    wri.write((mapa[y][x] + 'a' - 1));
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

}
