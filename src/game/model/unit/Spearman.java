package game.model.unit;

import game.model.player.Player;

public class Spearman extends Unit {
    private final double distanceModify = 1.1;
    private final double powerModify = 1.2;

    public Spearman(int amount, Player player) {
        super(amount, player);
    }
}
