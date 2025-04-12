package game.model.unit;

import game.model.player.Player;

public class Cavalryman extends Unit {
    private final double distanceModify = 1.1;
    private final double powerModify = 1.4;

    public Cavalryman(int amount, Player player) {
        super(amount, player);
    }
}
