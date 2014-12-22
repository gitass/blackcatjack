/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.model;

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
     * Test of hit method, of class BlackjackRound.
     */
    @Test
    public void testHit() {
        System.out.println("hit");
        BlackjackRound instance = null;
        Boolean expResult = null;
        Boolean result = instance.hit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stand method, of class BlackjackRound.
     */
    @Test
    public void testStand() {
        System.out.println("stand");
        BlackjackRound instance = null;
        Boolean expResult = null;
        Boolean result = instance.stand();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWon method, of class BlackjackRound.
     */
    @Test
    public void testIsWon() {
        System.out.println("isWon");
        BlackjackRound instance = null;
        Boolean expResult = null;
        Boolean result = instance.isWon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of score method, of class BlackjackRound.
     */
    @Test
    public void testScore() {
        System.out.println("score");
        BlackjackRound instance = null;
        int expResult = 0;
        int result = instance.score();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
