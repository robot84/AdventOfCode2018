package ovh.robot84.advent2018;

import java.util.Collection;
import java.util.HashMap;

public class HelperTemporaryMethods {

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

    void printResult(String result) {
        System.out.println("Result: " + result);
    }

    void printResult(int result) {
        System.out.println("Result: " + result);
    }

}
