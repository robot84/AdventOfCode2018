package ovh.robot84.advent2018.helpers;

import java.util.List;

public class Point4D {
private List<Integer> coordinates;
private boolean isInConstelation;
private int id;


public Point4D(List<Integer> coordinates, int id) {
    this.coordinates = coordinates;
    this.id = id;

}


public Point4D(List<Integer> coordinates) {
    this.coordinates = coordinates;

}


public boolean isInConstelation() {
    return this.isInConstelation;
}


public void addToConstelation() {
    this.isInConstelation = true;
}


public int getX() {
    return this.coordinates.get(0);
}


public int getY() {
    return this.coordinates.get(1);
}


public int getZ() {
    return this.coordinates.get(2);
}


public int getT() {
    return this.coordinates.get(3);
}


@Override
public String toString() {
    return String.format("[ID:%d](%d,%d,%d,%d)", getID(), getX(), getY(), getZ(), getT());
}


public int getID() {
    return id;
}

}
