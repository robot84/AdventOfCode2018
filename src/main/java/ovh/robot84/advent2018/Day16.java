package ovh.robot84.advent2018;

import ovh.robot84.advent2018.HelperMethods;
import ovh.robot84.advent2018.MyReader;
import ovh.robot84.advent2018.Verbose;

import java.util.*;
import java.io.*;


// PRZEBO++ROBIC NA int ZAMIAST INTEGER  a najlepiej na more generic typu Object czy cos

/**
 * Format linii wejsciowej:
 * <p>
 * token[x]:	b inc 5 if a > 1
 * x:			0  1  2 3  4 5 6
 * <p>
 * <p>
 * Przyklad uzycia:
 * JezykProgramowania d=new JezykProgramowania();
 * d.doAssigment("A",5);
 * d.doAssigment("B",10);
 * d.doAssigment("C",15);
 * // if(A<B) println(C);
 * if(d.isConditionTrue("<",d.valueOfregister("A"),d.valueOfregister("B"))){
 * System.out.println(d.valueOfregister("C"));
 * }
 * <p>
 * Bardziej skomplikowany przyklad uzycia:
 * Zalozmy ze mamy linie "b inc 5 if a > 1" rozbita splitem na tokeny:
 * <p>
 * token[x]:	b inc 5 if a > 1
 * x:			0  1  2 3  4 5 6
 * <p>
 * // if(a>1) b=b+5;
 * if(d.isConditionTrue(token[5], d.valueOfregister(token[4]), Integer.parseInt(token[6]))){
 * d.doAssigment(token[0], d.mathematicalOperation(token[1], d.valueOfregister(token[0]), Integer.parseInt(token[2])));
 * }
 */


public class Day16 {
HashMap<Integer, Integer> rejestry = new HashMap<Integer, Integer>();


public static void main(String[] args) {
    String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day16input1.txt";
    Day16 day = new Day16();
    //Verbose.mute();
    day.parsingProgramArguments(args, INPUT_FILE1);
    day.start(INPUT_FILE1);
}


int mathematicalOperation(String op, int arg1, int arg2) {
    HashMap<String, Integer> opMap = new HashMap<String, Integer>();
    opMap.put("addr", 1);
    opMap.put("addi", 2);
    opMap.put("mulr", 3);
    opMap.put("muli", 4);


    switch (opMap.get(op)) {
        case 1:
            return arg1 + arg2;
        case 2:
            return arg1 * arg2;
        case 3:
            return arg1 % arg2;
        case 4:
            return arg1 % arg2;
        default:
            return 0;
    }
}


int threeArgOperation(int op, int arg1, int arg2, int arg3) {
    HashMap<String, Integer> opMap = new HashMap<String, Integer>();
    opMap.put("addr", 1);
    opMap.put("addi", 2);
    opMap.put("mulr", 3);
    opMap.put("muli", 0);
    opMap.put("banr", 4);
    opMap.put("bani", 5);
    opMap.put("borr", 6);
    opMap.put("bori", 7);
    opMap.put("setr", 8);
    opMap.put("seti", 9);
    opMap.put("gtir", 10);
    opMap.put("gtri", 11);
    opMap.put("gtrr", 12);
    opMap.put("eqir", 13);
    opMap.put("eqri", 14);
    opMap.put("eqrr", 15);

    switch (opMap.get(op)) {
        case 1:
            doAssigment(arg1, arg2);
            break;
        case 2:
            if (arg1 > 0) return arg2;
            break;
        case 3:
            doAssigment(arg1, arg1 + arg2);
            break;
        case 4:
            doAssigment(arg1, arg1 * arg2);
            break;
        case 5:
            doAssigment(arg1, arg1 % arg2);
            break;

        default:
            break;
    }
    return 1;
}


int oneArgOperation(String op, int arg1) {
    HashMap<String, Integer> opMap = new HashMap<String, Integer>();
    opMap.put("snd", 1);
    opMap.put("rcv", 2);
    //opMap.put("mod",3);

    switch (opMap.get(op)) {
        case 1:
            System.out.println("Playing at freq: " + arg1);
            break;
        case 2:
            if (arg1 != 0) {
                System.out.println("Recovery reached. End of song");
                System.exit(0);
            }
            break;
        //case 3: return arg1%arg2;
        default:
            break;
    }
    return 1;
}


void doAssigment(int register, int value) {
    rejestry.put(register, value);
}


int valueOfregister(String register) {
    if (rejestry.containsKey(register)) {
        return rejestry.get(register);
    } else {
        /*
         * najlepiej rzuc wyjatkiem, ale na razie po prostu zakoncz program
         */
        System.err.format("NO '%s' REGISTER FOUND!\n", register);
        System.exit(0);
        return 0;
    }
}


/*
 * more generic method
 * token can be a name of a register or integer number wrapped in String
 */
int valueOf(String token) {
    if (rejestry.containsKey(token)) {
        return rejestry.get(token);
    } else {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            System.err.format("NO '%s' REGISTER FOUND!\n", token);
            System.err.format("'%s' IS NEIGHTER A VALID INTEGER!\n", token);
            System.exit(0);
        }
        /*
         * najlepiej rzuc wyjatkiem, ale na razie po prostu zakoncz program
         */
        System.err.format("NO '%s' REGISTER FOUND!\n", token);
        System.exit(0);
        return 0;
    }

}


boolean isConditionTrue(String op, int arg1, int arg2) {

    HashMap<String, Integer> opMap = new HashMap<String, Integer>();
    opMap.put(">", 1);
    opMap.put(">=", 2);
    opMap.put("<", 3);
    opMap.put("<=", 4);
    opMap.put("==", 5);
    opMap.put("!=", 6);
    opMap.put("<>", 6);
    opMap.put("><", 6);

    switch (opMap.get(op)) {
        case 1:
            return arg1 > arg2;
        case 2:
            return arg1 >= arg2;
        case 3:
            return arg1 < arg2;
        case 4:
            return arg1 <= arg2;
        case 5:
            return arg1 == arg2;
        case 6:
            return arg1 != arg2;
        default:
            return false;
    }

}


void start(String input_file) {

    MyReader myReader = new MyReader();
    myReader.open_file(input_file);


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
