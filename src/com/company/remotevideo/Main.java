package com.company.remotevideo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by Andrei.Tihomirov on 18.10.2017.
 */
public class Main extends Application {

    @Override public void start(Stage stage) throws Exception {
        WebView webview = new WebView();
        webview.getEngine().load("https://www.youtube.com/embed/D6Ac5JpCHmI?&autoplay=1");
        webview.setPrefSize(640, 390);

        stage.setScene(new Scene(webview));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
