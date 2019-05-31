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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class GUI {

    private static TextField startNumber = new TextField();
    private static TextField endNumber = new TextField();
    private static TextField increaseNumber = new TextField();
    private static Button btnStart = new Button("Start");
    private static Button btnStop = new Button("Stop");

    public static HashMap<Integer, InputValueObject> data;

    public static void setGUI(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
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
        primaryStage.show();


        // on start button click: return input data and save it to txt file;
        btnStart.setOnAction(event -> {

            if (!startNumber.getText().isEmpty() && !endNumber.getText().isEmpty() && !increaseNumber.getText().isEmpty()){
                data = new DataStorage().InputDataMap(startNumber.getText(), endNumber.getText(), increaseNumber.getText());
            }

            try {
                if (data != null){
                    new DataStorage().saveFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private static void inputValidation(TextField textField) {
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

}
