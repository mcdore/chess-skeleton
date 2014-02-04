package chess.pieces;

import chess.Player;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
        this.movementModels.add(new PawnMovementModel(owner));
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }
}
