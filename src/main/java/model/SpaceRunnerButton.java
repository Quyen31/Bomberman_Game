package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceRunnerButton extends Button {

    private static final String FONT_PATH = "src/main/resources/Resources/kenvector_future.ttf";

    private static final String urlYellow01 = new File("src/main/resources/Resources/yellow_button01.png").toURI().toString();
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;-fx-background-image: url("+ urlYellow01 + ");";

    private static final String urlYellow02 = new File("src/main/resources/Resources/yellow_button00.png").toURI().toString();
    private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;-fx-background-image: url("+ urlYellow02 + ");";

    public SpaceRunnerButton(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
        //System.out.println(BUTTON_PRESSED_STYLE);
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));

        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }

    }

    private void setButtonPressedStyle() {
       // setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setStyle(BUTTON_PRESSED_STYLE);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY()-4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }
}
