package game.model.building.onmap;

import game.api.Immovable;
import game.api.Position;
import game.api.FieldObject;
import game.model.building.incastle.BuildingCastle;
import game.model.player.Player;
import game.model.player.HumanPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Castle extends FieldObject implements Immovable {
    private final List<BuildingCastle> objects = new ArrayList<>();
    private final Scanner scanner;
    private int health;
    private final int maxHealth;

    public Castle(Position position, Scanner scanner, int maxHe) {
        super(position, "♜", 2); // Символ замка и его приоритет
        this.scanner = scanner;
        this.health = maxHe;
        this.maxHealth = maxHe;
    }

    public boolean contains(BuildingCastle obj) {
        return objects.contains(obj);
    }

    public void addObject(BuildingCastle obj) {
        objects.add(obj);
    }

    public List<BuildingCastle> getBuildings() {
        return new ArrayList<>(objects);
    }

    public Castle(Position position, Scanner scanner, String color, int maxHe) {
        super(position, color + "♜" + color + "♜" + "\u001B[0m", 2); // Символ замка и его приоритет
        this.scanner = scanner;
        this.health = maxHe;
        this.maxHealth = maxHe;
    }

    public void takeDamage(int damage) {
        health = Math.max(health - damage, 0);
    }

    public int getHealth() {
        return health;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    private void buyBuilding(HumanPlayer player) {
        System.out.println("Вы купили здание в замке! (Функционал можно расширить)");
    }

    @Override
    public void interact(Player player) {

    }
}
