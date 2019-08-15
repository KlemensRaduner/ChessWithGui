package ch.nyp.chess;

public interface Movable {

    public boolean canJump();

    public boolean isValidMove(Field from, Field to);


}
