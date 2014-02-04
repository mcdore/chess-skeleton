package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    protected List<MovementModel> movementModels = new ArrayList<MovementModel>();

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }
    
    public List<MovementModel> getMovemementModels() {
        return movementModels;
    }

    protected abstract char getIdentifyingCharacter();
}
