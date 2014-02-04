package chess;

/**
 * Describes a position on the Chess Board
 */
public class Position {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';
    private int row;
    private char column;

    /**
     * Create a new position object
     *
     * @param column The column
     * @param row The row
     */
    public Position(char column, int row) {
        if (!validPosition(column, row)) throw new RuntimeException("Invalid position");
        this.row = row;
        this.column = column;
    }
    
    public static boolean validPosition(char column, int row) {
        if ((row > MAX_ROW) || (row < MIN_ROW))
            return false;
        else if ((column > MAX_COLUMN) || (column < MIN_COLUMN))
            return false;
        return true;
    }
    
    public static boolean validPosition(String colrow) {
        if (colrow.length() != 2) return false;
        return validPosition(colrow.toCharArray()[0], Character.digit(colrow.toCharArray()[1], 10));
    }

    /**
     * Create a new Position object by parsing the string
     * @param colrow The column and row to use.  I.e. "a1", "h7", etc.
     */
    public Position(String colrow) {
        this(colrow.toCharArray()[0], Character.digit(colrow.toCharArray()[1], 10));
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;

        //noinspection RedundantIfStatement
        if (row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + (int) column;
        return result;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

}
