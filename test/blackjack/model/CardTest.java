/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.model;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex Nulman
 * This class tests against a specific card to make sure card creation is correct.
 */
public class CardTest {
    
    public static final int NUMBER = 5;
    public static final Suit SUIT = Suit.CLUBS;
    Card card;
    
    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        card = new Card(NUMBER, SUIT);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of number method, of class Card.
     */
    @Test
    public void testNumber() {
        System.out.println("number");
        int expResult = NUMBER;
        int result = card.number();
        assertEquals(expResult, result);
    }

    /**
     * Test of suit method, of class Card.
     */
    @Test
    public void testSuit() {
        System.out.println("suit");
        Suit expResult = SUIT;
        Suit result = card.suit();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Card{5 of CLUBS}";
        String result = card.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of cardsToString method, of class Card.
     */
    @Test
    public void testCardsToString() {
        System.out.println("cardsToString");
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        String expResult = "Card{5 of CLUBS}\n";
        String result = Card.cardsToString(cards);
        assertEquals(expResult, result);
    }
    
}
