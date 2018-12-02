package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.HashMap;

public class Day02Star1 {
    final String INPUT_FILE = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\src\\main\\resources\\day02input1.txt";

    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<String> boxID = new ArrayList<>();
    String s;

    int result = 0;
    int hits2 = 0;
    int hits3 = 0;
    MyReader myReader = new MyReader();

    public static void main(String[] args) {
        Day02Star1 day02Star1 = new Day02Star1();
        //day02Star1.star1start();
        day02Star1.star2start();

    }


    void star1start() {
        HashMap<Character, Integer> hm1 = new HashMap();
        String s;

        int result = 0;
        int hits2 = 0;
        int hits3 = 0;
        MyReader myReader;
        myReader = new MyReader();
        myReader.open_file(INPUT_FILE);

        while (myReader.get_line() != null) {
            while ((s = myReader.read_string(" ")) != null) {
                for (int i = 0; i < 26; i++) {
                    hm1.put((char) ('a' + i), 0);
                    System.out.print(hm1.get((char) ('a' + i)));
                }
                System.out.println("ReadLine: " + s);
                for (int i = 0; i < 26; i++) {
                    //System.out.print("Parsing char:'"+s.charAt(i)+"'");
                    if (hm1.get(((s.charAt(i)))) != 0) {
                        hm1.put(s.charAt(i), hm1.get(s.charAt(i)) + 1);
                        //   System.out.println(hm1.get(((s.charAt(i)))));
                    } else {

                        hm1.put(s.charAt(i), 1);
                        //  System.out.println(hm1.get(((s.charAt(i)))));
                    }
                }
                for (int i = 0; i < 26; i++) {
                    System.out.print(hm1.get((char) ('a' + i)));
                }
                //System.out.println(hm1.containsValue(2));
                if (hm1.containsValue(2)) hits2++;
                if (hm1.containsValue(3)) hits3++;
            }
        }
        System.out.println("Result hit2: " + (hits2));
        System.out.println("Result hits3: " + (hits3));
        System.out.println("Result: " + (hits2 * hits3));
    }

    void star2start() {

        myReader.open_file(INPUT_FILE);
        while (myReader.get_line() != null) {
            while ((s = myReader.read_string(" ")) != null) {
                boxID.add(s);
            }
        }

        for (int thisBoxNum = 0; thisBoxNum < boxID.size() - 1; thisBoxNum++) {
            System.out.println("");
            for (int boxNum = thisBoxNum; boxNum < boxID.size(); boxNum++) {
                System.out.print(".");
                int numOfLettersTheSame = 0;
                for (int i = 0; i < boxID.get(0).length(); i++) {
                    if (boxID.get(thisBoxNum).charAt(i) == boxID.get(boxNum).charAt(i)) {
                        numOfLettersTheSame++;
                    }
                }
                if (numOfLettersTheSame == 25) {
                    System.out.println("");
                    System.out.println("Success. I found two valid BoxIDs:");
                    System.out.println(boxID.get(thisBoxNum));
                    System.out.println(boxID.get(boxNum));
                    return;
                }
            }
        }

    }
}
