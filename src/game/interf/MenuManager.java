package game.interf;

import game.core.engine.GameManager;
import game.model.player.ComputerPlayer;
import game.model.player.HumanPlayer;


public class MenuManager {
    private final StartMenu startMenu = new StartMenu();
    private final CastleMenu castleMenu = new CastleMenu();
    private final GameMenu gameMenu = new GameMenu();
    private final GameManager gmanager = new GameManager();
    private HumanPlayer hplayer;
    private ComputerPlayer cplayer;

    public void run() {
        boolean inMainMenu = true;
        while (inMainMenu) {
            startMenu.display();
            int choice = startMenu.handleInput();

            switch (choice) {
                case 1:
                    startNewGame();
                    inMainMenu = false;
                    break;
                case 2:
                    loadGame();
                    inMainMenu = false;
                    break;
                case 3:
                    System.out.println("Выход из игры...");
                    return;
                default:
                    startMenu.clearConsole();
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

        }
    }

    public boolean showGameMenu() {
        int choice;

        do {
            System.out.println("Игровое меню:");
            System.out.println("1. Зайти в замок");
            System.out.println("2. Вернуться в игру");
            System.out.println("3. Вернуться в главное меню");

            choice = startMenu.handleInput();

            switch (choice) {
                case 1:
                    startMenu.clearConsole();
                    handleCastleMenu();
                    break;
                case 3:
                    return true;
                default:
                    startMenu.clearConsole();
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 2);
        return false;
    }

    public void callGame() {
        gameMenu.display();
    }

    private void handleCastleMenu() {
        int result;
        do {
            castleMenu.display();
            result = castleMenu.handleInput();
        } while (result != 3);
    }

    private void startNewGame() {
        gameMenu.clearConsole();
        System.out.println("Начинаем новую игру...");
        gmanager.startGame();
    }

    public void setComputerPlayer(ComputerPlayer player){
        cplayer = player;
    }

    public ComputerPlayer getComputerPlayer(){
        return cplayer;
    }

    public void setHumanPlayer(HumanPlayer player){
        hplayer = player;
    }

    public HumanPlayer getHumanPlayer(){
        return hplayer;
    }

    private void loadGame() {
        System.out.println("Загрузка игры...");
    }
}