package game.map;

import game.api.FieldObject;
import game.api.Position;
import game.model.building.onmap.Castle;
import game.model.player.ComputerPlayer;
import game.model.player.Player;

public class Field {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Cell();
            }
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    public void moveObject(FieldObject obj, int fromX, int fromY, int toX, int toY) {
        Cell source = getCell(fromX, fromY);
        Cell destination = getCell(toX, toY);

        if (source != null && destination != null) {
            source.removeObject(obj);
            destination.addObject(obj);
        }
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            // Верхняя граница строки
            for (int x = 0; x < width; x++) {
                System.out.print("+---");
            }
            System.out.println("+");

            // Содержимое клеток
            for (int x = 0; x < width; x++) {
                System.out.print("|" + grid[x][y].getDisplaySymbol());
            }
            System.out.println("|");
        }

        // Нижняя граница
        for (int x = 0; x < width; x++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    public ComputerPlayer getComputerPlayerAt(Position pos) {
        Cell cell = getCell(pos.x(), pos.y());
        if (cell != null) {
            for (FieldObject obj : cell.getObjects()) {
                if (obj instanceof ComputerPlayer) {
                    return (ComputerPlayer) obj;
                }
            }
        }
        return null;
    }

    public Castle getCastleAt(Position pos) {
        Cell cell = getCell(pos.x(), pos.y());
        if (cell != null) {
            for (FieldObject obj : cell.getObjects()) {
                if (obj instanceof Castle) {
                    return (Castle) obj;
                }
            }
        }
        return null;
    }

    public void removePlayer(Player player) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = grid[x][y];
                if (cell.contains(player)) {
                    cell.removeObject(player);
                    return;
                }
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
}