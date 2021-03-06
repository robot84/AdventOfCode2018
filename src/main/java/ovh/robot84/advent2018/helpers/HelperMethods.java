package ovh.robot84.advent2018.helpers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

public class HelperMethods {

public static void parsingProgramArguments(String[] args) {
    parsingVerbosityLevel(args);
}


public static void parsingProgramArguments(String[] args, String input_file, Object clas) {
    parsingVerbosityLevel(args);
    if (args.length > 0 && args[0].equals("--create-input-file"))
        HelperMethods.getInputFileFromWWW(Integer.valueOf(clas.getClass().getSimpleName().substring(3)), input_file);

}


private static void parsingVerbosityLevel(String[] args) {
    if (args.length > 0 && args[0].equals("-v")) Verbose.enableVerbose();
    if (args.length > 0 && args[0].equals("-vv")) Verbose.enableVerbose(2);
    if (args.length > 0 && args[0].equals("-vvv")) Verbose.enableVerbose(3);
}

public static void printResult(String result) {
    System.out.println("\nResult: " + result);
}


public static void printResult(int result) {
    System.out.println("\nResult: " + result);
}


public static boolean getInputFileFromWWW(int day, String path) {
    URL url = null;
    System.out.println("Connecting to https://adventofcode2018.com...");
    try {
        useBuiltCookieForAllSites(buildCookieFromFinalValues());
        url = new URL("https://adventofcode.com/2018/day/" + day + "/input");
        File file = (new File(path));
        //file.createNewFile();
        FileUtils.copyURLToFile(url, file);
        System.out.println("Connected.");
        System.out.println("Fetching data...");
        System.out.println("Copying data to file...");
        System.out.println("Done.");
    } catch (
            MalformedURLException e) {
        System.out.println("Exception occured: Something went wrong with connecting to HTTP server.");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("Exception occured: Something went wrong during operation on file " + path);
        e.printStackTrace();
    } finally {
        return true;
    }
}


public static CookieHandler buildCookieFromFinalValues() {
    CookieHandler cookieHandler = new CookieHandler() {

        @Override
        public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException {
            System.out.println("URI: " + uri.toString());
            ArrayList<String> cookie = new ArrayList<>();
            ArrayList<String> userAgent = new ArrayList<>();
            userAgent.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
            cookie.add("_ga=GA1.2.2125363643.1540726955");
            cookie.add("_gid=GA1.2.594666710.1544183716");
            cookie.add("_gat=1");
            cookie.add("session=53616c7465645f5f18ff3e7ac561be838a9abda496d1188bb46909325f0051c805b51381a29f01165026985edce697df");
            HashMap<String, List<String>> newHeader = new HashMap<>();
            newHeader.putAll(requestHeaders);
            newHeader.remove("User-Agent");
            newHeader.put("User-Agent", userAgent);
            newHeader.put("cookie", cookie);
            System.out.println(newHeader.size() + " sent: " + newHeader.toString());
            return newHeader;
        }


        @Override
        public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {
            System.out.println(responseHeaders.size() + " received: " + responseHeaders.toString());
        }
    };
    return cookieHandler;
}


public static void useBuiltCookieForAllSites(CookieHandler cookieHandler) {
    CookieHandler.setDefault(cookieHandler);
}



public static String getCurrentTime() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    return (dateFormat.format(date));
}


public static int getElapsedTimeInMiliSeconds(Date startDate) {
    Date endDate = new Date();
    int elapsedTime = (int) (endDate.getTime() - startDate.getTime());
    return ((elapsedTime));
}


public static int getElapsedTimeInSeconds(Date startDate) {
    return getElapsedTimeInMiliSeconds(startDate) / 1_000;
}


@Deprecated
public static Integer maxInArray(ArrayList<Integer> someData) {
    Integer max = 0;
    for (int i = 0; i < someData.size(); i++) {
        if (someData.get(i) > max) max = someData.get(i);
    }
    return max;
}


@Deprecated
public static Integer minInArray(ArrayList<Integer> someData) {
    /* min value on list */
    Integer min = someData.get(0);
    for (int i = 0; i < someData.size(); i++) {
        if (someData.get(i) < min) min = someData.get(i);
    }
    return min;
}


public static void printPatternMatchedGroups(Matcher m) {
    for (int i = 1; i <= m.groupCount(); i++) {
        Verbose.setVerboseLevelForNextPrint(3);
        Verbose.printf("m.group(%s): %s\n", i, m.group(i));
    }
}

public Integer sumInArray(ArrayList<Integer> someData) {
    /* sum of values on list */
    int sum = 0;
    for (int i = 1; i <= someData.size(); i++) {
        sum += someData.get(i);
    }
    return sum;
}


public static String getElapsedTimeAsHHmmss(Date startDate) {
    int time = getElapsedTimeInSeconds(startDate);
    int hh, mm, ss;
    String hhStr, mmStr, ssStr;

    ss = time % 60;
    mm = (time - ss) % 60;
    hh = (time - ss - mm * 60) % 24;

    hhStr = hh < 10 ? "0" + hh : Integer.toString(hh);
    mmStr = mm < 10 ? "0" + mm : Integer.toString(mm);
    ssStr = ss < 10 ? "0" + ss : Integer.toString(ss);

    return hhStr + ":" + mmStr + ":" + ssStr;
}


public void initializeCollection(Collection collection, int collectionSize) {
    for (int i = 0; i < collectionSize; i++) {
        collection.add(0);
    }
}


public static void initializeCharKeyHashMap(HashMap hashMap, int initializer) {
    final int charactersInAlphabet = 26;
    final char firstLetterInAlphabet = 'a';

    for (int i = 0; i < charactersInAlphabet; i++) {
        hashMap.put((char) (firstLetterInAlphabet + i), initializer);
        System.out.print(hashMap.get((char) (firstLetterInAlphabet + i)));
    }
}


public static void printCharKeyHashMap(HashMap hashMap) {
    final int charactersInAlphabet = 26;
    final char firstLetterInAlphabet = 'a';
    for (int i = 0; i < charactersInAlphabet; i++) {
        System.out.print(hashMap.get((char) (firstLetterInAlphabet + i)));
    }
}


public static void sleep(int miliseconds) {
    try {
        Thread.sleep(miliseconds);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
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
