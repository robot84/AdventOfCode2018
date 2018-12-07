package ovh.robot84.advent2018;

import java.awt.*;

public class ManhattanDistance {

    public static int compute(Point p1, Point p2) {
        int x0 = p1.x, x1 = p2.x, y0 = p1.y, y1 = p2.y;
        int distance = Math.abs(x1 - x0) + Math.abs(y1 - y0);
        return distance;
    }
}
