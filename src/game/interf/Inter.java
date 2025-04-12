package game.interf;

import game.model.player.ComputerPlayer;
import game.model.player.HumanPlayer;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Inter {
    protected Scanner scanner = new Scanner(System.in);


    public abstract void display();

    public int handleInput() {
        try {
            System.out.print("Выберите опцию: ");
            int input = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Очищаем неверный ввод
            return -1; // Возвращаем невалидное значение
        }
    }


    protected void clearConsole() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}