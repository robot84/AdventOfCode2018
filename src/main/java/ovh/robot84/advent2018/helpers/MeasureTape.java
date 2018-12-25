package ovh.robot84.advent2018.helpers;

import ovh.robot84.advent2018.Verbose;

import java.util.function.Function;
import java.util.function.IntConsumer;

public class MeasureTape {
public MeasureTape(final int shiftSize, final int maxNumber, final int numOfLeftSpaceFilledFields, Runnable tapeDetails) {
    tapeDetails.run();
}


public MeasureTape(final int shiftSize, final int maxNumber, Runnable tapeDetails) {
    final int numOfLeftSpaceFilledFields = 9;
    tapeDetails.run();
}


public static void measureTapeType1(final int shiftSize, final int maxNumber) {
    final int numOfLeftSpaceFilledFields = 9;
    printOneLineOfTape(shiftSize, maxNumber, x -> x / 10, false);
    printOneLineOfTape(shiftSize, maxNumber, x -> x % 10);
    printOneLineOfTape(shiftSize, maxNumber, x -> 0);
}


private static void printOneLineOfTape(final int shiftSize, final int maxNumber, Function<Integer, Integer> function, IntConsumer printingMethod) {
    for (int shift = 0; shift < shiftSize; shift++) Verbose.printf(" ");
    for (int x = 0; x <= maxNumber / 10; x++) {
        printingMethod.accept(function.apply(x));
        for (int digit = 0; digit < 9; digit++) Verbose.printf(" ");
    }
    Verbose.printf("\n");
}


private static void printOneLineOfTape(final int shiftSize, final int maxNumber, Function<Integer, Integer> function) {
    printOneLineOfTape(shiftSize, maxNumber, function, x -> {
        Verbose.printf("%d", x);
    });
}


private static void printOneLineOfTape(final int shiftSize, final int maxNumber, Function<Integer, Integer> function, boolean isZeroVisible) {
    if (isZeroVisible) printOneLineOfTape(shiftSize, maxNumber, function);
    else printOneLineOfTape(shiftSize, maxNumber, function, x -> {
        if (x != 0) Verbose.printf("%d", x);
        else Verbose.printf(" ");
    });
}
}