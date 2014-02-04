package chess;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import chess.pieces.MovementModel;
import chess.pieces.PawnMovementModel;

/**
 * @author mdore
 *
 */
public class MovementModelTest {
    
    @Test
    public void testValidMoveNotMultiple() {
        MovementModel m = new MovementModel(false, 0, 1);
        Position p = new Position("b3");
        List<Position> actualMoves = m.directionalMoves(p, 1, 1);
        Assert.assertTrue(actualMoves.size() == 1);
        Assert.assertTrue(actualMoves.contains(new Position("b4")));
    }
    
    @Test
    public void testValidMoveMultiple() {
        MovementModel m = new MovementModel(true, 1, 1);
        Position p = new Position("e5");
        List<Position> actualMoves = m.directionalMoves(p, 1, 1);
        Assert.assertEquals(actualMoves.get(0), new Position("f6"));
        Assert.assertEquals(actualMoves.get(1), new Position("g7"));
        Assert.assertEquals(actualMoves.get(2), new Position("h8"));
    }
    
    @Test
    public void testGetMoves() {
        MovementModel m = new MovementModel(true, 1, 0);
        Position p = new Position("c4");
        List<List<Position>> moves = m.getMoves(p);
        Assert.assertEquals(moves.get(0).get(0), new Position("d4"));
        Assert.assertEquals(moves.get(0).get(1), new Position("e4"));
        Assert.assertEquals(moves.get(0).get(2), new Position("f4"));
        Assert.assertEquals(moves.get(0).get(3), new Position("g4"));
        Assert.assertEquals(moves.get(0).get(4), new Position("h4"));
        Assert.assertEquals(moves.get(1).get(0), new Position("b4"));
        Assert.assertEquals(moves.get(1).get(1), new Position("a4"));
    }
    
    @Test
    public void testPawnMovementModel() {
        MovementModel m = new PawnMovementModel(Player.White);
        Position p1 = new Position("c4");        
        Position p2 = new Position("d2");
        Position p3 = new Position("a5");
        Position p4 = new Position("h3");
        
        List<Position> moves = m.getMoves(p1).get(0);
        Assert.assertEquals(moves.size(), 3);
        Assert.assertTrue(moves.contains(new Position("c5")));
        Assert.assertTrue(moves.contains(new Position("b5")));
        Assert.assertTrue(moves.contains(new Position("d5")));
        moves = m.getMoves(p2).get(0);
        Assert.assertEquals(moves.size(), 4);
        Assert.assertTrue(moves.contains(new Position("d3")));
        Assert.assertTrue(moves.contains(new Position("d4")));
        Assert.assertTrue(moves.contains(new Position("c3")));
        Assert.assertTrue(moves.contains(new Position("e3")));
        moves = m.getMoves(p3).get(0);
        Assert.assertEquals(moves.size(), 2);
        Assert.assertTrue(moves.contains(new Position("a6")));
        Assert.assertTrue(moves.contains(new Position("b6")));
        moves = m.getMoves(p4).get(0);
        Assert.assertEquals(moves.size(), 2);
        Assert.assertTrue(moves.contains(new Position("h4")));
        Assert.assertTrue(moves.contains(new Position("g4")));  
    }
}