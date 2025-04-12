package game.api;

import game.map.Field;

public interface Movable extends GameObject {
    // Перемещение объекта
    void move(int dx, int dy, Field field);

    // Изменение направления
    void turn(Direction direction);

    // Получение текущей позиции
    Position getPosition();

    // Получение текущего направления
    Direction getDirection();

    // Символ для отрисовки в консоли
    String getColoredSymbol();
}