import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class Controller {

    private GridPane chessboard;
    private Button nextButton;
    private Button closeButton;

    private int[][] board;
    private int currentRow;

    public void initialize(GridPane chessboard, Button nextButton, Button closeButton) {
        this.chessboard = chessboard;
        this.nextButton = nextButton;
        this.closeButton = closeButton;

        board = new int[8][8];
        //currentRow = 0;

        //nextButton.setOnAction(this::handleNextButtonAction);
        //closeButton.setOnAction(this::handleCloseButtonAction);
    }

    public void handleNextButtonAction(ActionEvent event) {
        //TODO: předělat
//        if (currentRow >= 8) {
//            return; // Already found a solution
//        }
//
//        for (int col = 0; col < 8; col++) {
//            if (isSafe(currentRow, col)) {
//                board[currentRow][col] = 1;
//                Circle circle = new Circle(20);
//                chessboard.add(circle, col, currentRow);
//            }
//        }
//
//        currentRow++;
    }

    private boolean isSafe(int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower-right diagonal
        for (int i = row, j = col; i < 8 && j < 8; i++, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < 8; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i < 8 && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }
}