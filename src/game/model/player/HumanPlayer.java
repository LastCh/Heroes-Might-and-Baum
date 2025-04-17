package game.model.player;

import game.api.Direction;
import game.api.Position;
import game.map.Field;
import game.model.building.onmap.Castle;


public class HumanPlayer extends Player {
    private static final String COLOR = "\u001B[34m";

    public HumanPlayer(Position startPosition, int points, Castle castle, int gold) {
        super(startPosition, Direction.UP, COLOR, castle, 10, points, gold);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Игрок взаимодействует с другим игроком.");
        // Логика взаимодействия
    }

    @Override
    public void move(int dx, int dy, Field field) {
        if (movementPoints > 0) {
            Position newPos = new Position(position.x() + dx, position.y() + dy);
            if (isValidPosition(newPos)) {

                // Проверка на атаку
                ComputerPlayer target = field.getComputerPlayerAt(newPos);
                if (target != null) {
                    this.attack(target);
                    spendMovementPoints(1);
                    return;
                }

                // Проверка на атаку замка
                Castle castle = field.getCastleAt(newPos);
                if (castle != null) {
                    if (castle != myCastle) { // Атакуем только ЧУЖИЕ замки
                        castle.takeDamage(power);
                        System.out.println("Вы атакуете вражеский замок! Здоровье: " + castle.getHealth());
                    } else {
                        System.out.println("Это ваш замок, атака невозможна!");
                    }
                    return;
                }

                // Обычное перемещение
                spendMovementPoints(1);
                System.out.println("Вы переместились на: " + newPos);
                field.moveObject(this, position.x(), position.y(), newPos.x(), newPos.y());
                this.position = newPos;

            } else {
                System.out.println("Невозможно переместиться в эту позицию!");
            }
        } else {
            System.out.println("Недостаточно очков для перемещения!");
        }
    }

    public void move(int dx, int dy, Field field, int d) {
        diag += d;
        if (((movementPoints > 0) && (diag % 2 == 1)) || ((movementPoints > 1) && (diag % 2 == 0))) {
            Position newPos = new Position(position.x() + dx, position.y() + dy);
            if (isValidPosition(newPos)) {

                // Проверка на атаку замка
                Castle castle = field.getCastleAt(newPos);
                if (castle != null) {
                    if (castle != myCastle) { // ЧУЖИЕ замки?
                        spendMovementPoints(1);
                        castle.takeDamage(power);
                        System.out.println("Вы атакуете вражеский замок! Здоровье: " + castle.getHealth());
                    } else {
                        System.out.println("Это ваш замок, атака невозможна!");
                    }
                    return;
                }

                // Проверка на атаку
                ComputerPlayer target = field.getComputerPlayerAt(newPos);
                if (target != null) {
                    this.attack(target);
                    spendMovementPoints(1);
                    return;
                }


                if (diag % 2 == 0) {
                    spendMovementPoints(1);
                }

                spendMovementPoints(1);
                System.out.println("Вы переместились на: " + newPos);
                field.moveObject(this, this.position.x(), this.position.y(), newPos.x(), newPos.y());
                this.position = newPos;

            } else {
                System.out.println("Невозможно переместиться в эту позицию!");
            }
        } else {
            System.out.println("Недостаточно очков для перемещения!");
        }
    }

    public boolean isValidPosition(Position newPos) {
        // Проверка на допустимость позиции на поле
        return newPos.x() >= 0 && newPos.x() < 10 && newPos.y() >= 0 && newPos.y() < 10;
    }
}
