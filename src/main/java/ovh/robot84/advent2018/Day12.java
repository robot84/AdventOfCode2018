package ovh.robot84.advent2018;

import ovh.robot84.advent2018.helpers.HelperMethods;
import ovh.robot84.advent2018.helpers.MeasureTape;
import ovh.robot84.advent2018.helpers.MyReader;
import ovh.robot84.advent2018.helpers.Verbose;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-24
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


public static void main(String[] args) throws IOException {
    Day12 dayStar1 = new Day12(INPUT_FILE2);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


// #..#.#..##......###...###
//...## => #
private void star1start() throws IOException {
    Verbose.disablePrompts();
    StringBuffer field[] = new StringBuffer[1000];
    //field[0] = new StringBuffer("#..#.#..##......###...###");
    // field[0] = new StringBuffer("#...#..##.......####.#..###..#.##..########.#.#...#.#...###.#..###.###.#.#..#...#.#..##..#######.##");
    //int fieldSize=(field[0]).toString().length();
    int fieldSize;
    //="#...#..##.......####.#..###..#.##..########.#.#...#.#...###.#..###.###.#.#..#...#.#..##..#######.##".length();
    ArrayList<String> rules = new ArrayList<>();
    Stream<String> rulesStream;
    ArrayList<Boolean> isPlant = new ArrayList<Boolean>();
    int shiftSize = 10;
    int lenOfHeader = "gen: 001  ".length();
    int fieldExpansionLeft = 0;
    int fieldExpansionRight = 0;
    int lastGeneration = 0;
    String line = null;
    field[0] = new StringBuffer(myReader.get_line());
    fieldSize = field[0].length();

    Stream<String> allRulesStream = Files.lines(Paths.get(INPUT_FILE2));
    List<String> inputList = allRulesStream.collect(Collectors.toList());
    String header = inputList.get(0);
    inputList.remove(0);
    allRulesStream = inputList.stream();

    /*allRulesStream.peek(rule -> {

                Verbose.print(3,"Line: " + rule);
            });*/

    Stream<String> fullPotRulesStream = allRulesStream.peek(rule -> {
        System.out.println("Line: " + rule);
    }).filter(s -> s.charAt(9) == '#');
    System.out.println(fullPotRulesStream.count());





    while ((line = myReader.get_line()) != null) {
        Verbose.print(3, "Line: " + line);

        //...## => #
        // look out when input has [ ] symbols. Quote them! Then in trouble start with:
        Pattern p = Pattern.compile("(.+) => (.)");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            HelperMethods.printPatternMatchedGroups(m);
            rules.add(m.group(1));
            if ((m.group(2)).equals("#")) isPlant.add(true);
            else isPlant.add(false);
        }
    }
    boolean hit = false;

    MeasureTape.measureTapeType1(shiftSize + lenOfHeader, 160);
    Verbose.printf("FiledSize: %d\n", fieldSize);

    Stream<String> dotStream = Stream.generate(() -> ".").limit(shiftSize);
    Stream<String> dotStream2 = Stream.generate(() -> ".").limit(shiftSize);
    Stream fieldStream = Stream.of(field[0]);
    Stream fieldStream2 = Stream.concat(dotStream, fieldStream);
    Stream fieldStream3 = Stream.concat(fieldStream2, dotStream2);
    Stream<String> nextFieldStream;

    for (int gen = 0; gen <= lastGeneration; gen++) {
        Verbose.printf("gen: %3d  %s\n", gen, field[gen].toString());
        StringBuffer nextGenField = new StringBuffer("");
        nextFieldStream = Stream.empty();
        //for (int shift = 0; shift < 2; shift++) nextGenField.append(".");
        nextFieldStream = Stream.concat(nextFieldStream, Stream.of(".."));

        for (int index = 0; index < fieldSize + shiftSize + fieldExpansionRight + fieldExpansionLeft; index++) {
            hit = false;
            for (int rule = 0; rule < rules.size(); rule++) {
                Verbose.printf(3, "%s %s|", field[gen].substring(index, index + 5));//,rules.get(rule));
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
        Verbose.printf(3, "nextGen: (%s)\n", nextGenField.toString());

        field[gen + 1] = nextGenField;
        //Verbose.printf("nextGen: %s\n",field[gen+1]);
    }
    MeasureTape.measureTapeType1(shiftSize + lenOfHeader, 160);

    //result
    int sum = 0;

    Verbose.printf("Plants on positions: ");
    for (int index = 2; index < fieldSize + shiftSize + fieldExpansionRight + fieldExpansionLeft; index++) {
        if (field[lastGeneration].charAt(index) == '#') {

            int position = (-fieldExpansionLeft + index - shiftSize);
            sum += (position);
            Verbose.printf("%d ", position);

        }
    }

    int numOfPlants = 0;
    for (int index = 0; index < field[lastGeneration].length(); index++) {
        if (field[lastGeneration].charAt(index) == '#') numOfPlants++;
    }

    System.out.println();
    Verbose.printf("Num of plants: %d\n", numOfPlants);
    System.out.println("Result for star01: " + sum);
    Long result = 7025L + (91L * (50000000000L - 100L + 46L));
    System.out.println("Result of star02: " + result);

}


}
