package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Scanner;


public class Day03 {
final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day03input1.txt";
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day03input2.txt";

MyReader myReader = new MyReader();
ArrayList<Elf> elfs = new ArrayList<>();


Day03(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day03 dayStar1 = new Day03(INPUT_FILE1);
    dayStar1.star1start();
}


void printResult(int result) {
    System.out.println("Result: " + result);
}


void doForEachLineOfInput(String line) {
        /*
        #1 @ 817,273: 26x26
         */
    String notUsed = line;
    String s1 = myReader.read_string(" ").substring(1);
    String s2 = myReader.read_string(" ");
    String s3 = myReader.read_string(" ").replaceFirst(",", " ").replaceFirst(":", "");
    String s4 = myReader.read_string(" ").replaceFirst("x", " ");

    Scanner ss3 = new Scanner(s3);
    Scanner ss4 = new Scanner(s4);
    int lineNum = Integer.valueOf(s1);
    int fromLeft = ss3.nextInt();
    int fromTop = ss3.nextInt();
    int width = ss4.nextInt();
    int height = ss4.nextInt();
    elfs.add(new Elf(lineNum, fromLeft, fromTop, width, height));


}


void star1start() {
    String line;
    Fabric fabric = new Fabric();
    while ((line = myReader.get_line()) != null) {
        elfs.add(new Elf(HelperMethods.findAllNumbersInLine(line)));
    }

    System.out.printf("number of Elfs read: " + elfs.size());

    for (Elf elf : elfs) {
        fabric.addToFabric(elf);
    }

    System.out.println("\nstar 1 result");
    printResult(fabric.numOfFabricFieldWithTwoOrMoreElfs());

    System.out.println("star 2 result");
    for (Elf elf : elfs)
        if (!fabric.isElfOverlappingWithAnotherInFabric(elf))
            System.out.println("Elf " + elf.elfID + " not overlaps on fabric");
}


}
