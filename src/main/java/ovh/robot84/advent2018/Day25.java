package ovh.robot84.advent2018;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class Day25 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day25input2.txt";
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day25input2.txt";
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<Integer> boxID = new ArrayList<>();
private MyReader myReader = new MyReader();


private Day25(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day25 dayStar1 = new Day25(INPUT_FILE1);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    try {
        dayStar1.star1start();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


private void star1start() throws IOException {
    String line = null;

/*ArrayList<Integer> boxID = new ArrayList<>();
    ArrayList<ArrayList<Integer>> a={{0,0,0,0},
    {3,0,0,0},
    {0,3,0,0},
    {0,0,3,0},
    {0,0,0,-3},
    {0,0,0,6},
    {-9,0,0,0},
    {12,0,0,0}};*/

    /*        Read input         */
    //
    List<Point4D> points4D = new ArrayList<>();
    int numOfConstelations = 0;
    int nuOfPoints = 0;
    while ((line = myReader.get_line()) != null) {
        Verbose.println(3, "Line: " + line);
            /*while ((c = myReader.read()) != -1) {
                Verbose.print("!" + (char) c);
            */

        //Stream<String> inputDataStream= Files.lines(Paths.get(INPUT_FILE1));
        /*
        Parse input
         */
/*
25 min - walka z regexp bo \\s+ nie wykrywa przecinka. rozw jest \\p{Punct}. no i ujemne liczby obsluzyc
40 min - stracony czas na probe wprowadzenia danych nie z pliku, tylko jako fragment kodu, ze zmienna={caly input}
50 min - dane wejsciowe juz w pamieci i wyswietlam je.
 */

        //0,0,0,0
        // look out when input has [ ] symbols. Quote them! Then in trouble start with:
        //Pattern p = Pattern.compile("\\s*(.*)\\s*");
        //(\-?\d+)\s*\p{Punct}(\-?\d+)\p{Punct}(\-?\d+)\s*\p{Punct}(\-?\d+)
        // Pattern p = Pattern.compile("(-?\\d+).*");

        Point4D point4D;

        List<Integer> coordinate = new ArrayList<>();
        Pattern p = Pattern.compile("(-?\\d+)\\s*\\p{Punct}(-?\\d+)\\p{Punct}(-?\\d+)\\s*\\p{Punct}(-?\\d+)");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            Verbose.print(3, "matched");
            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.printf(3, "m.group(%s): \"%s\" \t", i, m.group(i));
                coordinate.add(Integer.valueOf(m.group(i)));
            }
            Verbose.println("");
            point4D = new Point4D(coordinate, nuOfPoints);
            points4D.add(point4D);
            nuOfPoints++;
        }
    }

    for (Point4D point4D : points4D)
        Verbose.printf("::: %d %d %d %d\n", point4D.getX(), point4D.getY(), point4D.getZ(), point4D.getT());
    int index = 1;
    for (Point4D point4D_1 : points4D) {
        for (Point4D point4D_2 : points4D.subList(index, points4D.size())) {
            // if ( manh dist(p1,p2) <=3) if(!p1.isIn
            int dist = ManhattanDistance.compute4DDistance(point4D_1, point4D_2);
            //Verbose.printf("Manh distance between %s %s is %d\n",point4D_1.toString(),point4D_2.toString(),dist);
            Verbose.printf("Manh distance between %d %d is %d\n", point4D_1.getID(), point4D_2.getID(), dist);

            if (dist <= 3) {
                if (!point4D_1.isInConstelation() && !point4D_2.isInConstelation()) {
                    Verbose.printf("Creating new constelation \"KuTa%d\".\n", numOfConstelations);
                    Verbose.printf("Adding point %d to constelation.\n", point4D_1.getID());
                    point4D_1.addToConstelation();
                    Verbose.printf("Adding point %d to constelation.\n", point4D_2.getID());
                    point4D_2.addToConstelation();
                    numOfConstelations++;
                } else if (!point4D_1.isInConstelation() && point4D_2.isInConstelation()) {
                    Verbose.printf("Adding point %d to constelation.\n", point4D_1.getID());
                    point4D_1.addToConstelation();
                } else if (point4D_1.isInConstelation() && !point4D_2.isInConstelation()) {
                    Verbose.printf("Adding point %d to constelation.\n", point4D_2.getID());
                    point4D_2.addToConstelation();
                }
                if (point4D_1.isInConstelation() && point4D_2.isInConstelation()) {
                    // nothing to do
                }
            }
        }
        index++;
    }
    int numOfPointsInConstelations = 0;
    for (Point4D point4D_1 : points4D) if (point4D_1.isInConstelation()) numOfPointsInConstelations++;
    System.out.println("overall num of points in any constelation " + numOfPointsInConstelations);
    System.out.println("Rezult: " + numOfConstelations);
}




}

class Point4D {
private List<Integer> coordinates;
private boolean isInConstelation;
private int id;


public Point4D(List<Integer> coordinates, int id) {
    this.coordinates = coordinates;
    this.id = id;

}


public Point4D(List<Integer> coordinates) {
    this.coordinates = coordinates;

}


public boolean isInConstelation() {
    return this.isInConstelation;
}


public void addToConstelation() {
    this.isInConstelation = true;
}


public int getX() {
    return this.coordinates.get(0);
}


public int getY() {
    return this.coordinates.get(1);
}


public int getZ() {
    return this.coordinates.get(2);
}


public int getT() {
    return this.coordinates.get(3);
}


@Override
public String toString() {
    return String.format("[ID:%d](%d,%d,%d,%d)", getID(), getX(), getY(), getZ(), getT());
}


public int getID() {
    return id;
}

}
