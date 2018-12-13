package ovh.robot84.advent2018;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class HelperMethods {


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


static void printResult(String result) {
    System.out.println("\nResult: " + result);
}


static void printResult(int result) {
    System.out.println("\nResult: " + result);
}


public static Integer[] findAllNumbersInLine(String line) {
    String[] foundByFindRegexMethod;
    foundByFindRegexMethod = FindRegex.findRegex("(\\d+)", line);
    Integer[] numbers = new Integer[foundByFindRegexMethod.length];
    for (int i = 0; i < numbers.length; i++) {
        numbers[i] = Integer.valueOf(foundByFindRegexMethod[i]);
    }
    return numbers;
}


static boolean getInputFileFromWWW(int day, String path) {
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


static CookieHandler buildCookieFromFinalValues() {
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


static void useBuiltCookieForAllSites(CookieHandler cookieHandler) {
    CookieHandler.setDefault(cookieHandler);
}


public static Integer maxInArray(ArrayList<Integer> someData) {
    Integer max = 0;
    for (int i = 0; i < someData.size(); i++) {
        if (someData.get(i) > max) max = someData.get(i);
    }
    return max;
}


public static Integer minInArray(ArrayList<Integer> someData) {
    /* min value on list */
    Integer min = someData.get(0);
    for (int i = 0; i < someData.size(); i++) {
        if (someData.get(i) < min) min = someData.get(i);
    }
    return min;
}


public Integer sumInArray(ArrayList<Integer> someData) {
    /* sum of values on list */
    int sum = 0;
    for (int i = 1; i <= someData.size(); i++) {
        sum += someData.get(i);
    }
    return sum;
}


static String getCurrentTime() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    return (dateFormat.format(date));
}


static int getElapsedTimeInMiliSeconds(Date startDate) {
    Date endDate = new Date();
    int elapsedTime = (int) (endDate.getTime() - startDate.getTime());
    return ((elapsedTime));
}


static int getElapsedTimeInSeconds(Date startDate) {
    return getElapsedTimeInMiliSeconds(startDate) / 1_000;
}


static String getElapsedTimeAsHHmmss(Date startDate) {
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
