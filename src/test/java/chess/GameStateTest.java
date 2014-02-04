package chess;

import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }
    
    @Test
    public void testGetActiveKingPosition() {
        state.reset();
        Position king = state.getActiveKingPosition();
        Assert.assertEquals(new Position("e1"), king);
    }
    
    @Test
    public void testInactiveInCheck() {
        GameState state = new GameState();
        state.placePiece(new King(Player.Black), new Position("c4"));
        state.placePiece(new King(Player.White), new Position("g1"));
        Assert.assertFalse(state.inactiveInCheck(new Position("c4")));
        
        state.placePiece(new Bishop(Player.White), new Position("f7"));        
        Assert.assertTrue(state.inactiveInCheck(new Position("c4")));
    }
    
    @Test
    public void testPutsInCheck() {
        GameState state = new GameState();
        state.placePiece(new King(Player.White), new Position("f3"));
        state.placePiece(new Bishop(Player.White), new Position("f5"));
        state.placePiece(new Rook(Player.Black), new Position("a2"));
        state.placePiece(new King(Player.Black), new Position("a8"));
        
        Assert.assertFalse(state.putsInCheck(new Position("f5"),
                new Position("d7"), new Position("f3")));
        Assert.assertTrue(state.putsInCheck(new Position("f3"),
                new Position("e2"), new Position("f3")));
        
        state.placePiece(new Rook(Player.Black), new Position("f8"));   
        Assert.assertTrue(state.putsInCheck(new Position("f5"),
                new Position("d7"), new Position("f3")));
        Assert.assertFalse(state.putsInCheck(new Position("f3"),
                new Position("e3"), new Position("f3")));
    }
    
    @Test
    public void testGetPlayerMoves() {
        GameState state = new GameState();
        state.placePiece(new Bishop(Player.White), new Position("c4"));
        state.placePiece(new Rook(Player.White), new Position("e6"));
        state.placePiece(new Pawn(Player.White), new Position("e5"));
        state.placePiece(new Rook(Player.Black), new Position("b5"));
        state.placePiece(new Bishop(Player.Black), new Position("f6"));
        Map<Position, Set<Position>> moves = state.getPlayerMoves(Player.White);
        
        Set<Position> pieceMoves = moves.get(new Position("c4"));
        Assert.assertTrue(pieceMoves.size() == 7);
        Assert.assertTrue(pieceMoves.contains(new Position("d5")));
        Assert.assertTrue(pieceMoves.contains(new Position("b5")));
        Assert.assertTrue(pieceMoves.contains(new Position("b3")));
        Assert.assertTrue(pieceMoves.contains(new Position("a2")));
        Assert.assertTrue(pieceMoves.contains(new Position("d3")));
        Assert.assertTrue(pieceMoves.contains(new Position("e2")));
        Assert.assertTrue(pieceMoves.contains(new Position("f1")));
        
        pieceMoves = moves.get(new Position("e6"));
        Assert.assertTrue(pieceMoves.size() == 7);
        Assert.assertTrue(pieceMoves.contains(new Position("e7")));
        Assert.assertTrue(pieceMoves.contains(new Position("e8")));
        Assert.assertTrue(pieceMoves.contains(new Position("a6")));
        Assert.assertTrue(pieceMoves.contains(new Position("b6")));
        Assert.assertTrue(pieceMoves.contains(new Position("c6")));
        Assert.assertTrue(pieceMoves.contains(new Position("d6")));
        Assert.assertTrue(pieceMoves.contains(new Position("f6")));
        
        pieceMoves = moves.get(new Position("e5"));
        Assert.assertTrue(pieceMoves.size() == 1);
        Assert.assertTrue(pieceMoves.contains(new Position("f6")));
    }
    
    @Test
    public void testGetMoves() {
        GameState state = new GameState();
        state.placePiece(new King(Player.White), new Position("g1"));
        state.placePiece(new Bishop(Player.White), new Position("d1"));
        state.placePiece(new Rook(Player.Black), new Position("g4"));
        
        Map<Position, Set<Position>> moves = state.getMoves();
        Set<Position> kingMoves = moves.get(new Position("g1"));
        Assert.assertEquals(kingMoves.size(), 4);
        Assert.assertTrue(kingMoves.contains(new Position("h1")));
        Assert.assertTrue(kingMoves.contains(new Position("h2")));
        Assert.assertTrue(kingMoves.contains(new Position("f1")));
        Assert.assertTrue(kingMoves.contains(new Position("f2")));
        
        Set<Position> bishopMoves= moves.get(new Position("d1"));
        Assert.assertEquals(bishopMoves.size(), 1);
        Assert.assertTrue(bishopMoves.contains(new Position("g4")));   
    }
    
    @Test
    public void testMove() {
        GameState state = new GameState();
        King k = new King(Player.White);
        state.placePiece(k, new Position("b6"));
        state.move(new Position("b6"), new Position("b7"));
        
        Assert.assertEquals(k, state.getPieceAt("b7"));
        Assert.assertEquals(null, state.getPieceAt("b6"));
    }
}
