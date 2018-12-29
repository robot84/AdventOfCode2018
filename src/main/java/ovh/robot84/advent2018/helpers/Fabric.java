package ovh.robot84.advent2018.helpers;

import javafx.util.Pair;

import java.util.Random;

/**
 * Fabric is an 2D array of fields.
 * Every field at the begining has integer value zero.
 * You can add Elfs to fabric, and you can get info how many Elfs
 * share particular fabric field.
 * You can get also which elf, if any, not share any of its field with others.
 */
public class Fabric {
public final static int FABRIC_MAX_X = 10_000;
public final static int FABRIC_MAX_Y = 10_000;

int[][] fabric = new int[FABRIC_MAX_Y][FABRIC_MAX_X];


public void addToFabric(int fromLeft, int fromTop, int width, int height) {
    for (int y = fromTop; y < fromTop + height; y++) {
        for (int x = fromLeft; x < fromLeft + width; x++) {
            this.fabric[y][x]++;
        }
    }
}


public void addToFabric(Elf elf) {
    addToFabric(elf.fromLeft, elf.fromTop, elf.width, elf.height);
}


public boolean isElfOverlappingWithAnotherInFabric(Elf elf) {
    for (int y = elf.fromTop; y < (elf.fromTop + elf.height); y++) {
        for (int x = elf.fromLeft; x < (elf.fromLeft + elf.width); x++) {
            if (this.fabric[y][x] > 1) return true;
        }
    }
    return false;
}


public int numOfFabricFieldWithTwoOrMoreElfs() {
    int count = 0;
    for (int y = 0; y < FABRIC_MAX_Y; y++) {
        for (int x = 0; x < FABRIC_MAX_X; x++) {
            if (this.fabric[y][x] > 1) count++;
        }
    }
    return count;
}


public static void main(String[] args) {
    //Let's generate a Fabric object
    Fabric fabric = new Fabric();
    // and fill it by random data
    Random random = new Random();
    for (int i = 0; i < Fabric.FABRIC_MAX_X; i++) {
        fabric.addToFabric(random.nextInt(2000), random.nextInt(2000), random.nextInt(2000) + 1, random.nextInt(2000) + 1);
    }
    // We fill line 777 more than other lines
    for (int i = 0; i < 1000; i++) {
        fabric.addToFabric(787, 777, 1, 1);
    }
    // and check what is a max value of stored numbers in its
    // we can check it for 3 methods:
    System.out.println("Below I present 3 methods of get max value from fabric's line and index of a field at which this max value occured");

    System.out.printf("method one: value: %d index: %d\n", fabric.getMaxValue(777), fabric.getIndexForMaxValue(777, fabric.getMaxValue(777)));

    Pair<Integer, Integer> maxVAI = fabric.getMaxWithIndex(777);
    System.out.printf("getMaxWithIndex: value: %d index: %d\n", maxVAI.getKey(), maxVAI.getValue());

    System.out.printf("getMaxWithIndex: value: %d index: %d\n",
            fabric.getMaxValue(777),
            fabric.getIndexForMaxValue(777, fabric.getMaxValue(777)));

}


public Integer getField(int lineNum, int fieldInLineNum) {
    if (lineNum < fabric.length & fieldInLineNum < fabric[0].length)
        return fabric[lineNum][fieldInLineNum];
    else
        return null;
}


public Pair<Integer, Integer> getMaxWithIndex(int line) {
    // PT 2 ms
    int maxValue = 0;
    int maxAtIndex = 0;
    for (int index = 0, valueNew = 0; index < this.FABRIC_MAX_X; index++) {
        valueNew = this.getField(line, index);
        if (valueNew > maxValue) {
            maxValue = valueNew;
            maxAtIndex = index;
        }
    }
    Pair<Integer, Integer> maxValueAndIndex = new Pair<>(maxValue, maxAtIndex);
    return maxValueAndIndex;
}


public int getMaxValue(int line) {
    int valueMax = 0;
    for (int index = 0, valueNew = 0; index < this.FABRIC_MAX_X; index++) {
        valueNew = this.getField(line, index);
        if (valueNew > valueMax) {
            valueMax = valueNew;
        }
    }
    return valueMax;
}


public int getIndexForMaxValue(int line, int valueMax) {
    // PT 1.5ms @ metoda2B(metoda2A(fabric))
    for (int index = 0, valueNew = 0, indexMax = 0; index < Fabric.FABRIC_MAX_X; index++) {
        valueNew = this.getField(line, index);
        if (valueNew == valueMax) {
            return index;
        }
    }
    return -1;
}
}