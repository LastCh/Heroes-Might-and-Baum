package game.map;

public enum TerrainType {
    ROAD('░', 1.0, "\u001B[47m", "\u001B[30m"),     // Белый фон, черный текст
    FOREST('♣', 0.5, "\u001B[42m", "\u001B[32m"),   // Зеленый фон, зеленый текст
    GRASS(',', 0.7, "\u001B[43m", "\u001B[33m"),    // Желтый фон, желтый текст
    WATER('≈', 0.3, "\u001B[44m", "\u001B[34m"),    // Синий фон, синий текст
    MOUNTAIN('▲', 0.1, "\u001B[45m", "\u001B[35m"); // Пурпурный фон, пурпурный текст

    private final char symbol;
    private final double movementModifier;
    private final String backgroundColor;
    private final String textColor;

    TerrainType(char symbol, double modifier, String bgColor, String txtColor) {
        this.symbol = symbol;
        this.movementModifier = modifier;
        this.backgroundColor = bgColor;
        this.textColor = txtColor;
    }

    public String getColoredBlock() {
        return backgroundColor + textColor + " " + symbol + " " + "\u001B[0m";
    }

    public char getSymbol() {
        return symbol;
    }

    public String getBack() {
        return backgroundColor;
    }

    public double getModifier() {
        return movementModifier;
    }
}