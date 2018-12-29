package ovh.robot84.advent2018;

import ovh.robot84.advent2018.helpers.FuelCell3x3Field;
import ovh.robot84.advent2018.helpers.FuelCellField;
import ovh.robot84.advent2018.helpers.HelperMethods;
import ovh.robot84.advent2018.helpers.MyReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-01
 */
public class Day11 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input1.txt";
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
private MyReader myReader = new MyReader();


private Day11(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day11 dayStar1 = new Day11(INPUT_FILE1);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    //dayStar1.star1start();
    dayStar1.star2start();
}


/*
After:
 6 minute - read the story
 13 min - first successful compilation and class FuelCell created
15 min - 4 tests of class completed
25 min - class FuelCell3x3Field implemented and first test passed
29 min - added to class FuelCell3x3Field fields x,y and method getXY()
33 min - tested new method
35 - Star01 accomplished with full success :))

 */
private void star1start() {

    int MAP_X = 300;
    int MAP_Y = 300;

    ovh.robot84.advent2018.helpers.FuelCell cell = new ovh.robot84.advent2018.helpers.FuelCell(3, 5, 8);
    System.out.println(cell.getPwr());
    cell = new ovh.robot84.advent2018.helpers.FuelCell(122, 79, 57);
    System.out.println(cell.getPwr());
    cell = new ovh.robot84.advent2018.helpers.FuelCell(217, 196, 39);
    System.out.println(cell.getPwr());
    cell = new ovh.robot84.advent2018.helpers.FuelCell(101, 153, 71);
    System.out.println(cell.getPwr());

    ArrayList<ovh.robot84.advent2018.helpers.FuelCell3x3Field> fields = new ArrayList<ovh.robot84.advent2018.helpers.FuelCell3x3Field>();

    for (int y = 1; y < MAP_Y - 1; y++) {
        for (int x = 1; x < MAP_X - 1; x++) {
            fields.add(new ovh.robot84.advent2018.helpers.FuelCell3x3Field(x, y, 8141));
        }
    }
    FuelCell3x3Field max = Collections.max(fields);
    System.out.printf("Rezult: %d @(%d,%d)\n", max.getTotalPwr(), max.getXY().getKey(), max.getXY().getValue());


}

/*
After:
15min - class expanded
25min - waiting for end of long run
35 min - first optimalization done
48 min - run ends little bit faster. incorrect result :(
50 min - second optimalization done
60 min - third optimalization - clear max list when it reach 300 elements, not (300x300x300=27*10^6)
85 min - forth opt. Now we .set() arraylist elements, not .add() them.
100 min (10min) - fifth opt. no arraylist for counting max. no .clone. only static methods.
        and still slow :( about 300 seconds
105 min - test ended with success :) so let's put real data from our puzzle and wait...
115 min - success :) Star02 completed
 */


private void star2start() {

    int MAP_X = 300;
    int MAP_Y = 300;
    int localMaxPwr3 = 0, localMaxPwrX3 = 0, localMaxPwrY3 = 0, localMaxPwrSize3 = 0;

    for (int size = 1; size <= MAP_X; size++) {
        int localMaxPwr2 = 0, localMaxPwrX2 = 0, localMaxPwrY2 = 0, localMaxPwrSize2 = 0;
        System.out.println("Size: " + size);
        for (int y = 1; y < MAP_Y + 1 - size; y++) {
            int localMaxPwr1 = 0, localMaxPwrX1 = 0, localMaxPwrY1 = 0, localMaxPwrSize1 = 0;
            for (int x = 1; x < MAP_X + 1 - size; x++) {
                // System.out.println("fileds.size(): "+fields.size()+" ("+x+","+y+")");
                int newFieldPwr = FuelCellField.getTotalPwr(x, y, 8141, size);
                if (localMaxPwr1 < newFieldPwr) {
                    localMaxPwr1 = newFieldPwr;
                    localMaxPwrX1 = x;
                    localMaxPwrY1 = y;
                    localMaxPwrSize1 = size;
                }
            }
            if (localMaxPwr2 < localMaxPwr1) {
                localMaxPwr2 = localMaxPwr1;
                localMaxPwrX2 = localMaxPwrX1;
                localMaxPwrY2 = localMaxPwrY1;
                localMaxPwrSize2 = localMaxPwrSize1;
            }
        }
        if (localMaxPwr3 < localMaxPwr2) {
            localMaxPwr3 = localMaxPwr2;
            localMaxPwrX3 = localMaxPwrX2;
            localMaxPwrY3 = localMaxPwrY2;
            localMaxPwrSize3 = localMaxPwrSize2;
        }
    }
    System.out.printf("!!! Rezult: %d @(%d,%d,%d)\n", localMaxPwr3, localMaxPwrX3, localMaxPwrY3, localMaxPwrSize3);

}


}
