package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-01
 */
public class Day08star02 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input1.txt";
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
private MyReader myReader = new MyReader();


private Day08star02(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day08star02 dayStar1 = new Day08star02(INPUT_FILE2);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


private void star1start() {
    String line = null;

    /*        Read input         */
    if ((line = myReader.get_line()) != null) {
        Verbose.print("Line: " + line);
    }

    Scanner scanner = new Scanner(line);
    //18568
    System.out.println("Result is: " + readNode(scanner));

}


private int readNode(Scanner scanner) {
    int numOfChilds = 0;
    int numOfMetadata = 0;
    int sumOfMedata = 0;
    ArrayList<Integer> childValue = new ArrayList<Integer>();
    ArrayList<Integer> indexOfChild = new ArrayList<Integer>();
    childValue.add(0); // not used element, but with it we can numerate child from 1. not from 0

    if (scanner.hasNextInt()) numOfChilds = scanner.nextInt();
    else System.out.println("ERROR: End of input in readNode()");
    Verbose.printf("numOfchilds: %d ", numOfChilds);
    if (scanner.hasNextInt()) numOfMetadata = scanner.nextInt();
    else System.out.println("ERROR: End of input in readNode()");
    Verbose.printf("Number of metadata fields: %d\n", numOfMetadata);

    for (int childNum = 0; childNum < numOfChilds; childNum++) {
        childValue.add(readNode(scanner));
    }
    Verbose.printf("sumOfMedata+=readNode(scanner): %d", sumOfMedata);
    if (numOfChilds == 0)
        for (int metadataNum = 0; metadataNum < numOfMetadata; metadataNum++) {
            if (scanner.hasNextInt()) sumOfMedata += scanner.nextInt();
            else System.out.println("ERROR: End of input in readNode()");
        }
    else {
        for (int metadataNum = 0; metadataNum < numOfMetadata; metadataNum++) {
            if (scanner.hasNextInt()) indexOfChild.add(scanner.nextInt());
            else Verbose.println("ERROR: End of input in readNode()");
        }
        for (Integer child : indexOfChild) if (child > 0 && child <= numOfChilds) sumOfMedata += childValue.get(child);
    }
    Verbose.printf("returning sumOfMedatada: %d\n", sumOfMedata);
    return sumOfMedata;

}


}
