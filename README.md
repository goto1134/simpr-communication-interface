[![Build Status](https://travis-ci.org/goto1134/simpr-java-client.svg?branch=master)](https://travis-ci.org/goto1134/simpr-java-client)

# simpr-java-client 

SIMPR Java client is a library for creating a client for SIMPR program written in java.

# Tutorial

1. Implement `com.github.goto1134.simpr.SimprClient`

```java
public class TestApp
        implements SimprClient {

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
```

2. Create a `com.github.goto1134.simpr.SimprMessageHandler`

```java
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
```

3. Start your application

4. Run SIMPR simulation
