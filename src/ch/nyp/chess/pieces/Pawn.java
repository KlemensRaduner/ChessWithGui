package ch.nyp.chess.pieces;

import ch.nyp.chess.ChessPiece;
import ch.nyp.chess.Field;

public class Pawn extends ChessPiece {

    private boolean moved;

    public Pawn(boolean white) {
        setWhite(white);
        setJump(false);
    }

    @Override
    public String toString() {
        return isWhite() ? "\u2659" : "\u265F";
    }

    @Override
    public void onMove(Field from, Field to) {
        moved = true;
        if(to.getY() == 1 || to.getY() == 8) {
            to.setChessPiece(new Queen(isWhite()));
        }
    }

    @Override
    public boolean isValidMove(Field from, Field to) {
        int y = isWhite() ? -1 : 1;
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        // straight 1
        if (dx == 0 && dy == y && to.getChessPiece() == null) {
            return true;
        }

        // straight 2
        if (!moved && dx == 0 && dy == y * 2 && to.getChessPiece() == null) {
            return true;
        }

        // diagonal
        if (to.getChessPiece() != null && !to.isPlayersPiece(isWhite()) && Math.abs(dx) == 1 && dy == y) {
            return true;
        }

        return false;
    }

}
