package chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.Player;
import chess.Position;

/**
 * @author mdore
 *
 */
public class MovementModel {

    private boolean multiple;
    private int columnar;
    private int ranknar;
    
    public MovementModel() {
    }
    
    public MovementModel(boolean multiple, int columnar, int ranknar) {
        this.multiple = multiple;
        this.columnar = columnar;
        this.ranknar = ranknar;
    }
    
    public List<Position> directionalMoves(Position p, int verticalFactor, int horizontalFactor) {
        List<Position> moves = new ArrayList<Position>();
        
        Position newPosition;
        
        if (Position.validPosition((char) (p.getColumn() + (columnar * verticalFactor)),
                p.getRow() + (ranknar * horizontalFactor))) {
            newPosition = new Position((char) (p.getColumn() + (columnar * verticalFactor)),
                    p.getRow() + (ranknar * horizontalFactor));
            moves.add(newPosition);
        }
        else {
            return moves;
        }
        
        if(multiple) {
            while(true) {
                if (Position.validPosition((char) (newPosition.getColumn() + (columnar * verticalFactor)),
                        newPosition.getRow() + (ranknar * horizontalFactor))) {
                    newPosition = new Position((char) (newPosition.getColumn() + (columnar * verticalFactor)),
                            newPosition.getRow() + (ranknar * horizontalFactor));
                    moves.add(newPosition);
                }
                else {
                    return moves;
                }
            }
        }
        return moves;
    }
    
    public List<List<Position>> getMoves(Position p) {
        List<List<Position>> moves = new ArrayList<List<Position>>();
        
        moves.add(directionalMoves(p, 1, 1));
        if (ranknar != 0) moves.add(directionalMoves(p, 1, -1));
        if (columnar != 0) moves.add(directionalMoves(p, -1, 1));
        if ((ranknar != 0) && (columnar != 0)) moves.add(directionalMoves(p, -1, -1));

        return moves;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public int getColumnar() {
        return columnar;
    }

    public void setColumnar(int columnar) {
        this.columnar = columnar;
    }

    public int getRanknar() {
        return ranknar;
    }

    public void setRanknar(int ranknar) {
        this.ranknar = ranknar;
    }
}
