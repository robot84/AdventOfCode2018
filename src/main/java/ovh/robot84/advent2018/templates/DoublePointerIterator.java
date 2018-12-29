package ovh.robot84.advent2018.templates;

import java.util.LinkedList;
import java.util.ListIterator;

public class DoublePointerIterator<E> implements ListIterator<E> {
protected transient int modCount = 0;
LinkedList<E> list;
transient Node<E> first;
transient Node<E> last;
private DoublePointerIterator.Node<E> lastReturned;
private DoublePointerIterator.Node<E> next;
private int nextIndex;
private int expectedModCount = modCount;


DoublePointerIterator(ListIterator<E> listIterator, int index) {
//    this.list=list;
    //  this.first = list;
    // assert isPositionIndex(index);
    next = (index == list.size()) ? null : node(index);
    nextIndex = index;
}


Node<E> node(int index) {
    // assert isElementIndex(index);
    int size = list.size();
    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        Node<E> x = last;
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
public E next() {
    return null;
}


@Override
public boolean hasPrevious() {
    return false;
}


@Override
public E previous() {
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
public void set(E e) {

}


@Override
public void add(E e) {

}


private static class Node<E> {
    E item;
    DoublePointerIterator.Node<E> next;
    DoublePointerIterator.Node<E> prev;


    Node(DoublePointerIterator.Node<E> prev, E element, DoublePointerIterator.Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
}
