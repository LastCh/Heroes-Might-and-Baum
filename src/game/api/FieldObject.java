package game.api;

import game.model.player.Player;

public abstract class FieldObject implements GameObject {
    protected Position position;
    protected String coloredSymbol;
    protected int priority;

    public FieldObject(Position position, String coloredSymbol, int priority) {
        this.position = position;
        this.coloredSymbol = coloredSymbol;
        this.priority = priority;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColoredSymbol() {
        return coloredSymbol;
    }

    public int getPriority() {
        return priority;
    }

    public abstract void interact(Player player);
}