package ovh.robot84.advent2018;

import com.google.common.collect.HashBiMap;
import ovh.robot84.advent2018.helpers.HelperMethods;
import ovh.robot84.advent2018.helpers.MyReader;
import ovh.robot84.advent2018.helpers.ThreeArgFunction;
import ovh.robot84.advent2018.helpers.Verbose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day21 {
private static final Integer REGISTER_WHICH_STORE_IP = 4;
//private static final int DIVIDER = 9_000_000;
private static final int DIVIDER = 1;
// TODO refactor in OOP style: Register rejestry, InstructionHelper.ConvertOpToMnemonic()
HashMap<Integer, Long> rejestry = new HashMap<>();
HashBiMap<String, Integer> opMap = HashBiMap.create();
private Integer numberToRemove;


public static void main(String[] args) {
    String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day21input1.txt";
    String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day21input2.txt";
    Day21 day = new Day21();
    //Verbose.mute();
    Verbose.disablePrompts();
    HelperMethods.parsingProgramArguments(args, INPUT_FILE1, day);

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

    Long imm1 = Long.valueOf(arg1);
    Long imm2 = Long.valueOf(arg2);
    Long rr1 = 0L;
    if (arg1 >= 0 && arg1 < rejestry.size()) rr1 = rejestry.get(arg1);
    Long rr2 = 0L;
    if (arg2 >= 0 && arg2 < rejestry.size()) rr2 = rejestry.get(arg2);
//if(op==14) System.out.printf("muli: %d [%x] * %d [%x] = %d [%x]\n",rr1,rr1,imm2,imm2,rr1*imm2,((long)rr1)*imm2);

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
            doAssigment(arg3, (long) (imm1 > rr2 ? 1 : 0));
            break;
        case 12:
            doAssigment(arg3, (long) (rr1 > imm2 ? 1 : 0));
            break;
        case 4:
            doAssigment(arg3, (long) (rr1 > rr2 ? 1 : 0));
            break;
        case 10:
            doAssigment(arg3, (long) (imm1 == rr2 ? 1 : 0));
            break;
        case 2:
            doAssigment(arg3, (long) (rr1 == imm2 ? 1 : 0));
            break;
        case 11:
            doAssigment(arg3, (long) (rr1 == rr2 ? 1 : 0));
            break;
        default:
            break;
    }
    return 1;
}


void doAssigment(int register, Long value) {
    rejestry.put(register, value);
}


void startStar001(String input_file) {
    ArrayList<ArrayList<Integer>> instructions = new ArrayList<ArrayList<Integer>>();
    MyReader myReader = new MyReader();

    myReader.open_file(input_file);
    // for star1 use this:
    initRegistersWithZeros();
    // for star2 use this:
    //initRegistersWithZerosForStart2();
    mnemonicToOpCodeTableInit(opMap);

    loadInputDataFromFile(myReader, instructions);
    //computeResult();
    runEngine(instructions);

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
    BiFunction<Integer, Boolean, Boolean> f2 = (x, y) -> (x % DIVIDER == 0 && y == true);
    ThreeArgFunction<Integer, Boolean, Boolean, Boolean> f3 = (x, y, z) -> (x % DIVIDER == 0 && y == true && z == true);
    ArrayList<Integer> instruction;
    int ip = 6;
    int ipForStar2 = 0;
    //ip = ipForStar2;
    int count = 0;
    Long r2 = 0L;
    int min = Integer.MAX_VALUE;
    int max = 0;
    boolean triggered = false;
    boolean newMin = false;
    boolean newMax = false;
    boolean stateComparing = false;
    ArrayList<Integer> arrayList = new ArrayList();
    ArrayList<Integer> arrayList2 = new ArrayList();
    while (ip < instructions.size()) {
        if (ip == 30) triggered = false;

        instruction = instructions.get(ip);
        doAssigment(REGISTER_WHICH_STORE_IP, Long.valueOf(ip));
        if (ip == 29) {
            triggered = true;
            newMin = false;
            newMax = false;
            if (rejestry.get(3) < min) {
                min = Math.toIntExact(rejestry.get(3));
                newMin = true;
            }
            if (rejestry.get(3) > max) {
                max = Math.toIntExact(rejestry.get(3));
                newMax = true;
            }
        }
        //if((rejestry.get(2)) > 0x7FFFFE && rejestry.get(2)!=r2) triggered = true;
        if (f3.apply(count, triggered, newMin)) {
            Verbose.printf("ip=%d [0x%x] [0X%X] %s", ip, rejestry.get(2), rejestry.get(3), rejestry.toString());
            Verbose.printf(" %s %d %d %d", opCodeToMnemonic(instruction.get(0)),
                    instruction.get(1), instruction.get(2), instruction.get(3));
        }
        r2 = rejestry.get(2);
        runInstruction(instruction);
        ip = Math.toIntExact(rejestry.get(REGISTER_WHICH_STORE_IP));
        ip++;
        /*if (f2.apply(count, triggered))
            Verbose.printf(" %s\n", rejestry.toString());*/
        count++;
    }
}


private void loadInputDataFromFile(MyReader myReader, ArrayList<ArrayList<Integer>> instructions) {
    String line1;
    int count = 0;
    while ((line1 = myReader.get_line()) != null) {
        ArrayList<Integer> instructionFromLine = new ArrayList<>();
        Verbose.println(2, "ip=" + count + ": " + line1);
        convertInputDataToMemoryDataStructures(line1, instructionFromLine);
        instructions.add(instructionFromLine);
        count++;
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
            Verbose.printf(3, "m2.group(%s): %s\n", i, m2.group(i));
            instructionFromLine.add(Integer.valueOf(m2.group(i)));
        }
    }
}


private void initRegistersWithZerosForStar1() {
    //0=1, 1=1, 2=59, 3=16433800, 4=6, 5=0
    rejestry.put(0, (long) 1);
    rejestry.put(1, (long) 1);
    rejestry.put(2, (long) 59);
    rejestry.put(3, (long) 16433800);
    rejestry.put(4, (long) 6);
    rejestry.put(5, (long) 0);
}


private void initRegistersWithZeros() {

    rejestry.put(0, (long) 0);
    rejestry.put(1, (long) 0);
    rejestry.put(2, (long) 0);
    rejestry.put(3, (long) 0);
    rejestry.put(4, (long) 0);
    rejestry.put(5, (long) 0);
}


/*
private void initRegistersWithZerosForStart2a() {
    rejestry.put(0, 1);
    rejestry.put(1, 0);
    rejestry.put(2, 10551340);
    rejestry.put(3, 10551340);
    rejestry.put(4, 3);
    rejestry.put(5, 10551345);
}
*/


} // end of class
