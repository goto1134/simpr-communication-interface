package com.github.goto1134.simpr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
public class ExampleApp
        extends Application
        implements SimprClient {


    private SimprMessageHandler smartHouse;

    @Override
    public void start(Stage primaryStage)
            throws Exception {
        smartHouse = new SimprMessageHandler("SmartHouse", "Smart House Simulator", this);
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public boolean getConditionValue(int tableIndex, int conditionIndex) {
        System.out.println("condition " + tableIndex + " " + conditionIndex);
        return false;
    }

    @Override
    public boolean performEvent(int tableIndex, int event) {
        System.out.println("event " + tableIndex + " " + event);
        return true;
    }
}
