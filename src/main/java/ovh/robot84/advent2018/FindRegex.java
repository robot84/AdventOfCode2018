package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindRegex {

    /**
     * @param regex      - regular expression pattern in format that is acceptable by java.util.regex.Pattern
     * @param parsedLine - text to parse for regex pattern matching
     * @return String[] table with all matched occurencies of pattern regex in parsedLine
     */
    static String[] findRegex(String regex, String parsedLine) {
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
