/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.model;

import java.util.ArrayList;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Mr.Wootz
 */
public class HandTest {
    Hand instanceD, instanceP;
    
    public HandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instanceD = new Hand(PlayerKind.Dealer);
        instanceP = new Hand(PlayerKind.Player);
        instanceD.addCard(new Card(5,Suit.CLUBS));
        instanceD.addCard(new Card(7,Suit.SPADES));
        instanceP.addCard(new Card(8,Suit.DIAMONDS));
        instanceP.addCard(new Card(10,Suit.HEARTS));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of visibleCards method, of class Hand.
     * create new hands for dealer and player and test for correct visibility of those hands
     */
    @Test
    public void testVisibleCards() {
        System.out.println("visibleCards");
        ArrayList<Card> d;
        ListIterator<Card> iter;
        d = instanceP.visibleCards();
        iter = d.listIterator();
        Card c = iter.next(); 
        assert (c.number() == 8 && c.suit() == Suit.DIAMONDS);
        c = iter.next();
        assert (c.number() == 10 && c.suit() == Suit.HEARTS);
        d = instanceD.visibleCards();
        iter = d.listIterator();
        c = iter.next(); 
        assert (!(c.number() == 5 && c.suit() == Suit.CLUBS)); // this card shouldn't be visible as it's the first card of the dealer
        assert (c.number() == 7 && c.suit() == Suit.SPADES); // the second card should be visible as the first visible card
    }

    /**
     * Test of allCards method, of class Hand.
     */
    @Test
    public void testAllCards() {
        System.out.println("allCards");
        ArrayList<Card> d;
        ListIterator<Card> iter;
        d = instanceP.allCards();
        iter = d.listIterator();
        Card c = iter.next(); 
        assert (c.number() == 8 && c.suit() == Suit.DIAMONDS);
        c = iter.next();
        assert (c.number() == 10 && c.suit() == Suit.HEARTS);
        d = instanceD.allCards();
        iter = d.listIterator();
        c = iter.next(); 
        assert (c.number() == 5 && c.suit() == Suit.CLUBS); // this card shouldn't be visible as it's the first card of the dealer
        c = iter.next();
        assert (c.number() == 7 && c.suit() == Suit.SPADES); // the second card should be visible as the first visible card
    }

    /**
     * Test of addCard method, of class Hand.
     */
    @Test
    public void testAddCard() {
        System.out.println("addCard");
        instanceP.addCard(new Card(10,Suit.DIAMONDS));
        Card c;
        ArrayList<Card> d;
        ListIterator<Card> iter;
        d = instanceP.allCards();
        iter = d.listIterator();
        do {
            c = iter.next();
        } while (iter.hasNext());
        assert (c.suit() == Suit.DIAMONDS && c.number() == 10);
    }

    /**
     * Test of value method, of class Hand.
     */
    @Test
    public void testValue() {
        System.out.println("value");
        Card c;
        ArrayList<Card> d;
        ListIterator<Card> iter;
        d = instanceP.allCards();
        iter = d.listIterator();
        int res = 0;
        do {
            c = iter.next();
            res += c.number();
        } while (iter.hasNext());
        assert(res == instanceP.value());
    }

    /**
     * Test of isBusted method, of class Hand.
     */
    @Test
    public void testIsBusted() {
        System.out.println("isBusted");
        Hand hand = new Hand(PlayerKind.Dealer);
        assert (!hand.isBusted());
        hand.addCard(new Card(1,Suit.CLUBS)); // 11
        assert (!hand.isBusted());
        hand.addCard(new Card(1,Suit.DIAMONDS)); // 1
        assert (!hand.isBusted());
        hand.addCard(new Card(1,Suit.HEARTS)); // 1
        hand.addCard(new Card(1,Suit.SPADES)); // 1
        hand.addCard(new Card(7,Suit.HEARTS)); // now we are at 21
        assert (!hand.isBusted());
        hand.addCard(new Card(10,Suit.HEARTS)); // now we are STILL at 21
        assert (!hand.isBusted());
        hand.addCard(new Card(2,Suit.HEARTS));
        assert (hand.isBusted());
        
        hand = new Hand(PlayerKind.Dealer);
        hand.addCard(new Card(1,Suit.CLUBS));
        hand.addCard(new Card(10,Suit.CLUBS));
        assert (!hand.isBusted());
        
        hand = new Hand(PlayerKind.Player);
        hand.addCard(new Card(1,Suit.CLUBS)); // 11
        hand.addCard(new Card(1,Suit.DIAMONDS)); // 11
        assert (!hand.isBusted()); // we are at 12
        hand.addCard(new Card(10,Suit.DIAMONDS));
        assert (hand.isBusted());
    }
    
}
