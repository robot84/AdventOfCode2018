package ovh.robot84.advent2018;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapWriter {
private BufferedWriter fileWriter;
private String mapFilePath;


MapWriter(String path) {
    mapFilePath = path;
}


void openMap() {
    if (mapFilePath != null) openMap(mapFilePath);
}


public void openMap(String mapFilePath) {
    this.mapFilePath = mapFilePath;
    fileWriter = null;
    try {
        fileWriter = new BufferedWriter(new FileWriter(this.mapFilePath));
    } catch (IOException e) {
        e.printStackTrace();
    }
}


void closeMap() {

    try {
        fileWriter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@Deprecated
void writeMap(Boolean[][] mapa, int mapXsize, int mapYsize, String mapHeaderText) {
    try {
        fileWriter.write("\n" + mapHeaderText + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to printableMap file " + this.mapFilePath);
        e.printStackTrace();
    }
    for (int y = 0; y < mapYsize; y++) {
        for (int x = 0; x < mapXsize; x++) {
            try {
                if (!mapa[y][x])
                    fileWriter.write(('.'));
                else {
                    fileWriter.write(('X'));
                }
            } catch (IOException e) {
                System.out.println("Exception: Cannot write value '" +
                        mapa[y][x] + "' to printableMap file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to printableMap file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


void writeMap(Boolean[][] mapa, String mapHeaderText) {
    final int mapXsize = mapa[0].length, mapYsize = mapa.length;
    writeMap(mapa, mapXsize, mapYsize, mapHeaderText);
}




@Deprecated
void writeMap(Integer[][] mapa, int mapXsize, int mapYsize, String mapHeaderText) {
    try {
        fileWriter.write("\n" + mapHeaderText + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to printableMap file " + this.mapFilePath);
        e.printStackTrace();
    }
    for (int y = 0; y < mapXsize; y++) {
        for (int x = 0; x < mapYsize; x++) {
            try {
                fileWriter.write((mapa[y][x] + '.'));
            } catch (IOException e) {
                System.out.println("Exception: Cannot write value '" +
                        mapa[y][x] + "' to printableMap file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to printableMap file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


void writeMap(Integer[][] mapa, String mapHeaderText) {
    int mapXsize = mapa[0].length, mapYsize = mapa.length;
    writeMap(mapa, mapXsize, mapYsize, mapHeaderText);
}


boolean[][] shrinkTwice(boolean[][] map, int threshold) {
    final int SHRINK_FACTOR = 2;
    int density;
    final int xSize = map[0].length;
    final int ySize = map.length;
    boolean[][] shrinkedMap = new boolean[ySize / SHRINK_FACTOR][xSize / SHRINK_FACTOR];
    System.out.printf("Input printableMap xSize %d, ySize %d\n", xSize, ySize);
    System.out.printf("Shrinked printableMap xSize %d, ySize %d\n", xSize / 2, ySize / 2);
    if (threshold < 1) threshold = 1;
    else if (threshold > 4) threshold = 4;

    for (int y = 0; y < ySize / SHRINK_FACTOR; y++) {
        for (int x = 0; x < xSize / SHRINK_FACTOR; x++) {
            density = 0;
            if (map[y * 2][x * 2]) density++;
            if (map[y * 2 + 1][x * 2]) density++;
            if (map[y * 2][x * 2 + 1]) density++;
            if (map[y * 2 + 1][x * 2 + 1]) density++;
            shrinkedMap[y][x] = density >= threshold;
        }
    }
    return shrinkedMap;
}


boolean[][] shrinkTwice(boolean[][] map) {
    return shrinkTwice(map, 2);
}


}
