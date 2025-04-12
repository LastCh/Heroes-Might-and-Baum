package game.model.player;

import game.api.Direction;
import game.api.Position;
import game.map.Field;
import game.model.building.onmap.Castle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private TestPlayer player;
    private Field field;
    private Castle castle;

    // Вспомогательный класс для тестирования
    private static class TestPlayer extends Player {
        public TestPlayer(Position startPosition, Direction startDirection, String colorCode, Castle castle) {
            super(startPosition, startDirection, colorCode, castle, 1, 5, 100);
        }

        @Override
        public void interact(Player player) {
            // Пустая реализация для тестов
        }
    }

    @BeforeEach
    void setUp() {
        // Создаем сканер для конструктора Castle
        Scanner scanner = new Scanner(System.in);
        castle = new Castle(new Position(0, 0), scanner, 0); // Используем правильный конструктор
        player = new TestPlayer(new Position(2, 2), Direction.UP, "RED", castle);
        field = new Field(10, 10);
    }

    @Test
    void testInitialPosition() {
        assertEquals(new Position(2, 2), player.getPosition());
    }

    @Test
    void testMove() {
        player.move(1, 0, field); // Движение вправо
        assertEquals(new Position(3, 2), player.getPosition());

        player.move(0, 1, field); // Движение вниз (DOWN)
        assertEquals(new Position(3, 3), player.getPosition());
    }

    @Test
    void testTurn() {
        player.turn(Direction.RIGHT);
        assertEquals(Direction.RIGHT, player.getDirection());

        player.turn(Direction.DOWN);
        assertEquals(Direction.DOWN, player.getDirection());
    }

    @Test
    void testMovementPoints() {
        assertEquals(5, player.getMovementPoints());

        player.spendMovementPoints(2);
        assertEquals(3, player.getMovementPoints());

        player.resetMovementPoints();
        assertEquals(5, player.getMovementPoints());
    }

    @Test
    void testAttack() {
        TestPlayer enemy = new TestPlayer(new Position(2, 3), Direction.DOWN, "BLUE", castle);
        int initialHealth = enemy.getHealth();

        player.attack(enemy);
        assertEquals(initialHealth - player.getPower(), enemy.getHealth());
    }

    @Test
    void testTakeDamage() {
        int initialHealth = player.getHealth();
        player.takeDamage(3);
        assertEquals(initialHealth - 3, player.getHealth());
    }

    @Test
    void testIsAlive() {
        assertTrue(player.isAlive());
        player.takeDamage(player.getHealth() + 1);
        assertFalse(player.isAlive());
    }

    @Test
    void testIsInRange() {
        TestPlayer nearbyPlayer = new TestPlayer(new Position(3, 3), Direction.LEFT, "BLUE", castle);
        TestPlayer farPlayer = new TestPlayer(new Position(10, 10), Direction.LEFT, "BLUE", castle);

        assertTrue(player.isInRange(nearbyPlayer, 2));
        assertFalse(player.isInRange(farPlayer, 2));
    }

    @Test
    void testGoldManagement() {
        assertEquals(100, player.getGold());
        player.setGold(150);
        assertEquals(150, player.getGold());
    }

    @Test
    void testDirectionDeltas() {
        assertEquals(0, Direction.UP.dx);
        assertEquals(-1, Direction.UP.dy);

        assertEquals(1, Direction.RIGHT.dx);
        assertEquals(0, Direction.RIGHT.dy);
    }

    @Test
    void testFromDeltaMethod() {
        assertEquals(Direction.UP, Direction.fromDelta(0, -1));
        assertEquals(Direction.RIGHT, Direction.fromDelta(1, 0));
        assertThrows(IllegalArgumentException.class, () -> Direction.fromDelta(2, 2));
    }
}