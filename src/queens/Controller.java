package queens;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;


public class Controller {

    private Stage primaryStage;
    private GridPane chessboard;
    private int[] distribution; // stores the solution in individual rows
    private Button nextButton;
    private Button closeButton;
    private List<StackPane> stackPanes; // track the stackpanes that are added

    private DatabaseManager databaseManager; // connection to the database

    // constructor
    public Controller(Stage primaryStage, GridPane chessboard) {
        this.primaryStage = primaryStage;
        this.chessboard = chessboard;
        stackPanes = new ArrayList<>();
        this.distribution = new int[8];
        for (int i = 0; i < 8; i++){
            distribution[i] = -1; // equivalent to empty
        }
        this.databaseManager = new DatabaseManager();
    }

    public void setButtons(Button nextButton, Button closeButton) {
        this.nextButton = nextButton;
        this.closeButton = closeButton;
    }

    public void handleCloseButtonAction(ActionEvent event) {
        primaryStage.close();
    }

    public void handleNextButtonAction(ActionEvent event) {
        findFirstSolution();
        removeCircles();
        drawPositions(distribution, chessboard);
        String solution = Arrays.toString(distribution); // convert to a string
        databaseManager.insertSolution(solution); // send the solution to the database

    }

    private void findFirstSolution(){
        int occupied; // number of correctly placed queens
        if (distribution[7] == -1){ // first time
            occupied = 1;
            distribution[0] = 0;
        }else if (distribution[0] == 7){ // already found all -> starting again
            occupied = 1;
            for (int i = 0; i < 8; i++){
                distribution[i] = -1; // equivalent to empty
            }
            distribution[0] = 0;
        }else{ // finding another solution
            occupied = 7;
        }

        while (occupied < 8 && occupied >= 0){
            int column = distribution[occupied]+1;
            distribution[occupied] = -1; // making the current empty
            while (column < 8 && !(isSafe(occupied, column))){
                column++;
            }
            if (column == 8){ // no convenient position, getting back by one
                distribution[occupied] = -1; // make it empty
                occupied--;
            }else{ // found solution, keep the position in distribution and move forward
                distribution[occupied] = column;
                occupied++;
            }
        }
    }

    private void drawPositions(int [] distribution, GridPane chessboard){
        for (int row = 0; row < 8; row++) {
            StackPane stackPane = new StackPane(); // container
            stackPane.setAlignment(Pos.CENTER); // center the child circle
            stackPanes.add(stackPane); // add stack pane to the list
            Circle circle = new Circle(20);
            circle.setFill(Color.RED);
            stackPane.getChildren().add(circle); // add the circle to the StackPane
            chessboard.add(stackPane, distribution[row], row);
        }
    }

    private void removeCircles() {
        for (StackPane stackPane : stackPanes) {
            chessboard.getChildren().remove(stackPane);
        }
        stackPanes.clear();
    }

    private boolean isSafe(int row, int col) {
        // Check column
        for (int i = 0; i < 8; i++) {
            if (distribution[i] == col) {
                return false;
            }
        }

        // Check row
        for (int i = 0; i < 8; i++) {
            if (distribution[row] == i) {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (distribution[i] == j) {
                return false;
            }
        }

        // Check lower-right diagonal
        for (int i = row, j = col; i < 8 && j < 8; i++, j++) {
            if (distribution[i] == j) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < 8; i--, j++) {
            if (distribution[i] == j) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i < 8 && j >= 0; i++, j--) {
            if (distribution[i] == j) {
                return false;
            }
        }
        return true;
    }
}