package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Day02 {
final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\src\\main\\resources\\day02input1.txt";
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\src\\main\\resources\\day02input2.txt";

MyReader myReader = new MyReader();
HashMap<Character, Integer> hm1 = new HashMap();
ArrayList<String> boxID = new ArrayList<>();
String s;
String result;

int hits2 = 0;
int hits3 = 0;


Day02(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day02 day03Star1 = new Day02(INPUT_FILE1);
    //Day02NewCode day03Star2=new Day02NewCode(INPUT_FILE2);
    day03Star1.star1start();
    day03Star1.star2start();

}


void printResult(String result) {
    System.out.println("Result: " + result);
}


void initializeCollection(Collection collection, int collectionSize) {
    for (int i = 0; i < collectionSize; i++) {
        collection.add(0);
    }
}





void doForEachLineOfInput() {
    while ((s = myReader.read_string(" ")) != null) {
        HelperMethods.initializeCharKeyHashMap(hm1, 0);
        System.out.println("Input string: " + s);
        forEachCharOfString(s);
        HelperMethods.printCharKeyHashMap(hm1);
        if (hm1.containsValue(2)) hits2++;
        if (hm1.containsValue(3)) hits3++;
    }
}


void forEachCharOfString(String string) {
        /*
        count num of occurencies of each char
         */
    for (int i = 0; i < string.length(); i++) {
        //System.out.print("Parsing char:'"+s.charAt(i)+"'");
        if (hm1.get(((string.charAt(i)))) != 0) {
            hm1.put(string.charAt(i), hm1.get(string.charAt(i)) + 1);
            //   System.out.println(hm1.get(((s.charAt(i)))));
        } else {

            hm1.put(string.charAt(i), 1);
            //  System.out.println(hm1.get(((s.charAt(i)))));
        }
    }
}


void star1start() {
    while (myReader.get_line() != null) {
        doForEachLineOfInput();
    }
    printResult(Integer.toString(hits2 * hits3));
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
