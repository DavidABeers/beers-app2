package baseline;

import javafx.stage.Stage;

public class MainSceneController {
    private Stage primaryStage;
    public void setStage(Stage stage){
        // get the stage because it's needed for file choosers
        primaryStage = stage;
    }
}
