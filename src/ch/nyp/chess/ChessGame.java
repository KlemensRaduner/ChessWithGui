package ch.nyp.chess;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ch.nyp.chess.pieces.Bishop;
import ch.nyp.chess.pieces.King;
import ch.nyp.chess.pieces.Knight;
import ch.nyp.chess.pieces.Pawn;
import ch.nyp.chess.pieces.Queen;
import ch.nyp.chess.pieces.Rook;

public class ChessGame {

    // contains all 64 Fields
    private Field[][] board;

    // true is wihte, false is black
    private boolean player;

    // field selected by the player can be null
    private Field selectedField;

    // list with all possible moves of piece on selectedField
    private List<Field> highlightedFields;

    // Tells the GuiController when to update
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ChessGame() {
        player = true;
        highlightedFields = new ArrayList<Field>();
        initBoard();
        placeChessPieces();
    }

    /**
     * Adds a PropertyChangeListener
     * @param l
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    /**
     * Handles clicks on Fields
     * @param f
     */
    public void handleLeftClick(Field f) {
        if (selectedField == null) {
            if (f.isPlayersPiece(player)) {
                selectField(f);
                highlighAllPossbleField(f);
            }
        } else {
            if (canMove(selectedField, f)) {
                movePiece(selectedField, f);
                removeSelection();
                removeHighlightedFields();
                player = !player;
                propertyChangeSupport.firePropertyChange("turn", !player, player);
            }
        }
    }

    /**
     * Select a Field to move with the next left click
     * @param f
     */

    public void selectField(Field f) {
        selectedField = f;
        f.setHighlighted(true);
    }

    /**
     * Rmemoves the selection if there is one
     */
    public void removeSelection() {
        if (selectedField != null) {
            selectedField.setHighlighted(false);
            selectedField = null;
        }
    }

    /**
     * Handles right clicks
     */

    public void handleRightCklick() {
        removeSelection();
        removeHighlightedFields();
    }

    /**
     * Removes all highlighted fields
     */
    public void removeHighlightedFields() {
        for (Field f : highlightedFields) {
            f.setHighlighted(false);
        }
    }

    /**
     * Highlights all Fields the Piece on the current Field can move to
     * @param field
     */
    private void highlighAllPossbleField(Field field) {
        highlightedFields = getAllPossibleFields(field);
        for (Field f : highlightedFields) {
            f.setHighlighted(true);
        }
    }

    /**
     * Returns a List with all Fields the Piece on the current Field can move to
     * @param field
     * @return
     */

    private List<Field> getAllPossibleFields(Field field) {
        List<Field> list = new ArrayList<Field>();
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                if (canMove(field, board[x][y])) {
                    list.add(board[x][y]);
                }
            }
        }
        return list;
    }

    /**
     * Returns true if Piece can move from one Field to another, false otherwise
     * @param from
     * @param to
     * @return
     */
    private boolean canMove(Field from, Field to) {
        if (from.getChessPiece().isValidMove(from, to)) {
            if (checkPath(from, to)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves a Piece to an new Field
     * @param from
     * @param to
     */
    private void movePiece(Field from, Field to) {
        to.setChessPiece(from.getChessPiece());
        from.setChessPiece(null);
        to.getChessPiece().onMove(from, to);

    }

    /**
     * returns the x coordinate when moved one field towards the Field to
     * @param currentX
     * @param to
     * @return
     */
    private int moveCurrentX(int currentX, Field to) {
        if (currentX > to.getX()) {
            currentX -= 1;
        } else if (currentX < to.getX()) {
            currentX += 1;
        }
        return currentX;
    }

    /**
     * returns the Y coordinate when moved one field towards the Field to
     * @param currentY
     * @param to
     * @return
     */
    private int moveCurrentY(int currentY, Field to) {
        if (currentY > to.getY()) {
            currentY -= 1;
        } else if (currentY < to.getY()) {
            currentY += 1;
        }
        return currentY;
    }

    /**
     * returns true if the piece on the field from can move to the field to
     * @param from
     * @param to
     * @return
     */
    private boolean checkPath(Field from, Field to) {
        if (!from.getChessPiece().canJump()) {
            int currentX = from.getX();
            int currentY = from.getY();

            currentX = moveCurrentX(currentX, to);
            currentY = moveCurrentY(currentY, to);

            while (currentX != to.getX() || currentY != to.getY()) {

                if (board[currentX][currentY].getChessPiece() != null) {
                    return false;
                }

                currentX = moveCurrentX(currentX, to);
                currentY = moveCurrentY(currentY, to);
            }
        }
        return true;
    }


    /**
     * initializes the board array
     */
    private void initBoard() {
        board = new Field[9][9];
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                board[x][y] = new Field(x, y);
            }
        }
    }

    /**
     * places all piesecs in the starting spots
     */
    private void placeChessPieces() {
        for (int i = 1; i <= 8; i++) {
            board[i][2].setChessPiece(new Pawn(false));
            board[i][7].setChessPiece(new Pawn(true));
        }
        board[1][1].setChessPiece(new Rook(false));
        board[1][8].setChessPiece(new Rook(true));
        board[8][1].setChessPiece(new Rook(false));
        board[8][8].setChessPiece(new Rook(true));

        board[2][1].setChessPiece(new Knight(false));
        board[2][8].setChessPiece(new Knight(true));
        board[7][1].setChessPiece(new Knight(false));
        board[7][8].setChessPiece(new Knight(true));

        board[3][1].setChessPiece(new Bishop(false));
        board[3][8].setChessPiece(new Bishop(true));
        board[6][1].setChessPiece(new Bishop(false));
        board[6][8].setChessPiece(new Bishop(true));

        board[5][1].setChessPiece(new Queen(false));
        board[5][8].setChessPiece(new Queen(true));

        board[4][1].setChessPiece(new King(false));
        board[4][8].setChessPiece(new King(true));

    }


    /**
     * returns board array
     * @return
     */
    public Field[][] getBoard() {
        return board;
    }

}
