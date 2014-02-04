package chess;

import junit.framework.Assert;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Basic Unit Tests for the Position class
 */
public class PositionTest {

    @Test
    public void testStringParsingConstructor() {
        Position pos = new Position("d5");

        assertEquals("The column should be 'd'", 'd', pos.getColumn());
        assertEquals("The row should be 5", 5, pos.getRow());
    }

    @Test
    public void testPositionEquality() {
        Position one = new Position('a', 1);
        Position other = new Position('a', 1);
        Position different = new Position('b', 1);

        assertEquals("The positions should equal each other", one, other);
        assertNotSame("The positions should not be equal", one, different);
    }
    
    @Test
    public void testInvalidPosition() {
        
        try {
            new Position('a', 9);
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
        
        try {
            new Position('j', 7);
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
        
        try {
            new Position('A', 7);
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
        
        try {
            new Position("x7");
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }
    
}
