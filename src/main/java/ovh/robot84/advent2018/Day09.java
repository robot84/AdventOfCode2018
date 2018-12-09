package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day09input1.txt";
    private MyReader myReader = new MyReader();

    /*10 players; last marble is worth 1618 points: high score is 8317
            13 players; last marble is worth 7999 points: high score is 146373
            17 players; last marble is worth 1104 points: high score is 2764
            21 players; last marble is worth 6111 points: high score is 54718
            30 players; last marble is worth 5807 points: high score is 37305*/
    //403 players; last marble is worth 71920 points
    //private int players=403,marbles=900000;
    private static final int MAGIC_MARBLE_NUM = 23;
    ArrayList<Integer> marbleList = new ArrayList<>(), score = new ArrayList<>();
    private int players = 5, marbles = 57;
    private int marbleToAdd, currentMarblePosition = 0, newCurrentMarblePosition, counter;
    private Day09(String input_file) {
        myReader.open_file(input_file);
    }

    public static void main(String[] args) {
        Day09 dayStar1 = new Day09(INPUT_FILE1);
        dayStar1.parsingProgramArguments(args);
        //Verbose.mute();
        dayStar1.star1start();
    }

    private void parsingProgramArguments(String[] args) {
        System.out.println("Program ARGS num:" + args.length + " ARGS:" + args);
        if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
        if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
        if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
        if (args.length > 0 && args[0].equals("--create-input-file"))
            HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), INPUT_FILE1);
    }

    private void star1start() {
        Date runDate = new Date();
        System.out.println(HelperMethods.getCurrentTime());
        for (int i = 0; i < players; i++) score.add(0);
        marbleList.add(0); // throw first marble
        currentMarblePosition = 0;
        /* when sencense says " last marble is worth 1618 points", the number of marbles is not
        1618, but 1618+1 = 1619 because of marble num 0, which we must count.
        this is justification of command: marbles++
         */
        marbles++;
        int playerNum = 0;
        marbleToAdd = 1;
        while (marbleToAdd < marbles) {

            long startTime = System.nanoTime();

            //if (marbleToAdd%7000 == 0) System.out.print(",");
            for (playerNum = 0; playerNum < players; playerNum++) {

                if ((marbleToAdd % MAGIC_MARBLE_NUM) != 0) {
                    newCurrentMarblePosition = (currentMarblePosition + 2) % (marbleList.size());
                    marbleList.add(newCurrentMarblePosition, marbleToAdd);
                    currentMarblePosition = newCurrentMarblePosition;
                    displayList(playerNum);
                } else {

                    int marbleToRemoveIndex = ((currentMarblePosition - 7 < 0 ?
                            currentMarblePosition - 7 + marbleList.size() :
                            currentMarblePosition - 7) % (marbleList.size()));

//                       if(playerNum==62) {
                    score.set(playerNum, score.get(playerNum) + marbleToAdd);
                    score.set(playerNum, score.get(playerNum) + marbleList.get(marbleToRemoveIndex));
                    Verbose.printf("Player %d gets %d + %d = %d points\n", playerNum + 1, marbleToAdd, marbleList.get(marbleToRemoveIndex), marbleToAdd + marbleList.get(marbleToRemoveIndex));

                    /*    if(counter==players-1) {
                            counter=0;
                            System.out.println("");
                        }
                        else {
                            System.out.printf("%4d ", playerNum + 1);
                            counter++;
                        }*/
                    marbleList.remove(marbleToRemoveIndex);
                    if (marbleToRemoveIndex < currentMarblePosition) currentMarblePosition--;
                    currentMarblePosition = ((currentMarblePosition - 6 < 0 ?
                            currentMarblePosition - 6 + marbleList.size() :
                            currentMarblePosition - 6) % (marbleList.size()));

                    displayList(playerNum);
                }
                marbleToAdd++;
            }

            //methodToTime();
            /*    if(counter>=100) {
                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime);
                    if (duration > 1_000_000) System.out.printf("Time elapsed: %,6.3f ms\n", (duration / 1_000_000.0));
                    else System.out.printf("Time elapsed: %,6d ns\n", duration);
                    counter=0;
                }
                counter++;*/
            }
        for (int i = 0; i < players; i++) {
            Verbose.setVerboseLevelForNextPrint(2);
            Verbose.printf("Player %d has score %d\n", i + 1, score.get(i));
        }
        System.out.println("Last marble thrown by player " + ((marbles % players) + 1));
        System.out.println("Last marble with score thrown by player " + (((marbles % MAGIC_MARBLE_NUM) % playerNum) + 1));
        System.out.println("Rezult: " + HelperMethods.maxInArray(score));
        System.out.println("Elapsed time: " + HelperMethods.getElapsedTimeAsHHmmss(runDate));



    }

    void displayList(int player) {
        String displayMe;
        displayMe = "!" + (player + 1) + "!" +
                marbleList.subList(0, currentMarblePosition).toString() +
                " (" +
                marbleList.get(currentMarblePosition) +
                ") " +
                marbleList.subList(currentMarblePosition + 1, marbleList.size());
        Verbose.println(displayMe);
    }

}
