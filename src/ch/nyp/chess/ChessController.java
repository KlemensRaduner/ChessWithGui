package ch.nyp.chess;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ChessController implements Initializable {

    @FXML
    BorderPane borderPane;

    @FXML
    GridPane gridPane;

    private ChessGame chessGame;

    private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            Field f = (Field) e.getSource();
            if (e.getButton() == MouseButton.PRIMARY) {
                chessGame.handleLeftClick(f);
            } else {
                chessGame.handleRightCklick();
            }
        }
    };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        chessGame = new ChessGame();
        initializeViews();
    }

    private void initializeViews() {
        Field[][] board = chessGame.getBoard();
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                Field field = board[x][y];
                field.setOnMouseClicked(eventHandler);
                int size = 80;
                field.prefHeight(size);
                field.prefWidth(size);
                field.setMinWidth(size);
                field.setMinHeight(size);
                field.setAlignment(Pos.CENTER);
                field.setFont(new Font(size * 0.7));
                gridPane.add(field, x, y);
            }
        }
    }

}
