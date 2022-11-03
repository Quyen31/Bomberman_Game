package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class ShowView implements MusicGame, Image_Game, Contruction_Game {
    protected AnchorPane Pane;
    protected Scene Scene;
    protected Stage Stage;

    public ShowView (String nameView) {
        Pane = new AnchorPane();
        Scene = new Scene(Pane, SCREEN_WIDTH, SCREEN_HEIGHT);
        Pane.setLayoutX(0);
        Pane.setLayoutY(0);
        Stage = new Stage();
        Stage.setTitle(nameView);
        Stage.setScene(Scene);
    }
}
