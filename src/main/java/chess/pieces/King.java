package chess.pieces;

import chess.Player;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
        this.movementModels.add(new MovementModel(false, 1, 0));
        this.movementModels.add(new MovementModel(false, 0, 1));
        this.movementModels.add(new MovementModel(false, 1, 1));
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
}
