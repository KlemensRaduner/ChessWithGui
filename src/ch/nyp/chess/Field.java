package ch.nyp.chess;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Field extends Label implements PropertyChangeListener {
	private int x, y;
	private boolean highlighted;
	private ChessPiece chessPiece;

	public Field(int x, int y) {
		this.x = x;
		this.y = y;

		setHighlighted(false);

	
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

	public boolean isHighlighted() {
		return highlighted;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);

	}

	public void setChessPiece(ChessPiece chessPiece) {
		this.chessPiece = chessPiece;
		setText(chessPiece == null ? "" : chessPiece.toString());
	}

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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Field [x=" + x + ", y=" + y + ", chessPiece=" + chessPiece + "]";
	}

}
