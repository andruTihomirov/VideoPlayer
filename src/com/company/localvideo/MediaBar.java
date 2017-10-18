package com.company.localvideo;

import javafx.application.Platform;
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

    private static final String PAUSED_BUTTON_TEXT = "||";
    private static final String VOLUME_LABEL = "Volume: ";

    private static final int INSETS_TOP = 5;
    private static final int INSETS_RIGHT = 10;
    private static final int INSETS_BOTTOM = 5;
    private static final int INSETS_LEFT = 10;
    private static final String PLAY_BUTTON_TEXT = ">";
    private static final int PREF_WIDTH_VOLUME_SLIDER = 70;
    private static final int MIN_WIDTH_VOLUME_SLIDER = 30;
    private static final int VALUE_SLIDER = 100;
    public static final int PREF_WIDTH_PLAY_BUTTON = 30;

    private Slider time = new Slider();
    private Slider vol = new Slider();
    private Button playButton = new Button(PAUSED_BUTTON_TEXT);
    private Label volume = new Label(VOLUME_LABEL);
    private MediaPlayer player;

    public MediaBar(MediaPlayer play) {
        player = play;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(INSETS_TOP, INSETS_RIGHT, INSETS_BOTTOM, INSETS_LEFT));

        vol.setPrefWidth(PREF_WIDTH_VOLUME_SLIDER);
        vol.setMinWidth(MIN_WIDTH_VOLUME_SLIDER);
        vol.setValue(VALUE_SLIDER);

        HBox.setHgrow(time, Priority.ALWAYS);

        playButton.setPrefWidth(PREF_WIDTH_PLAY_BUTTON);

        getChildren().add(playButton);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);

        playButton.setOnAction(event -> {
            MediaPlayer.Status status = player.getStatus();

            if (status == MediaPlayer.Status.PLAYING) {
                if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                    player.seek(player.getStartTime());
                    player.play();
                    playButton.setText(PAUSED_BUTTON_TEXT);
                } else {
                    player.pause();
                    playButton.setText(PLAY_BUTTON_TEXT);
                }
            }

            if (status == MediaPlayer.Status.PAUSED) {
                player.play();
                playButton.setText(PAUSED_BUTTON_TEXT);
            }
        });

        player.currentTimeProperty().addListener(observable -> updateValues());

        time.valueProperty().addListener(observable -> {
            if (time.isPressed()) {
                player.seek(player.getMedia().getDuration().multiply(time.getValue() / VALUE_SLIDER));
            }
        });

        vol.valueProperty().addListener(observable -> {
            if (vol.isPressed()) {
                player.setVolume(vol.getValue() / VALUE_SLIDER);
            }
        });
    }

    private void updateValues() {
        Platform.runLater(() -> time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * VALUE_SLIDER));
    }
}
