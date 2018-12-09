package ovh.robot84.advent2018;

import java.util.ArrayList;
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
public class Day09alternativeSolution {
    final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day09input2.txt";
    final static int ARRAY_MAX_X = 2_000;
    final static int ARRAY_MAX_Y = 2_000;
    private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
            "src\\main\\resources\\day09input1.txt";
    HashMap<Character, Integer> hm1 = new HashMap();
    ArrayList<String> boxID = new ArrayList<>();
    int marbleToAdd;
    /*10 players; last marble is worth 1618 points: high score is 8317
            13 players; last marble is worth 7999 points: high score is 146373
            17 players; last marble is worth 1104 points: high score is 2764
            21 players; last marble is worth 6111 points: high score is 54718
            30 players; last marble is worth 5807 points: high score is 37305*/
    ArrayList<Integer> marbleList = new ArrayList<>();
    ArrayList<Integer> score = new ArrayList<>();
    int currentMarblePosition = 0;
    private MyReader myReader = new MyReader();
    private int players = 403;
    //private int marbles=900000+1;
    //private int players=403;
    private int marbles = 7192000 + 1;
    private int newCurrentMarblePosition;
    private int counter;

    //403 players; last marble is worth 71920 points
    private Day09alternativeSolution(String input_file) {
        myReader.open_file(input_file);
    }


    public static void main(String[] args) {
        Day09alternativeSolution dayStar1 = new Day09alternativeSolution(INPUT_FILE1);
        dayStar1.parsingProgramArguments(args);
        //Verbose.mute();
        dayStar1.star1start();
    }


    private void star1start() {
        for (int i = 0; i < players; i++) {
            score.add(0);
        }

        marbleList.add(0);


        currentMarblePosition = 1;
        int playerNum = 0;
        marbleToAdd = 1;
        DotCounter dc = new DotCounter(1000);
        dc.start();
        counter = 0;
        int playersMinusOne = players - 1;
        int mSize = marbleList.size();
        int winner = 62 - 1;
        while (marbleToAdd < marbles) {
            // dc.check();
            //if (marbleToAdd%7000 == 0) System.out.print(",");
            for (playerNum = 0; playerNum < winner; playerNum++) {

                if ((marbleToAdd % 23) != 0) {
                    newCurrentMarblePosition = (currentMarblePosition + 2) % (mSize++);
                    marbleList.add(newCurrentMarblePosition, marbleToAdd);
                    currentMarblePosition = newCurrentMarblePosition;
                    // displayList(playerNum);
                } else {

                    int marbleToRemoveIndex = ((currentMarblePosition - 7 < 0 ?
                            currentMarblePosition - 7 + marbleList.size() :
                            currentMarblePosition - 7) % (marbleList.size()));

                    //Verbose.printf("[player %d gained by %d+%d=%d]\n",playerNum+1,marbleToAdd,marbleList.get(marbleToRemoveIndex),marbleToAdd+marbleList.get(marbleToRemoveIndex));

                    marbleList.remove(marbleToRemoveIndex);
                    if (marbleToRemoveIndex < currentMarblePosition) currentMarblePosition--;
                    currentMarblePosition = ((currentMarblePosition - 6 < 0 ?
                            currentMarblePosition - 6 + marbleList.size() :
                            currentMarblePosition - 6) % (marbleList.size()));

                    // displayList(playerNum);
                }


                {
                    score.set(playerNum, score.get(playerNum) + marbleToAdd);
                    int marbleToRemoveIndex = ((currentMarblePosition - 7 < 0 ?
                            currentMarblePosition - 7 + marbleList.size() :
                            currentMarblePosition - 7) % (marbleList.size()));
                    score.set(playerNum, score.get(playerNum) + marbleList.get(marbleToRemoveIndex));
                    //Verbose.printf("[player %d gained by %d+%d=%d]\n",playerNum+1,marbleToAdd,marbleList.get(marbleToRemoveIndex),marbleToAdd+marbleList.get(marbleToRemoveIndex));

                    marbleList.remove(marbleToRemoveIndex);
                    if (marbleToRemoveIndex < currentMarblePosition) currentMarblePosition--;
                    currentMarblePosition = ((currentMarblePosition - 6 < 0 ?
                            currentMarblePosition - 6 + marbleList.size() :
                            currentMarblePosition - 6) % (marbleList.size()));

                    // displayList(playerNum);
                }

                for (playerNum = winner + 1; playerNum < players; playerNum++) {

                    if ((marbleToAdd % 23) != 0) {
                        newCurrentMarblePosition = (currentMarblePosition + 2) % (mSize++);
                        marbleList.add(newCurrentMarblePosition, marbleToAdd);
                        currentMarblePosition = newCurrentMarblePosition;
                        // displayList(playerNum);
                    } else {
                        //  score.set(playerNum,score.get(playerNum)+marbleToAdd);
                        int marbleToRemoveIndex = ((currentMarblePosition - 7 < 0 ?
                                currentMarblePosition - 7 + marbleList.size() :
                                currentMarblePosition - 7) % (marbleList.size()));
                        // score.set(playerNum,score.get(playerNum)+marbleList.get(marbleToRemoveIndex));
                        //Verbose.printf("[player %d gained by %d+%d=%d]\n",playerNum+1,marbleToAdd,marbleList.get(marbleToRemoveIndex),marbleToAdd+marbleList.get(marbleToRemoveIndex));

                        marbleList.remove(marbleToRemoveIndex);
                        if (marbleToRemoveIndex < currentMarblePosition) currentMarblePosition--;
                        currentMarblePosition = ((currentMarblePosition - 6 < 0 ?
                                currentMarblePosition - 6 + marbleList.size() :
                                currentMarblePosition - 6) % (marbleList.size()));

                        // displayList(playerNum);
                    }

                    marbleToAdd++;
                }

            }

            System.out.println("Rezult: " + HelperMethods.maxInArray(score) + " for player " + score.indexOf(HelperMethods.maxInArray(score)));


        }


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

    private void parsingProgramArguments(String[] args) {
        System.out.println("Program ARGS num:" + args.length + " ARGS:" + args);
        if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
        if (args.length > 0 && args[0].equals("-vv")) Verbose.enableTwoLevelVerbose();
        if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableThreeLevelVerbose();
        if (args.length > 0 && args[0].equals("--create-input-file"))
            HelperMethods.getInputFileFromWWW(Integer.valueOf(this.getClass().getSimpleName().substring(3)), INPUT_FILE1);
    }

}
