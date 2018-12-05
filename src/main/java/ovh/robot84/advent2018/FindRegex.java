package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindRegex {

    /**
     * @param regex      - regular expression pattern in format that is acceptable by java.util.regex.Pattern
     * @param parsedLine - text to parse for regex pattern matching
     * @return String[] table with all matched occurencies of pattern regex in parsedLine
     * If nothing found, you are able to check this state by checking if returned var.length==0
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

    /**
     * Search for all integer numbers in String given as argument
     * Warning! String must not have float numbers.
     * Otherwise they will be treated as intNumber<dot>nextIntNumber
     *
     * @param line - String when we search for numbers
     * @return Array with all integer numbers found.
     */
    static Integer[] findAllNumbers(String line) {
        String[] foundByFindRegexMethod;
        foundByFindRegexMethod = FindRegex.findRegex("(\\d+)", line);
        Integer[] numbers = new Integer[foundByFindRegexMethod.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.valueOf(foundByFindRegexMethod[i]);
        }
        return numbers;
    }

}
