package com.promeme.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public class ItemView {
    private String imagePath;
    @FXML
    VBox imageHolder;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @FXML
    ImageView imageView;
    public void setData(File file) throws FileNotFoundException, MalformedURLException {

        imagePath = new String(file.getPath());
        imageView.setImage(new Image(Path.of(imagePath).toUri().toURL().toExternalForm(), 200, 0, true, true, true));
    }
    @FXML
    public void setOnClicked() throws IOException {
        FXMLLoader editableImageloader = new FXMLLoader(EditableImageView.class.getResource("editable-image-view.fxml"));

        AnchorPane pane = editableImageloader.load();

        EditableImageView editableImageView = editableImageloader.getController();


        Scene scene = new Scene(pane);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        editableImageView.loadImage(new File(imagePath));
    }
}
