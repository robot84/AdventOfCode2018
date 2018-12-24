package ovh.robot84.advent2018;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.function.Consumer;

public class DoublePointerIterator implements ListIterator<Long> {
protected transient int modCount = 0;
LinkedList<Long> list;
transient Node<Long> first;
transient Node<Long> last;
private DoublePointerIterator.Node<Long> lastReturned;
private DoublePointerIterator.Node<Long> next;
private int nextIndex;
private int expectedModCount = modCount;


DoublePointerIterator(ListIterator<Long> listIterator, int index) {
//    this.list=list;
    this.first = list;
    // assert isPositionIndex(index);
    next = (index == list.size()) ? null : node(index);
    nextIndex = index;
}


Node<Long> node(int index) {
    // assert isElementIndex(index);
    int size = list.size();
    if (index < (size >> 1)) {
        Node<Long> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        Node<Long> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}


@Override
public boolean hasNext() {
    return false;
}


@Override
public Long next() {
    return null;
}


@Override
public boolean hasPrevious() {
    return false;
}


@Override
public Long previous() {
    return null;
}


@Override
public int nextIndex() {
    return 0;
}


@Override
public int previousIndex() {
    return 0;
}


@Override
public void remove() {

}


@Override
public void set(Long e) {

}


@Override
public void add(Long e) {

}


private static class Node<Long> {
    Long item;
    DoublePointerIterator.Node<Long> next;
    DoublePointerIterator.Node<Long> prev;


    Node(DoublePointerIterator.Node<Long> prev, Long element, DoublePointerIterator.Node<Long> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
}
