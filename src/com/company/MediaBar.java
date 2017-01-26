package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

/**
 * Created by Andru on 27.01.2017.
 */
public class MediaBar extends HBox {

    private Slider time = new Slider();
    private Slider vol = new Slider();
    private Button playButton = new Button("||");
    private Label volume = new Label("Volume: ");
    private MediaPlayer player;

    public MediaBar(MediaPlayer play) {
        player = play;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));

        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);

        HBox.setHgrow(time, Priority.ALWAYS);

        playButton.setPrefWidth(30);

        getChildren().add(playButton);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);
    }


}
