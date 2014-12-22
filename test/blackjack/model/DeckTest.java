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
 * @author Mr.Wootz
 */
public class DeckTest {
    Deck d;
    Deck.DeckIterator iter;
    public DeckTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        d = new Deck();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of shuffledCards method, of class Deck.
     * Assuming a shuffled deck has at least one third of the cards in an unexpected place
     */
    @Test
    public void testShuffledCards() {
        int shuffleTester=0;
        Card card;
        System.out.print("shuffledCards");
        iter = d.iterator();
        for (int n = 1; n <= 13; n++) {
            for (Suit s : Suit.values()) {
                card=iter.next();
                if(card.number()!=n && card.suit()!=s){
                    ++shuffleTester;
                }
            }
        }
        System.out.println(" - the amount of shaffled cards is " + shuffleTester);
        assert(shuffleTester>=17); //17 is about one third of a standard 52 card deck

    }

    /**
     * Test of shuffle method, of class Deck.
     * check if any shuffling at all was made. a more rigorous test is preformed in testShuffledCards()
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        boolean T=true;
        int i=0;
        Card c1,c2;
        Deck instance = new Deck();
        instance.shuffle();
        Deck.DeckIterator nditer=instance.iterator();
        iter=d.iterator();
        while(iter.hasNext() && T){
            c1=iter.next();
            c2=nditer.next();
            T=T&(c1.number()==c2.number() && c1.suit()==c2.suit());
        }
        assert(!T);
    }

    /**
     * Test of iterator method, of class Deck.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Deck.DeckIterator titer=d.iterator();
        Card c1,c2;
        iter=d.iterator();
        c1=iter.next();
        c2=titer.next();
        while(iter.hasNext() || titer.hasNext()){
            if (!(c1.number()==c2.number() && c1.suit()==c2.suit())){
                fail("The card iterators dont iterate properly");
            }
            else if(!(iter.hasNext()==titer.hasNext() && iter.cardsLeft()==titer.cardsLeft())){
                fail("The card iterator doesn't calculate the amount of card left and/or if they have another card left correctly");
            }
            c1=iter.next();
            c2=titer.next();
        }
    }

    /**
     * Test of toString method, of class Deck.
     * unused debug method. doesn't get tested because it simply uses Card toString method in a loop and that one is tested
     */
    /*
    @Test
    public void testToString() {
        System.out.println("toString");
        Deck instance = new Deck();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
