package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;

// Ad-hoc testing.
public class TestStuff {
    public static void main(String[] args) {
        Card c = new Card(13, Suit.SPADES);
        System.out.println(c);

        Deck d = new Deck();
        Deck.DeckIterator di = d.iterator();
        Hand h = new Hand(PlayerKind.Dealer);
        Card c1 = di.next();
        Card c2 = di.next();
        System.out.println(c1.toString() + c2);

        h.addCard(new Card(1, Suit.SPADES));
        System.out.println(h.value());
        h.addCard(new Card(1, Suit.SPADES));
        System.out.println(h.value());


        // My idea of simple iteration... not.
        /*
        Deck.DeckIterator di = d.iterator();
        while (di.hasNext()) {
            Card cc = di.next();
            System.out.println(cc);
            System.out.println(di.cardsLeft());
        }

        */
        
        /*
        for (Card cc : d) {
            System.out.println(cc);
        }
        */
        //Card c3 = new Card(13, null);
        //System.out.println(c3);
        
        ArrayList<Card> a = new ArrayList<>(Arrays.asList(c1, c2));
        System.out.println(Card.cardsToString(a));
        
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        for (Integer i : b.subList(1, 1)) {
            System.out.println(i);
        }
        
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(7, Suit.CLUBS),
                new Card(6, Suit.CLUBS),
                new Card(1, Suit.CLUBS),
                new Card(2, Suit.CLUBS)
        ));
        Hand hand = new Hand(PlayerKind.Dealer, cards);
        System.out.println(hand.value());
        
    }
}














