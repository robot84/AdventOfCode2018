package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Day08 dayStar1 = new Day08(INPUT_FILE1);
        System.out.println("ARGS:" + args.length);
        if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
        dayStar1.star1start();
    }


    private void star1start() {
        String line = null;
        int c = 0;


        /*
        Read input
         */
        while ((line = myReader.get_line()) != null) {
            Verbose.print("Line: " + line);
        }

        while ((c = myReader.read()) != -1) {
            Verbose.print("!" + (char) c);
        }

        /*
        Parse input
         */
        line = "#20 @ 214,215: 217x218";
        Pattern p = Pattern.compile("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                Verbose.printf("m.group(%s): %s\n", i, m.group(i));
            }
        }


        Pattern pa = Pattern.compile("(\\d+)");
        Matcher ma = pa.matcher(line);
        System.out.println("Using Matcher.Find();");
        while (ma.find()) {
            Verbose.print("m.find(): " + ma.group() + " ");
            Verbose.printf("Start index: %d, end index: %d\n", ma.start(), ma.end());
        }


        Pattern delimeter = Pattern.compile("([#@ ,:x])+");
        Verbose.print("Using Pattern.split(); Matches:");
        String[] finding = delimeter.split(line);
        for (int i = 0; i < finding.length; i++) {
            Verbose.printf("%d:%s\n", i, finding[i]);
        }
    }


}
