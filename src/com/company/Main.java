package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {

    private static final String TEST_VIDEO_FILE_PATH = "file:///D:/projects/VideoPlayer/SampleVideo_1280x720_1mb.mp4";

    private static final int WIDTH = 720;
    private static final int HEIGHT = 535;
    private static final String MENU_FILE_NAME = "File";
    private static final String MENU_ITEM_OPEN_NAME = "Open";

    private Player player;
    private FileChooser fileChooser;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Menu file = new Menu(MENU_FILE_NAME);
        MenuItem open = new MenuItem(MENU_ITEM_OPEN_NAME);

        MenuBar menu = new MenuBar();

        file.getItems().add(open);
        menu.getMenus().add(file);

        fileChooser = new FileChooser();

        open.setOnAction(event -> {
            player.getPlayer().pause();
            File file1 = fileChooser.showOpenDialog(primaryStage);
            if (file1 != null) {
                try {
                    player = new Player(file1.toURI().toURL().toExternalForm());
                    player.setTop(menu);
                    Scene scene = new Scene(player, WIDTH, HEIGHT, Color.BLACK);
                    primaryStage.setScene(scene);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        player = new Player(TEST_VIDEO_FILE_PATH);
        player.setTop(menu);
        Scene scene = new Scene(player, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
