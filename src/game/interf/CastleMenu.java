// CastleMenu.java
package game.interf;

public class CastleMenu extends Inter {
    private final BuyBuildingMenu buyBuildingMenu = new BuyBuildingMenu();
    private final BuyUnitMenu buyUnitMenu = new BuyUnitMenu();



    @Override
    public void display() {

        System.out.println("Меню замка:");
        System.out.println("1. Улучшить замок");
        System.out.println("2. Нанять юнитов");
        System.out.println("3. Вернуться в главное меню");
    }

    @Override
    public int handleInput() {
        int choice = super.handleInput();
        clearConsole();
        switch (choice) {
            case 1:
                clearConsole();
                handleBuildingMenu();
                break;
            case 2:
                clearConsole();
                handleUnitMenu();
                break;
            case 3:
                System.out.println("Возврат в игровое меню...");
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
        return choice;
    }

    private void handleUnitMenu() {
        int result;
        do {
            buyUnitMenu.display();
            result = buyUnitMenu.handleInput();
        } while (result != 6);
    }

    private void handleBuildingMenu() {
        int result;
        do {
            buyBuildingMenu.display();
            result = buyBuildingMenu.handleInput();
        } while (result != 8);
    }
}