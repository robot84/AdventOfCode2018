package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Scanner;

public class Day03 {
    final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day03input1.txt";
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day03input2.txt";
    final static int ARRAY_MAX_X = 10_000;
    final static int ARRAY_MAX_Y = 10_000;

    MyReader myReader = new MyReader();
    ArrayList<Elf> elfs = new ArrayList<>();

    int[][] fabric = new int[ARRAY_MAX_Y][ARRAY_MAX_X];

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

    void doForEachLineOfInputNewVersion(String line) {
        String[] foundByFindRegexMethod;
        foundByFindRegexMethod = FindRegex.findRegex("(\\d+)", line);
        Integer[] numbers = new Integer[foundByFindRegexMethod.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.valueOf(foundByFindRegexMethod[i]);
        }
        elfs.add(new Elf(numbers));
    }

    void star1start() {
        String line;
        while ((line = myReader.get_line()) != null) {
            //doForEachLineOfInputNewVersion(line);
            doForEachLineOfInput(line);
        }

        System.out.printf("number of Elfs read: " + elfs.size());

        for (Elf elf : elfs) {
            addToFabric(elf);
        }

        System.out.println("\nstar 1 result");
        printResult(numOfFabricFieldWithTwoOrMoreElfs());

        System.out.println("star 2 result");
        for (Elf elf : elfs)
            if (!isElfOverlappingWithAnotherInFabric(elf))
                System.out.println("Elf " + elf.elfID + " not overlaps on fabric");
    }


    void addToFabric(int fromLeft, int fromTop, int width, int height) {
        for (int y = fromTop; y < fromTop + height; y++) {
            for (int x = fromLeft; x < fromLeft + width; x++) {
                fabric[y][x]++;

            }
        }
    }

    void addToFabric(Elf elf) {
        addToFabric(elf.fromLeft, elf.fromTop, elf.width, elf.height);
    }

    boolean isElfOverlappingWithAnotherInFabric(Elf elf) {
        for (int y = elf.fromTop; y < (elf.fromTop + elf.height); y++) {
            for (int x = elf.fromLeft; x < (elf.fromLeft + elf.width); x++) {
                if (fabric[y][x] > 1) return true;
            }
        }
        return false;
    }


    private int numOfFabricFieldWithTwoOrMoreElfs() {
        int count = 0;
        for (int y = 0; y < ARRAY_MAX_Y; y++) {
            for (int x = 0; x < ARRAY_MAX_X; x++) {
                if (fabric[y][x] > 1) count++;
            }
        }
        return count;
    }


}
