package ch.nyp.chess.pieces;

import ch.nyp.chess.ChessPiece;
import ch.nyp.chess.Field;

public class Queen extends ChessPiece {

    public Queen(boolean white) {
        setWhite(white);
    }

    @Override
    public String toString() {
        return isWhite() ? "\u2654" : "\u265A";
    }

    @Override
    public boolean isValidMove(Field from, Field to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        boolean straight = (dx == 0 && dy != 0 || dx != 0 && dy == 0);
        boolean diagonal = (Math.abs(dy) == Math.abs(dx) && dx != 0);
        return (straight || diagonal) && emptyOrEnemy(to);
    }

}