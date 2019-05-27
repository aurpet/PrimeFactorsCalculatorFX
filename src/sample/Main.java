package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            new GUI().setGUI(primaryStage);
        } catch (Exception e){
            System.out.println("GUI error: " + e.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
