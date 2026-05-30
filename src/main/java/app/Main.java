package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("MainView.fxml")
        );

        Scene scene = new Scene(loader.load(), 640, 700);

        stage.setTitle("Exportador SGE - PostgreSQL -> TXT");
        stage.setMinWidth(640);
        stage.setMinHeight(700);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}