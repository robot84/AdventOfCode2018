package ovh.robot84.advent2018.helpers;

public class PerformanceTest {
private long startTime;


public void startPerformanceTest() {
    startTime = System.nanoTime();
}


public void stopPerformanceTest() {
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    if (duration > 1_000_000) System.out.printf("Time elapsed: %,6.3f ms\n", (duration / 1_000_000.0));
    else System.out.printf("Time elapsed: %,6d ns\n", duration);
}

}
