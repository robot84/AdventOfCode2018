package ovh.robot84.advent2018;

import com.google.common.collect.HashBiMap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day19 {
private static final Integer REGISTER_WHICH_STORE_IP = 4;
private static final int DIVIDER = 9_000_000;
// TODO refactor in OOP style: Register rejestry, InstructionHelper.ConvertOpToMnemonic()
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
    day.parsingProgramArguments(args, INPUT_FILE2);

    day.startStar001(INPUT_FILE2);
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
    int rr1 = 0;
    if (arg1 >= 0 && arg1 < rejestry.size()) rr1 = rejestry.get(arg1);
    int rr2 = 0;
    if (arg2 >= 0 && arg2 < rejestry.size()) rr2 = rejestry.get(arg2);


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
    // for star1 use this:
    //initRegistersWithZeros();
    // for star2 use this:
    initRegistersWithZerosForStart2();
    mnemonicToOpCodeTableInit(opMap);

    loadInputDataFromFile(myReader, instructions);
    computeResult();
    runEngine(instructions);
    System.out.println("Rezult is: " + rejestry.get(0));

}


private void computeResult() {
    int r0 = 0;
    for (int r1 = 1; r1 <= 10551345; r1++) {
        if (10551345 % r1 == 0) {
            r0 = r0 + r1;
            Verbose.printf("1051345%%%d==0, adding %d to result. result is: %d\n", r1, r1, r0);
        }
    }
    System.out.println("Result is: " + r0);
    System.exit(0);
}
private void runEngine(ArrayList<ArrayList<Integer>> instructions) {
    ArrayList<Integer> instruction;
    int ip = 0;
    int ipForStar2 = 0;
    ip = ipForStar2;
    int count = 0;
    while (ip < instructions.size()) {
        instruction = instructions.get(ip);
        doAssigment(REGISTER_WHICH_STORE_IP, ip);
        if (count % DIVIDER == 0) {
            Verbose.printf("ip=%d %s", ip, rejestry.toString());
            Verbose.printf(" %s %d %d %d", opCodeToMnemonic(instruction.get(0)),
                    instruction.get(1), instruction.get(2), instruction.get(3));
        }
        runInstruction(instruction);
        ip = rejestry.get(REGISTER_WHICH_STORE_IP);
        ip++;
        if (count % DIVIDER == 0)
            Verbose.printf(" %s\n", rejestry.toString());
        count++;
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


private void initRegistersWithZerosForStart2() {
    rejestry.put(0, 1);
    rejestry.put(1, 0);
    rejestry.put(2, 0);
    rejestry.put(3, 0);
    rejestry.put(4, 0);
    rejestry.put(5, 0);
}


private void initRegistersWithZerosForStart2a() {
    rejestry.put(0, 1);
    rejestry.put(1, 0);
    rejestry.put(2, 10551340);
    rejestry.put(3, 10551340);
    rejestry.put(4, 3);
    rejestry.put(5, 10551345);
}


private void parsingProgramArguments(String[] args, String input_file) {
    System.out.println("Program ARGS num:" + args.length + " ARGS:" + args);
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
    if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
    if (args.length > 0 && args[0].equals("--create-input-file"))
        HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), input_file);
}

} // end of class
