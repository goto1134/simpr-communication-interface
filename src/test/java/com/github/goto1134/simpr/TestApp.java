package com.github.goto1134.simpr;

import com.github.goto1134.simpr.SimprClient;
import com.github.goto1134.simpr.SimprMessageHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
public class TestApp
        extends Application
        implements SimprClient {


    private SimprMessageHandler smartHouse;

    @Override
    public void start(Stage primaryStage)
            throws Exception {
        smartHouse = new SimprMessageHandler("SmartHouse", "Smart House Simulator", this);

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Simpr Test Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public boolean getConditionValue(int tableIndex, int conditionIndex) {
        Alert alert = new Alert(CONFIRMATION, "Is condition " + tableIndex + " " + conditionIndex + " true?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Checking condition");
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.get() == ButtonType.YES;
    }

    @Override
    public void onEndStateReached(int tableIndex) {
        Alert alert = new Alert(INFORMATION, "End state reached in table " + tableIndex);
        alert.show();
    }

    @Override
    public boolean performEvent(int tableIndex, int event) {
        System.out.println("event " + tableIndex + " " + event + " occured");
        return true;
    }
}
