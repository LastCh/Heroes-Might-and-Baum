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

    public void move(int dx, int dy, Field field, int d) {

        Position newPos = new Position(position.x() + dx, position.y() + dy);
        int newDiag = diag + d;
        int cost = movementPoints;
        double newAccumulatedMovementCoef = accumulatedMovementCoef;

        //Проверка возможность перемещения по позиции
        if (!isValidPosition(newPos)) {
            System.out.println("Невозможно переместиться в эту позицию!");
            return;
        }

        //Проверка возможность перемещения по очкам диагонали
        if (!isValidPoints(newDiag, d)) {
            System.out.println("Недостаточно очков для перемещения!");
            return;
        }

        // Накопление коэффициента
        newAccumulatedMovementCoef += (1.0 - field.getCell(newPos.x(), newPos.y()).getTerrainType().getModifier());

        // Проверка на атаку замка
        if (castleCheck(field, newPos)) {
            return;
        }

        // Проверка на атаку противника
        if (targetCheck(field, newPos)) {
            return;
        }

        //Трата второго очка передвижения после второй диагонали
        if (newDiag % 2 == 0 && d != 0) {
            cost--;
        }

        //Трата очков за каждый ход и очков коэффициента поля
        cost--;
        cost -= (int)newAccumulatedMovementCoef;

        //Проверка стоимости
        if(cost < 0){
            System.out.println("Недостаточно очков для перемещения!");
            return;
        }

        accumulatedMovementCoef =newAccumulatedMovementCoef - (int)newAccumulatedMovementCoef;
        int finalCost = movementPoints - cost;
        spendMovementPoints(finalCost);
        System.out.println("Вы переместились на: " + newPos);
        diag = newDiag;
        field.moveObject(this, this.position.x(), this.position.y(), newPos.x(), newPos.y());
        this.position = newPos;

    }

    public boolean targetCheck(Field field, Position newPos) {
        ComputerPlayer target = field.getComputerPlayerAt(newPos);
        if (target != null) {
            this.attack(target);
            spendMovementPoints(1);
            return true;
        } else {
            return false;
        }
    }

    public boolean castleCheck(Field field, Position newPos) {
        Castle castle = field.getCastleAt(newPos);
        if (castle != null) {
            if (castle != myCastle) { // Атакуем только ЧУЖИЕ замки
                castle.takeDamage(power);
                System.out.println("Вы атакуете вражеский замок! Здоровье: " + castle.getHealth());
            } else {
                System.out.println("Это ваш замок, атака невозможна!");
            }
            return true;
        }
        return false;
    }

    public boolean isValidPosition(Position newPos) {
        return newPos.x() >= 0 && newPos.x() < 10 && newPos.y() >= 0 && newPos.y() < 10;
    }

    public boolean isValidPoints(int newDiag, int oldDiag) {
        return (((movementPoints > 0) && (newDiag % 2 == 1)) ||
                ((movementPoints > 1) && (newDiag % 2 == 0) && (oldDiag != 0)) ||
                ((movementPoints > 1) && (oldDiag == 0)));
    }
}
