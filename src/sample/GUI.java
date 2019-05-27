package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GUI {

    public static TextField startNumber = new TextField();
    public static TextField endNumber = new TextField();
    public static TextField increaseNumber = new TextField();
    public static Button btnStart = new Button("Start");
    public static Button btnStop = new Button("Stop");

    public static void setGUI(Stage primaryStage) {
        primaryStage.setTitle("Prime factors calculator");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        //set hint in input fields
        startNumber.setPromptText("Start number");
        endNumber.setPromptText("End number");
        increaseNumber.setPromptText("Increase");


        // set input validation
        inputValidation(startNumber);
        inputValidation(endNumber);
        inputValidation(increaseNumber);

        layout.getChildren().addAll(startNumber, endNumber, increaseNumber, btnStart, btnStop);
        Scene scene = new Scene(layout, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        btnStart.setOnAction(event -> {
            try {
                String input = startNumber.getText().toString();
                saveFile(input);
            } catch (FileNotFoundException e) {
                System.out.println("klaida" + e.toString());
            }
        });

    }

    private static void inputValidation(TextField textField) {
        //alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("Only numbers are accepted. Max length 10");

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")) {
                textField.setText(oldValue);
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                alert.show();
            }
        });
    }

    public static void saveFile (String numbers) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("numbers.txt");
        out.println(numbers);
        if (out != null){
            out.close();
        }
    }

}
