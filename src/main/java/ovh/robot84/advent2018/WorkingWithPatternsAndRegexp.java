package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkingWithPatternsAndRegexp {


public static void main(String[] args) {
    WorkingWithPatternsAndRegexp main1 = new WorkingWithPatternsAndRegexp();
    main1.usePatternSplit();

    System.out.println("");
    main1.useMatcherFind();

    System.out.println("");
    main1.useFindRegex();

    System.out.println("");
    main1.useMatchesGroup();
}


private void useFindRegex() {
    System.out.println("FindRegex() method written by me:");
    String[] foundByFindRegexMethod;
    foundByFindRegexMethod = FindRegex.findRegex("(\\d+)", "#20 @ 214,215: 217x218");
    for (String s : foundByFindRegexMethod) {
        System.out.println(s);
    }
}


void usePatternSplit() {
    //#1 @ 817,273: 26x26
    String line = "#20 @ 214,215: 217x218";
    System.out.println("Input line:");
    System.out.println(line);

    Pattern delimeter = Pattern.compile("([#@ ,:x])+");
    System.out.println("Using Pattern.split(); Matches:");
    String[] finding = delimeter.split(line);
    for (int i = 0; i < finding.length; i++) {
        System.out.printf("%d:%s\n", i, finding[i]);
    }
}


void useMatcherFind() {
    //#1 @ 817,273: 26x26
    String line = "#20 @ 214,215: 217x218";
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


void useMatchesGroup() {
    //#1 @ 817,273: 26x26
    String line = "#20 @ 214,215: 217x218";
    System.out.println("Input line:");
    System.out.println(line);

    //Pattern p = Pattern.compile("#(\\d+) @ (\\d+),(\\d+):.*");
    Pattern p = Pattern.compile("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$");
    Matcher m = p.matcher(line);
    if (m.matches()) {
        for (int i = 1; i <= m.groupCount(); i++) {
            System.out.printf("m.group(%s): %s\n", i, m.group(i));
        }
    }
}


}
