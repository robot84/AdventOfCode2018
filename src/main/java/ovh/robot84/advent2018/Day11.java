package ovh.robot84.advent2018;

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
    dayStar1.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
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

    FuelCell cell = new FuelCell(3, 5, 8);
    System.out.println(cell.getPwr());
    cell = new FuelCell(122, 79, 57);
    System.out.println(cell.getPwr());
    cell = new FuelCell(217, 196, 39);
    System.out.println(cell.getPwr());
    cell = new FuelCell(101, 153, 71);
    System.out.println(cell.getPwr());

    ArrayList<FuelCell3x3Field> fields = new ArrayList<FuelCell3x3Field>();

    for (int y = 1; y < MAP_Y - 1; y++) {
        for (int x = 1; x < MAP_X - 1; x++) {
            fields.add(new FuelCell3x3Field(x, y, 8141));
        }
    }
    FuelCell3x3Field max = Collections.max(fields);
    System.out.printf("Rezult: %d @(%d,%d)\n", max.getTotalPwr(), max.getXY().getKey(), max.getXY().getValue());


}




private void parsingProgramArguments(String[] args) {
    System.out.println("Program ARGS num:" + args.length + " ARGS:" + args);
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
    if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
    if (args.length > 0 && args[0].equals("--create-input-file"))
        HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), INPUT_FILE1);
}

}
