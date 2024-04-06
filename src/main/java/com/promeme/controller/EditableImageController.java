package com.promeme.controller;

import com.promeme.model.EditableImage;
import com.promeme.model.EditableText;
import com.promeme.model.EditableTextList;
import com.promeme.view.EditableImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.*;

public class EditableImageController {

    private EditableImage editableImage;
    private EditableImageView editableImageView;
    public EditableImageController(EditableImageView editableImageView){
        this.editableImageView = editableImageView;
        editableImage = new EditableImage();
    }

    public EditableImageView getEditableImageView() {
        return editableImageView;
    }

    public void setEditableImageView(EditableImageView editableImageView) {
        this.editableImageView = editableImageView;
    }

    public EditableImageController(EditableImage editableImage){
        this.editableImage = editableImage;
    }
    public void loadEditableImage(){
        double scale = editableImageView.getScale();
        editableImage.setTexts(new EditableTextList());
        editableImage.getTexts().setProperty(editableImageView.getTexts());
        editableImage.getTexts().transformSizeByScale(1 / scale);
    }
    public void export(File file) throws IOException {
        loadEditableImage();

        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(new ImageView(new Image(new FileInputStream(new File(editableImage.getImagePath())))));
        for(EditableText text : editableImage.getTexts()){
            pane.getChildren().add(text);
        }
        // các label phải được load vào scene thì mới hiển thị được.
        new Scene(pane);
        WritableImage imageToExport = pane.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(imageToExport, null), "png", file);
    }
    public void openFromImage(File file) throws FileNotFoundException {
        editableImage.setImagePath(file.getPath());
    }
    public void openFromProjectFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        editableImage = (EditableImage) ois.readObject();
        ois.close();
    }
    public void saveToProjectFile(File file) throws IOException {
        loadEditableImage();
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file));
        ous.writeObject(editableImage);
        ous.close();
    }

    public EditableImage getEditableImage() {
        return editableImage;
    }
}
