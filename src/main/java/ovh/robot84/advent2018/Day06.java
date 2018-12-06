package ovh.robot84.advent2018;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 {
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day06input2.txt";
    private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day06input1.txt";
    final static int ARRAY_MAX_X = 2_000;
    final static int ARRAY_MAX_Y = 2_000;
    final static int COMMON = Integer.MAX_VALUE - 1;
    private final static String MAP_FILE = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\mapDay06star1.txt";
    int[][] mapa;
    boolean[][] freshMove;
    BufferedWriter wri;
    Integer maxX = 0;
    Integer maxY = 0;
    Integer numOfPlayers;

    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<String> boxID = new ArrayList<>();
    private MyReader myReader = new MyReader();
    ArrayList<Point> input = new ArrayList<>();
    ArrayList<Boolean> playerCountedToSummary = new ArrayList<>();
    ArrayList<Integer> playersPossesion = new ArrayList<>();
    ArrayList<Integer> playersDistance = new ArrayList<>();
    ArrayList<Integer> playersCountedToSummary = new ArrayList<>();


    private Day06(String input_file) {
        myReader.open_file(input_file);
    }


    public static void main(String[] args) {
        Day06 dayStar1 = new Day06(INPUT_FILE2);
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

        /*for (int distance = 1; distance < Math.min(maxX,maxY); distance++) {
            clearFreshMove();
            Verbose.print("Move nr "+distance);
            for (int i = 1; i <= numOfPlayers; i++) {
                Verbose.print("Player nr "+i);
                goPossesing((Integer)(i), input.get(i), distance);
                Verbose.print("End of turn for Player nr "+i);
            }

        }*/
        dividingLandsToPeople();
        removeNeiboursWithInfinityPossesion();

        HelperMethods.printResult(typeMaxPossesionFromValidUsers());
        System.out.println("Writing map. Please be patient...");
        writeMap();
        closeMap();
        System.exit(0);
    }

    private void removeNeiboursWithInfinityPossesion() {
        int userNum = 1;
        {
            HERE_IS_LIFE:
            for (Integer phantom = null; userNum <= numOfPlayers; userNum++) {
                //Verbose.printf("***Player %s try to pass the challenge.\n",userNum);
                int y = 0;
                for (int x = 0; x < maxX; x++) if (mapa[y][x] == userNum) continue HERE_IS_LIFE;
                y = maxY - 1;
                for (int x = 0; x < maxX; x++) if (mapa[y][x] == userNum) continue HERE_IS_LIFE;

                int x = 0;
                //Verbose.printf("***Player %s passed round 2.\n",userNum);
                for (y = 0; y < maxY; y++) if (mapa[y][x] == userNum) continue HERE_IS_LIFE;
                x = maxX - 1;
                //Verbose.printf("***Player %s passed round 3.\n",userNum);
                for (y = 0; y < maxY; y++) if (mapa[y][x] == userNum) continue HERE_IS_LIFE;

                playersCountedToSummary.add(userNum);
                Verbose.printf("***Player %s is going to final part of the game.\n", userNum);
            }
        }
    }

    void dividingLandsToPeople() {
        DotCounter dotCounter1 = new DotCounter(300);
        dotCounter1.start();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                dotCounter1.check();
                Point actualIteratedFieldOnMap = new Point(x, y);
                playersDistance.clear();
                playersDistance.add(10000);
                for (int userNum = 1; userNum <= numOfPlayers; userNum++) {
                    playersDistance.add(manhattanDistance(actualIteratedFieldOnMap, input.get(userNum)));
                    Verbose.printf("ManDist to field (%s,%s) for player %s on (%s,%s) is %s\n", x, y, userNum, input.get(userNum).x, input.get(userNum).y, playersDistance.get(userNum));
                }

                int min = 10000;
                for (int i = 1; i <= numOfPlayers; i++) {
                    if (playersDistance.get(i) < min) min = playersDistance.get(i);
                }


                int indexOfMinValue = 0;
                int userCounter = 0;
                for (int index = 1, valueNew = 0; index <= numOfPlayers; index++) {
                    valueNew = playersDistance.get(index);
                    if (valueNew == min) {
                        userCounter++;
                        indexOfMinValue = index;
                    }
                }
                if (userCounter > 1) {
                    mapa[y][x] = COMMON;
                    Verbose.printf("Minimal dist has more than one user!\n");
                } else {
                    mapa[y][x] = indexOfMinValue;
                    playersPossesion.set(indexOfMinValue, playersPossesion.get(indexOfMinValue) + 1);
                    Verbose.printf("Minimal dist has got user %s\n", indexOfMinValue);
                }
            }
        }
    }

    private int manhattanDistance(Point p1, Point p2) {
        // kto ma najblizej do tego punktu na mapie?
        int x0 = p1.x, x1 = p2.x, y0 = p1.y, y1 = p2.y;
        int distance = Math.abs(x1 - x0) + Math.abs(y1 - y0);
        //Verbose.printf("Welcome in Manhattan project!\n");
        //Verbose.printf("(%s,%s) (%s,%s) -> %s\n",x0,y0,x1,y1,distance);
        return distance;
    }

    void initPositions(Integer numOfPlayers) {
        for (int i = 1; i <= numOfPlayers; i++) {
            goPossesing(i, input.get(i), 0);
        }
    }


    private int typeMaxPossesionFromValidUsers() {

        int max = 0;
        for (Integer player : playersCountedToSummary)
            if (playersPossesion.get(player) > max) max = playersPossesion.get(player);


        //Verbose.printf("User %s has got %s farms\n",player,playersPossesion.get(player));


        return max;
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
