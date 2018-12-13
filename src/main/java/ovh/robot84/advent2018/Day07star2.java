package ovh.robot84.advent2018;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-01
 */
public class Day07star2 {
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day07input1.txt";
private final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day07input2.txt";
private static final int DEFAULT_BIG_VALUE = 99_999;
private static final int NOT_AN_ENTRY_POINT = 99999;
private static final int REAL_WORK_TIME = 60 + 1;
ListIterator<Integer> entryPointListIterator;
ArrayList<Integer> entryPoints = new ArrayList<>();
int someBigValue = NOT_AN_ENTRY_POINT, bestArm = someBigValue;
//private static final int REAL_WORK_TIME = 0;
private ArrayList<Integer> alphabet = new ArrayList<>();
private ArrayList<Integer> result = new ArrayList<>();
private MyReader myReader = new MyReader();
private int inSize;
private int alphabetSize;
private Integer[][] in = new Integer[101][2];
private int totalTime = 0;
private int workers = 5;
private boolean workerIdle[] = new boolean[workers];
private Integer[] workerWorkingOn = new Integer[workers];
private Integer[] workerWillBeIdleAfterSeconds = new Integer[workers];
private boolean workDoneInThisSecond;
private boolean WORK_ON_LETTER_A_STARTED;
private int workerWorkingOnLetterA;


private Day07star2(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day07star2 dayStar1 = new Day07star2(INPUT_FILE2);
    dayStar1.parsingProgramArguments(args);
    Verbose.mute();
    dayStar1.run();
}


private void mainLoop() {
    for (int time = 0; time < 10000; time++) {
        Verbose.printf("\n[%3d]  ", time);
        workDoneInThisSecond = false;
        forEachWorker();

        Verbose.printf("\t");
        for (Integer letter : result) Verbose.printf("%c", letter);

        if (workDoneInThisSecond) {
            ArrayList<Integer> copy = new ArrayList<>();
            while (entryPointListIterator.hasPrevious()) entryPointListIterator.previous();
            while (entryPointListIterator.hasNext()) copy.add(entryPointListIterator.next());

            entryPoints = findEntryPoints();
            if (entryPoints != null) {
                for (int i = 0; i < workers; i++) {
                    if (!workerIdle[i]) {
                        entryPoints.remove(workerWorkingOn[i]);
                    }
                }
                System.out.print("->");
                for (Integer ii : entryPoints) System.out.printf("%c", ii);
                System.out.println();
                entryPointListIterator = entryPoints.listIterator();
            } else
                return;
        }
    }


}


private void forEachWorker() {
    for (int workerNum = 0; workerNum < workers; workerNum++) {
        if (workerIdle[workerNum]) {
            Verbose.setVerboseLevelForNextPrint(3);
            Verbose.printf("Worker %d is idle.\n", workerNum);

            LOOP_01_HEAD:
            while (entryPointListIterator.hasNext()) {
                int ep;
                ep = entryPointListIterator.next();
                entryPointListIterator.remove();
                Verbose.setVerboseLevelForNextPrint(2);
                Verbose.printf("Oh, yes! We have letter %c for worker %d\n", ep, workerNum);
                boolean isSomebodyWorkingOnit = false;
                for (int j = 0; j < workers; j++) {
                    if (workerWorkingOn[j] == ep) {
                        isSomebodyWorkingOnit = true;
                        Verbose.setVerboseLevelForNextPrint(2);
                        Verbose.printf("Ktos pracuje na literze %c. Nie daje jej nikomu.\n", ep);
                        continue LOOP_01_HEAD;
                    }
                }
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("It looks like nobody is working on letter %c\n", ep);
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("Worker %d, come here. I have work for you.\n", workerNum);
                workerIdle[workerNum] = false;
                workerWorkingOn[workerNum] = ep;
                workerWillBeIdleAfterSeconds[workerNum] = ep - 'A' + REAL_WORK_TIME;
                Verbose.setVerboseLevelForNextPrint(2);
                Verbose.printf("worker %d will work for %d seconds on letter %c (%d)\n", workerNum, workerWillBeIdleAfterSeconds[workerNum] + 1, ep, ep);
                break LOOP_01_HEAD;
            }

        } else {
            workerWillBeIdleAfterSeconds[workerNum]--;
            if (workerWillBeIdleAfterSeconds[workerNum] <= 0) {
                result.add(workerWorkingOn[workerNum]);
                removeLetterAndAllReferencesToItFromVariables(workerWorkingOn[workerNum]);
                Verbose.setVerboseLevelForNextPrint(2);
                Verbose.printf("Worker %d finish his work on letter %c\n", workerNum, workerWorkingOn[workerNum]);
                workerIdle[workerNum] = true;
                workerWorkingOn[workerNum] = 46;

            } else {
                Verbose.setVerboseLevelForNextPrint(2);
                Verbose.printf("Worker %d will working hard on letter %c for next %d seconds\n", workerNum, workerWorkingOn[workerNum], workerWillBeIdleAfterSeconds[workerNum]);
            }
        }
        Verbose.printf("%c\t", workerWorkingOn[workerNum]);
    }
}


private void removeLetterAndAllReferencesToItFromVariables(int letter) {
    Verbose.setVerboseLevelForNextPrint(2);
    Verbose.printf("Work on letter %c done. deleting all references to this letter\n", letter);
    //noinspection SuspiciousMethodCalls
    alphabet.remove((Object) letter);
    alphabetSize--;
    ArrayList<Pair<Integer, Integer>> in2 = new ArrayList<>();
    int in3[][] = new int[inSize][2];

            /*
             (in[i][0]!=0) don;t make an action when array cell has nothing inside
             (its value==default value of empty cell)
             */
    //noinspection ForLoopReplaceableByForEach
    int ii = 0;
    for (int i = 0; i < inSize; i++)
        if ((in[i][0] != letter) && (in[i][0] != 0)) {
            in3[ii][0] = in[i][0];
            in3[ii][1] = in[i][1];
            ii++;
        }
    inSize = ii;
    for (int i = 0; i < inSize; i++) {
        in[i][0] = in3[i][0];
        in[i][1] = in3[i][1];
    }

    Verbose.setVerboseLevelForNextPrint(3);
    Verbose.println("A->B means: A is dependency of B");
    for (int i = 0; i < inSize; i++) {
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.printf("%c->%c\n", in[i][0], in[i][1]);
    }

    workDoneInThisSecond = true;
}


private ArrayList<Integer> findEntryPoints() {
          /* entry point is the letter that has no dependencies.
        so it is _always_  the FIRST letter in pair (A,B)
        where the pair is compiled from sentence:
        "Step A must be finished before step B can begin."
        B has dependencies in form of A
        A is the dependence of B

        When there are many entry points possible - we want to choose most first
        in alphabetical order
         */
    Verbose.setVerboseLevelForNextPrint(2);
    Verbose.println("Looking for entry point letter");
    int entryPoint = NOT_AN_ENTRY_POINT;
    ArrayList<Integer> entryPoints = new ArrayList<Integer>();
    HERE:
    for (int letter = 0; letter < alphabetSize; letter++) {
        for (int i = 0; i < inSize; i++) {
            if (alphabet.get(letter) == in[i][1]) {
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("%c is not an entry point\n", alphabet.get(letter));
                continue HERE;
            }
        }
        entryPoints.add((alphabet.get(letter)));
        Verbose.setVerboseLevelForNextPrint(2);
        Verbose.printf("%c IS the entry point for workers\n", (alphabet.get(letter)));
    }
    Collections.sort(entryPoints);
    Verbose.setVerboseLevelForNextPrint(2);
    Verbose.println("Sorted entryPoints:");
    System.out.println();
    for (Integer letter : entryPoints) System.out.printf("%c", letter);
    System.out.println();

    if (entryPoints.isEmpty()) return null;
    else
        return entryPoints;
}


private void printResult() {
    System.out.println("Rezult is:");
    for (Integer letter : result) System.out.printf("%c", letter);
    System.out.println();
}


private void initializationOfStructures() {
    for (int i = 0; i < workerIdle.length; i++) {
        workerIdle[i] = true;
    }
    for (int i = 0; i < workerIdle.length; i++) {
        workerWorkingOn[i] = 0;
    }
    Verbose.unmute();
    Verbose.disablePrompts();

    entryPoints = findEntryPoints();
    entryPointListIterator = entryPoints.listIterator();
    Verbose.printf("\n[%3d]  ", 0);
}


private void buildSetOfInputSymbols() {
    Verbose.println("Building set of input symbols (an alphabet)...");
    for (int i = 0; i < inSize; i++) {
        for (int j = 0; j < 2; j++) {
            Verbose.setVerboseLevelForNextPrint(3);
            Verbose.printf("Parsing %d \n", in[i][j]);
            if (!alphabet.contains(in[i][j])) {
                alphabet.add(in[i][j]);
                Verbose.printf("Adding %d to alphabet\n", in[i][j]);
            }
        }
    }

    Verbose.println("Searching for alphabet size...");

    alphabetSize = alphabet.size();

    Verbose.printf("Alphabet size is %d \n", alphabet.size());

    Verbose.setVerboseLevelForNextPrint(1);
    Verbose.printf("Displaying alphabet\n");
    Verbose.setVerboseLevelForNextPrint(1);
    for (Integer b : alphabet) {
        Verbose.setVerboseLevelForNextPrint(1);
        Verbose.printf("%c", b);
    }

    Verbose.setVerboseLevelForNextPrint(1);
    Verbose.printf("\n");

}


private void readInput() {
    String line;
    int c3, c4;
    int a = 0;
    Verbose.println("Mapping Input file to memory data structure (array)...");
    while ((line = myReader.get_line()) != null) {
        Verbose.setVerboseLevelForNextPrint(2);
        Verbose.print("Line: " + line);
        Pattern p = Pattern.compile("Step (\\w) must be finished before step (\\w) can begin.");
        Matcher m = p.matcher(line);
        if (m.matches()) {
            c3 = (m.group(1).charAt(0));
            c4 = (m.group(2).charAt(0));
            Verbose.setVerboseLevelForNextPrint(3);
            Verbose.printf("c3 %s c4 %s\n", c3, c4);
            in[a][0] = c3;
            in[a][1] = c4;
            Verbose.setVerboseLevelForNextPrint(3);
            Verbose.printf("c3 %s c4 %s\n", in[a][0], in[a][1]);
        }
        a++;
    }

    Verbose.println("Counting num of lines in input file...");
    inSize = a;
    Verbose.println("Size of input array " + inSize + ". Number of input lines " + a + ".");
}


private void parsingProgramArguments(String[] args) {
    System.out.println("Program ARGS num:" + args.length);
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
    if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
    if (args.length > 0 && args[0].equals("--create-input-file"))
        HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), INPUT_FILE1);
}


private void run() {
    readInput();
    buildSetOfInputSymbols();
    initializationOfStructures();
    mainLoop();
    printResult();
}
}
/*

    ArrayList<Integer> someData = new ArrayList<Integer>();
    */
/* max value on list *//*

    int max = 0;
        for (int i = 0; i < someData.size(); i++) {
        if (someData.get(i) > max) max = someData.get(i);
        }

        */
/* min value on list *//*

        int min = someData.get(0);
        for (int i = 0; i < someData.size(); i++) {
        if (someData.get(i) < min) min = someData.get(i);
        }

        */
/* sum of values on list *//*

        int sum = 0;
        for (int i = 1; i <= someData.size(); i++) {
        sum += someData.get(i);
        }

        */
/* for all letters of alphabet
        build regex from chars and do something *//*

        StringBuffer out1=null;
        for (byte i = 0; i < 26; i++) {
        byte c1 = 'a', c2 = 'A';
        String regex = "" + "[" + (char) (c1 + i);
        regex += (char) (c2 + i) + "]";
        Verbose.println("RegEx: "+regex);
        StringBuffer out = new StringBuffer(out1.toString().replaceAll(regex, ""));
        StringBuffer outOld = out;
        }*/