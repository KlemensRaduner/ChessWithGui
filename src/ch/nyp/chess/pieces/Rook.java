package ch.nyp.chess.pieces;

import ch.nyp.chess.ChessPiece;
import ch.nyp.chess.Field;

public class Rook extends ChessPiece {

    public Rook(boolean white) {
        setWhite(white);
    }

    @Override
    public String toString() {
        return isWhite() ? "\u2656" : "\u265C";
    }

    @Override
    public boolean isValidMove(Field from, Field to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        return (dx == 0 && dy != 0 || dx != 0 && dy == 0) && emptyOrEnemy(to);
    }

}
