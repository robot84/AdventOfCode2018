package ovh.robot84.advent2018;

public class ScratchTest {

    public static void main(String[] args) {
        ScratchTest scratchTest = new ScratchTest();
        scratchTest.start();
    }

    void start() {
        System.out.println("FindRegex() method written by me:");
        String[] foundByFindRegexMethod;
        String line = "#20 @ 214,215 216: 217x218";
        foundByFindRegexMethod = FindRegex.findRegex("(\\d+)", line);
        for (String s : foundByFindRegexMethod) {
            System.out.println(s);
        }
    }
}
