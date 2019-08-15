package ch.nyp.chess.pieces;

import ch.nyp.chess.ChessPiece;
import ch.nyp.chess.Field;

public class Bishop extends ChessPiece {

    public Bishop(boolean white) {
        setWhite(white);
    }

    @Override
    public String toString() {
        return isWhite() ? "\u2657" : "\u265D";
    }

    @Override
    public boolean isValidMove(Field from, Field to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        return (Math.abs(dy) == Math.abs(dx) && dx != 0) && emptyOrEnemy(to);
    }

}