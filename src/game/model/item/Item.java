package game.model.item;

import game.model.player.Player;

public abstract class Item {
    protected final int amount;
    protected Player affiliation;

    public Item(int count, Player player) {
        this.amount = count;
        this.affiliation = player;
    }
}
