package game.interf;

public class GameMenu extends Inter {

    @Override
    public void display() {
        System.out.println("Управление в игре:");
        System.out.println("W - вверх, S - вниз, A - влево, D - вправо, M - открыть меню, Q - пропуск хода");
    }

    public void display(int points, int castleHealth, int enemyCastleHealth) {
        System.out.println("Ваши очки передвижения: " + points + ", " +
                "здоровье вашего замка: " + castleHealth + ", " +
                "здоровье вражеского замка: " + enemyCastleHealth);
    }
}
