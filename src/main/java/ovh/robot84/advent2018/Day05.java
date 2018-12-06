package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.HashMap;

public class Day05 {
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day05input2.txt";
    final static int ARRAY_MAX_X = 10_000;
    final static int ARRAY_MAX_Y = 10_000;
    private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day05input1.txt";
    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<String> boxID = new ArrayList<>();
    String s;
    private MyReader myReader = new MyReader();


    private Day05(String input_file) {
        myReader.open_file(input_file);
    }

    public static void main(String[] args) {
        Day05 dayStar1 = new Day05(INPUT_FILE1);
        System.out.println("ARGS:" + args.length);
        if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
        Verbose.disableVerbose();
        dayStar1.star1start();
    }


    private StringBuffer doForEachLineOfInput(StringBuffer line) {
        StringBuffer output = new StringBuffer();
        String c1 = null;
        String c2 = null;
        Character cc1 = null, cc2 = null;
        int i = 0;
        int lineLen = line.length() - 1;
        for (i = 0; i < lineLen; i++) {
            cc1 = line.charAt(i);
            cc2 = line.charAt(i + 1);
            if (Math.abs(cc1.compareTo(cc2)) == 32) {
                //Verbose.printf("%c%c ",cc1,cc2);
            /*c1 = line.substring(i, i + 1);
            c2 = line.substring(i + 1, i + 2);
            if (c1.compareToIgnoreCase(c2) == 0 && c1.compareTo(c2) != 0) {
            */    //System.out.printf("%s__%s",output,line.substring(i+2));
                output.append(line.substring(i + 2));
                return output;
            } else {
                output.append(cc1);
            }
        }
        output.append(cc2);
        //System.out.printf("returned len: %d \n",output.length());
        return output;

    }


    private StringBuffer doForEachLineOfInputNotOptimized(StringBuffer line) {
        StringBuffer output = new StringBuffer();
        String c1 = null;
        String c2 = null;
        int i = 0;
        for (i = 0; i < line.length() - 1; i++) {
            //Verbose.printf("%c%c ",cc1,cc2);
            c1 = line.substring(i, i + 1);
            c2 = line.substring(i + 1, i + 2);
            if (c1.compareToIgnoreCase(c2) == 0 && c1.compareTo(c2) != 0) {
                //System.out.printf("%s__%s",output,line.substring(i+2));
                output.append(line.substring(i + 2));
                return output;
            } else {
                output.append(c1);
            }
        }
        output.append(c2);
        //System.out.printf("returned len: %d \n",output.length());
        return output;

    }

    private void star1start() {

        StringBuffer out1 = new StringBuffer(myReader.get_line());
        //StringBuffer out=new StringBuffer("aAbBdCcCabcAcCaCBAcCcaDA");
        int counter = 1;
        ArrayList<Integer> results = new ArrayList<>();

        for (byte i = 0; i < 26; i++) {
            byte c1 = 'a', c2 = 'A';
            String regex = "" + "[" + (char) (c1 + i);
            regex += (char) (c2 + i) + "]";
            Verbose.println(regex);
            StringBuffer out = new StringBuffer(out1.toString().replaceAll(regex, ""));
            StringBuffer outOld = out;

            // Verbose.printf("%c LEN: %d\n", c1 + i, out.toString().length());
            //System.out.flush();

            DotCounter dotCounter = new DotCounter(250, "!.");
            dotCounter.start();

            long startGlobal = System.nanoTime();
            do {
                outOld = out;
                //System.out.printf("\n%d: ",out.length());
                //System.out.printf("\n%d: %s\n",out.length(),out.substring(0,50));
                //out = doForEachLineOfInputNotOptimized(outOld);
                out = doForEachLineOfInput(outOld);
                //System.out.println(out.toString().compareTo(outOld.toString()));
                counter++;
                dotCounter.check();
            }
            while ((outOld.length() != out.length()));
            System.out.print("\n[t:" + (System.nanoTime() - startGlobal) / 1000_000 + " ms]\n");
            results.add(outOld.length());
            //HelperMethods.printResult(out.toString());
        }

        int max = 0;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) > max) max = results.get(i);
        }

        int min = results.get(0);
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) < min) min = results.get(i);
        }
        HelperMethods.printResult(min);
        HelperMethods.printResult("Literka: " + (char) ('a' + results.indexOf(min)));

    }


}
