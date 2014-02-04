package chess.pieces;

import chess.Player;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
        this.movementModels.add(new MovementModel(true, 0, 1));
        this.movementModels.add(new MovementModel(true, 1, 0));
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }
}
