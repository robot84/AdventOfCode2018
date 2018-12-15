package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
public class Day08 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input1.txt";
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
private MyReader myReader = new MyReader();


private Day08(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day08 dayStar1 = new Day08(INPUT_FILE2);
    dayStar1.parsingProgramArguments(args);
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
    readNode(scanner);

    System.out.println("Result is: " + readNode(scanner));

           /* Pattern p = Pattern.compile("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$");
            Matcher m = p.matcher(line);
            if (m.matches()) {
                for (int i = 1; i <= m.groupCount(); i++) {
                    Verbose.printf("m.group(%s): %s\n", i, m.group(i));
                }
            }*/


           /*
            Pattern pa = Pattern.compile("(\\d+)");
            Matcher ma = pa.matcher(line);
            System.out.println("Using Matcher.Find();");
            while (ma.find()) {
                Verbose.print("m.find(): " + ma.group() + " ");
                Verbose.printf("Start index: %d, end index: %d\n", ma.start(), ma.end());
            }
            */

            /*
            Pattern delimeter = Pattern.compile("([#@ ,:x])+");
            Verbose.print("Using Pattern.split(); Matches:");
            String[] finding = delimeter.split(line);
            for (int i = 0; i < finding.length; i++) {
                Verbose.printf("%d:%s\n", i, finding[i]);
            }
            */

}


private int readNode(Scanner scanner) {
    int numOfChilds = 0;
    int numOfMetadata = 0;
    int sumOfMedata = 0;


    if (scanner.hasNextInt()) numOfChilds = scanner.nextInt();
    else System.out.println("ERROR: End of input in readNode()");
    Verbose.printf("numOfchilds: %d ", numOfChilds);
    if (scanner.hasNextInt()) numOfMetadata = scanner.nextInt();
    else System.out.println("ERROR: End of input in readNode()");
    Verbose.printf("Number of metadata fields: %d\n", numOfMetadata);
    for (int childNum = 0; childNum < numOfChilds; childNum++) {
        sumOfMedata += readNode(scanner);
    }
    Verbose.printf("sumOfMedata+=readNode(scanner): %d", sumOfMedata);
    for (int metadataNum = 0; metadataNum < numOfMetadata; metadataNum++) {
        if (scanner.hasNextInt()) sumOfMedata += scanner.nextInt();
        else System.out.println("ERROR: End of input in readNode()");
    }
    Verbose.printf("returning sumOfMedatada: %d\n", sumOfMedata);
    return sumOfMedata;

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
