package chess.pieces;

import chess.Player;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    
    public Bishop(Player owner) {
        super(owner);
        this.movementModels.add(new MovementModel(true, 1, 1));
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }
}
