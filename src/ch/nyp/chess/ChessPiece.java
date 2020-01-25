package ch.nyp.chess;

public abstract class ChessPiece {

    private boolean white;
    private boolean jump;

    public void onMove(Field from, Field to) { }

    public boolean canJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isValidMove(Field from, Field to){return false;}


}
