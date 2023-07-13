package queens;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Eight Queens Problem");

        // init chessboard
        GridPane chessboard = createChessboard(8, 8);
        root.setCenter(chessboard);

        // buttonbox to be centered
        HBox buttonBox = createButtonBox();
        root.setBottom(buttonBox);
        BorderPane.setAlignment(buttonBox, Pos.CENTER);

        // init scene
        Scene scene = new Scene(root, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createChessboard(int rows, int cols) {
        GridPane chessboard = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle square = new Rectangle(50, 50);
                if ((row + col) % 2 == 0) {
                    square.setFill(Color.WHITE);
                } else {
                    square.setFill(Color.GRAY);
                }
                chessboard.add(square, col, row);
            }
        }
        return chessboard;
    }

    private HBox createButtonBox() {
        Button nextButton = new Button("Next");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> Platform.exit()); // lambda expression in an event handler

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(nextButton, closeButton);

        return buttonBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
