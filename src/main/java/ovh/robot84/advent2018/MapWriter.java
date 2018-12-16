package ovh.robot84.advent2018;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class MapWriter {
BufferedWriter fileWriter;
String mapFilePath;


MapWriter(String path) {
    mapFilePath = path;
}


void openMap() {
    if (mapFilePath != null) openMap(mapFilePath);
}


void openMap(String mapFilePath) {
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
        System.out.println("Exception: Cannot write to map file " + this.mapFilePath);
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
                        mapa[y][x] + "' to map file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to map file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


void writeMap(Boolean[][] mapa, String mapHeaderText) {
    int mapXsize = mapa[0].length, mapYsize = mapa.length;
    try {
        fileWriter.write("\n" + mapHeaderText + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to map file " + this.mapFilePath);
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
                        mapa[y][x] + "' to map file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to map file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


@Deprecated
void writeMap(boolean[][] mapa, int mapXsize, int mapYsize, String mapHeaderText) {
    try {
        fileWriter.write("\n" + mapHeaderText + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to map file " + this.mapFilePath);
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
                        mapa[y][x] + "' to map file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to map file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


void writeMap(boolean[][] mapa, String mapHeaderText) {
    int mapXsize = mapa[0].length, mapYsize = mapa.length;
    try {
        fileWriter.write("\n" + mapHeaderText + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to map file " + this.mapFilePath);
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
                        mapa[y][x] + "' to map file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to map file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


@Deprecated
void writeMap(Integer[][] mapa, int mapXsize, int mapYsize, String mapHeaderText) {
    try {
        fileWriter.write("\n" + mapHeaderText + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to map file " + this.mapFilePath);
        e.printStackTrace();
    }
    for (int y = 0; y < mapXsize; y++) {
        for (int x = 0; x < mapYsize; x++) {
            try {
                fileWriter.write((mapa[y][x] + '.'));
            } catch (IOException e) {
                System.out.println("Exception: Cannot write value '" +
                        mapa[y][x] + "' to map file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to map file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


void writeMap(Integer[][] mapa, String mapHeaderText) {
    int mapXsize = mapa[0].length, mapYsize = mapa.length;
    try {
        fileWriter.write("\n" + mapHeaderText + "\n");
    } catch (IOException e) {
        System.out.println("Exception: Cannot write to map file " + this.mapFilePath);
        e.printStackTrace();
    }
    for (int y = 0; y < mapXsize; y++) {
        for (int x = 0; x < mapYsize; x++) {
            try {
                fileWriter.write((mapa[y][x] + '.'));
            } catch (IOException e) {
                System.out.println("Exception: Cannot write value '" +
                        mapa[y][x] + "' to map file " + this.mapFilePath);
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Exception: Cannot write new line character to map file " + this.mapFilePath);
            e.printStackTrace();
        }
    }
}


}
