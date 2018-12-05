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
        dayStar1.star1start();
    }

    private StringBuffer doForEachLineOfInput(StringBuffer line) {
        StringBuffer output = new StringBuffer();
        String c1 = null;
        String c2 = null;
        int i = 0;
        for (i = 0; i < line.length() - 1; i++) {
            c1 = line.substring(i, i + 1);
            c2 = line.substring(i + 1, i + 2);
            if (c1.compareToIgnoreCase(c2) == 0 && c1.compareTo(c2) != 0) {
                //System.out.printf("%s__%s",output,line.substring(i+2));
                output.append(line.substring(i + 2));
                System.out.printf("\n**%s-%s**",
                        output.toString().substring(i - 20 > 0 ? i - 20 : 0, i - 1 > 0 ? i - 1 : 0),
                        output.toString().substring(i, i + 20 < line.length() ? i + 20 : i));
                return output;
            } else {
                // System.out.printf("!%s%s ",c1,c2);
                output.append(c1);
            }

        }

        output.append(c2);
        System.out.printf("NOTHING %s", output);
        //System.out.printf("returned len: %d \n",output.length());
        return output;

    }

    private void star1start() {

        StringBuffer out = new StringBuffer(myReader.get_line());
        //StringBuffer out=new StringBuffer("aAbBdCcCabcAcCaCBAcCcaDA");
        StringBuffer outOld = out;
        int counter = 1;
        System.out.printf("LEN: %d", out.toString().length());
        //System.out.flush();

        long start = System.nanoTime();
        do {
            outOld = out;
            //System.out.printf("\n%d: ",out.length());

            //System.out.printf("\n%d: %s\n",out.length(),out.substring(0,50));
            out = doForEachLineOfInput(outOld);
            //System.out.println(out.toString().compareTo(outOld.toString()));
/*
            if((counter%50)==0) {
                System.out.println();
                System.out.printf(" i:%d (Len:%d) ",counter,out.length());
            }*/
            counter++;
        }
        while ((outOld.length() != out.length()));
        System.out.print("\n[t:" + (System.nanoTime() - start) / 1000_000 + " ms]\n");
        HelperMethods.printResult(outOld.length());
        //HelperMethods.printResult(out.toString());

    }
}
