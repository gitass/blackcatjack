package blackjack.model;

import java.util.List;

public class Card {
    private final int _number;   // Card number: 1-13.
    private final Suit _suit;

    public Card(int number, Suit suit) {
        if (number < 1 || number > 13) {
            throw new IllegalArgumentException(number + " is not a valid card number");
        }
        _number = number;
        _suit = suit;
    }

    public int number() { return _number; }
    public Suit suit() { return _suit; }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Card{");
        b.append(_number);
        b.append(" of ");
        b.append(_suit);
        b.append("}");
        return b.toString();
    }
    
    public static String cardsToString(List<Card> cards) {
        StringBuilder b = new StringBuilder();
        for (Card c : cards) {
            b.append(c);
            b.append("\n");
        }
        return b.toString();
    }
}
