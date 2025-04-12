package game.core.engine;

import game.interf.MenuManager;
import game.map.Field;
import game.model.building.onmap.Castle;
import game.model.player.ComputerPlayer;
import game.model.player.HumanPlayer;
import java.util.Scanner;

public class Render {
    private final Field field;
    private final HumanPlayer player;
    private final ComputerPlayer computerPlayer;
    private final Scanner scanner; // Теперь передаётся извне
    private final MenuManager menu = new MenuManager();
    private final Castle castlePlayer;
    private final Castle castleBot;

    public Render(Field field, HumanPlayer player, Scanner sharedScanner, ComputerPlayer computer, Castle cb, Castle cp) {
        this.field = field;
        this.player = player;
        this.scanner = sharedScanner;
        this.computerPlayer = computer;
        this.castleBot = cb;
        this.castlePlayer = cp;
        menu.setComputerPlayer(computer);
        menu.setHumanPlayer(player);

    }

    public void startGameLoop() {
        boolean running = true;
        while (running) {
            renderField();
            menu.callGame();
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "W" -> safeMove(0, -1);
                case "S" -> safeMove(0, 1);
                case "A" -> safeMove(-1, 0);
                case "D" -> safeMove(1, 0);
                case "Q" -> {
                    while(player.getMovementPoints() > 1) {
                        safeMove(0, 0);
                    }
                    if(computerPlayer.isAlive()) {
                        computerPlayer.performAITurn(field, player); // Вызов ИИ
                        computerPlayer.resetMovementPoints(); // Сбрасываем очки ИИ
                    }
                    player.resetMovementPoints(); // Восстанавливаем очки
                }
                case "M" -> {
                    clearConsole();
                    if (menu.showGameMenu()) {
                        return; // Выход в главное меню
                    }
                }
                case "SD", "DS"  -> safeMove(1, 1, 1);
                case "AS", "SA"  -> safeMove(-1, 1, 1);
                case "AW", "WA" -> safeMove(-1, -1, 1);
                case "WD", "DW" -> safeMove(1, -1, 1);
                default -> {
                    clearConsole();
                    System.out.println("Неверная команда.");
                }
            }

            if(checkCastleCaptured()) {
                System.out.println("Замок захвачен!");
                break;
            }

            if(!player.isAlive()){
                System.out.println("Ваш герой погиб!");
                break;
            }

            if(!computerPlayer.isAlive()){
                field.removePlayer(computerPlayer);
            }
        }
    }

    private void safeMove(int dx, int dy) {
        clearConsole();
        try {
            player.move(dx, dy, field);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка движения: " + e.getMessage());
        }
    }

    private void safeMove(int dx, int dy, int diag) {
        clearConsole();
        try {
            player.move(dx, dy, field, diag);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка движения: " + e.getMessage());
        }
    }

    public void renderField() {
        field.render();
        System.out.println("Ваши очки передвижения: " + player.getMovementPoints());
        System.out.println("Здоровье вашего замка: " + castlePlayer.getHealth());
        System.out.println("Здоровье вражеского замка: " + castleBot.getHealth());
    }

    private boolean checkCastleCaptured() {
        if (castleBot == null || castlePlayer == null) return false;

        // Проверка захвата компьютерного замка игроком
        if (castleBot.isDestroyed()) {
            System.out.println("Игрок захватил вражеский замок!");
            return true;
        }

        // Проверка захвата игрового замка ботом
        if (castlePlayer.isDestroyed()) {
            System.out.println("Компьютер захватил ваш замок!");
            return true;
        }

        return false;
    }

    private void clearConsole() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}