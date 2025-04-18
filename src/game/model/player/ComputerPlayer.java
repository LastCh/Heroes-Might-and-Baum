package game.model.player;

import game.api.Direction;
import game.api.Position;
import game.map.Field;
import game.model.building.onmap.Castle;

import java.util.Random;

public class ComputerPlayer extends Player {
    private static final String COLOR = "\u001B[31m";
    private final Position targetCastle;
    private boolean preferX = true; // Флаг для чередования осей

    public ComputerPlayer(Position startPosition, Position targetCastle, int points, Castle castle, int gold) {
        super(startPosition, Direction.UP, COLOR, castle, 10, points, gold);
        this.targetCastle = targetCastle;
        this.power = 5;
    }

    public void performAITurn(Field field, HumanPlayer humanPlayer) {
        moveTowardsCastle(field);

        // Проверка расстояния для атаки
        if (isInRange(humanPlayer, 2)) {
            this.attack(humanPlayer);
        }

        // Проверка позиции замка
        Castle targetCastleObj = field.getCastleAt(targetCastle);
        if (getPosition().equals(targetCastleObj.getPosition()) && targetCastleObj != null) {
            targetCastleObj.takeDamage(power);
            System.out.println("Компьютер атакует ваш замок, у него осталось " + targetCastleObj.getHealth() + " hp!");
        }
    }

    private boolean tryAxisMove(int dx, int dy, Field field) {
        if (dx == 0 && dy == 0) return false;

        Position newPos = new Position(position.x() + dx, position.y() + dy);
        if (isValidPosition(newPos, field)) {
            field.moveObject(this, position.x(), position.y(), newPos.x(), newPos.y());
            updatePosition(newPos.x(), newPos.y());
            spendMovementPoints(1); // Тратим очки
            return true;
        }
        return false;
    }

    private void moveTowardsCastle(Field field) {
        while (movementPoints > 0) {
            Position current = getPosition();
            int dx = Integer.compare(targetCastle.x(), current.x());
            int dy = Integer.compare(targetCastle.y(), current.y());

            boolean moved = false;

            // Чередование порядок проверки осей
            if (preferX) {
                moved = tryAxisMove(dx, 0, field); // X
                if (!moved) moved = tryAxisMove(0, dy, field); // Y
            } else {
                moved = tryAxisMove(0, dy, field); // Y
                if (!moved) moved = tryAxisMove(dx, 0, field); // X
            }

            // Если движение невозможно в нужном направлении - выход
            if (!moved) break;
        }
    }

    private boolean tryMove(int dx, int dy, Field field) {
        Position newPos = new Position(position.x() + dx, position.y() + dy);
        if (isValidPosition(newPos, field)) {
            field.moveObject(this, position.x(), position.y(), newPos.x(), newPos.y());
            updatePosition(newPos.x(), newPos.y());
            return true;
        }
        return false;
    }

    private boolean isValidPosition(Position pos, Field field) {
        return pos.x() >= 0 && pos.x() < field.getWidth()
                && pos.y() >= 0 && pos.y() < field.getHeight();
    }

    private void startBattle(HumanPlayer player) {
        System.out.println("Компьютер атакует игрока!");
        player.setHealth(player.getHealth() - this.power);

        if (player.getHealth() <= 0) {
            System.out.println("Игрок побежден!");
        } else {
            System.out.println("У игрока осталось здоровья: " + player.getHealth());
        }
    }

    @Override
    public void move(int dx, int dy, Field field) {
        // Реализация для интерфейса
        tryMove(dx, dy, field);
    }

    @Override
    public void interact(Player player) {

    }
}