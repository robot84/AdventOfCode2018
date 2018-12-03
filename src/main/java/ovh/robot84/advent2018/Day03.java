package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day03input1.txt";
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day03input2.txt";

    MyReader myReader = new MyReader();
    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<Elf> elfs = new ArrayList<>();
    String s;
    int[][] fabric = new int[10000][10000];

    Day03(String input_file) {
        myReader.open_file(input_file);
    }

    public static void main(String[] args) {
        Day03 dayStar1 = new Day03(INPUT_FILE1);
        dayStar1.star1start();
        //Day03 dayStar2=new Day03(INPUT_FILE2);
        //dayStar2.star2start();
    }

    void printResult(String result) {
        System.out.println("Result: " + result);
    }

    void printResult(int result) {
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


    void doForEachLineOfInput(String line) {
        /*
        #1 @ 817,273: 26x26
         */

        String s1 = myReader.read_string(" ").substring(1);
        String s2 = myReader.read_string(" ");
        String s3 = myReader.read_string(" ").replaceFirst(",", " ").replaceFirst(":", "");
        String s4 = myReader.read_string(" ").replaceFirst("x", " ");
//
//            int numOfSlice = Integer.valueOf(s1.substring(1));
//
        Scanner ss3 = new Scanner(s3);
        Scanner ss4 = new Scanner(s4);
        int lineNum = Integer.valueOf(s1);
        int fromLeft = ss3.nextInt();
        int fromTop = ss3.nextInt();
        int width = ss4.nextInt();
        int height = ss4.nextInt();
        elfs.add(new Elf(lineNum, fromLeft, fromTop, width, height));
        // addToFabric(fromLeft,fromTop,width,height);

        //   System.out.println("Input string: " + line);
        //  System.out.printf("!%s!\n",s3);
        // System.out.printf("!%s!\n",s4);


        //Pattern p = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
//        Pattern p = Pattern.compile("( )");
//        Matcher m = p.matcher(line);
//        for (int i = 1; i <= 2; i++) {
//            System.out.println(i+" "+m.group(1));
//        }

        //    System.out.printf("!%d!%d!%d!%d\n\n",fromLeft,fromTop,width,height);
        //    System.exit(0);
//            initializeCharKeyHashMap(hm1, 0);
//            forEachCharOfString(s);
//            printCharKeyHashMap(hm1);

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
        String line;
        while ((line = myReader.get_line()) != null) {
            doForEachLineOfInput(line);
        }

        System.out.printf("number of Elfs read: " + elfs.size());

        for (Elf elf : elfs) {
            addToFabric(elf);
        }

        System.out.println("star 1 result");
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
        System.out.println("FALSE for elf" + elf.elfID);
        return false;
    }


    int numOfFabricFieldWithTwoOrMoreElfs() {
        int count = 0;
        for (int y = 0; y < 10000; y++) {
            for (int x = 0; x < 10000; x++) {
                if (fabric[y][x] > 1) count++;
            }
        }
        return count;
    }

    class Elf {
        int elfID;
        int fromLeft;
        int fromTop;
        int width;
        int height;

        Elf(int elfID, int fromLeft, int fromTop, int width, int height) {
            this.elfID = elfID;
            this.fromLeft = fromLeft;
            this.fromTop = fromTop;
            this.width = width;
            this.height = height;
        }
    }
}
