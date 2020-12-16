import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;
import view.ui.gui.HubController;

import java.io.IOException;

public class ApplicationStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/ui/gui/MediaContentManagementSystem.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("MediaContentManagementSystem");
        primaryStage.setMaximized(true);
        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getResource("view/ui/gui/Style.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.F11));

        ((HubController) loader.getController()).scale(scene);
        primaryStage.show();
    }
}
