package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenereraRapport extends Stage {

    Stage primaryStage;
    Scene primaryScene;

    public void start() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GenereraRapport.fxml"));
        primaryStage = new Stage();
        primaryScene = new Scene(root, 545, 346);
        primaryStage.setScene(primaryScene);
        primaryStage.show();

    }

}
