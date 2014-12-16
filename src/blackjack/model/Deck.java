package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Deck implements Iterable<Card> {

    private static final int DECK_SIZE = 52;

    private ArrayList<Card> _cards;

    // Construct a deck of the given cards.
    public Deck(ArrayList<Card> cards) {
        _cards = cards;
    }

    // Construct a new shuffled deck.
    public Deck() {
        this(shuffledCards());
    }

    public static ArrayList<Card> shuffledCards() {
        ArrayList<Card> cards = new ArrayList<Card>(DECK_SIZE);
        for (int n = 1; n <= 13; n++) {
            for (Suit s : Suit.values()) {
                cards.add(new Card(n, s));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(_cards);
    }

    class DeckIterator implements Iterator<Card> {
        private final Deck _deck;
        // Index of the next card to draw. Increments after drawing.
        private int _next_card;
        DeckIterator(Deck deck) {
            _deck = deck;
            _next_card = 0;
        }
        public boolean hasNext() {
            return _next_card < DECK_SIZE;
        }
        public Card next() {
            return _deck._cards.get(_next_card++);
        }
        public int cardsLeft() {
            return DECK_SIZE - _next_card;
        }
        // Needed for implementing the Iterator interface...
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public DeckIterator iterator() {
        return new DeckIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Deck{");
        for (Card c : _cards) {
            b.append("  ");
            b.append(c);
            b.append("\n");
        }
        b.append("}");
        return b.toString();
    }
}
