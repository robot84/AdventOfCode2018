package ovh.robot84.advent2018.helpers;

public class Elf {
/**
 * Elf is a class representing named rectangle.
 * elfID is a name of the rectangle
 */
int elfID;
int fromLeft;
int fromTop;
int width;
int height;


public Elf(int elfID, int fromLeft, int fromTop, int width, int height) {
    this.elfID = elfID;
    this.fromLeft = fromLeft;
    this.fromTop = fromTop;
    this.width = width;
    this.height = height;
}


public Elf(Integer[] numbers) {
    this(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]);
}


public int getElfID() {
    return elfID;
}
}
