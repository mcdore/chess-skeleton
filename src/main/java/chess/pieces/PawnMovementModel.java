package chess.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.Player;
import chess.Position;

/**
 * @author mdore
 *
 */
public class PawnMovementModel extends MovementModel {
    
    Player currentPlayer;

    /**
     * @param multiple
     * @param columnar
     * @param ranknar
     */
    public PawnMovementModel(Player currentPlayer) {
        super();
        this.currentPlayer = currentPlayer;
    }
    
    @Override
    public List<List<Position>> getMoves(Position p) {
        List<Position> moveList = new ArrayList<Position>();
        
        if (currentPlayer == Player.White) {
            if (p.getRow() < 8) {
                moveList.add(new Position(p.getColumn(), p.getRow() + 1));
                if (p.getColumn() > 'a')
                    moveList.add(new Position((char) (p.getColumn() - 1), p.getRow() + 1));
                if (p.getColumn() < 'h')
                    moveList.add(new Position((char) (p.getColumn() + 1), p.getRow() + 1));
            }
            if (p.getRow() == 2) {
                moveList.add(new Position(p.getColumn(), p.getRow() + 2));
            }
        }
        else {
            if (p.getRow() > 1) {
                moveList.add(new Position(p.getColumn(), p.getRow() - 1));
                if (p.getColumn() > 'a')
                    moveList.add(new Position((char) (p.getColumn() - 1), p.getRow() - 1));
                if (p.getColumn() < 'h')
                    moveList.add(new Position((char) (p.getColumn() + 1), p.getRow() - 1));
            }
            if (p.getRow() == 7) {
                moveList.add(new Position(p.getColumn(), p.getRow() - 2));
            }
        }
        
        return Arrays.asList(moveList);
    }

}