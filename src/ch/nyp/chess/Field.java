package ch.nyp.chess;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Field extends Label {
	// coordinates of the Field on the chess board
    private int x, y;

    // true if blue
    private boolean highlighted;

    // the piece currently on the Field can be null
    private ChessPiece chessPiece;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        setHighlighted(false);
    }

	/**
	 * sets the chess piece
	 * @param chessPiece
	 */
	public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
        setText(chessPiece == null ? "" : chessPiece.toString());
    }

	/**
	 * Changes the Style of the Field
	 * @param highlighted
	 */
	public void setHighlighted(boolean highlighted) {
        if (highlighted) {
            setStyle("-fx-border-color: black;");
            setBackground(
                    new Background(new BackgroundFill(Color.rgb(150, 200, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            setStyle("-fx-border-color: none;");
            setBackground(new Background(
                    new BackgroundFill(((x + y) % 2 == 0) ? Color.rgb(204, 153, 102) : Color.rgb(255, 204, 153),
                            CornerRadii.EMPTY, Insets.EMPTY)));
        }
        this.highlighted = highlighted;
    }

    /**
     * return true if this field is on a straight line with to
     * @param to
     * @return
     */
    public  boolean isStraight( Field to) {
        int dx = to.getX() - this.getX();
        int dy = to.getY() - this.getY();

        return (dx == 0 && dy != 0 || dx != 0 && dy == 0);
    }

    /**
     * returns true if this field is on a diagonal line with to
     * @param to
     * @return
     */
    public boolean isDiagonal( Field to) {
        int dx = to.getX() - this.getX();
        int dy = to.getY() - this.getY();

        return (Math.abs(dy) == Math.abs(dx) && dx != 0);
    }

    /**
     * Return true if there is a piece on the field with the wanted color
     * @param player
     * @return
     */
    public  boolean isPlayersPiece(boolean player) {
        return this.getChessPiece() != null && player == this.getChessPiece().isWhite();
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
