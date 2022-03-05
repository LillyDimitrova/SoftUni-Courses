package CardsWithPower_03;


public class Card {
    private CardSuits cardSuits;
    private CardRank cardRank;
    private int powerCard;

    public Card(CardSuits cardSuits, CardRank cardRank) {
        this.cardSuits = cardSuits;
        this.cardRank = cardRank;
    }

    public void setPowerCard(int powerCard) {
        this.powerCard = powerCard;
    }

    public CardSuits getCardSuits() {
        return cardSuits;
    }

    public void setCardSuits(CardSuits cardSuits) {
        this.cardSuits = cardSuits;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public void setCardRank(CardRank cardRank) {
        this.cardRank = cardRank;
    }
    public int getPowerCard() {
        return cardRank.getPowerRank() + cardSuits.getPowerSuits();
    }
}
