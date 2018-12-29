package ovh.robot84.advent2018.helpers;

public class DotCounter {
private String dotSymbol = ".";
private long start;
private int intervalInMiliSec = 500;
private boolean started;


public DotCounter() {
}


public DotCounter(int intervalInMiliSec) {
    this.intervalInMiliSec = intervalInMiliSec;
}


public DotCounter(int intervalInMiliSec, String dotSymbol) {
    this.intervalInMiliSec = intervalInMiliSec;
    this.dotSymbol = dotSymbol;
}


public DotCounter(String dotSymbol) {
    this.dotSymbol = dotSymbol;
}


public void start() {
    start = System.nanoTime();
    started = true;
}


public void stop() {
    started = false;
}


public boolean check() {

    if ((started == true) &
            ((System.nanoTime() - start) / 1_000_000 > intervalInMiliSec)) {
        System.out.print(dotSymbol);
        start = System.nanoTime();
        return true;
    } else
        return false;
}
}
