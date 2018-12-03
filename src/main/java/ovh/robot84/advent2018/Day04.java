package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.HashMap;

public class Day04 {
    final static int ARRAY_MAX_X = 10_000;
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day04input2.txt";
    final static int ARRAY_MAX_Y = 10_000;
    private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day04input1.txt";
    private MyReader myReader = new MyReader();
    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<String> boxID = new ArrayList<>();
    String s;

    private Day04(String input_file) {
        myReader.open_file(input_file);
    }

    public static void main(String[] args) {
        Day04 dayStar1 = new Day04(INPUT_FILE1);
        dayStar1.star1start();
    }

    private void doForEachLineOfInput(String line) {


        String[] foundByFindRegexMethod;
        foundByFindRegexMethod = FindRegex.findRegex("(\\d+)", line);

        Integer[] numbers = new Integer[foundByFindRegexMethod.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.valueOf(foundByFindRegexMethod[i]);
        }

    }

    private void star1start() {
        String line;
        while ((line = myReader.get_line()) != null) {
            doForEachLineOfInput(line);
        }
    }
}
