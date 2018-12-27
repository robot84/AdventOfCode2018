package ovh.robot84.advent2018;

import java.util.*;

/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-01
 */
public class Day09 {
final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day09input2.txt";
final static int ARRAY_MAX_X = 2_000;
final static int ARRAY_MAX_Y = 2_000;
private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day09input1.txt";


int marbleToAdd;
LinkedList<Integer> marbleList = new LinkedList<>();
ArrayList<Long> score = new ArrayList<>();
ListIterator<Integer> marbleListIterator;
int currentMarblePosition = 0;
/*10 players; last marble is worth 1618 points: high score is 8317
        13 players; last marble is worth 7999 points: high score is 146373
        17 players; last marble is worth 1104 points: high score is 2764
        21 players; last marble is worth 6111 points: high score is 54718
        30 players; last marble is worth 5807 points: high score is 37305*/
private MyReader myReader = new MyReader();
//private int players = 17;
//private int marbles = 1104 + 1;
private int players = 403;
private int marbles = 7192000 + 1;
private int newCurrentMarblePosition;
private int counter;


//403 players; last marble is worth 71920 points
private Day09(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Day09 dayStar1 = new Day09(INPUT_FILE1);
    HelperMethods.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


private void star1start() {
    Date runDate = new Date();
    System.out.println(HelperMethods.getCurrentTime());
    PerformanceTest performanceTest = new PerformanceTest();
    for (int i = 0; i < players; i++) {
        score.add(0L);
    } // 200 us

    MarbleHandler mHandler = new MarbleHandler();
    //System.out.println("Elapsed time: " + HelperMethods.getElapsedTimeInMiliSeconds(runDate));

    marbleList.add(0);
    marbleListIterator = marbleList.listIterator(0);
    currentMarblePosition = 0;
    int playerNum;
    marbleToAdd = 1;
    counter = 0;
    int marbleListSize = marbleList.size();

    // marbleListIterator = marbleList.listIterator(marbleToAdd);

    while (marbleToAdd < marbles) {
        performanceTest.startPerformanceTest();
        for (playerNum = 0; playerNum < players; playerNum++) {
            //this.displayMarbleList(marbleList, marbleListIterator);
            if (marbleToAdd > 90 && marbleToAdd < 150) this.displayMarbleList(marbleList, marbleListIterator);
            if ((marbleToAdd % 23) == 0) {
                int removedValue;
                counter++;
                if ((removedValue = removeMarbleAndGetRemovedValue(marbleList, marbleListIterator)) != (-1)) {
                    Verbose.printf("added score %d + %d + %d = %d to player %d\n", score.get(playerNum), marbleToAdd, removedValue, score.get(playerNum) + marbleToAdd + removedValue, playerNum);
                } else {
                    int stepNum = 0;
                    //this.displayMarbleList(marbleList, marbleListIterator);
                    this.displayMarbleListInRange(marbleList, marbleListIterator, 0, 10);
                    this.displayMarbleListInRange(marbleList, marbleListIterator, marbleListSize - 10, marbleListSize);
                    System.out.println("when placing marble " + marbleToAdd);
                    for (stepNum = 0; stepNum < 8; stepNum++) {
                        if (marbleListIterator.hasPrevious()) {
                            marbleListIterator.previous();
                        } else {
                            System.out.println("--->Problem with marbleListIterator.previos() iteration " + stepNum);
                            // if(stepNum==6)
                            marbleListIterator = marbleList.listIterator(marbleList.size() - 1);
                        }
                    }
                    removedValue = marbleListIterator.next();
                    // marbleListIterator.previous();
                    if (marbleListIterator.hasPrevious()) marbleListIterator.remove();
                    if (marbleListIterator.hasNext()) marbleListIterator.next();
                    else {
                        System.out.println("hasNext() failed. WE HAVE A BIG TROUBLE CAUSE WE NOT HANDLE IT CORRECTLY!!");
                        System.exit(-1);
                    }
                    this.displayMarbleListInRange(marbleList, marbleListIterator, 0, 10);
                    this.displayMarbleListInRange(marbleList, marbleListIterator, marbleListSize - 10, marbleListSize);
                    //System.exit(1);
                    Verbose.printf("--->added score %d + %d + %d = %d to player %d\n", score.get(playerNum), marbleToAdd, removedValue, score.get(playerNum) + marbleToAdd + removedValue, playerNum);
                }

                score.set(playerNum, score.get(playerNum) + marbleToAdd + removedValue);
//                score.set(playerNum, score.get(playerNum) + removedValue);
                marbleListSize--;

                // performanceTest.stopPerformanceTest();
            } else if ((marbleToAdd % 23) == 1) {
                if ((addMarble(marbleListIterator, marbleToAdd))) {
                } else {
                    marbleListIterator = marbleList.listIterator(0);
                    this.addMarble(marbleListIterator, marbleToAdd);
                }
                marbleListSize++;
            } else {
                if ((addMarble(marbleListIterator, marbleToAdd))) {
                } else {
                    marbleListIterator = marbleList.listIterator(0);
                    this.addMarble(marbleListIterator, marbleToAdd);
                }
                marbleListSize++;
            }
            marbleToAdd++;
        }
    }

    for (int i = 0; i < players; i++) {
        Verbose.setVerboseLevelForNextPrint(1);
        Verbose.printf("Player %d has score %d\n", i + 1, score.get(i));
    }
    System.out.println("Rezult: " + Collections.max(score));
    System.out.println("Ile razy mielismy magiczny kamien? " + counter);
}


void displayMarbleList(LinkedList<Integer> linkedList, ListIterator listIterator) {
    final int START_OF_LIST = 0;
    ListIterator ldisplay = linkedList.listIterator(START_OF_LIST);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (listIterator.nextIndex() > 0)
        System.out.print("  (" + (listIterator.nextIndex() - 1) + ")  " + linkedList.get((listIterator.nextIndex() - 1)));
    System.out.println();
}


void displayMarbleListInRange(LinkedList<Integer> linkedList, ListIterator listIterator, int min, int max) {
    final int START_OF_LIST = 0;
    ListIterator ldisplay = linkedList.listIterator(min);
    for (int i = min; i < max & ldisplay.hasNext(); i++) System.out.printf("%d ", ldisplay.next());
    if (listIterator.nextIndex() > 0)
        System.out.print("  (" + (listIterator.nextIndex() - 1) + ")  " + linkedList.get((listIterator.nextIndex() - 1)));
    System.out.println();
}


boolean addMarble(ListIterator listIterator, int value) {
    if (listIterator.hasNext()) {
        listIterator.next();
    } else {
        return false;
    }
    listIterator.add(value);
    return true;
}


boolean removeMarble(LinkedList<Integer> linkedList, ListIterator listIterator) {
    int c = 0;
    for (; c < 8; c++) {
        if (listIterator.hasPrevious()) {
            listIterator.previous();
        } else {
            for (int d = 0; c > d; d++) {
                listIterator.next();
            }
            System.out.println("Cannot go to previous position. c=" + c);
            return false;
        }
    }
    if (listIterator.hasPrevious()) listIterator.remove();
    else {
        System.out.println("Cannot do .remove because hasPrevious() failed :(" +
                "This case was not tested. I don;t know is ok or not.");
        return false;
    }
    if (listIterator.hasNext()) listIterator.next();
    else {
        System.out.println("hasNext() failed");
        return false;
    }
    return true;
}


int removeMarbleAndGetRemovedValue(LinkedList<Integer> linkedList, ListIterator<Integer> listIterator) {
    int removedValue;
    final int FAIL = -1;
    int c = 0;
    for (; c < 8; c++) {
        if (listIterator.hasPrevious()) {
            listIterator.previous();
        } else {
            for (int d = 0; c > d; d++) {
                listIterator.next();
            }
            System.out.println("Cannot go to previous position. c=" + c);
            return FAIL;
        }
    }
    removedValue = listIterator.next();
    // listIterator.previous();
    if (listIterator.hasPrevious()) listIterator.remove();
    else {
        System.out.println("Cannot do .remove because hasPrevious() failed :(" +
                "This case was not tested. I don;t know is ok or not.");
        return FAIL;
    }

    if (listIterator.hasNext()) listIterator.next();
    else {
        System.out.println("hasNext() failed. WE HAVE A BIG TROUBLE CAUSE WE NOT HANDLE IT CORRECTLY!!");
        return FAIL;
    }
    return removedValue;
}


}


