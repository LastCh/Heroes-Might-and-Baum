package game.interf;

public class GameMenu extends Inter {

    @Override
    public void display() {
        System.out.println("Управление в игре:");
        System.out.println("W - вверх, S - вниз, A - влево, D - вправо");
        System.out.println("M - открыть меню, Q - пропуск хода");
    }
}
