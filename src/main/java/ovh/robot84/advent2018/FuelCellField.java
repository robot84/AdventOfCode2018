package ovh.robot84.advent2018;

import javafx.util.Pair;

public class FuelCellField implements Comparable<FuelCellField>, Cloneable {

int totalPwr;
FuelCell cell;
int x, y;
int size = 3;


FuelCellField() {
    initiate(1, 1, 1, 1);
}


FuelCellField(int x, int y, int sn) {
    initiate(x, y, sn);
}


FuelCellField(int x, int y, int sn, int size) {
    initiate(x, y, sn, size);
}


static int getTotalPwr(int x, int y, int sn, int size) {
    int totalPwr = 0;
    for (int y1 = 0; y1 < size; y1++) {
        for (int x1 = 0; x1 < size; x1++) {
            totalPwr += FuelCell.getPwr(x + x1, y + y1, sn);
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
            totalPwr += FuelCell.getPwr(x + x1, y + y1, sn);
        }
    }
}


int getTotalPwr() {
    return totalPwr;
}


int getX() {
    return this.x;
}


int getY() {
    return this.y;
}


Pair<Integer, Integer> getXY() {
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


