package game.core.engine;

import game.map.Field;
import game.map.TerrainType;

import java.util.Random;

import static game.map.TerrainType.GRASS;
import static game.map.TerrainType.ROAD;

public class Generation {
    private static final Random random = new Random();

    public Field generateField(int width, int height) {
        Field field = new Field(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TerrainType[] types = TerrainType.values();
                if ((x == y) && x != 0 && x != 1 && x != 8 && x != 9) {
                    field.getCell(x, y).setTerrainType(ROAD);
                } else {
                    TerrainType type = types[random.nextInt(types.length)];
                    if(type != ROAD) {
                        field.getCell(x, y).setTerrainType(type);
                    }
                    else {
                        field.getCell(x, y).setTerrainType(GRASS);
                    }
                }
            }
        }
        return field;
    }
}