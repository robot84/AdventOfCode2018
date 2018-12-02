package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Day04 {
    final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day04input1.txt";
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day04input2.txt";

    MyReader myReader = new MyReader();
    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<String> boxID = new ArrayList<>();
    String s;

    Day04(String input_file) {
        myReader.open_file(input_file);
    }

    public static void main(String[] args) {
        Day04 dayStar1 = new Day04(INPUT_FILE1);
        dayStar1.star1start();
        //Day03 day03Star2=new Day03(INPUT_FILE2);
        //day03Star2.star2start();
    }

    void printResult(String result) {
        System.out.println("Result: " + result);
    }


    void initializeCollection(Collection collection, int collectionSize) {
        for (int i = 0; i < collectionSize; i++) {
            collection.add(0);
        }
    }


    void initializeCharKeyHashMap(HashMap hashMap, int initializer) {
        final int charactersInAlphabet = 26;
        final char firstLetterInAlphabet = 'a';

        for (int i = 0; i < charactersInAlphabet; i++) {
            hashMap.put((char) (firstLetterInAlphabet + i), initializer);
            System.out.print(hashMap.get((char) (firstLetterInAlphabet + i)));
        }
    }


    void printCharKeyHashMap(HashMap hashMap) {
        final int charactersInAlphabet = 26;
        final char firstLetterInAlphabet = 'a';
        for (int i = 0; i < charactersInAlphabet; i++) {
            System.out.print(hashMap.get((char) (firstLetterInAlphabet + i)));
        }
    }


    void doForEachLineOfInput() {
        while ((s = myReader.read_string(" ")) != null) {
            System.out.println("Input string: " + s);
            initializeCharKeyHashMap(hm1, 0);
            forEachCharOfString(s);
            printCharKeyHashMap(hm1);
        }
    }


    void forEachCharOfString(String string) {
        for (int i = 0; i < s.length(); i++) {
            //System.out.print("Parsing char:'"+s.charAt(i)+"'");
            if (hm1.get(((s.charAt(i)))) != 0) {
                hm1.put(s.charAt(i), hm1.get(s.charAt(i)) + 1);
                //   System.out.println(hm1.get(((s.charAt(i)))));
            } else {
                hm1.put(s.charAt(i), 1);
                //  System.out.println(hm1.get(((s.charAt(i)))));
            }
        }
    }


    void star1start() {
        while (myReader.get_line() != null) {
            doForEachLineOfInput();
        }
        printResult(Integer.toString(5));
    }


    void star2start() {

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
