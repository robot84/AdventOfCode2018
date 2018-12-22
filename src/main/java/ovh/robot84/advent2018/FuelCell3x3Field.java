package ovh.robot84.advent2018;

import javafx.util.Pair;

public class FuelCell3x3Field implements Comparable<FuelCell3x3Field> {

int totalPwr;
FuelCell cell;
int x, y;


FuelCell3x3Field(int x, int y, int sn) {
    this.x = x;
    this.y = y;
    for (int y1 = 0; y1 < 3; y1++) {
        for (int x1 = 0; x1 < 3; x1++) {
            cell = new FuelCell(x + x1, y + y1, sn);
            totalPwr += cell.getPwr();
        }
    }
}


int getTotalPwr() {
    return totalPwr;
}


Pair<Integer, Integer> getXY() {
    return new Pair(x, y);
}


@Override
public int compareTo(FuelCell3x3Field o) {
    return this.getTotalPwr() - o.getTotalPwr();
}

}


class FuelCell {
int pwr;
int rackID;


FuelCell(int x, int y, int sn) {
    rackID = x + 10;
    pwr = (rackID * y + sn) * rackID;
    if (pwr / 100 > 0) pwr = (pwr / 100) % 10;
    else pwr = 0;
    pwr -= 5;
}


int getPwr() {
    return this.pwr;
}
}