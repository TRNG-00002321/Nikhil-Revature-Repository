package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToe extends Application
{

    private Button[][] buttons = new Button[3][3];
    private boolean xTurn = true;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Button btn = new Button();
                btn.setFont(new Font(36));
                btn.setPrefSize(100, 100);

                final int row = r;
                final int col = c;

                btn.setOnAction(e -> handleMove(btn, row, col));

                buttons[r][c] = btn;
                grid.add(btn, c, r);
            }
        }

        stage.setScene(new Scene(grid, 330, 330));
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    private void handleMove(Button btn, int r, int c) {
        if (!btn.getText().isEmpty()) return; // Already taken

        btn.setText(xTurn ? "X" : "O");
        if (checkWin()) {
            disableBoard();
            return;
        }
        xTurn = !xTurn;
    }
    private boolean checkWin()
    {
        // Check rows, cols, diagonals
        for (int i = 0; i < 3; i++) {
            if (equal(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (equal(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        if (equal(buttons[0][0], buttons[1][1], buttons[2][2])) return true;
        if (equal(buttons[0][2], buttons[1][1], buttons[2][0])) return true;

        return false;
    }

    private boolean equal(Button a, Button b, Button c) {
        return !a.getText().isEmpty() &&
                a.getText().equals(b.getText()) &&
                b.getText().equals(c.getText());
    }

    private void disableBoard() {
        for (Button[] row : buttons)
            for (Button b : row)
                b.setDisable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}