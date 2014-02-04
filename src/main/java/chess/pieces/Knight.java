package chess.pieces;

import chess.Player;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
        this.movementModels.add(new MovementModel(false, 2, 1));
        this.movementModels.add(new MovementModel(false, 1, 2));
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }
}
