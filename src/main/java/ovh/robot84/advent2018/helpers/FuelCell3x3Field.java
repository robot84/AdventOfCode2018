package ovh.robot84.advent2018.helpers;

import javafx.util.Pair;

public class FuelCell3x3Field implements Comparable<FuelCell3x3Field>, Cloneable {

int totalPwr;
FuelCell cell;
int x, y;
int size = 3;


FuelCell3x3Field() {
    initiate(1, 1, 1, 1);
}


public FuelCell3x3Field(int x, int y, int sn) {
    initiate(x, y, sn);
}


public FuelCell3x3Field(int x, int y, int sn, int size) {
    initiate(x, y, sn, size);
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
            totalPwr += FuelCell.getPwr(x + x1, y + y1, sn);
        }
    }
}


public int getTotalPwr() {
    return totalPwr;
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
public int compareTo(FuelCell3x3Field o) {
    return this.getTotalPwr() - o.getTotalPwr();
}

}


