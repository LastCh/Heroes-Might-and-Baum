package game.api;
import game.model.player.Player;

public interface Immovable extends GameObject {

    // Взаимодействие с объектом
    void interact(Player player);

    // Позиция на карте
    Position getPosition();

}