package game.model.player;

import game.api.Direction;
import game.api.Movable;
import game.api.Position;
import game.api.FieldObject;
import game.map.Field;
import game.model.building.onmap.Castle;
import game.model.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public abstract class Player extends FieldObject implements Movable {
    private final List<Unit> objects = new ArrayList<>();
    protected Direction direction;
    protected Castle myCastle;
    protected int maxMovementPoints;
    protected int movementPoints;
    protected int diag = 0;
    protected int health;
    protected int power;
    protected int gold;

    public Player(Position startPosition, Direction startDirection, String colorCode, Castle castle, int priority, int points, int gold) {
        super(startPosition, colorCode + "☻" +" " + "\u001B[0m", priority);
        this.direction = startDirection;
        this.maxMovementPoints = points;
        this.movementPoints = points;
        this.health = 20;  // Добавить
        this.power = 5;
        this.coloredSymbol = colorCode + "☻" + " " + "\u001B[0m";
        this.myCastle = castle;
        this.gold = gold;
    }

    public boolean isInRange(Player other, int range) {
        Position otherPos = other.getPosition();
        return Math.abs(position.x() - otherPos.x()) <= range
                && Math.abs(position.y() - otherPos.y()) <= range;
    }

    public void attack(Player target) {
        target.takeDamage(this.power);
        System.out.printf("%s атаковал %s! Осталось здоровья: %d\n",
                this.getClass().getSimpleName(),
                target.getClass().getSimpleName(),
                target.getHealth()
        );
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            System.out.println(this.getClass().getSimpleName() + " погиб!");
        }
    }

    public void setGold(int count) {
        this.gold = count;
    }

    public int getGold() {
        return this.gold;
    }

    public Castle getMyCastle() {
        return this.myCastle;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void move(int dx, int dy, Field field) {
        updatePosition(position.x() + dx, position.y() + dy);
    }

    public void updatePosition(int newX, int newY) {
        this.position = new Position(newX, newY);
    }

    @Override
    public void turn(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public String getColoredSymbol() {
        return String.format("%-3s", coloredSymbol); // Фиксированная ширина в 3 символа
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setPower(int newPower) {
        power = newPower;
    }

    public int getPower() {
        return power;
    }

    public int getMovementPoints() {
        return movementPoints;
    }

    public void spendMovementPoints(int points) {
        movementPoints -= points;
    }

    public void resetMovementPoints() {
        movementPoints = 5;
    }
}