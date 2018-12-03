package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkingWithPatternsAndRegexp {

    public static void main(String[] args) {
        WorkingWithPatternsAndRegexp main = new WorkingWithPatternsAndRegexp();
        main.usePatternSplit();
        System.out.println("");

        main.useMatcherFind();

        System.out.println("");
        System.out.println("FindRegex() method written by me:");
        String[] foundByFindRegexMethod;
        foundByFindRegexMethod = main.findRegex("(\\d+)", "#20 @ 214,215 216: 217x218");
        for (int i = 0; i < foundByFindRegexMethod.length; i++) {
            System.out.println(foundByFindRegexMethod[i]);
        }
    }

    void usePatternSplit() {
        //#1 @ 817,273: 26x26
        String line = "#20 @ 214,215 216: 217x218";
        System.out.println("Input line:");
        System.out.println(line);

        //Pattern p = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
        Pattern delimeter = Pattern.compile("(#|@| |,|:|x)+");
        System.out.println("Using Pattern.split(); Matches:");
        String[] finding = delimeter.split(line);
        for (int i = 0; i < finding.length; i++) {
            System.out.printf("%d:%s\n", i, finding[i]);
        }
    }

    void useMatcherFind() {
        //#1 @ 817,273: 26x26
        String line = "#20 @ 214,215 216: 217x218";
        System.out.println("Input line:");
        System.out.println(line);

        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(line);
        System.out.println("Using Matcher.Find();");
        while (m.find()) {
            System.out.print("m.find(): " + m.group() + " ");
            System.out.printf("Start index: %d, end index: %d\n", m.start(), m.end());
        }
    }

    String[] findRegex(String regex, String parsedLine) {
        ArrayList<String> result = new ArrayList<>();
        Pattern patternCompiled = Pattern.compile(regex);
        Matcher matcher = patternCompiled.matcher(parsedLine);

        while (matcher.find()) {
            result.add(matcher.group());
        }
        String[] s = new String[result.size()];
        s = result.toArray(s);
        return s;
    }
}
