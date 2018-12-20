package ovh.robot84.advent2018;

import com.google.common.collect.HashBiMap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day19 {
private static final Integer REGISTER_A = 1;
private static final Integer REGISTER_B = 2;
private static final Integer REGISTER_C = 3;
private static final Integer REGISTER_D = 4;
private static final Integer REGISTER_E = 5;
HashMap<Integer, Integer> rejestry = new HashMap<Integer, Integer>();
HashBiMap<String, Integer> opMap = HashBiMap.create();
private Integer numberToRemove;


public static void main(String[] args) {
    String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day19input1.txt";
    String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day19input2.txt";
    Day19 day = new Day19();
    //Verbose.mute();
    Verbose.disablePrompts();
    day.parsingProgramArguments(args, INPUT_FILE1);

    day.startStar001(INPUT_FILE1);
}


int threeArgOperation(ArrayList<Integer> instruction) {
    return threeArgOperation(instruction.get(0), instruction.get(1), instruction.get(2), instruction.get(3));
}


void threeArgOperation(String op, int arg1, int arg2, int arg3) {
    threeArgOperation(mnemonicToOpCode(op), arg1, arg2, arg3);
}


String opCodeToMnemonic(int opCode) {
    if (opCode >= 0 && opCode < 16) return opMap.inverse().get(opCode);
    else return null;
}


int mnemonicToOpCode(String mnemonic) {
    final int ERROR = -1;
    if (opMap.containsKey(mnemonic)) return opMap.get(mnemonic);
    else return ERROR;
}


void mnemonicToOpCodeTableInit(HashBiMap<String, Integer> opMap) {

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
}


int threeArgOperation(int op, int arg1, int arg2, int arg3) {

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


void startStar001(String input_file) {
    ArrayList<ArrayList<Integer>> instructions = new ArrayList<ArrayList<Integer>>();
    MyReader myReader = new MyReader();

    myReader.open_file(input_file);
    initRegistersWithZeros();
    mnemonicToOpCodeTableInit(opMap);

    loadInputDataFromFile(myReader, instructions);
    runEngine(instructions);
    System.out.println("Rezult is: " + rejestry.get(0));

}


private void runEngine(ArrayList<ArrayList<Integer>> instructions) {
    ArrayList<Integer> instruction;
    int ip = 0;

    for (int i = 0; i < instructions.size(); i++) {
        instruction = instructions.get(ip);
        Verbose.printf("ip=%d %s", ip, rejestry.toString());
        Verbose.printf(" %s %d %d %d", opCodeToMnemonic(instruction.get(0)),
                instruction.get(1), instruction.get(2), instruction.get(3));
        runInstruction(instruction);
        Verbose.printf(" %s\n", rejestry.toString());
    }
}


private void loadInputDataFromFile(MyReader myReader, ArrayList<ArrayList<Integer>> instructions) {
    String line1;
    while ((line1 = myReader.get_line()) != null) {
        ArrayList<Integer> instructionFromLine = new ArrayList<>();
        Verbose.setVerboseLevelForNextPrint(2);
        Verbose.println("Line1: " + line1);
        convertInputDataToMemoryDataStructures(line1, instructionFromLine);
        instructions.add(instructionFromLine);
    }
}


private void runInstruction(ArrayList<Integer> instructionFromLine) {
    threeArgOperation(instructionFromLine);
}


private void convertInputDataToMemoryDataStructures(String line, ArrayList<Integer> instructionFromLine) {
    Pattern p2 = Pattern.compile("(\\w+) (\\d+) (\\d+) (\\d+)");
    Matcher m2 = p2.matcher(line);
    if (m2.matches()) {
        instructionFromLine.add(mnemonicToOpCode(m2.group(1)));
        for (int i = 2; i <= m2.groupCount(); i++) {
            Verbose.setVerboseLevelForNextPrint(3);
            Verbose.printf("m2.group(%s): %s\n", i, m2.group(i));
            instructionFromLine.add(Integer.valueOf(m2.group(i)));
        }
    }
}


private void initRegistersWithZeros() {
    rejestry.put(0, 0);
    rejestry.put(1, 0);
    rejestry.put(2, 0);
    rejestry.put(3, 0);
    rejestry.put(4, 0);
    rejestry.put(5, 0);
}


private void parsingProgramArguments(String[] args, String input_file) {
    System.out.println("Program ARGS num:" + args.length + " ARGS:" + args);
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
    if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
    if (args.length > 0 && args[0].equals("--create-input-file"))
        HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), input_file);
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
                Verbose.setVerboseLevelForNextPrint(3);
                Verbose.println("maxSize iteration " + index);

                array.get(index);
                if (!array.get(index).isEmpty()) {
                    int alSize = array.get(index).size();
                    Verbose.setVerboseLevelForNextPrint(3);
                    Verbose.println("Max in row: " + alSize);
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
