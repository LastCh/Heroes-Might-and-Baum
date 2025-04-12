// StartMenu.java
package game.interf;

public class StartMenu extends Inter {
    @Override
    public void display() {
        clearConsole();
        System.out.println("Добро пожаловать в игру!");
        System.out.println("1. Начать новую игру");
        System.out.println("2. Загрузить игру");
        System.out.println("3. Выйти");
    }
}