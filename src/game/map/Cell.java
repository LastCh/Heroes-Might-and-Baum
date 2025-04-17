package game.map;

import game.api.FieldObject;

import java.util.List;
import java.util.ArrayList;

public class Cell {
    private final List<FieldObject> objects = new ArrayList<>();
    private TerrainType terrainType = TerrainType.GRASS;

    public boolean contains(FieldObject obj) {
        return objects.contains(obj);
    }

    public void addObject(FieldObject obj) {
        objects.add(obj);
        objects.sort((a, b) -> Integer.compare(b.getPriority(), a.getPriority()));
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void removeObject(FieldObject obj) {
        objects.remove(obj);
    }

    public List<FieldObject> getObjects() {
        return new ArrayList<>(objects);
    }

    public String getDisplaySymbol() {
        String symbol;
        if (!objects.isEmpty()) {
            symbol = objects.get(0).getColoredSymbol();
        } else {
            symbol = terrainType != null
                    ? terrainType.getColoredBlock()
                    : "·"; // Символ пустой клетки
        }
        return String.format("%-3s", symbol); // Фиксированная ширина в 3 символа
    }

    /**
     * Проверяет, есть ли на клетке объект определенного типа.
     *
     * @param type класс объекта, который нужно проверить.
     * @return true, если объект найден, иначе false.
     */
    public boolean hasObjectOfType(Class<?> type) {
        return objects.stream().anyMatch(type::isInstance);
    }
}