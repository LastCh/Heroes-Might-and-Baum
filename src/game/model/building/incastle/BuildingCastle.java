package game.model.building.incastle;

import game.model.building.onmap.Castle;
import game.model.player.Player;

public abstract class BuildingCastle {
    protected int cost;
    protected final Castle affiliationCastle;
    protected final Player affiliationPlayer;

    public BuildingCastle(Castle castle, Player player) {
        affiliationCastle = castle;
        affiliationPlayer = player;
    }
}
