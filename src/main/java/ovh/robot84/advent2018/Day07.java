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
public class Day07 {
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day07input1.txt";
private final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day07input2.txt";
private static final int DEFAULT_BIG_VALUE = 99_999;
private static final int NOT_AN_ENTRY_POINT = 99999;
private ArrayList<Integer> alphabet = new ArrayList<>();
private ArrayList<Integer> result = new ArrayList<>();
private MyReader myReader = new MyReader();
private int inSize;
private int alphabetSize;
private int[][] in = new int[101][2];


/* zaczynamy od C ii bierzemy wszystkie jego raczki i wybieramy z nich wg sort()
    szukamy litery przed ktora zadna inna litera nie musi byc powstawiona,
     bo taka mozemy postawic kiedy chemy (nie zalezy od innych)
    (czyli taka co nie ma lapek do niej! czyli entry point!)*/
private Day07(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day07 dayStar1 = new Day07(INPUT_FILE2);
    HelperMethods.parsingProgramArguments(args);
    Verbose.mute();
    dayStar1.star1start();

}


private void star1start() {
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

    Verbose.println("Building alphabet...");
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
    Verbose.unmute();
    Verbose.disablePrompts();
    DependencyTree dependencyTree = new DependencyTree(alphabet);
    dependencyTree.printDependencyTree();


    int someBigValue = NOT_AN_ENTRY_POINT, bestArm = someBigValue;
    int entryPoint;

    // System.exit(1);
    while ((entryPoint = findEntryPoint()) != (NOT_AN_ENTRY_POINT)) {
        result.add(entryPoint);
        Verbose.printf("Entering entry point. deleting all references to it\n");
        //noinspection SuspiciousMethodCalls
        alphabet.remove((Object) entryPoint);
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
            if ((in[i][0] != entryPoint) && (in[i][0] != 0)) {
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

        Verbose.println("Find all arms and select first in alphabetic order");
        dependencyTree.printDependencyTree(entryPoint);
        dependencyTree = new DependencyTree(alphabet);
    }
    for (Integer letter : result) System.out.printf("%c", letter);
    System.out.println();
    dependencyTree = new DependencyTree(alphabet);
    dependencyTree.printDependencyTree();

}


private int findEntryPoint() {
    ArrayList<Integer> entryPointsList = new ArrayList<>();
          /* entry point is the letter that has no dependencies.
        so it is _always_  the FIRST letter in pair (A,B)
        where the pair is compiled from sentence:
        "Step A must be finished before step B can begin."
        B has dependencies in form of A
        A is the dependence of B

        When there are many entry points possible - we want to choose most first
        in alphabetical order
         */
    Verbose.println("Looking for entry point letter");
    int entryPoint = NOT_AN_ENTRY_POINT;
    HERE:
    for (int letter = 0; letter < alphabetSize; letter++) {
        for (int i = 0; i < inSize; i++) {
            if (alphabet.get(letter) == in[i][1]) {
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.printf("%c is not an entry point\n", alphabet.get(letter));
                continue HERE;
            }
        }
        // new way to do the same:
        entryPointsList.add(alphabet.get(letter));
        // old way to this:
        Verbose.setVerboseLevelForNextPrint(2);
        Verbose.printf("%c IS an potential  entry point\n", (alphabet.get(letter)));
        if (alphabet.get(letter) < entryPoint) entryPoint = alphabet.get(letter);

    }
// new way to do the same:
    Collections.sort(entryPointsList);

    Verbose.disablePrompts();
    Verbose.printf("\n-->");
    for (Integer ep : entryPointsList) Verbose.printf("%c", ep);
    Verbose.printf("\n");

    // if(entryPointsList.isEmpty()) return NOT_AN_ENTRY_POINT; else return entryPointsList.get(0);
    if (entryPoint != NOT_AN_ENTRY_POINT) {
        Verbose.printf("%c IS THE entry point. ", ((entryPoint)));
        return entryPoint;
    } else
        return NOT_AN_ENTRY_POINT;
}


class DependencyTree {

    ArrayList<Integer> alphabet = new ArrayList<Integer>() {
        @Override
        public Object clone() {
            return super.clone();
        }
    };
    ArrayList<ArrayList<Integer>> dependencyTree;


    public DependencyTree(ArrayList<Integer> alphabet) {
        this.dependencyTree = new ArrayList<>();
        this.alphabet = (ArrayList<Integer>) alphabet.clone();
        for (Integer letter : this.alphabet)
            this.dependencyTree.add(buildDependencyList(letter));
    }


    void printDependencyList(ArrayList<Integer> dependencyList) {
        Verbose.printf("-->");
        for (Integer letter : dependencyList) {
            Verbose.printf("%c", letter);
        }
        Verbose.printf("\n");
    }


    void printDependencyList(ArrayList<Integer> dependencyList, int highlightMe) {
        Verbose.printf("-->");
        for (Integer letter : dependencyList) {
            if (letter == highlightMe) {
                Verbose.setColor(Verbose.ANSI_RED);
                Verbose.printf("%c", letter);
                Verbose.setColor(Verbose.ANSI_CYAN);
            } else {
                Verbose.printf("%c", letter);
            }
        }
        Verbose.printf("\n");
    }


    void printDependencyTree() {
        ListIterator<Integer> iter = alphabet.listIterator();
        for (ArrayList<Integer> letterList : this.dependencyTree) {
            if (iter.hasNext()) Verbose.printf("%c", iter.next());
            printDependencyList(letterList);
        }
    }


    void printDependencyTree(int highlightMe) {
        ListIterator<Integer> iter = alphabet.listIterator();
        for (ArrayList<Integer> letterList : this.dependencyTree) {
            if (iter.hasNext()) Verbose.printf("%c", iter.next());
            printDependencyList(letterList, highlightMe);
        }
    }


    ArrayList<Integer> buildDependencyList(Integer letter) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < inSize; i++) {
            if (in[i][1] == letter) list.add(in[i][0]);
        }
        return list;
    }


    ArrayList<Integer> buildDeepDependencyList(Integer letter, ArrayList<ArrayList<Integer>> tree) {
        ArrayList<Integer> deepList = new ArrayList<>();
        if (tree.get(letter) == null) deepList.add(letter);
        else
            for (Integer dependency : tree.get(letter)) {
                deepList.addAll(buildDeepDependencyList(dependency, tree));
            }
        return deepList;
    }
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
