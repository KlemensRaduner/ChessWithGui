package ch.nyp.chess.pieces;

import ch.nyp.chess.ChessPiece;
import ch.nyp.chess.Field;

public class Knight extends ChessPiece {

    public Knight(boolean white) {
        setWhite(white);
        setJump(true);
    }

    @Override
    public String toString() {
        return isWhite() ? "\u2658" : "\u265E";
    }

    @Override
    public boolean isValidMove(Field from, Field to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        return (Math.abs(dx) == 2 && Math.abs(dy) == 1 || Math.abs(dx) == 1 && Math.abs(dy) == 2) && !to.isPlayersPiece(isWhite());
    }

}