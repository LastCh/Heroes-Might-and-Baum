package game.model.unit;

import game.api.Direction;
import game.api.Position;
import game.model.player.Player;

public abstract class Unit {
    protected final int amount;
    protected final Player affiliation;
    protected float powerModify;
    protected float distanceModify;

    public Unit(int count, Player player) {
        this.amount = count;
        this.affiliation = player;
    }
}
