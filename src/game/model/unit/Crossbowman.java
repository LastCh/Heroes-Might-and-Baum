package game.model.unit;

import game.model.player.Player;

public class Crossbowman extends Unit {
    private final double distanceModify = 1.3;
    private final double powerModify = 1.2;

    public Crossbowman(int amount, Player player) {
        super(amount, player);
    }
}
