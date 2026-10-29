import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TipCalculator extends Application {

    @Override
    public void start(Stage stage) {

        // Create GUI components
        Label title = new Label("Tip Calculator");
        Label billLabel = new Label("Bill Amount:");
        TextField billField = new TextField();
        billField.setPromptText("Enter bill amount");

        Label tipLabel = new Label("Tip Percentage:");
        ComboBox<Integer> tipBox = new ComboBox<>();
        tipBox.getItems().addAll(5, 10, 15, 20, 25);
        tipBox.setValue(15);

        Button calculateButton = new Button("Calculate");
        Button clearButton = new Button("Clear");

        Label tipResult = new Label("Tip Amount: ₹0.00");
        Label totalResult = new Label("Total Amount: ₹0.00");
        Label message = new Label();

        // Arrange input components
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.add(billLabel, 0, 0);
        grid.add(billField, 1, 0);
        grid.add(tipLabel, 0, 1);
        grid.add(tipBox, 1, 1);

        HBox buttons = new HBox(10, calculateButton, clearButton);
        buttons.setAlignment(Pos.CENTER);

        // Handle Calculate button click
        calculateButton.setOnAction(e -> {
            try {
                double bill = Double.parseDouble(billField.getText().trim());
                int percentage = tipBox.getValue();

                if (!Double.isFinite(bill) || bill < 0) {
                    message.setText("Enter a valid non-negative amount.");
                    return;
                }

                double tip = bill * percentage / 100.0;
                double total = bill + tip;

                tipResult.setText(String.format(
                    "Tip Amount: ₹%.2f", tip));
                totalResult.setText(String.format(
                    "Total Amount: ₹%.2f", total));
                message.setText("");

            } catch (NumberFormatException ex) {
                message.setText("Please enter a valid bill amount.");
            }
        });

        // Handle Clear button click
        clearButton.setOnAction(e -> {
            billField.clear();
            tipBox.setValue(15);
            tipResult.setText("Tip Amount: ₹0.00");
            totalResult.setText("Total Amount: ₹0.00");
            message.setText("");
        });

        // Arrange the main layout
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        tipResult.setStyle("-fx-font-size: 16px;");
        totalResult.setStyle(
            "-fx-font-size: 18px; -fx-font-weight: bold;");
        message.setStyle("-fx-text-fill: red;");

        VBox root = new VBox(18, title, grid, buttons,
                             tipResult, totalResult, message);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));

        // Create the scene and display the window
        Scene scene = new Scene(root, 380, 350);

        stage.setTitle("JavaFX Tip Calculator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
