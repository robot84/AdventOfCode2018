package ovh.robot84.advent2018;

import javafx.geometry.Point3D;

import java.awt.*;

public class ManhattanDistance {

public static int compute(Point p1, Point p2) {
    int x0 = p1.x, x1 = p2.x, y0 = p1.y, y1 = p2.y;
    int distance = Math.abs(x1 - x0) + Math.abs(y1 - y0);
    return distance;
}


public static int compute2DDistance(Point p1, Point p2) {
    return compute(p1, p2);

}


public static int compute3DDistance(Point3D p1, Point3D p2) {
    int x0 = (int) p1.getX(), x1 = (int) p2.getX();
    int y0 = (int) p1.getY(), y1 = (int) p2.getY();
    int z0 = (int) p1.getZ(), z1 = (int) p2.getZ();
    int distance = Math.abs(x1 - x0) + Math.abs(y1 - y0) + Math.abs(z1 - z0);
    return distance;
}


public static void getPoints3DWithinManhattanDistance(Point3D coords, int range) {
    int x0 = (int) coords.getX();
    int y0 = (int) coords.getY();
    int z0 = (int) coords.getZ();
}


private static void getPoint3DWithinManhattanDistance(Point3D coords, int range, int mapSizeX, int mapSizeY) {

    for (int xy = 0; xy < mapSizeX * mapSizeY; xy++) {


    }
}


public static int compute4DDistance(Point4D p1, Point4D p2) {
    int x0 = (int) p1.getX(), x1 = (int) p2.getX();
    int y0 = (int) p1.getY(), y1 = (int) p2.getY();
    int z0 = (int) p1.getZ(), z1 = (int) p2.getZ();
    int t0 = (int) p1.getT(), t1 = (int) p2.getT();
    int distance = Math.abs(x1 - x0) + Math.abs(y1 - y0) + Math.abs(z1 - z0) + Math.abs(t1 - t0);
    return distance;
}


}
