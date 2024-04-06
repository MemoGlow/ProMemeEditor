package com.promeme.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;

public class EditableImage implements Serializable {
    private String imagePath;
    private EditableTextList texts;

    public EditableImage(){
        texts = new EditableTextList();
    }
    public String getImagePath() throws FileNotFoundException {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public EditableTextList getTexts() {
        return texts;
    }

    public void setTexts(EditableTextList texts) {
        this.texts = texts;
    }
    public void writeObject(ObjectOutputStream ous) throws IOException {
        ous.writeObject(imagePath);
        ous.writeObject(texts);
    }
}
