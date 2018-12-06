package ovh.robot84.advent2018;

import java.util.Collection;
import java.util.HashMap;

public class HelperMethods {


    void initializeCollection(Collection collection, int collectionSize) {
        for (int i = 0; i < collectionSize; i++) {
            collection.add(0);
        }
    }

    void initializeCharKeyHashMap(HashMap hashMap, int initializer) {
        final int charactersInAlphabet = 26;
        final char firstLetterInAlphabet = 'a';

        for (int i = 0; i < charactersInAlphabet; i++) {
            hashMap.put((char) (firstLetterInAlphabet + i), initializer);
            System.out.print(hashMap.get((char) (firstLetterInAlphabet + i)));
        }
    }


    void printCharKeyHashMap(HashMap hashMap) {
        final int charactersInAlphabet = 26;
        final char firstLetterInAlphabet = 'a';
        for (int i = 0; i < charactersInAlphabet; i++) {
            System.out.print(hashMap.get((char) (firstLetterInAlphabet + i)));
        }
    }

    static void printResult(String result) {
        System.out.println("\nResult: " + result);
    }

    static void printResult(int result) {
        System.out.println("\nResult: " + result);
    }


    public static Integer[] findAllNumbersInLine(String line) {
        String[] foundByFindRegexMethod;
        foundByFindRegexMethod = FindRegex.findRegex("(\\d+)", line);
        Integer[] numbers = new Integer[foundByFindRegexMethod.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.valueOf(foundByFindRegexMethod[i]);
        }
        return numbers;
    }

    void performanceTest() {
        long startTime = System.nanoTime();
        //methodToTime();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        if (duration > 1_000_000) System.out.printf("Time elapsed: %,6.3f ms\n", (duration / 1_000_000.0));
        else System.out.printf("Time elapsed: %,6d ns\n", duration);
    }

/*
    void performanceTestWithGuava(){
        import com.google.common.base.Stopwatch;

        Stopwatch timer = Stopwatch.createStarted();
//method invocation
        LOG.info("Method took: " + timer.stop());
    }
*/


}
