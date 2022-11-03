package bomberman;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

import java.io.IOException;

public class AppBomberMain extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        ViewManager manager = new ViewManager();
        stage = manager.getMainStage();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}