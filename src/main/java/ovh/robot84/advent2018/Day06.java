package ovh.robot84.advent2018;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 {
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day06input2.txt";
    final static int ARRAY_MAX_X = 2_000;
    final static int ARRAY_MAX_Y = 2_000;
    private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day06input1.txt";
    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<String> boxID = new ArrayList<>();
    String s;
    private MyReader myReader = new MyReader();

    private Day06(String input_file) {
        myReader.open_file(input_file);
    }

    public static void main(String[] args) {
        Day06 dayStar1 = new Day06(INPUT_FILE1);
        dayStar1.star1start();
    }


    private void star1start() {
        String line = null;
        int c = 0;

        /*
        Read input
         */
        while ((line = myReader.get_line()) != null) {
            System.out.println("Line: " + line);
        }

        while ((c = myReader.read()) != -1) {
            System.out.print("!" + (char) c);
        }

        /*
        Parse input
         */
        line = "#20 @ 214,215: 217x218";
        Pattern p = Pattern.compile("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                System.out.printf("m.group(%s): %s\n", i, m.group(i));
            }
        }


        Pattern pa = Pattern.compile("(\\d+)");
        Matcher ma = pa.matcher(line);
        System.out.println("Using Matcher.Find();");
        while (ma.find()) {
            System.out.print("m.find(): " + ma.group() + " ");
            System.out.printf("Start index: %d, end index: %d\n", ma.start(), ma.end());
        }


        Pattern delimeter = Pattern.compile("([#@ ,:x])+");
        System.out.println("Using Pattern.split(); Matches:");
        String[] finding = delimeter.split(line);
        for (int i = 0; i < finding.length; i++) {
            System.out.printf("%d:%s\n", i, finding[i]);
        }
    }


}
