package ovh.robot84.advent2018.helpers;

import javafx.util.Pair;

public class FuelCellField implements Comparable<FuelCellField>, Cloneable {

int totalPwr;
ovh.robot84.advent2018.helpers.FuelCell cell;
int x, y;
int size = 3;


public FuelCellField() {
    initiate(1, 1, 1, 1);
}


public FuelCellField(int x, int y, int sn) {
    initiate(x, y, sn);
}


public FuelCellField(int x, int y, int sn, int size) {
    initiate(x, y, sn, size);
}


public static int getTotalPwr(int x, int y, int sn, int size) {
    int totalPwr = 0;
    for (int y1 = 0; y1 < size; y1++) {
        for (int x1 = 0; x1 < size; x1++) {
            totalPwr += ovh.robot84.advent2018.helpers.FuelCell.getPwr(x + x1, y + y1, sn);
        }
    }
    return totalPwr;
}


public void initiate(int x, int y, int sn) {
    initiate(x, y, sn, this.size);
}


public void initiate(int x, int y, int sn, int size) {
    this.x = x;
    this.y = y;
    for (int y1 = 0; y1 < size; y1++) {
        for (int x1 = 0; x1 < size; x1++) {
            //cell = new FuelCell(x + x1, y + y1, sn);
            totalPwr += ovh.robot84.advent2018.helpers.FuelCell.getPwr(x + x1, y + y1, sn);
        }
    }
}


public int getTotalPwr() {
    return totalPwr;
}


public int getX() {
    return this.x;
}


public int getY() {
    return this.y;
}


public Pair<Integer, Integer> getXY() {
    return new Pair(x, y);
}


@Override
public Object clone() {
    Object clon = null;
    try {
        clon = super.clone();
    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    } finally {
        return clon;
    }
}


@Override
public int compareTo(FuelCellField o) {
    return this.getTotalPwr() - o.getTotalPwr();
}

}


