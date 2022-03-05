package CardsWithPower_03;

public enum CardSuits {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);
    private int powerSuits;

    public int getPowerSuits() {
        return powerSuits;
    }

    CardSuits(int powerSuits) {
        this.powerSuits = powerSuits;
    }
}
