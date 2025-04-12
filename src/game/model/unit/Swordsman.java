package game.model.unit;

import game.model.player.Player;

public class Swordsman extends Unit {
    private final double distanceModify = 1;
    private final double powerModify = 1.1;

    public Swordsman(int amount, Player player) {
        super(amount, player);
    }
}
