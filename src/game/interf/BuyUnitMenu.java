package game.interf;

public class BuyUnitMenu extends Inter {
    private int gold = 200;

    @Override
    public void display() {

        System.out.println("Меню покупки юнитов:");
        System.out.println("1. Купить копейщика (10 золота)");
        System.out.println("2. Купить арбалетчика (20 золота)");
        System.out.println("3. Купить месника (30 золота)");
        System.out.println("4. Купить кавалериста (40 золота)");
        System.out.println("5. Купить паладина (50 золота)");
        System.out.println("6. Вернуться в главное меню");
    }

    @Override
    public int handleInput() {
        int choice = super.handleInput();
        clearConsole();
        switch (choice) {
            case 1:
                if (gold >= 10) {
                    gold -= 10;
                    System.out.println("Пехотинец куплен!");
                } else {
                    System.out.println("Не хватает золота!");
                }
                break;
            case 2:
                if (gold >= 20) {
                    gold -= 20;
                    System.out.println("Кавалерист куплен!");
                } else {
                    System.out.println("Не хватает золота!");
                }
                break;
            case 3:
                System.out.println("Возврат в игровое меню...");
                break;
            case 4:
                System.out.println("Возврат в игровое меню...");
                break;
            case 5:
                System.out.println("Возврат в игровое меню...");
                break;
            case 6:
                System.out.println("Возврат в игровое меню...");
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
        return choice;
    }
}