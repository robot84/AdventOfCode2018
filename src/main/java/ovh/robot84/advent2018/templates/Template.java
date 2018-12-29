package ovh.robot84.advent2018.templates;

import ovh.robot84.advent2018.helpers.MyReader;
import ovh.robot84.advent2018.helpers.Verbose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class implements an application that
 * resolves AdventOfCode2018 task for particular day,
 * as class name says
 *
 * @author Robert Ząbkiewicz
 * @version 0.1.0
 * @since 2018-12-24
 *
 * If you don't have or don't want to use Verbose class, you can
 * change all Verbose.printf() to System.out.printf()
 * and delete calls to other Verbose methods.
 *
 * WARNING:
 * To compile this class:
 * Please change MyClass to any class you want to us.
 */

public class Template {

private final static String INPUT_FILE1 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input1.txt";
private final static String INPUT_FILE2 = "C:\\Users\\qtcj47\\IdeaProjects\\AdventOfCode2018\\" +
        "src\\main\\resources\\day08input2.txt";
private final static int ARRAY_MAX_X = 2_000;
private final static int ARRAY_MAX_Y = 2_000;
List<MyClass> points4D = new ArrayList<MyClass>();
private HashMap<Character, Integer> hm1 = new HashMap();
private ArrayList<String> strings = new ArrayList<>();
private ArrayList<Integer> integers = new ArrayList<Integer>();
private ArrayList<Long> longs = new ArrayList<Long>();
private MyReader myReader = new MyReader();


private Template(String input_file) {
    myReader.open_file(input_file);
}


public static void main(String[] args) {
    Template dayStar1 = new Template(INPUT_FILE1);
    dayStar1.parsingProgramArguments(args);
    //Verbose.mute();
    dayStar1.star1start();
}


private void star1start() {
    String line = null;

    /*        Read input         */
    while ((line = myReader.get_line()) != null) {
        Verbose.println("Line: " + line);
            /*while ((c = myReader.read()) != -1) {
                Verbose.print("!" + (char) c);
            */

        /*
        Parse input
         */

         /*  // look out when input has [ ] symbols. Quote them! Then in trouble start with:
           //Pattern p = Pattern.compile("\\s*(.*)\\s*");
           Pattern p = Pattern.compile("^#\\s*(\\d+)\\s*@\\s*(\\d+),(\\d+):\\s*(\\d+)x(\\d+)$");
            MyClass objectOfMyClass;
        List<Integer> fieldOfObjectOfMyClass=new ArrayList<>();
        Pattern p = Pattern.compile("(-?\\d+)\\s*\\p{Punct}(-?\\d+)\\p{Punct}(-?\\d+)\\s*\\p{Punct}(-?\\d+)");
            Matcher m = p.matcher(line);
            if (m.matches()) {
            Verbose.print("Matched ");
                for (int i = 1; i <= m.groupCount(); i++) {
                    Verbose.printf("m.group(%s): \"%s\" \t", i, m.group(i));
                    fieldOfObjectOfMyClass.add(Integer.valueOf(m.group(i)));
                }
                Verbose.println("");
                objectOfMyClass=new objectOfMyClass(fieldOfObjectOfMyClass);
                listOfObjectsOfMyClass.add(objectOfMyClass);
            }
    for(MyClass objectOfMyClass:listOfObjectsOfMyClass) Verbose.printf("::: %d %d %d %d\n",objectOfMyClass.getX(),objectOfMyClass.getY(),objectOfMyClass.getZ(),objectOfMyClass.getT());
    for(MyClass objectOfMyClass:listOfObjectsOfMyClass) Verbose.printf("::: %s\n",objectOfMyClass.toString());
            }*/


/*
            Pattern pa = Pattern.compile("(\\d+)");
            Matcher ma = pa.matcher(line);
            System.out.println("Using Matcher.Find();");
            while (ma.find()) {
                Verbose.print("m.find(): " + ma.group() + " ");
                Verbose.printf("Start index: %d, end index: %d\n", ma.start(), ma.end());
            }

            Pattern delimeter = Pattern.compile("([#@ ,:x])+");
            Verbose.print("Using Pattern.split(); Matches:");
            String[] finding = delimeter.split(line);
            for (int i = 0; i < finding.length; i++) {
                Verbose.printf("%d:%s\n", i, finding[i]);
            }
*/
    }
}


}
