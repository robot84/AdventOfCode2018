package ovh.robot84.advent2018;

/**
 * Fabric is an 2D array of fields.
 * Every field at the begining has integer value zero.
 * You can add Elfs to fabric, and you can get info how many Elfs
 * share particular fabric field.
 * You can get also which elf, if any, not share any of its field with others.
 */
public class Fabric {
    final static int FABRIC_MAX_X = 10_000;
    final static int FABRIC_MAX_Y = 10_000;

    int[][] fabric = new int[FABRIC_MAX_Y][FABRIC_MAX_X];

    void addToFabric(int fromLeft, int fromTop, int width, int height) {
        for (int y = fromTop; y < fromTop + height; y++) {
            for (int x = fromLeft; x < fromLeft + width; x++) {
                this.fabric[y][x]++;
            }
        }
    }

    void addToFabric(Elf elf) {
        addToFabric(elf.fromLeft, elf.fromTop, elf.width, elf.height);
    }

    boolean isElfOverlappingWithAnotherInFabric(Elf elf) {
        for (int y = elf.fromTop; y < (elf.fromTop + elf.height); y++) {
            for (int x = elf.fromLeft; x < (elf.fromLeft + elf.width); x++) {
                if (this.fabric[y][x] > 1) return true;
            }
        }
        return false;
    }

    int numOfFabricFieldWithTwoOrMoreElfs() {
        int count = 0;
        for (int y = 0; y < FABRIC_MAX_Y; y++) {
            for (int x = 0; x < FABRIC_MAX_X; x++) {
                if (this.fabric[y][x] > 1) count++;
            }
        }
        return count;
    }
}