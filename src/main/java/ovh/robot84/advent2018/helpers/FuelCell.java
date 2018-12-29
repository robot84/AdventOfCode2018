package ovh.robot84.advent2018.helpers;

public class FuelCell {
int pwr;
int rackID;


public FuelCell(int x, int y, int sn) {
    setPwr(x, y, sn);
}


public static final int getPwr(final int x, final int y, final int sn) {
    final int rackID;
    int pwr;
    rackID = x + 10;
    pwr = (rackID * y + sn) * rackID;
    if (pwr / 100 > 0) pwr = (pwr / 100) % 10;
    else pwr = 0;
    pwr -= 5;
    return pwr;
}


private int setPwr(int x, int y, int sn) {
    rackID = x + 10;
    pwr = (rackID * y + sn) * rackID;
    if (pwr / 100 > 0) pwr = (pwr / 100) % 10;
    else pwr = 0;
    pwr -= 5;
    return pwr;
}


public int getPwr() {
    return this.pwr;
}
}
