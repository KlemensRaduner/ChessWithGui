package ch.nyp.chess.pieces;

import ch.nyp.chess.ChessPiece;
import ch.nyp.chess.Field;

public class King extends ChessPiece {

    public King(boolean white) {
        setWhite(white);
    }

    @Override
    public String toString() {
        return isWhite() ? "\u2655" : "\u265B";
    }

    @Override
    public boolean isValidMove(Field from, Field to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        return (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) && emptyOrEnemy(to);
    }

}