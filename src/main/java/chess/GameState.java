package chess;


import chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public String move(Position from, Position to) {
        Piece piece = positionToPieceMap.get(from);
        positionToPieceMap.put(to, piece);
        positionToPieceMap.remove(from);
        
        if (currentPlayer == Player.White) currentPlayer = Player.Black;
        else currentPlayer = Player.White;
        
        return "";
    }
    
    private void addMoveToPosition(Map<Position, Set<Position>> possibleMoves, Position to, Position from) {
        Set<Position> listFromPosition = possibleMoves.get(from);
        if (listFromPosition == null) {
            listFromPosition = new HashSet<Position>();
        }
        listFromPosition.add(to);
        possibleMoves.put(from, listFromPosition);
    }

    //for all of a given player's pieces, determines legal moves (IGNORING CHECK)
    protected Map<Position, Set<Position>> getPlayerMoves(Player player) {
        Map<Position, Set<Position>> possibleMoves = new HashMap<Position, Set<Position>>();
        
        for (Position pos : positionToPieceMap.keySet()) {
            Piece piece = positionToPieceMap.get(pos);
            if ((piece != null) && (piece.getOwner() == player))
                for (MovementModel m : piece.getMovemementModels())
                    for (List<Position> list : m.getMoves(pos))
                        for (Position p : list) {
                            String identifier = String.valueOf(piece.getIdentifier()).
                                    toUpperCase();
                            Piece atPosition = positionToPieceMap.get(p);
                            if (identifier.equals("P")) {
                                if (p.getColumn() != pos.getColumn()) {
                                    if ((atPosition != null) && (atPosition.getOwner() != player)){
                                        addMoveToPosition(possibleMoves, p, pos);
                                    }   
                                }
                                else if (atPosition == null) {
                                    addMoveToPosition(possibleMoves, p, pos);
                                }
                            }
                            else if (atPosition == null) {
                                addMoveToPosition(possibleMoves, p, pos);
                            }
                            else {
                                if (atPosition.getOwner() != player){
                                    addMoveToPosition(possibleMoves, p, pos);
                                }
                                break;
                            }
                        }
        }
        return possibleMoves;
    }
    
    protected Position getActiveKingPosition() {
        for (Position pos : positionToPieceMap.keySet()) {
            Piece piece = positionToPieceMap.get(pos);
            String identifier = String.valueOf(piece.getIdentifier()).toUpperCase();
            if ((piece.getOwner() == currentPlayer) && (identifier.equals("K")))
                return pos;
        }
        return null;
    }
    
    //calls getPlayerMoves to get legal moves ignoring check status
    //then returns subset of these moves that don't cause check
    public Map<Position, Set<Position>> getMoves() {
        Map<Position, Set<Position>> possibleMoves =  getPlayerMoves(currentPlayer);
        Position kingPosition = getActiveKingPosition();
        
        for (Position from : possibleMoves.keySet()) {
            Set<Position> valid = new HashSet<Position>();
            for (Position to : possibleMoves.get(from)) {
                if(!putsInCheck(from, to, kingPosition)) {
                    valid.add(to);
                }   
            }
            possibleMoves.put(from, valid);
        }
        
        return possibleMoves;
    }
    
    //tests if a move will cause check by doing the move, checking check, then undoing
    //king position is the current one if moving king, not the expected new one
    protected boolean putsInCheck(Position current, Position potential, Position king) {
        Piece currentlyOccupying = positionToPieceMap.get(potential);
        if (king.equals(current)) king = potential;
        move(current, potential);
        
        boolean retVal = inactiveInCheck(king);
        
        move(potential, current);
        if (currentlyOccupying != null) placePiece(currentlyOccupying, potential);
        return retVal;
    }
    
    //true if currentPlayer has legal move to non-currentPlayer's king
    protected boolean inactiveInCheck(Position inactiveKing) {
        boolean retVal = false;
        Map<Position, Set<Position>> opponentMoves = getPlayerMoves(currentPlayer);
        for (Set<Position> list : opponentMoves.values())
            for (Position p : list)
                if (p.equals(inactiveKing))
                    retVal = true;
        return retVal;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    protected void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }
}
