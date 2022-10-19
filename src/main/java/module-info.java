module com.example.bomberman_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bomberman_game to javafx.fxml;
    exports com.example.bomberman_game;
}