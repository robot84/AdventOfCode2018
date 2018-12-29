import java.util.*;


public class LinkedListIteratorTest {

public static void main(String[] args) {
    LinkedListIteratorTest scratchTest = new LinkedListIteratorTest();
    scratchTest.startTesting();
}


void removeTest() {
        /*
        .remove() remove last element got by .next().
        so you can get an element by next(), compare it, and them remove if you want
        next use of .next() will be next number than previous .next() before .remove()
         */
    LinkedList<Long> list = new LinkedList<>();
    // values : 0..19 on indexes 0..19
    for (long i = 0; i < 20; i++) {
        list.add(i);
    }
    // score.set(playerNum, score.get(playerNum) + marbleToAdd);
    // score.set(playerNum, score.get(playerNum) + li.next());
    ListIterator<Long> listIterator = list.listIterator();
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println();
    System.out.println("^");
    System.out.print("a=li.next(); a=");
    System.out.println(listIterator.next());
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("  ^");
    System.out.print("a=li.next(); a=");
    System.out.println(listIterator.next());
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("    ^");
    listIterator.remove();
    System.out.println(".remove()");
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("  ^");
    System.out.print("a=li.next(); a=");
    System.out.println(listIterator.next());
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("    ^");
    listIterator.add(88L);
    System.out.println("li.add(88);");
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("       ^");
    System.out.println("Cannot remove() directly after add(). We must use .next() first");
    System.out.print("a=li.next(); a=");
    System.out.println(listIterator.next());
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("         ^");
    listIterator.remove();
    System.out.println(".remove()");
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("       ^");

}


void startTesting() {
    System.out.println("Indexes test.");
    indexesTest();
    System.out.println("\n\nRemove test.");
    removeTest();


}


void indexesTest() {
    LinkedList<Long> list = new LinkedList<>();
    // values : 0..19 on indexes 0..19
    for (long i = 0; i < 20; i++) {
        list.add(i);
    }
    ListIterator<Long> listIterator = list.listIterator();
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex() +
            " hasPrevious(): " + listIterator.hasPrevious());
    System.out.println(listIterator.next());
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.println(listIterator.next());
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.println("V");
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    for (int i = 0; i < 50; i++) {
        System.out.print(" ");
    }
    System.out.println("^");
    while (listIterator.hasNext()) listIterator.next();
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex() +
            "  hasNext():" + listIterator.hasNext());

    list.clear();
    for (long i = 0; i < 20; i++) {
        list.add(i);
    }

    listIterator = list.listIterator(2);
    System.out.println("listIterator = list.listIterator(2);");
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("    ^");

    System.out.println("list.size() = " + list.size());
    listIterator = list.listIterator(list.size());
    System.out.println("listIterator = list.listIterator(list.size());\t list.size()\n " +
            "in the same way as in arrays returns index that is out of bound a list/array. Is above the maxIndex of 1\n");
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("                                                  ^");

    System.out.println("If we want to point to last element of list/array we should use index .size()-1 as in below example:");
    listIterator = list.listIterator(list.size() - 11);
    System.out.println("listIterator = list.listIterator(list.size()-11);");
    System.out.println(" indexes: previous: " + listIterator.previousIndex() + " next: " + listIterator.nextIndex());
    System.out.print(" ");
    displayMarbleList(list, listIterator);
    System.out.println("                                               ^");
}


void start() {
    // System.out.println(this.getClass().getSimpleName().substring(3));
    System.out.println("Starting...");

    //System.out.println(7192000%23);

    LinkedList<Long> a = new LinkedList<>();
    ListIterator<Long> li;

    //0  8  4  9  2 10  5 11  1 12  6 13  3 14  7(15)
    a.add(0L);
    a.add(16L);
    a.add(8L);
    a.add(4L);
    a.add(9L);
    a.add(2L);
    a.add(10L);
    a.add(5L);
    a.add(11L);
    a.add(1L);
    a.add(12L);
    a.add(6L);
    a.add(13L);
    a.add(3L);
    a.add(14L);
    a.add(7L);
    a.add(15L);

    Long marbleToAdd = 17L;
    int marbleToAddAsInteger = Integer.valueOf(marbleToAdd.toString());
    int marbleListSize = a.size();
    li = a.listIterator(2);

    ListIterator newLi = null;
    for (int w = 0; w < 13000; w++) {
        //newLi=null;
        //this.displayMarbleList(a,li);
        //System.out.println("jestem na wartosci XX a moj nastepny indeks to "+li.nextIndex());

        if ((marbleToAdd % 23) != 0) {
            if (addMarble1(a, li, marbleToAdd) != null) {
                // if((newLi=this.addMarble1(a,li,marbleToAdd))!=null) {
                System.out.println("Problem with adding marble " + marbleToAdd);
                //this.displayMarbleList(a,li);
                //li=newLi;
                //System.out.println("LI: "+li.nextIndex());
            }
            marbleListSize++;
            // displayList(playerNum);
        } else {
            if ((removeMarble1(a, li)) != null) {
                System.out.println("Problem with removing marble " + marbleToAdd);
                //this.displayMarbleList(a,li);
                // li=newLi;
            }
            marbleListSize--;
        }
        // score.set(playerNum, score.get(playerNum) + marbleToAdd);
        // score.set(playerNum, score.get(playerNum) + li.next());
        // Verbose.printf("%d %d %d\n", marbleToAdd, li.next(), marbleToAdd + li.next());

    }
    marbleToAdd++;

}


private void sleep(int miliseconds) {
    try {
        Thread.sleep(miliseconds);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


void subs(String s) {
    s.substring(1);
}


<T> void fora(Object[] numbers, int numbersSize) {
    /* sum of values on list */

    for (int i = 0; i < numbersSize; i++) {

    }

}


void displayMarbleList(LinkedList<Long> linkedList, ListIterator listIterator) {
    final int START_OF_LIST = 0;
    ListIterator ldisplay = linkedList.listIterator(START_OF_LIST);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (listIterator.nextIndex() > 0)
        System.out.println("  (" + (listIterator.nextIndex() - 1) + ")  " + linkedList.get((listIterator.nextIndex() - 1)));
    System.out.print("");

}


boolean addMarble(ListIterator listIterator, Long value) {
    if (listIterator.hasNext()) {
        listIterator.next();
    } else {
        return false;
    }
    listIterator.add(value);
    return true;
}


ListIterator<Long> addMarble1(LinkedList<Long> linkedList, ListIterator listIterator, Long value) {
    boolean failureOccured = false;
    if (listIterator.hasNext()) {
        listIterator.next();
    } else {
        failureOccured = true;
        //    System.out.println("Problem with adding marble "+marbleToAdd);
        System.out.println("Problem with adding marble.");
        //this.displayMarbleList(a,li);
        listIterator = linkedList.listIterator(0);
        listIterator.next();
    }
    listIterator.add(value);

    if (failureOccured == true) {
        System.out.println("Fail occured? " + failureOccured);
        return listIterator;
    } else
        return null;
}


boolean removeMarble(LinkedList<Long> linkedList, ListIterator listIterator) {
    for (int c = 0; c < 8; c++) {
        if (listIterator.hasPrevious()) {
            listIterator.previous();
        } else {
            return false;
        }

    }
    listIterator.remove();
    listIterator.next();
    listIterator.next();
    return true;

}


ListIterator<Long> removeMarble1(LinkedList<Long> linkedList, ListIterator listIterator) {
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


void linkedListIteratorTest() {
    LinkedList<Integer> a = new LinkedList<>();
    ListIterator<Integer> li = a.listIterator(18);

    // values : 0..19 on indexes 0..19
    for (int i = 0; i < 20; i++) {
        a.add(i);
    }
    for (Integer i : a) System.out.printf("v:%2d ", i);
    System.out.println();

    System.out.printf("jestem na wartosci: %d a moj nastepny indeks to: %d \n", li.next(), li.nextIndex());


    System.out.printf("\nLI jestem na wartosci: %d a moj nastepny indeks to: %d \n", li.next(), li.nextIndex());
    System.out.println();

    li = a.listIterator(6);
    System.out.printf("LIIII jestem na wartosci: %d a moj nastepny indeks to: %d \n", li.next(), li.nextIndex());

    System.out.println();

    li = a.listIterator(10);

    System.out.println("Get from nextIndex " + (li.nextIndex()) + " is " + a.get((li.nextIndex())));
    System.out.println("Dodaje nowa liczbe bedac pod indeksem 10, a w zasadzie to moj nastepny indeks!\n Indeksy licze od zera.");

    li.add(20);
    System.out.println("li.add(20);");
    System.out.println("Get from nextIndex " + (li.nextIndex()) + " is " + a.get((li.nextIndex())));

    ListIterator<Integer> ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    System.out.println();
    ldisplay = null;

    li.add(25);
    System.out.println("li.add(25);");


    ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (li.nextIndex() > 0) System.out.println("  (" + (li.nextIndex() - 1) + ")  " + a.get((li.nextIndex() - 1)));
    ldisplay = null;

    li.next();
    System.out.println("li.next();");
    li.add(25);
    System.out.println("li.add(25);");

    ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (li.nextIndex() > 0) System.out.println("  (" + (li.nextIndex() - 1) + ")  " + a.get((li.nextIndex() - 1)));
    ldisplay = null;

    li.next();
    System.out.println("li.next();");
    li.remove();
    System.out.println("li.remove();");


/*
cofajac wskaznik i robiac remove, pamietajmy, ze aktualna pozycja, to nie ta, na ktorej dalismy .add(),
   ani nie ta, o jeden mniejsza od wartosci z pod nastepnego wywolania .next()
   Aktualna pozycja, na ktorej zostanie przeprowadzona operacja remove() to pozycja a zreszta sam juz nie kumam
 */
    ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (li.nextIndex() > 0) System.out.println("  (" + (li.nextIndex() - 1) + ")  " + a.get((li.nextIndex() - 1)));
    ldisplay = null;

    System.out.println("System.out.println(li.next());");
    System.out.println(li.next());

    ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (li.nextIndex() > 0) System.out.println("  (" + (li.nextIndex() - 1) + ")  " + a.get((li.nextIndex() - 1)));
    ldisplay = null;

    li.next();
    System.out.println("li.next();");
    li.add(27);
    System.out.println("li.add(27);");

    ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (li.nextIndex() > 0) System.out.println("  (" + (li.nextIndex() - 1) + ")  " + a.get((li.nextIndex() - 1)));
    ldisplay = null;

    System.out.println("for (int c = 0; c < 8; c++) li.previous(); li.remove();");
    for (int c = 0; c < 8; c++) li.previous();
    li.remove();

    ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (li.nextIndex() > 0) System.out.println("  (" + (li.nextIndex() - 1) + ")  " + a.get((li.nextIndex() - 1)));
    ldisplay = null;

    li.next();
    System.out.println("li.next();");
    li.next();
    System.out.println("li.next();");
    li.add(29);
    System.out.println("li.add(29);");

    ldisplay = a.listIterator(0);
    while (ldisplay.hasNext()) System.out.printf("%d ", ldisplay.next());
    if (li.nextIndex() > 0) System.out.println("  (" + (li.nextIndex() - 1) + ")  " + a.get((li.nextIndex() - 1)));
    ldisplay = null;
}

}
