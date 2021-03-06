package ovh.robot84.advent2018;

import ovh.robot84.advent2018.helpers.MyReader;

import java.util.ArrayList;

public class Day01 {
public static void main(String[] args) {
    Day01 day01 = new Day01();
    int star = 2;
    if (star == 1) day01.start();
    else
        day01.star2start();
}


private void start() {
    ovh.robot84.advent2018.helpers.MyReader myReader = new ovh.robot84.advent2018.helpers.MyReader();
    myReader.open_file("C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\src\\main\\resources\\day01input1.txt");

    String s;
    int result = 0;
    while (myReader.get_line() != null) {
        while ((s = myReader.read_string(" ")) != null) {
            result = getResult(s, result);
        }
    }
    System.out.println("Result:" + result);
}


private void star2start() {
    ArrayList<Integer> hits = new ArrayList<>();
    String s;

    int result = 0;
    hits.add(result);
    ovh.robot84.advent2018.helpers.MyReader myReader;
    while (true) {
        myReader = new MyReader();
        myReader.open_file("C:\\Users\\qtcj47\\IdeaProjects\\" +
                "AdventOfCode2018\\src\\main\\resources\\day01input1.txt");

        while (myReader.get_line() != null) {
            while ((s = myReader.read_string(" ")) != null) {
                result = getResult(s, result);
                if (hits.contains(result)) {
                    System.out.println();
                    System.out.println("Result is: " + result);
                    System.exit(0);
                } else hits.add(result);
            }
        }
        System.out.print(".");
    }
}


private int getResult(String s, int result) {
    int inputNumber;
    if (s.charAt(0) == '+') inputNumber = Integer.valueOf(s.substring(1));
    else inputNumber = Integer.valueOf(s);
    result += inputNumber;
    return result;
}
}
