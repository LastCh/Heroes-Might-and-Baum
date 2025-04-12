package game.core.engine;

import game.api.Position;
import game.map.Field;
import game.model.player.HumanPlayer;
import game.model.player.ComputerPlayer;
import game.model.building.onmap.Castle;
import java.util.Scanner;

public class GameManager {
    private final Field field;
    private final HumanPlayer human;
    private final ComputerPlayer computer;
    private final Scanner scanner = new Scanner(System.in);
    private final Castle botCastle;
    private final Castle playerCastle;

    public GameManager() {
        Generation generation = new Generation();
        this.field = generation.generateField(10, 10);

        botCastle = new Castle(new Position(8, 8), scanner,"\u001B[31;47m", 20);
        playerCastle = new Castle(new Position(1, 1), scanner,"\u001B[31;47m", 20);

        field.getCell(8, 8).addObject(botCastle);
        field.getCell(1, 1).addObject(playerCastle);

        this.human = new HumanPlayer(new Position(0, 0),5, playerCastle, 2000);
        this.computer = new ComputerPlayer(new Position(9, 9), playerCastle.getPosition(), 5, botCastle, 500);

        field.getCell(0, 0).addObject(human);
        field.getCell(9, 9).addObject(computer);
    }

    public void startGame() {
        Render render = new Render(field, human, scanner, computer, botCastle, playerCastle);
        render.startGameLoop();
    }
}