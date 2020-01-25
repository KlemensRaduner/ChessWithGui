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
        return (from.isDiagonal(to) || from.isStraight(to)) && !to.isPlayersPiece(isWhite());
    }

}