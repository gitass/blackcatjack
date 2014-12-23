/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mr.Wootz
 */
public class BlackjackRoundTest {

    ArrayList<Card> cards;
    Deck testDeck;
    Deck.DeckIterator iter;

    public BlackjackRoundTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of hit method, of class BlackjackRound. uses a deterministic deck to
     * start a new round, check the values of both players hands, hits the
     * "player" and check to see if the values are expected
     */
    @Test
    public void testHit() {
        System.out.println("hit");
        cards = new ArrayList<>(Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(7, Suit.CLUBS),
                new Card(6, Suit.CLUBS),
                new Card(1, Suit.CLUBS),
                new Card(2, Suit.CLUBS),
                new Card(10, Suit.CLUBS),
                new Card(10, Suit.DIAMONDS)
        ));
        testDeck = new Deck(cards);
        iter = testDeck.iterator();
        BlackjackRound testRound = new BlackjackRound(iter);
        assert (testRound.playerHand().value() == 17);
        assert (testRound.dealerHand().value() == 12);
        testRound.hit();
        assert (testRound.playerHand().value() == 19);
        assert (!testRound.playerHand().isBusted());
        assert (testRound.dealerHand().value() == 12);

    }

    /**
     * Test of stand method, of class BlackjackRound.
     * tests: stand, isWon, and score
     */
    @Test
    public void testStandIsWonScore() {
        System.out.println("stand");
        //dealer will try to win and bust
        cards = new ArrayList<>(Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(7, Suit.CLUBS),
                new Card(6, Suit.CLUBS),
                new Card(1, Suit.CLUBS),
                new Card(2, Suit.CLUBS),
                new Card(10, Suit.CLUBS),
                new Card(10, Suit.DIAMONDS)
        ));
        testDeck = new Deck(cards);
        iter = testDeck.iterator();
        BlackjackRound testRound = new BlackjackRound(iter);
        assert (testRound.stand());
        assert(testRound.isWon());
        assert(testRound.score()==17);
        //player draws a blackjack
        cards = new ArrayList<>(Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(7, Suit.CLUBS),
                new Card(10, Suit.CLUBS),
                new Card(1, Suit.CLUBS),
                new Card(2, Suit.CLUBS),
                new Card(10, Suit.CLUBS),
                new Card(10, Suit.DIAMONDS)
        ));
        testDeck = new Deck(cards);
        iter = testDeck.iterator();
        testRound = new BlackjackRound(iter);
        assert (testRound.stand());
        assert(testRound.isWon());
        assert(testRound.score()==21);
        //dealer manages to win. also tests the soft 16 mechanic
        cards = new ArrayList<>(Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(7, Suit.CLUBS),
                new Card(3, Suit.CLUBS),
                new Card(1, Suit.CLUBS),
                new Card(4, Suit.CLUBS),
                new Card(5, Suit.DIAMONDS),
                new Card(10, Suit.DIAMONDS)
        ));
        testDeck = new Deck(cards);
        iter = testDeck.iterator();
        testRound = new BlackjackRound(iter);
        assert (!testRound.stand());
        assert(testRound.dealerHand().value()==21);
        assert(!testRound.isWon());
        assert(testRound.score()==-14);
    }
}
