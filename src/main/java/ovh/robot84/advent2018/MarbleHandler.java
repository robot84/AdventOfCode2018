package ovh.robot84.advent2018;

import java.util.LinkedList;
import java.util.ListIterator;

public class MarbleHandler {


static boolean addMarble0(ListIterator listIterator, Integer value) {
    if (listIterator.hasNext()) {
        listIterator.next();
    } else {
        return false;
    }
    listIterator.add(value);
    return true;
}


ListIterator<Integer> addMarble1(LinkedList<Long> linkedList, ListIterator listIterator, Integer value) {
    boolean failureOccured = false;
    if (listIterator.hasNext()) {
        listIterator.next();
    } else {
        failureOccured = true;
        //    System.out.println("Problem with adding marble "+marbleToAdd);
        System.out.println("Problem with adding marble .");
        //this.displayMarbleList(a,li);
        listIterator = linkedList.listIterator(0);
        this.addMarble1(linkedList, listIterator, value);
        //listIterator.next(); ????
    }
    listIterator.add(value);

    if (failureOccured == true)
        return listIterator;
    else
        return null;
}


static boolean goToPrevious(LinkedList<Integer> linkedList, ListIterator listIterator) {
    if (listIterator.hasPrevious()) {
        listIterator.previous();
    } else {
        return false;
    }
    return true;
}


static boolean goToNext(LinkedList<Integer> linkedList, ListIterator listIterator) {
    if (listIterator.hasNext()) {
        listIterator.next();
    } else {
        return false;
    }
    return true;
}


static boolean next(ListIterator listIterator, int steps) {
    /* if something goes wrong we return to base position */
    int c = 0;
    for (c = 0; c < steps; c++) {
        if (listIterator.hasNext())
            listIterator.next();
        else {
            for (int d = 0; c > d; d++) {
                listIterator.previous();
            }
            return false;
        }
    }
    return true;
}


boolean previous(ListIterator listIterator, int steps) {
    /* if something goes wrong we return to base position */
    int c = 0;
    for (c = 0; c < steps; c++) {
        if (!previous(listIterator)) {
            for (int d = 0; c > d; d++) {
                listIterator.next();
            }
            return false;
        }
    }
    return true;
}


boolean previous(ListIterator listIterator) {
    if (listIterator.hasPrevious())
        listIterator.previous();
    else {
        return false;
    }
    return true;
}


ListIterator<Integer> removeMarble1(LinkedList<Integer> linkedList, ListIterator listIterator) {
    boolean failureOccured = false;
    for (int stepNum = 0; stepNum < 8; stepNum++) {
        if (listIterator.hasPrevious()) {
            listIterator.previous();
        } else {
            failureOccured = true;
            //System.out.println("Problem with removing marble " + marbleToAdd);
            //System.out.println("Problem with removing marble ");
            this.displayMarbleList(linkedList, listIterator);
            System.out.println("--->Problem with li.previos() iteration " + stepNum);
            listIterator = linkedList.listIterator(linkedList.size());
        }
    }
    listIterator.remove();
    if (listIterator.hasNext())
        listIterator.next();
    else {
        failureOccured = true;
        System.out.println("--->Problem with li.next()");
        listIterator = linkedList.listIterator(0);
    }
    listIterator.next();

    if (failureOccured == true)
        return listIterator;
    else
        return null;
}


static void displayMarbleList(LinkedList<Integer> linkedList, ListIterator listIterator) {
    final int START_OF_LIST = 0;
    ListIterator ldisplay = linkedList.listIterator(START_OF_LIST);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (listIterator.nextIndex() > 0)
        System.out.print("  (" + (listIterator.nextIndex() - 1) + ")  " + linkedList.get((listIterator.nextIndex() - 1)));
    System.out.println();
}


static boolean addMarble(ListIterator listIterator, int value) {
    if (listIterator.hasNext()) {
        listIterator.next();
    } else {
        return false;
    }
    listIterator.add(value);
    return true;
}


static boolean removeMarble(LinkedList<Integer> linkedList, ListIterator listIterator) {
    int c = 0;
    for (; c < 8; c++) {
        if (listIterator.hasPrevious()) {
            listIterator.previous();
        } else {
            for (int d = 0; c > d; d++) {
                listIterator.next();
            }
            System.out.println("Cannot go to previous position. c=" + c);
            return false;
        }
    }
    if (listIterator.hasPrevious()) listIterator.remove();
    else {
        System.out.println("Cannot do .remove because hasPrevious() failed :(" +
                "This case was not tested. I don;t know is ok or not.");
        return false;
    }
    if (listIterator.hasNext()) listIterator.next();
    else {
        System.out.println("hasNext() failed");
        return false;
    }
    return true;
}

}
