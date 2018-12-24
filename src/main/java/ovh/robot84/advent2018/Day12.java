package ovh.robot84.advent2018;

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
 * <p>
 * If you don't have or don't want to use Verbose class, you can
 * change all Verbose.printf() to System.out.printf()
 * and delete calls to other Verbose methods.
 */

public class Day12 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day12input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day12input1.txt";
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
private MyReader myReader = new MyReader();


private Day12(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day12 dayStar1 = new Day12(INPUT_FILE2);
    dayStar1.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


// #..#.#..##......###...###
//...## => #
private void star1start() {
    Verbose.disablePrompts();
    StringBuffer field[] = new StringBuffer[1350];
    //field[0] = new StringBuffer("#..#.#..##......###...###");
    // field[0] = new StringBuffer("#...#..##.......####.#..###..#.##..########.#.#...#.#...###.#..###.###.#.#..#...#.#..##..#######.##");
    //int fieldSize=(field[0]).toString().length();
    int fieldSize;
    //="#...#..##.......####.#..###..#.##..########.#.#...#.#...###.#..###.###.#.#..#...#.#..##..#######.##".length();
    ArrayList<String> rules = new ArrayList<>();
    ArrayList<Boolean> isPlant = new ArrayList<Boolean>();
    int shiftSize = 10;
    int fieldExpansionLeft = 0;
    int fieldExpansionRight = 0;
    String line = null;
    field[0] = new StringBuffer(myReader.get_line());
    fieldSize = field[0].length();
    while ((line = myReader.get_line()) != null) {
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.print("Line: " + line);

        //...## => #
        // look out when input has [ ] symbols. Quote them! Then in trouble start with:
        Pattern p = Pattern.compile("(.+) => (.)");
        Matcher m = p.matcher(line);
        if (m.matches()) {

            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("m.group(%s): %s\n", i, m.group(i));
            }
            rules.add(m.group(1));
            if ((m.group(2)).equals("#")) isPlant.add(true);
            else isPlant.add(false);
        }
    }
    boolean hit = false;
    measureTape(shiftSize);
    Verbose.printf("FiledSize: %d\n", fieldSize);

    for (int shift = 0; shift < shiftSize; shift++) field[0].insert(0, ".");

    for (int shift = 0; shift < shiftSize; shift++) field[0].append(".");
    //for (int shift = 0; shift < shiftSize; shift++) isPlant.add(0,false);


    for (int gen = 0; gen < 21; gen++) {
        Verbose.printf("gen: %2d  %s\n", gen, field[gen].toString());
        StringBuffer nextGenField = new StringBuffer("");
        for (int shift = 0; shift < 2; shift++) nextGenField.append(".");

        for (int index = 0; index < fieldSize + shiftSize + fieldExpansionRight + fieldExpansionLeft; index++) {
            hit = false;
            for (int rule = 0; rule < rules.size(); rule++) {
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("%s %s|", field[gen].substring(index, index + 5));//,rules.get(rule));
                if (field[gen].substring(index, index + 5).equals(rules.get(rule))) {
                    hit = true;
                    if (isPlant.get(rule)) {
                        nextGenField.append("#");
                        if (index >= (fieldSize - 1 - 2 + shiftSize + 2 + fieldExpansionRight + fieldExpansionLeft)) {
                            fieldExpansionRight++;
                            //Verbose.printf("+");
                        }
                        if (index < shiftSize - 1 - 3 && index > shiftSize - 1 - 3 - 2) {
                            fieldExpansionLeft++;
                            //Verbose.printf("-");
                        }
                    } else nextGenField.append(".");
                } else {
                }
            }
            if (!hit) nextGenField.append(".");
        }
        for (int shift = 0; shift < shiftSize; shift++) nextGenField.append(".");
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.printf("nextGen: (%s)\n", nextGenField.toString());

        field[gen + 1] = nextGenField;
        //Verbose.printf("nextGen: %s\n",field[gen+1]);
    }
    measureTape(shiftSize);
    int sum = 0;
    Verbose.printf("%s\n", field[20].toString());
    for (int index = 2; index < fieldSize + shiftSize + fieldExpansionRight + fieldExpansionLeft; index++) {
        if (field[20].charAt(index) == '#') {

            int position = (-fieldExpansionLeft + index - shiftSize);
            sum += (position);
            Verbose.printf("%d ", position);
        }
    }
    System.out.println("Result: " + sum);

}


private void measureTape(int shiftSize) {
    for (int shift = 0; shift < shiftSize + 9; shift++) Verbose.printf(" ");
    //Verbose.printf("0         1");
    for (int x = 0; x < 6; x++) {
        Verbose.printf("%d", x);
        for (int digit = 0; digit < 9; digit++) Verbose.printf(" ");
    }
    Verbose.printf("\n");
    for (int shift = 0; shift < shiftSize + 9; shift++) Verbose.printf(" ");
    for (int x = 0; x < 6; x++) {
        Verbose.printf("0", x);
        for (int digit = 0; digit < 9; digit++) Verbose.printf(" ");
    }
    Verbose.printf("\n");
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
