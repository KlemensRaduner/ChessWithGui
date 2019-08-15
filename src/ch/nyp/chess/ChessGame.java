package ch.nyp.chess;

import java.util.ArrayList;
import java.util.List;

import ch.nyp.chess.pieces.Bishop;
import ch.nyp.chess.pieces.King;
import ch.nyp.chess.pieces.Knight;
import ch.nyp.chess.pieces.Pawn;
import ch.nyp.chess.pieces.Queen;
import ch.nyp.chess.pieces.Rook;

public class ChessGame {

    private Field[][] board;
    private boolean player;
    private Field selectedField;
    private List<Field> highlightedFields;

    public ChessGame() {
        player = true;
        highlightedFields = new ArrayList<Field>();
        initBoard();
        placeChessPieces();
    }

    public void handleLeftClick(Field f) {
        if (selectedField == null) {
            if (isPlayersPiece(player, f)) {
                selectField(f);
                highlighAllPossbleField(f);
            }
        } else {
            if (canMove(selectedField, f)) {
                movePiece(selectedField, f);
                removeSelection();
                removeHighlightedFields();
                player = !player;
            }
        }
    }

    public void selectField(Field f) {
        selectedField = f;
        f.setHighlighted(true);
    }

    public void removeSelection() {
        if (selectedField != null) {
            selectedField.setHighlighted(false);
            selectedField = null;
        }
    }

    public void handleRightCklick() {
        removeSelection();
        removeHighlightedFields();
    }

    public void removeHighlightedFields() {
        for (Field f : highlightedFields) {
            f.setHighlighted(false);
        }
    }

    private void highlighAllPossbleField(Field field) {

        highlightedFields = getAllPossibleFields(field);
        for (Field f : highlightedFields) {
            f.setHighlighted(true);
        }
    }

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

    private boolean canMove(Field from, Field to) {
        if (from.getChessPiece().isValidMove(from, to)) {
            if (checkPath(from, to)) {
                return true;
            }
        }
        return false;
    }

    private void movePiece(Field from, Field to) {
        to.setChessPiece(from.getChessPiece());
        from.setChessPiece(null);
        to.getChessPiece().onMove(from, to);

    }

    private int moveCurrentX(int currentX, Field to) {
        if (currentX > to.getX()) {
            currentX -= 1;
        } else if (currentX < to.getX()) {
            currentX += 1;
        }
        return currentX;
    }

    private int moveCurrentY(int currentY, Field to) {
        if (currentY > to.getY()) {
            currentY -= 1;
        } else if (currentY < to.getY()) {
            currentY += 1;
        }
        return currentY;
    }

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

    public static boolean isPlayersPiece(boolean player, Field f) {
        return f.getChessPiece() != null && player == f.getChessPiece().isWhite();
    }

    private void initBoard() {
        board = new Field[9][9];
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                board[x][y] = new Field(x, y);
            }
        }
    }

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

    public Field[][] getBoard() {
        return board;
    }

}
