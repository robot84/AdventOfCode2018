package ovh.robot84.advent2018;

import ovh.robot84.advent2018.helpers.HelperMethods;
import ovh.robot84.advent2018.helpers.MyReader;
import ovh.robot84.advent2018.helpers.Verbose;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day16 {
private static final Integer REGISTER_A = 1;
private static final Integer REGISTER_B = 2;
private static final Integer REGISTER_C = 3;
HashMap<Integer, Integer> rejestry = new HashMap<Integer, Integer>();
private Integer numberToRemove;


public static void main(String[] args) {
    String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day16input1.txt";
    String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day16input2.txt";
    Day16 day = new Day16();
    //Verbose.mute();
    Verbose.disablePrompts();
    HelperMethods.parsingProgramArguments(args, INPUT_FILE1, day);

    //day.startStar01(INPUT_FILE1);
    //day.test();
    day.startStar02(INPUT_FILE2);
}


private void test1a() {
    ArrayListWithHeader data = new ArrayListWithHeader();
    HashSet<Integer> set = new HashSet<Integer>();
    dataInit(data);
    for (ArrayList<Integer> me : data.getMap().values()) set.addAll(me);
    for (Integer intFromSet : set) {

        System.out.println("removing: " + intFromSet);
        dataInit(data);
        for (Map.Entry mee : data.getMap().entrySet())
            ((ArrayList<Integer>) mee.getValue()).remove((Object) intFromSet);

        test(data);

    }

}


private void dataInit(ArrayListWithHeader data) {
    data.clear();
    data.add(1, new ArrayList<Integer>(Arrays.asList(1)));
    data.add(2, new ArrayList<Integer>(Arrays.asList(4, 5, 9)));
    data.add(3, new ArrayList<Integer>(Arrays.asList(1, 7, 8, 9, 10, 13)));
    data.add(5, new ArrayList<Integer>(Arrays.asList(7, 8)));
    data.add(6, new ArrayList<Integer>(Arrays.asList(4, 5, 9, 10)));
    data.add(7, new ArrayList<Integer>(Arrays.asList(7, 8, 9, 13)));
    data.add(9, new ArrayList<Integer>(Arrays.asList(1, 3, 7, 8, 9, 10, 13)));
    data.add(10, new ArrayList<Integer>(Arrays.asList(1, 5, 8, 9)));
    data.add(11, new ArrayList<Integer>(Arrays.asList(4, 9)));
}


private void test(ArrayListWithHeader data) {


//    data.add(1, new ArrayList<Integer>(Arrays.asList(1, 13)));


    for (Map.Entry me : data.getMap().entrySet())
        System.out.println("Key: " + me.getKey() + " & Value: " + me.getValue());

    boolean removedInThisIteration = true;
    while (data.maxSizeOfEntry() > 1 & removedInThisIteration) {
        removedInThisIteration = false;
        for (int index = 0; index < 16; index++) {
            if (data.getMap().containsKey(index)) {
                //        System.out.printf("Index %d has size %d\n", index,(data.getMap().get(index).size()));
                if ((data.getMap().get(index).size()) == 1) {
                    removedInThisIteration = true;
                    numberToRemove = data.getMap().get(index).get(0);
                    Verbose.printf("original key %d -> %d\n", index, numberToRemove);
                    System.out.println("removing: " + numberToRemove);
                    for (Map.Entry me : data.getMap().entrySet())
                        ((ArrayList<Integer>) me.getValue()).remove((Object) numberToRemove);
                }
            }
        }
        System.out.println("---");
        for (Map.Entry me : data.getMap().entrySet()) {
            System.out.println("Key: " + me.getKey() + " & Value: " + me.getValue());
        }
    }
    System.out.println("---");
    System.out.println("---");
}


int threeArgOperation(ArrayList<Integer> instruction) {
    return threeArgOperation(instruction.get(0), instruction.get(1), instruction.get(2), instruction.get(3));
}

int threeArgOperation(int op, int arg1, int arg2, int arg3) {
    HashMap<String, Integer> opMap = new HashMap<String, Integer>();
    opMap.put("addr", 1);
    opMap.put("addi", 13);
    opMap.put("mulr", 15);
    opMap.put("muli", 14);
    opMap.put("banr", 0);
    opMap.put("bani", 9);
    opMap.put("borr", 8);
    opMap.put("bori", 5);
    opMap.put("setr", 3);
    opMap.put("seti", 7);
    opMap.put("gtir", 6);
    opMap.put("gtri", 12);
    opMap.put("gtrr", 4);
    opMap.put("eqir", 10);
    opMap.put("eqri", 2);
    opMap.put("eqrr", 11);
    int imm1 = arg1;
    int imm2 = arg2;
    int rr1 = rejestry.get(arg1);
    int rr2 = rejestry.get(arg2);

    switch (op) {
        case 1:
            doAssigment(arg3, rr1 + rr2);
            break;
        case 13:
            doAssigment(arg3, rr1 + imm2);
            break;
        case 15:
            doAssigment(arg3, rr1 * rr2);
            break;
        case 14:
            doAssigment(arg3, rr1 * imm2);
            break;
        case 0:
            doAssigment(arg3, rr1 & rr2);
            break;
        case 9:
            doAssigment(arg3, rr1 & imm2);
            break;
        case 8:
            doAssigment(arg3, rr1 | rr2);
            break;
        case 5:
            doAssigment(arg3, rr1 | imm2);
            break;
        case 3:
            doAssigment(arg3, rr1);
            break;
        case 7:
            doAssigment(arg3, imm1);
            break;
        case 6:
            doAssigment(arg3, imm1 > rr2 ? 1 : 0);
            break;
        case 12:
            doAssigment(arg3, rr1 > imm2 ? 1 : 0);
            break;
        case 4:
            doAssigment(arg3, rr1 > rr2 ? 1 : 0);
            break;
        case 10:
            doAssigment(arg3, imm1 == rr2 ? 1 : 0);
            break;
        case 2:
            doAssigment(arg3, rr1 == imm2 ? 1 : 0);
            break;
        case 11:
            doAssigment(arg3, rr1 == rr2 ? 1 : 0);
            break;
        default:
            break;
    }
    return 1;
}


void doAssigment(int register, int value) {
    rejestry.put(register, value);
}


void startStar02(String input_file) {
    ArrayList<ArrayList<Integer>> instructions = new ArrayList<ArrayList<Integer>>();
    MyReader myReader = new MyReader();
    myReader.open_file(input_file);
    rejestry.put(0, 0);
    rejestry.put(1, 0);
    rejestry.put(2, 0);
    rejestry.put(3, 0);
/* input file format:
    0 1 0 1
*/
    {
        String line1;
        int winningSamples = 0;
        Pattern p2 = Pattern.compile("(\\d+) (\\d+) (\\d+) (\\d+)");
        int iterations = 0;

        while ((line1 = myReader.get_line()) != null) {
            int successfullOpCodesNum = 0;
            ArrayList<Integer> instructionFromLine = new ArrayList<>();
            Verbose.println(3, "Line1: " + line1);

            Matcher m2 = p2.matcher(line1);
            if (m2.matches()) {
                for (int i = 1; i <= m2.groupCount(); i++) {
                    Verbose.printf(3, "m2.group(%s): %s\n", i, m2.group(i));
                    instructionFromLine.add(Integer.valueOf(m2.group(i)));
                }
            }
            threeArgOperation(instructionFromLine);
        }
        System.out.println("Rezult is: " + rejestry.get(0));
    }
}


void startStar01(String input_file) {
    ArrayList<ArrayList<Integer>> instructions = new ArrayList<ArrayList<Integer>>();
    MyReader myReader = new MyReader();
    myReader.open_file(input_file);
    rejestry.put(0, 0);
    rejestry.put(1, 0);
    rejestry.put(2, 0);
    rejestry.put(3, 0);
/*

    Before: [1, 1, 0, 1]
    0 1*0 1
    After:  [1, 0, 0, 1]
*/
    {
        String line1;
        int winningSamples = 0;
        Pattern p1 = Pattern.compile("Before: \\[\\s*(\\d+),\\s+(\\d+),\\s+(\\d+),\\s+(\\d+)\\]");
        Pattern p2 = Pattern.compile("(\\d+) (\\d+) (\\d+) (\\d+)");
        Pattern p3 = Pattern.compile("After:\\s*\\[\\s*(\\d+),\\s+(\\d+),\\s+(\\d+),\\s+(\\d+)\\]");
        //Pattern p = Pattern.compile("Before: \\[(.*)");
        int iterations = 0;

        while ((line1 = myReader.get_line()) != null) {
            int successfullOpCodesNum = 0;
            ArrayList<Integer> before = new ArrayList<>();
            ArrayList<Integer> instructionFromLine = new ArrayList<>();
            ArrayList<Integer> after = new ArrayList<>();
            Verbose.println(3, "Line1: " + line1);
            String line2 = myReader.get_line();
            Verbose.println(3, "Line2: " + line2);
            String line3 = myReader.get_line();
            Verbose.println(3, "Line3: " + line3);
            String line4 = myReader.get_line();
            Verbose.println(3, "Line4: " + line4);

            Matcher m1 = p1.matcher(line1);
            if (m1.matches()) {
                for (int i = 1; i <= m1.groupCount(); i++) {
                    Verbose.printf(3, "m1.group(%s): %s\n", i, m1.group(i));
                    before.add(Integer.valueOf(m1.group(i)));
                }
            }
            Matcher m2 = p2.matcher(line2);
            if (m2.matches()) {
                for (int i = 1; i <= m2.groupCount(); i++) {
                    Verbose.printf(3, "m2.group(%s): %s\n", i, m2.group(i));
                    instructionFromLine.add(Integer.valueOf(m2.group(i)));
                }
            }
            Matcher m3 = p3.matcher(line3);
            if (m3.matches()) {
                for (int i = 1; i <= m3.groupCount(); i++) {
                    Verbose.printf(3, "m3.group(%s): %s\n", i, m3.group(i));
                    after.add(Integer.valueOf(m3.group(i)));
                }
            }

            ArrayList<Integer> copyOfBefore = (ArrayList<Integer>) before.clone();
            ArrayList<Integer> copyOfInstruction = (ArrayList<Integer>) instructionFromLine.clone();
            ArrayList<Integer> copyOfAfter = (ArrayList<Integer>) after.clone();
            int oryginalOpCode = instructionFromLine.get(0);
            int successfullOpCode = 0;
            ArrayList<Integer> successfullOpCodes = new ArrayList<>();

            for (int opCode = 0; opCode < 16; opCode++) {

                for (Integer registerNum = 0; registerNum < 4; registerNum++) {
                    rejestry.put(registerNum, before.get(registerNum));
                }
                instructionFromLine.set(0, opCode);
                threeArgOperation(instructionFromLine);
                boolean success = false;
                for (int registerNum = 0; registerNum < 4; registerNum++) {
                    success = false;
                    if (rejestry.get(registerNum) != after.get(registerNum)) break;
                    success = true;
                }
                if (success) {
                    successfullOpCodesNum++;
                    successfullOpCodes.add(opCode);
                    // Verbose.println(instructionFromLine.toString());
                }
            }

            if (successfullOpCodesNum >= 3) {
                winningSamples++;

            }
            if (successfullOpCodesNum > 0) {
                instructionFromLine.set(0, oryginalOpCode);
                //Verbose.println(3,instructionFromLine.toString());
                //System.out.printf("Opcode mapping: (original) -> (our)  %d -> ",oryginalOpCode);
                if (oryginalOpCode != 4 && oryginalOpCode != 8 && oryginalOpCode != 12 &&
                        oryginalOpCode != 13 && oryginalOpCode != 14 && oryginalOpCode != 15) {
                    System.out.printf("%d -> ", oryginalOpCode);
                    for (Integer opCode : successfullOpCodes)
                        if (opCode != 4 && opCode != 8 && opCode != 12 &&
                                opCode != 13 && opCode != 14 && opCode != 15
                                && opCode != 5 && opCode != 7 && opCode != 1 &&
                                opCode != 11 && opCode != 2 && opCode != 10
                                && opCode != 6 && opCode != 3 && opCode != 0) Verbose.printf("%d ", opCode);
                    Verbose.printf("\n");
                }
                Verbose.printf(3, "star02winning iteration: %d", iterations);


            }
            //instructions.add(instructionFromLine);
            iterations++;
            Verbose.println(3, "-------");
        }
        Verbose.println("number of iterations: " + iterations);
        System.out.println("Rezult is: " + winningSamples);
    }
}


class ArrayListWithHeader {
    HashMap<Integer, ArrayList<Integer>> array = new HashMap<>();


    void clear() {
        array.clear();
    }


    void add(int header, ArrayList<Integer> body) {
        array.put(header, body);
    }


    ArrayList<Integer> get(int index) {
        return array.get(index);
    }


    HashMap<Integer, ArrayList<Integer>> getMap() {
        return array;
    }


    int maxSizeOfEntry() {
        ArrayList<Integer> maxAl = new ArrayList<>();
        for (int index = 0; index < 16; index++) {
            if (array.containsKey(index)) {
                Verbose.println(3, "maxSize iteration " + index);

                array.get(index);
                if (!array.get(index).isEmpty()) {
                    int alSize = array.get(index).size();
                    Verbose.println(3, "Max in row: " + alSize);
                    maxAl.add(alSize);
                }

            }
        }
        int max = -1;
        if (!maxAl.isEmpty()) max = Collections.max(maxAl);
        else {
            System.out.println("Contratulation. Your basket is empty.");
        }
        Verbose.printf("Max Size of array's entry is %d\n", max);
        return max;
    }
}
} // end of class
