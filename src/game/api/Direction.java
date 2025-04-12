package game.api;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final int dx;
    public final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction fromDelta(int dx, int dy) {
        if (dx == 0 && dy == -1) return UP;
        if (dx == 0 && dy == 1) return DOWN;
        if (dx == -1 && dy == 0) return LEFT;
        if (dx == 1 && dy == 0) return RIGHT;
        throw new IllegalArgumentException("Недопустимые значения dx и dy");
    }
}