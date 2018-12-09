package ovh.robot84.advent2018;

public class DotCounter {
    private String dotSymbol = ".";
    private long start;
    private int intervalInMiliSec = 500;
    private boolean started;


    DotCounter(int intervalInMiliSec) {
        this.intervalInMiliSec = intervalInMiliSec;
    }

    DotCounter(int intervalInMiliSec, String dotSymbol) {
        this.intervalInMiliSec = intervalInMiliSec;
        this.dotSymbol = dotSymbol;
    }

    DotCounter(String dotSymbol) {
        this.dotSymbol = dotSymbol;
    }


    void start() {
        start = System.nanoTime();
        started = true;
    }

    void stop() {
        started = false;
    }

    boolean check() {

        if ((started == true) &
                ((System.nanoTime() - start) / 1_000_000 > intervalInMiliSec)) {
            System.out.print(dotSymbol);
            start = System.nanoTime();
            return true;
        } else
            return false;
    }
}
