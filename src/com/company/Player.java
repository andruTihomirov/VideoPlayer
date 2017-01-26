package com.company;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Created by Andru on 27.01.2017.
 */
public class Player extends BorderPane {

    private Media media;
    private MediaPlayer player;
    private MediaView view;
    private Pane pane;

    private MediaBar bar;

    public Player(String filePath) {
        media = new Media(filePath);
        player = new MediaPlayer(media);
        view = new MediaView(player);
        pane = new Pane();

        pane.getChildren().add(view);

        setCenter(pane);

        bar = new MediaBar(player);
        setBottom(bar);
        setStyle("-fx-background-color: #bfc2c7");

        player.play();
    }

}
