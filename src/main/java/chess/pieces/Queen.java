package chess.pieces;

import chess.Player;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
        this.movementModels.add(new MovementModel(true, 1, 0));
        this.movementModels.add(new MovementModel(true, 0, 1));
        this.movementModels.add(new MovementModel(true, 1, 1));
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
}
