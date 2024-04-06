package com.promeme;

import com.promeme.model.EditableImage;
import com.promeme.model.EditableText;
import com.promeme.view.EditableImageView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(EditableImageView.class.getResource("library-view.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinHeight(550);
        stage.setMinWidth(835);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
